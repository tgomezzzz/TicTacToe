import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;

public class Board extends JComponent implements Drawable {

    private static final long serialVersionUID = 1L;
    private int[][] b;
    private int edgeSize;
    private Bundle tiles;
    private Line2D.Double[] boardLines;

    public Board(){
        this(3);
    }

    public Board(int n){
        BoardInfo.setN(n);
        this.b = new int[n][n];
        this.edgeSize = n;
        this.tiles = new Bundle(n);
        this.boardLines = new Line2D.Double[(n * 2) - 2];
        generateBoard(n);
    }

    public void generateBoard(int n){
        int margin = BoardInfo.BOARD_SIZE / 10;
        int lineLength = BoardInfo.BOARD_SIZE - (margin * 2);
        int distBetweenLines = lineLength / n;
        int boardLinePos = 0;
        for (int i = 1; i < n; i++){
            int marginOffset = margin + (i * distBetweenLines);
            this.boardLines[boardLinePos++] = new Line2D.Double(marginOffset, margin, marginOffset, margin + lineLength);
            this.boardLines[boardLinePos++] = new Line2D.Double(margin, marginOffset, margin + lineLength, marginOffset);
        }
    }

    @Override 
    public void paintComponent(Graphics gIn){
        Graphics2D g = (Graphics2D) gIn;
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(5));
        tiles.addIcon(1, new X(100, 100));
        for (Line2D.Double l : boardLines){
            g.draw(l);
        }
        tiles.paintComponent(gIn);
    }

    @Override
    public void mouseOver(Graphics2D g){

    }

    public void drawBoard(){
        for (int i = 0; i < edgeSize; i++){
            for (int j = 0; j < edgeSize; j++){
                //update later
            }
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