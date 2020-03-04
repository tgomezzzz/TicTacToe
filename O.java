import java.awt.geom.Ellipse2D;

import java.awt.Graphics2D;
public class O implements Drawable {

    private Ellipse2D.Double e;
    private final int RAD = 50;

    public O(int x, int y){
        e = new Ellipse2D.Double(x, y, RAD, RAD);
    }

    @Override
    public void draw(Graphics2D g){
        g.draw(e);
    }
}