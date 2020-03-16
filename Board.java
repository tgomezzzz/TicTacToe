import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

public class Board extends JComponent implements Drawable, MouseListener, MouseMotionListener {

    public static final int MARGIN = 50;

    private static final String[] TITLES = {" Tic", " Tac", " Toe"};
    private static final long serialVersionUID = 1L;

    private int[][] b;
    private int gridSize, boardPixelSize, iconPixelSize;
    private int pawn = 1;
    private Grid tiles;
    private Line2D.Double[] boardLines;
    private MouseOver mouseOver;
    private TileSet winningTiles;
    private Computer cpu;
    private TextBox t;
    private int moves = 0;
    private boolean gameOver;
    private int[] lastMove = new int[2];

    public Board(){
        this(3, 600, new TwoPlayer());
    }

    public Board(int n, int pixels, Thinkable t){
        this.b = new int[n][n];
        this.gridSize = n;
        this.boardPixelSize = pixels;
        this.iconPixelSize = (boardPixelSize - (2 * MARGIN)) / gridSize;
        this.tiles = new Grid(gridSize);
        this.boardLines = new Line2D.Double[(gridSize * 2) - 2];
        this.t = new TextBox(boardPixelSize / 2, boardPixelSize, generateTitle());
        this.cpu = new Computer(this, t);
        this.gameOver = false;
        generateBoard();
    }

    private String generateTitle() {
        StringBuilder s = new StringBuilder("Welcome to");
        for (int i = 0; i < gridSize; i++){
            s.append(TITLES[i % TITLES.length]);
        }
        s.append("!");
        return s.toString();
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
        if (mouseOver != null) {
            mouseOver.paintComponent(gIn);
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
            setText("Oops! That tile is aready occupied.");
            return false;
        }

        if (gameOver){
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
        //t.setText("It's your move!");
        lastMove[0] = r;
        lastMove[1] = c;
        moves++;

        if (moves > (gridSize * gridSize) - 1) {
            gameOver = true;
            if (!checkForWinner()){
                setText("It's a tie!");
            }
        }

        if (moves > (2 * gridSize - 2) && !gameOver){
            gameOver = checkForWinner();
        }
        return true;
    }

    public int getTile(int r, int c) {
        if (r < 0 || r > gridSize - 1 || c < 0 || c > gridSize - 1) {
            return 0;
        }
        return b[r][c];
    }

    public boolean checkForWinner(){
        boolean winnerFound = isWinningTile(0, 0, 1, 1, b[0][0])
                           || isWinningTile(0, gridSize - 1, 1, -1, b[0][gridSize - 1]);
        if (winnerFound){
            return true;
        }
        for (int i = 0; i < gridSize; i++){
            winnerFound = isWinningTile(i, 0, 0, 1, b[i][0]) || isWinningTile(0, i, 1, 0, b[0][i]);
            if (winnerFound){
                return true;
            }
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
                setText("The computer won. Good game!");
            } else {
                setText("You won, congratulations!");
            }
            winningTiles = new TileSet(r - xDir, c - yDir, xDir, yDir, this);
            return true;
        }
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
            return false;
        }
        if (b[r][c] != 0){
            return false;
        }
        return true;
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

    public int[] getLastMove() {
        return lastMove;
    }

    public int getPawn() {
        return pawn;
    }

    public void setPawn(int newPawn){
        if (newPawn < -1 || newPawn > 1) {
            return;
        }
        pawn = newPawn;
    }

    public void setMouseOver(int r, int c){
        if (r > -1 && r < gridSize && c > -1 && c < gridSize && isFreeTile(r, c)){
            mouseOver = new MouseOver(r, c, this);
        } else {
            mouseOver = null;
        }
    }

    public void setText(String s) {
        t.setText(s);
    }

    @Override
    public void mouseReleased(MouseEvent e) { 
        if (gameOver) {
            return;
        }
        int gridX = mouseToGridPos(e.getY());
        int gridY = mouseToGridPos(e.getX());

        if (setTile(gridX, gridY, pawn)) { 
            if (!gameOver) {
                cpu.move();
            }
        }
        this.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e){
        int gridX = mouseToGridPos(e.getY());
        int gridY = mouseToGridPos(e.getX());
        setMouseOver(gridX, gridY);
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