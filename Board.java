import java.awt.*;
//import java.awt.Graphics2D;
import javax.swing.Icon;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Line2D;

//import java.awt.color.*;

public class Board implements Drawable{

    private static int BOARD_SIZE = 800;

    private int[][] b;
    private int edgeSize;
    private Bundle tiles;
    private Line2D.Double[] boardLines;
    private final String[] PAWNS = {" ", "x", "o"};

    public Board(){
        this(3);
    }

    public Board(int n){
        this.b = new int[n][n];
        this.edgeSize = n;
        this.tiles = new Bundle(n);
        this.boardLines = new Line2D.Double[(n*2)-2];
        int margin = 50;
        int lineLength = BOARD_SIZE - (margin*2);
        int distBetweenLines = lineLength/n;
        int boardLinePos = 0;
        for (int i = 1; i < n; i++){
            boardLines[boardLinePos++] = new Line2D.Double(margin + (i*distBetweenLines), margin, margin + (i*distBetweenLines), margin + lineLength);
            boardLines[boardLinePos++] = new Line2D.Double(margin, margin + (i*distBetweenLines), margin + lineLength, margin + (i*distBetweenLines));
        }
    }

    @Override
    public void draw(Graphics2D g){
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(5));
        for (Line2D.Double l : boardLines){
            g.draw(l);
        }
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