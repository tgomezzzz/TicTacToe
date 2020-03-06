import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

public class Board extends JComponent implements Drawable, MouseListener, MouseMotionListener {

    private static final long serialVersionUID = 1L;
    private int[][] b;
    private int edgeSize;
    private Bundle tiles;
    private Line2D.Double[] boardLines;
    private MouseOver mouseEntry;
    private boolean[] freeIndeces;

    public Board(){
        this(3);
    }

    public Board(int n){
        BoardInfo.setN(n);
        this.b = new int[n][n];
        this.edgeSize = n;
        this.tiles = new Bundle(n);
        this.freeIndeces = new boolean[n * n];
        this.boardLines = new Line2D.Double[(n * 2) - 2];
        generateBoard(n);
    }

    public void generateBoard(int n){
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        
        for (int i = 0; i < freeIndeces.length; i++){
            freeIndeces[i] = true;
        }

        int lineLength = BoardInfo.BOARD_SIZE - (BoardInfo.MARGIN * 2);
        int distBetweenLines = lineLength / n;
        int boardLinePos = 0;
        for (int i = 1; i < n; i++){
            int marginOffset = BoardInfo.MARGIN + (i * distBetweenLines);
            this.boardLines[boardLinePos++] = new Line2D.Double(marginOffset, BoardInfo.MARGIN, marginOffset, BoardInfo.MARGIN + lineLength);
            this.boardLines[boardLinePos++] = new Line2D.Double(BoardInfo.MARGIN, marginOffset, BoardInfo.MARGIN + lineLength, marginOffset);
        }
    }

    @Override 
    public void paintComponent(Graphics gIn){
        Graphics2D g = (Graphics2D) gIn;

        if (mouseEntry != null){
            mouseEntry.paintComponent(gIn);
        }

        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(5));
        for (Line2D.Double l : boardLines){
            g.draw(l);
        }

        tiles.paintComponent(gIn);
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

    public int mouseToGridPos(int mousePos){
        if (mousePos < BoardInfo.MARGIN || mousePos > BoardInfo.MARGIN + BoardInfo.BOARD_SIZE){
            return -1;
        }
        return (mousePos - 60) / BoardInfo.entryWidth;
    }

    public int gridToMousePos(int gridPos){
        if (gridPos < 0 || gridPos > this.edgeSize - 1){
            return -1;
        }
        return (gridPos * BoardInfo.entryWidth) + BoardInfo.MARGIN;
    }

    public int gridToArrayPos(int gridX, int gridY){
        if (gridX == -1 || gridY == -1){
            return -1;
        } 
        return (gridY * (edgeSize)) + gridX;
    }

    public int arrayToGridX(int arrPos){
        if (arrPos < 0 || arrPos > edgeSize - 1){
            return -1;
        }
        return arrPos / edgeSize;
    }

    public int arrayToGridY(int arrPos){
        if (arrPos < 0 || arrPos > edgeSize - 1){
            return -1;
        }
        return arrPos % edgeSize;
    }

    @Override
    public void mouseClicked(MouseEvent e) { }
   
    @Override
    public void mouseEntered(MouseEvent e) { }
   
    @Override
    public void mouseExited(MouseEvent e) { }
   
    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { 
        int gridX = mouseToGridPos(e.getX());
        int gridY = mouseToGridPos(e.getY());

        if (setTile(gridX, gridY, 2)){
            int index = gridToArrayPos(gridX, gridY);
            if (freeIndeces[index]){
                tiles.addIcon(index, new X(gridToMousePos(gridX) + BoardInfo.entryWidth / 2, gridToMousePos(gridY) + BoardInfo.entryWidth / 2));
                freeIndeces[index] = false;
                // while (freeIndeces[index] == false) {
                //     index = (int) (Math.random()*(freeIndeces.length+1));
                // }
                // System.out.println(index);
                // gridX = arrayToGridX(index);
                // gridY = arrayToGridY(index);
                // tiles.addIcon(index, new O(gridToMousePos(gridX) + BoardInfo.entryWidth / 2, gridToMousePos(gridY) + BoardInfo.entryWidth / 2));
                // freeIndeces[index] = false;
            }
            
        }
        this.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e){
        int gridX = mouseToGridPos(e.getX());
        int gridY = mouseToGridPos(e.getY());
        
        if (gridX > -1 && gridX < edgeSize && gridY > -1 && gridY < edgeSize){
            mouseEntry = new MouseOver(gridToMousePos(gridX), gridToMousePos(gridY));
        } else {
            mouseEntry = null;
        }
        this.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e){ }

}