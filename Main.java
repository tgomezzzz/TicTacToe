import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;

public class Main {

    public static final int FRAME_SIZE = 600;
    public static void main(String[] iGotTha){
        String[] gameModeOptions = {"Hard", "Easy", "Two-Player"};
        Thinkable[] cpuOptions = {new BasicAI(), new RandomAI(), new TwoPlayer()};
        int gameMode, n;

        gameMode = JOptionPane.showOptionDialog(null, "Select game mode", "Gameplay", 
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, 
                    null, gameModeOptions, gameModeOptions[2]);

        if (gameMode == -1) {
            System.exit(0);
        }

        while (true) {
            try {
                String in = JOptionPane.showInputDialog("How many rows and columns would you like?");
                if (in == null){
                    System.exit(0);
                }
                n = Integer.parseInt(in);
                if (n < 1 || n > 25){
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Please enter an integer value between 1 and 25", "Oops", 0);
            }
        }

        Board b = new Board(n, FRAME_SIZE, cpuOptions[gameMode]);
        JFrame frame = new JFrame();
        frame.getContentPane().add(b, BorderLayout.CENTER);
        frame.pack();
        frame.setSize(FRAME_SIZE, FRAME_SIZE + 50);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}