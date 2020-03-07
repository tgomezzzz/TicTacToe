import java.awt.*;
import java.awt.geom.Rectangle2D;

public class MouseOver implements Drawable {

    private Rectangle2D.Double mouseSpace;

    public MouseOver(int x, int y, Board b){
        int width = b.getIconPixelSize();
        mouseSpace = new Rectangle2D.Double(b.gridToMousePos(x), b.gridToMousePos(y), width, width);
    }

    @Override
    public void paintComponent(Graphics gIn){
        Graphics2D g = (Graphics2D) gIn;
        g.setColor(Color.LIGHT_GRAY);
        g.fill(mouseSpace);
        g.draw(mouseSpace);
    }

}