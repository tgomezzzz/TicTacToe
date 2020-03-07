import java.util.concurrent.*;

public class Computer {

    private Board b;
    public Computer(Board board){
        this.b = board;
    }

    public void move() {
        int randX = (int) (Math.random() * b.getGridSize());
        int randY = (int) (Math.random() * b.getGridSize());
        while (!b.isFreeTile(randX, randY) && !b.gameIsOver()) {
            randX = (int) (Math.random() * b.getGridSize());
            randY = (int) (Math.random() * b.getGridSize());
        }
        b.setTile(randX, randY, -1);
    }


}