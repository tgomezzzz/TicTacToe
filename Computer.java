import java.util.concurrent.*;

public class Computer {

    private Board b;
    private Thinkable AI;
    public Computer(Board board, Thinkable inAI){
        this.b = board;
        this.AI = inAI;
    }

    public void move() {
        AI.move(b);
    }


}