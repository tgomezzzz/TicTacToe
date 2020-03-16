import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class TwoPlayer implements Thinkable {
    
    @Override
    public void move(Board b) {
        if (b.getPawn() == 1) {
            b.setText("It's Player 2's turn!");
        } else {
            b.setText("It's Player 1's turn!");
        }
        b.setPawn(b.getPawn() * -1);
    }


}