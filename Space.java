import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Space implements Drawable {

    protected final int WIDTH = BoardInfo.BOARD_SIZE / BoardInfo.n;
    protected Rectangle2D.Double mouseSpace;
    private int x, y;

    public Space(int a, int b){
        this.x = a;
        this.y = b;
        this.mouseSpace = new Rectangle2D.Double(x - WIDTH / 2, y - WIDTH / 2, WIDTH, WIDTH);
    }

    @Override
    public void paintComponent(Graphics g){
        //no icon for a space
    }

    @Override
    public void mouseOver(Graphics2D g){
        g.setColor(Color.LIGHT_GRAY);
        g.draw(mouseSpace);
    }
}