import java.awt.geom.Ellipse2D;
import java.awt.*;

public class O implements Drawable {

    private Ellipse2D.Double e;

    public O(int r, int c, Board b){
        int width = b.getIconPixelSize();
        int x = b.gridToMousePos(r) + width / 4;
        int y = b.gridToMousePos(c) + width / 4;
        e = new Ellipse2D.Double(x, y, width / 2, width / 2);
    }

    @Override
    public void paintComponent(Graphics gIn){
        Graphics2D g = (Graphics2D) gIn;
        g.setColor(Color.RED);
        g.draw(e);

    }

}