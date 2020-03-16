

public class RandomAI implements Thinkable {

    private final String[] WIN_MESSAGES = {
        "The computer won. Good game!",
        "You won, well played!"
    };

    @Override
    public void move(Board b){
        int randX = (int) (Math.random() * b.getGridSize());
        int randY = (int) (Math.random() * b.getGridSize());
        while (!b.isFreeTile(randX, randY) && !b.gameIsOver()) {
            randX = (int) (Math.random() * b.getGridSize());
            randY = (int) (Math.random() * b.getGridSize());
        }
        b.setText("It's your turn!");
        b.setTile(randX, randY, -1);
    }

    @Override
    public String getWinnerText(int player) {
        return WIN_MESSAGES[(player + 1) / 2];
    }

}