import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;

public class Main {

    public static void main(String[] iGotTha){
        final int FRAME_WIDTH = 800;
        final int FRAME_HEIGHT = 800;
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setBackground(Color.CYAN);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Board b = new Board(10);
        Icon board = new MyIcon(b, FRAME_WIDTH/2, FRAME_HEIGHT/2);
        JLabel label = new JLabel(board);
    
        frame.add(label, BorderLayout.CENTER);
        //frame.pack();
        //frame.repaint();
        // b.setTile(3, 4, 1);
        // b.setTile(5, 5, 2);
        // b.setTile(3, 4, 2);
        // b.drawBoard();
        
    }


}