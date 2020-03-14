

public class RandomAI implements Thinkable{

    @Override
    public void move(Board b){
        int randX = (int) (Math.random() * b.getGridSize());
        int randY = (int) (Math.random() * b.getGridSize());
        while (!b.isFreeTile(randX, randY) && !b.gameIsOver()) {
            randX = (int) (Math.random() * b.getGridSize());
            randY = (int) (Math.random() * b.getGridSize());
        }
        b.setTile(randX, randY, -1);
    }

}