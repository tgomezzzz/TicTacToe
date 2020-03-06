import java.awt.geom.Line2D;
import java.awt.*;

public class X implements Drawable {

    private Line2D.Double x1, x2;

    public X(int x, int y){
        double rad = Math.sqrt((2 * BoardInfo.entryWidth * BoardInfo.entryWidth)) / 6;
        x1 = new Line2D.Double(x - rad, y - rad, x + rad, y + rad);
        x2 = new Line2D.Double(x - rad, y + rad, x + rad, y - rad);
    }

    @Override
    public void paintComponent(Graphics gIn){
        Graphics2D g = (Graphics2D) gIn;
        g.setColor(Color.BLUE);
        g.draw(x1);
        g.draw(x2);

    }
}