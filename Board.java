import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.concurrent.TimeUnit;

public class Board extends JComponent implements Drawable, MouseListener, MouseMotionListener {

    public static final int MARGIN = 50;

    private static final long serialVersionUID = 1L;

    private int[][] b;
    private int gridSize, boardPixelSize, iconPixelSize;
    private Grid tiles;
    private Line2D.Double[] boardLines;
    private MouseOver mouseEntry;
    private TileSet winningTiles;
    private Computer cpu;
    private TextBox t;
    private int moves = 0;
    private boolean gameOver = false;

    public Board(){
        this(3, 600);
    }

    public Board(int n, int pixels){
        this.b = new int[n][n];
        this.gridSize = n;
        this.boardPixelSize = pixels;
        this.iconPixelSize = (boardPixelSize - (2 * MARGIN)) / gridSize;
        this.tiles = new Grid(gridSize);
        this.boardLines = new Line2D.Double[(gridSize * 2) - 2];
        this.t = new TextBox(boardPixelSize/2, boardPixelSize, "Welcome to Tic Tac Toe!");
        this.cpu = new Computer(this);
        generateBoard();
    }

    public void generateBoard(){
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        int lineLength = boardPixelSize - (MARGIN * 2);
        int distBetweenLines = lineLength / gridSize;
        int boardLinePos = 0;
        for (int i = 1; i < gridSize; i++){
            int marginOffset = MARGIN + (i * distBetweenLines);
            this.boardLines[boardLinePos++] = new Line2D.Double(marginOffset, MARGIN, marginOffset, MARGIN + lineLength);
            this.boardLines[boardLinePos++] = new Line2D.Double(MARGIN, marginOffset, MARGIN + lineLength, marginOffset);
        }
    }

    @Override 
    public void paintComponent(Graphics gIn){
        if (mouseEntry != null){
            mouseEntry.paintComponent(gIn);
        }

        Graphics2D g = (Graphics2D) gIn;
        int strokeSize = -(gridSize / 5) + 5;
        g.setStroke(new BasicStroke(Math.max(strokeSize, 1)));

        if (winningTiles != null) {
            winningTiles.paintComponent(gIn);
        }

        g.setColor(Color.BLACK);
        for (Line2D.Double l : boardLines){
            g.draw(l);
        }

        tiles.paintComponent(gIn);
        t.paintComponent(gIn);
    }

    public boolean setTile(int r, int c, int pawn){
        if (!isFreeTile(r, c)){
            System.out.println("Oops! Tile (" + r + ", " + c + ") is aready occupied!");
            return false;
        }

        b[r][c] = pawn;
        Drawable d;
        if (pawn == 0) {
            tiles.removeIcon(r, c);
            return true;
        } else if (pawn < 0){
            d = new O(r, c, this); 
        } else {
            d = new X(r, c, this);
        }
        tiles.addIcon(r, c, d);
        moves++;

        if (moves > (2 * gridSize - 2) && !gameOver){
            gameOver = checkForWinner();
        }
        if (moves > (gridSize * gridSize) - 1) {
            gameOver = true;
            t.setText("It's a tie!");
        }
        return true;
    }

    public boolean checkForWinner(){
        boolean winnerFound = isWinningTile(0, 0, 1, 1, b[0][0])
                           || isWinningTile(0, gridSize - 1, 1, -1, b[0][gridSize - 1]);
        for (int i = 0; i < gridSize; i++){
            if (winnerFound){
                return true;
            }
            winnerFound = isWinningTile(i, 0, 0, 1, b[i][0]) || isWinningTile(0, i, 1, 0, b[0][i]);
        }
        return false;
    }

    public boolean isWinningTile(int r, int c, int xDir, int yDir, int player){
        if (player == 0){
            return false;
        }
        if (r > -1 && c > -1 && r < gridSize && c < gridSize){
            if (b[r][c] != player){
                return false;
            }
            return isWinningTile(r + xDir, c + yDir, xDir, yDir, player);
        } else {
            if (player == -1){
                t.setText("The computer won!");
            } else {
                t.setText("You won!");
            }
            winningTiles = new TileSet(r - xDir, c - yDir, xDir, yDir, this);
            return true;
        }
    }

    public boolean gameIsOver(){
        return gameOver;
    }

    public int getGridSize(){
        return gridSize;
    }

    public int getIconPixelSize(){
        return iconPixelSize;
    }

    public int mouseToGridPos(int mousePos){
        if (mousePos < MARGIN || mousePos > boardPixelSize - MARGIN){
            return -1;
        }
        return (mousePos - MARGIN) / iconPixelSize;
    }

    public int gridToMousePos(int gridPos){
        if (gridPos < 0 || gridPos > gridSize - 1){
            return -1;
        }
        return (gridPos * iconPixelSize) + MARGIN;
    }

    public boolean isFreeTile(int r, int c){
        if (r > gridSize - 1 || r < 0 || c > gridSize - 1 || c < 0){
            System.out.println("ERROR: tile is out of bounds");
            return false;
        }
        if (b[r][c] != 0){
            return false;
        }
        return true;
    }

    @Override
    public void mouseReleased(MouseEvent e) { 
        if (gameOver) {
            return;
        }
        int gridX = mouseToGridPos(e.getY());
        int gridY = mouseToGridPos(e.getX());
        if (setTile(gridX, gridY, 1)) { 
            cpu.move();
        }
        this.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e){
        int gridX = mouseToGridPos(e.getY());
        int gridY = mouseToGridPos(e.getX());
        if (gridX > -1 && gridX < gridSize && gridY > -1 && gridY < gridSize && isFreeTile(gridX, gridY)){
            mouseEntry = new MouseOver(gridX, gridY, this);
        } else {
            mouseEntry = null;
        }
        this.repaint();
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
    public void mouseDragged(MouseEvent e){ }

}