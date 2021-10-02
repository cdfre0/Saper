import java.util.ArrayList;
import java.util.Random;


public class Board {

    //Length of board
    private final int x;
    //Height of board
    private final int y;
    //Number of bombs
    private final int numOfBombs;
    //Coordinates of each bomb
    private final ArrayList<int[]> takenPlaces = new ArrayList<>();
    //board with values
    private Square[][] fullTable;
    //board which will be shown to player
    private String[][] visualTable;
    /**
     * Constructor makes board with given parameters
     * @param x - width of board
     * @param y - length of board
     * @param bombs - number of bombs
     */
    public Board(int x, int y, int bombs){
        this.x = x;
        this.y = y;
        numOfBombs = bombs;
        createEmptyBoard();
        createEmptyStringList();
        implementBombs();
    }

    /**
     * Method create empty board with 0
     */
    private void createEmptyBoard(){
        fullTable = new Square[x][y];
        for (int row = 0; row < y; row++) {
            for (int col = 0; col < x; col++) {
                fullTable[col][row] = new Square();
            }
        }
    }

    /**
     * Method creates hidden board
     */
    private void createEmptyStringList(){
        visualTable = new String[x][y];
        for (int row = 0; row < y; row++) {
            for (int col = 0; col < x; col++) {
                visualTable[col][row] = "@";
            }
        }
    }

    /**
     * Method place bombs x times
     */
    private void implementBombs(){
        for (int i = 0; i < numOfBombs; i++) {
            putBomb();
        }
        bombAroundCheck();
    }

    /**
     * Method put bomb on board
     */
    private void putBomb() {
        int[] randomNumbers = randomizer();
        Square square = fullTable[randomNumbers[0]][randomNumbers[1]];
        if (square.isBomb()) {
            putBomb();
        } else {
            takenPlaces.add(randomNumbers);
            square.makeBomb();
        }
    }
    /**
     * Method generates 2 random numbers not bigger than x and y
     * @return List with 2 numbers
     */
    private int[] randomizer() {
        Random randomMaker = new Random();
        int xRandom = randomMaker.nextInt(x);
        int yRandom = randomMaker.nextInt(y);
        return new int[]{xRandom, yRandom};
    }

    /**
     * Method adds value to empty squares around bomb
     */
    private void bombAroundCheck(){
        for(int[] elem : takenPlaces){
            int xBomb = elem[0];
            int yBomb = elem[1];
            //Loops adds 1 to squares around each bomb in List
            for (int row = yBomb-1; row <= yBomb+1; row++) {
                for (int col = xBomb - 1; col <= xBomb + 1; col++) {
                    if (col >= 0 && row >= 0 && row < y && col < x) {
                        if(!fullTable[col][row].isBomb()) {
                            fullTable[col][row].addValue();
                        }
                    }
                }
            }
        }
    }
    /**
     * Method shows value of given square on visualized table
     * @param x length
     * @param y height
     */
    public void setSquare(int x, int y){
        visualTable[x][y] = String.valueOf(fullTable[x][y].getValue());
    }
    public void setProbablyBomb(int x, int y){
        visualTable[x][y] = "F";
    }

    public Square getSquare(int x, int y){
        return fullTable[x][y];
    }
    public String getVisualSquare(int x, int y){
        return visualTable[x][y];
    }
    public void returnVisualSquare(int x, int y){
        visualTable[x][y] = "@";
    }
    /**
     * Method shows all board
     */
    public void showTable(){
        String end = "";
        for (int row =0; row < y; row ++){
            for (int col = 0; col < x; col++) {
                if(fullTable[col][row].isBomb()){
                    System.out.print("x ");
                }else{
                    System.out.print(fullTable[col][row] + " ");
                }
            }
            System.out.println();
        }
    }
    /**
     * Method returns Saper board
     * @return String with progress on board
     */
    public String toString() {
        String end = "";
        for (int row = 0; row < y; row++) {
            for (int col = 0; col < x; col++) {
                end += visualTable[col][row] + " ";
            }
            end += "\n";
        }
        return end;
    }
}