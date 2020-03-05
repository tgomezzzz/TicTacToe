import java.awt.geom.Line2D;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;

public class X extends Space {

    private Line2D.Double x1, x2;

    public X(int x, int y){
        super(x, y);
        int rad = Math.sqrt(WIDTH / 2);
        x1 = new Line2D.Double(x - rad, y - rad, x + rad, y + rad);
        x2 = new Line2D.Double(x - rad, y + rad, x + rad, y - rad);
    }

    @Override
    public void draw(Graphics2D g){
        g.setColor(Color.BLUE);
        g.draw(x1);
        g.draw(x2);
    }
}