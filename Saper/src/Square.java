public class Square {

    //Value of square
    int value;
    public Square(){
        value = 0;
    }

    /**
     * Method adds value to value
     */
    public void addValue(){
        value++;
    }

    /**
     * Method to make squareClass Bomb
     */
    public void setValue(int value){
        this.value = value;
    }
    public void makeBomb(){
        value = -1;
    }
    /**
     * Method returns value of Square
     * @return int value
     */
    public int getValue(){
        return value;
    }

    /**
     * Method checks if square is a bomb
     * @return true if bomb
     */
    public boolean isBomb() {
        return value == -1;
    }
    /**
     * Method shows value of Square as String
     * @return String value
     */
    public String toString(){
        return String.valueOf(value);
    }


}
