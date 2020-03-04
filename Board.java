import java.awt.Graphics2D;
import javax.swing.Icon;
import java.awt.geom.Rectangle2D;

public class Board implements Drawable{

    private int[][] b;
    private int edgeSize;
    private Bundle tiles;
    private final String[] PAWNS = {" ", "x", "o"};

    public Board(){
        this(3);
    }

    public Board(int n){
        this.b = new int[n][n];
        this.tiles = new Bundle(n);
        this.edgeSize = n;
    }

    @Override
    public void draw(Graphics2D g){
        Rectangle2D.Double rect = new Rectangle2D.Double(100, 100, 100, 100);
        tiles.addIcon(2, new O(300, 300));
        tiles.addIcon(4, new O(500, 100));
        g.draw(rect);
        tiles.draw(g);
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