import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;

public class Main {

    public static void main(String[] iGotTha){
        final int FRAME_SIZE = 600;
        JFrame frame = new JFrame();
        Board b = new Board(5, FRAME_SIZE);
        frame.getContentPane().add(b);
        frame.setSize(FRAME_SIZE, FRAME_SIZE);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}