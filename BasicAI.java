import java.util.List;
import java.util.ArrayList;

/**
 * 
 * Implements a Basic AI, which for now chooses a free tile next to the player's last move
 * 
 * 
 */
public class BasicAI implements Thinkable {

    private final int[][] SURROUNDING = {
        {-1, 0, 1},
        {-1,    1},
        {-1, 0, 1}
    };

    @Override
    public void move(Board b){

        int[] lastMove = b.getLastMove();
        int[] compMove = (int[]) checkSurroundingTiles(lastMove[0], lastMove[1], b);
        
        if (compMove == null) {
            RandomAI backup = new RandomAI();
            backup.move(b);
        } else {
            b.setTile(compMove[0], compMove[1], -1);
        }

    }

    public int[] checkSurroundingTiles(int x, int y, Board b){
        List<List<Integer>> possibleMoves = new ArrayList<>();
        for (int i = 0; i < SURROUNDING.length; i++){
            for (int j = 0; j < SURROUNDING[i].length; j++){
                int r = x + (i - 1);
                int c = y + SURROUNDING[i][j];
                if (b.isFreeTile(r, c)) {
                    List<Integer> move = new ArrayList<Integer>(2);
                    move.add(r);
                    move.add(c);
                    possibleMoves.add(move);
                }
            }
        }

        if (possibleMoves.isEmpty()) {
            return null;
        }

        int randIndex = (int) (Math.random() * possibleMoves.size());
        int[] move = new int[2];
        move[0] = possibleMoves.get(randIndex).get(0);
        move[1] = possibleMoves.get(randIndex).get(1);
        return move;
    }

}