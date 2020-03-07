import java.awt.*;
import javax.swing.*;

public class TextBox implements Drawable {

    private int x, y;
    private String text; 

    public TextBox(int x, int y, String t) {
        this.x = x;
        this.y = y;
        this.text = t;
    }

    @Override
    public void paintComponent(Graphics gIn) {
        Graphics2D g = (Graphics2D) gIn;
        g.setFont(new Font("Avenir", Font.PLAIN, 30));
        g.setColor(Color.BLACK);
        g.drawString(text, x - (g.getFontMetrics().stringWidth(text) / 2), y);
    }

    public void setText(String newText){
        text = newText;
    }

}