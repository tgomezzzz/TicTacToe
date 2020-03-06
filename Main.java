import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;

public class Main {

    public static void main(String[] iGotTha){
        final int FRAME_WIDTH = 600;
        final int FRAME_HEIGHT = 600;
        
        JFrame frame = new JFrame();
        Board b = new Board(4);
        frame.getContentPane().add(b);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }


}