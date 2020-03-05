import java.awt.geom.Line2D;

import javafx.scene.shape.Line;

import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;

public class X implements Drawable {

    private Line2D.Double x1, x2;
    private final int RAD = 22;

    public X(int x, int y){
        x1 = new Line2D.Double(x-RAD, y-RAD, x+RAD, y+RAD);
        x2 = new Line2D.Double(x-RAD, y+RAD, x+RAD, y-RAD);
    }

    @Override
    public void draw(Graphics2D g){
        g.setColor(Color.BLUE);
        g.draw(x1);
        g.draw(x2);
    }
}