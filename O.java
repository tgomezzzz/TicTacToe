import java.awt.geom.Ellipse2D;
import java.awt.*;

public class O implements Drawable {

    private Ellipse2D.Double e;

    public O(int r, int c, int width, Board b){
        int x = b.gridToMousePos(r) + width / 4;
        int y = b.gridToMousePos(c) + width / 4;
        e = new Ellipse2D.Double(x, y, width, width);
    }

    @Override
    public void paintComponent(Graphics gIn){
        Graphics2D g = (Graphics2D) gIn;
        g.setColor(Color.RED);
        g.draw(e);

    }

}