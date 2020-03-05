import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;

public class Main {

    public static void main(String[] iGotTha){
        final int FRAME_WIDTH = 600;
        final int FRAME_HEIGHT = 600;
        
        JFrame frame = new JFrame();
        Board b = new Board(3);
        frame.getContentPane().add(b);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // Icon board = new MyIcon(b, FRAME_WIDTH, FRAME_HEIGHT);
        // JLabel label = new JLabel(board);

        // label.addMouseListener(new MouseListener() {
        //     @Override
        //     public void mouseChanged(MouseEvent e){
        //         b.draw(g);
        //     }
        // });
    
        //frame.add(label, BorderLayout.CENTER);
       //frame.repaint();
        // b.setTile(3, 4, 1);
        // b.setTile(5, 5, 2);
        // b.setTile(3, 4, 2);
        // b.drawBoard();
        
    }


}