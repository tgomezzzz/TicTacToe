import java.awt.geom.Ellipse2D;
import java.awt.*;

public class O implements Drawable {

    private Ellipse2D.Double e;

    public O(int x, int y){
        e = new Ellipse2D.Double(x, y, BoardInfo.entryWidth / 1.5, BoardInfo.entryWidth / 1.5);
    }

    @Override
    public void paintComponent(Graphics gIn){
        Graphics2D g = (Graphics2D) gIn;
        g.setColor(Color.RED);
        g.draw(e);

    }

}