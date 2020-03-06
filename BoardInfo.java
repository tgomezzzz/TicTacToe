public class BoardInfo{

    public static final int BOARD_SIZE = 600;
    public static final int MARGIN = BOARD_SIZE / 10;
    public static int n;
    public static int entryWidth;


    public static void setN(int x){
        n = x;
        entryWidth = (BoardInfo.BOARD_SIZE - 2 * MARGIN) / BoardInfo.n;
    }

}