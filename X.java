import java.awt.geom.Line2D;
import java.awt.*;

public class X implements Drawable {

    private Line2D.Double x1, x2;

    public X(int r, int c, int width, Board b){
        int x = b.gridToMousePos(r) + b.iconPixelSize / 2;
        int y = b.gridToMousePos(c) + b.iconPixelSize / 2;
        x1 = new Line2D.Double(x - width, y - width, x + width, y + width);
        x2 = new Line2D.Double(x - width, y + width, x + width, y - width);
    }

    @Override
    public void paintComponent(Graphics gIn){
        Graphics2D g = (Graphics2D) gIn;
        g.setColor(Color.BLUE);
        g.draw(x1);
        g.draw(x2);

    }
}