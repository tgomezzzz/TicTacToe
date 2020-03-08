import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;

public class Main {

    public static final int FRAME_SIZE = 600;
    public static void main(String[] iGotTha){
        int n;
        while (true) {
            try {
                String in = JOptionPane.showInputDialog(null, "How many rows and columns would you like?");
                if (in == null){
                    System.exit(0);
                }
                n = Integer.parseInt(in);
                if (n < 1 || n > 25){
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Please enter a numerical value between 1 and 25", "Oops", 0);
            }
        }

        Board b = new Board(n, FRAME_SIZE);
        JFrame frame = new JFrame();
        frame.getContentPane().add(b, BorderLayout.CENTER);
        frame.pack();
        frame.setSize(FRAME_SIZE, FRAME_SIZE + 50);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}