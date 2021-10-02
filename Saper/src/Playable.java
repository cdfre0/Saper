import java.util.Scanner;

public class Playable {

    int x;
    int y;
    int bombs;
    int bombCount;
    int flagLimit;
    Board board;
    Scanner in = new Scanner(System.in);
    public Playable(){
        this.board = createGame();
        board.showTable();
        chooseMove();
    }

    /**
     * Method creates board of given values
     * @return Board made from values
     */
    private Board createGame(){
        System.out.println("What dimensions do you want ?");
        System.out.print("X : ");
        x = in.nextInt();
        System.out.print("Y : ");
        y = in.nextInt();
        System.out.print("Bombs : ");
        bombs = in.nextInt();
        bombCount = bombs;
        flagLimit = bombs;
        return new Board(x,y,bombs);
    }
    private void chooseMove(){
        System.out.println("You have " + flagLimit +" flags more.");
        System.out.println("To shoot, press 1.");
        System.out.println("To flag bomb, press 2.");
        System.out.println("To undo flag, press 3.");
        int input = in.nextInt();
        switch(input){
            case 1:
                shoot();
                break;
            case 2:
                flag();
                break;
            case 3:
                undoFlag();
                break;
            default:
                System.out.println("Wrong Number!!!");
                chooseMove();
        }

    }


    /**
     * Method let you choose coords until You Hit a bomb
     */
    private void shoot(){
        int[] coords = chooseCoords();
        if(board.getSquare(coords[0],coords[1]).isBomb()) {
            board.showTable();
            System.out.println("You hit bomb, Game over");
            System.exit(0);
        }else if(board.getSquare(coords[0],coords[1]).getValue() == 0){
            letsMakeRecursion(coords[0],coords[1]);

        }else{
            showMonitoredSquare(coords[0],coords[1]);
        }
        System.out.println(board);
        chooseMove();
    }

    private void letsMakeRecursion(int x, int y){
        System.out.println();
        System.out.println("Starting position " + x + " " + y);
        for (int row = y-1; row <= y+1; row++) {
            for (int col = x - 1; col <= x + 1; col++) {
                if (col >= 0 && row >= 0 && row < this.y && col < this.x && board.getSquare(col,row).getValue() == 0) {
                    System.out.println("Walk throught " + col + " " + row + ". Value " + board.getSquare(col,row).getValue());
                    board.getSquare(col,row).setValue(9);
                    showMonitoredSquare(col,row);
                    letsMakeRecursion(col, row);

                }
            }
        }
    }
    private void flag() {
        if(flagLimit > 0) {
            int[] coords = chooseCoords();
            board.setProbablyBomb(coords[0], coords[1]);
            flagLimit--;
            if (board.getSquare(coords[0], coords[1]).isBomb()) {
                bombCount--;
                winCondition();

            }
        }else{
            System.out.println("You have run out of flags!");
        }
        System.out.println(board);

        chooseMove();
    }
    private void undoFlag(){
        int coords[] = chooseCoords();
        if(board.getVisualSquare(coords[0],coords[1]).equals("F")){
            board.returnVisualSquare(coords[0],coords[1]);
            flagLimit++;
        }else{
            System.out.println("You Have chosen wrong Square.");
        }
        System.out.println(board);
        chooseMove();
    }
    private void winCondition(){
        if(bombCount <= 0){
            board.showTable();
            System.out.println("CONGRATULATIONS, YOU WON");
            System.exit(0);
        }
    }
    /**
     * Method takes from keyboard 2 values of chosen coordinates
     * @return int[] with given values
     */
    private int[] chooseCoords(){
        int x;
        int y;
        System.out.println("What Coords you choose");
        System.out.print("X : ");
        x = in.nextInt() - 1;
        System.out.print("Y : ");
        y = in.nextInt() - 1;
        if(x >= this.x && y >= this.y){
            chooseCoords();
        }
        return new int[]{x,y};
    }
    /**
     * Method takes value from table and put it onto empty board
     * @param x length coordinates
     * @param y height coordinates
     */
    private void showMonitoredSquare(int x, int y){
        board.setSquare(x,y);
    }
    //When clicked 0, show the nearest squares. 8 options.
}
