import java.awt.*;
import javax.swing.JFrame;

public class Main {

    public static void main(String[] iGotTha){
        JFrame frame = new JFrame();
        frame.setSize(800, 800);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Board b = new Board(10);
        b.setTile(3, 4, 1);
        b.setTile(5, 5, 2);
        b.setTile(3, 4, 2);
        b.drawBoard();
        
    }


}