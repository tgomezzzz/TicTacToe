public class Board {

    private int[][] b;
    private int edgeSize;
    private final String[] PAWNS = {" ", "x", "o"};

    public Board(){
        this(3);
    }

    public Board(int n){
        this.b = new int[n][n];
        this.edgeSize = n;
    }

    public void drawBoard(){
        for (int i = 0; i < edgeSize; i++){
            for (int j = 0; j < edgeSize; j++){
                System.out.print(PAWNS[b[i][j]] + " | ");
            }
            System.out.println();
        }
    }

    public boolean setTile(int r, int c, int pawn){
        if (r > edgeSize - 1 || r < 0 || c > edgeSize - 1 || c < 0){
            System.out.println("ERROR: tile is out of bounds");
            return false;
        }
        if (b[r][c] != 0){
            System.out.println("Oops! Tile (" + r + ", " + c + ") is aready occupied!");
            return false;
        }
        b[r][c] = pawn;
        return true;
    }

}