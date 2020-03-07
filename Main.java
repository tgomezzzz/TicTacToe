import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;

public class Main {

    public static void main(String[] iGotTha){
        final int FRAME_SIZE = 600;
        JFrame frame = new JFrame();
        int n;
        while (true) {
            try {
                n = Integer.parseInt(JOptionPane.showInputDialog(null, "How many rows and columns would you like?\n(Enter 0 to exit)"));
                if (n < 1){
                    System.exit(0);
                }
                break;
            } catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Please enter a numerical value", "Oops", 0);
            }
        }
        Board b = new Board(n, FRAME_SIZE);
        TextBox t = new TextBox(500, 600, "test");
        frame.getContentPane().add(b, BorderLayout.CENTER);
        frame.pack();
        frame.setSize(FRAME_SIZE, FRAME_SIZE + 100);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}