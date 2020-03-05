import java.awt.geom.Ellipse2D;
import java.awt.*;

public class O extends Space {

    private Ellipse2D.Double e;

    public O(int x, int y){
        super(x, y);
        e = new Ellipse2D.Double(x, y, RAD, RAD);
    }

    @Override
    public void paintComponent(Graphics gIn){
        Graphics2D g = (Graphics2D) gIn;
        g.setColor(Color.RED);
        g.draw(e);
    }

}