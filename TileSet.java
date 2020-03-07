import java.awt.*;
import java.awt.geom.Rectangle2D;

public class TileSet implements Drawable {

    private Rectangle2D.Double[] tiles;
    public TileSet(int r, int c, int xDir, int yDir, Board b){
        this.tiles = new Rectangle2D.Double[b.getGridSize()];
        int width = b.getIconPixelSize();
        for (int i = 0; i < tiles.length; i++){
            int x = b.gridToMousePos(c + (i * -yDir));
            int y = b.gridToMousePos(r + (i * -xDir));
            tiles[i] = new Rectangle2D.Double(x, y, width, width);
        }
    }

    @Override
    public void paintComponent(Graphics gIn){
        Graphics2D g = (Graphics2D) gIn;
        g.setColor(Color.GREEN);
        for (Rectangle2D.Double r : tiles){
            g.fill(r);
            g.draw(r);
        }
    }

}