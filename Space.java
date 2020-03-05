import javafx.geometry.Rectangle2D;

import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;

public class Space implements Drawable {

    protected final int WIDTH = BoardInfo.BOARD_SIZE / BoardInfo.n;
    protected Rectangle2D.Double mouseSpace;
    private int x, y;

    public Space(int a, int b){
        this.x = a;
        this.y = b;
        this.mouseSpace = new Rectangle2D.Double(x - WIDTH / 2, y - width / 2, WIDTH, WIDTH);
    }

    @Override
    public void draw(Graphics2D g){
        //no icon for a space
    }

    @Override
    public void mouseOver(Graphics2D g){
        g.setColor(Color.LIGHT_GRAY);
        g.draw(mouseSpace);
    }
}