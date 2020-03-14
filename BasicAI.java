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
        int[] compMove = blockPlayerWin(b);

        if (compMove == null) {
            compMove = checkSurroundingTiles(lastMove[0], lastMove[1], b);
        }
        if (compMove == null) {
            RandomAI backup = new RandomAI();
            backup.move(b);
        } else {
            b.setTile(compMove[0], compMove[1], -1);
        }
        System.out.println("Moving at (" + compMove[0] + ", " + compMove[1] + ")");

    }

    public int[] blockPlayerWin(Board b){
        int n = b.getGridSize();
        int[][] diagParams = {
            {0,     0,      1,  1}, //top left corner
            {0,     n - 1,  1, -1}, //top right corner
        };

        int[] move = new int[2];

        for (int[] p : diagParams) {
            move = checkPlayerWin(p[0], p[1], p[2], p[3], 0, false, move, b);
            if (move != null) {
                return move;
            }
        }
        for (int i = 0; i < n; i++) {
            int[][] linParams = {
                {i,     0,     0,  1}, //left edge, scanning right
                {0,     i,     1,  0}, //top edge, scanning down
            };
            for (int[] p : linParams) {
                move = checkPlayerWin(p[0], p[1], p[2], p[3], 0, false, move, b);
                if (move != null) {
                    return move;
                }
            }
        }  
        return move;
    }

    public int[] checkPlayerWin(int x, int y, int xDir, int yDir, int count, boolean seenBlankTile, int[] m, Board b) {
        System.out.println("Checking (" + x + ", " + y + ") with count " + count);
        if (x < 0 || x > b.getGridSize() - 1 || y < 0 || y > b.getGridSize() - 1) {
            if (count >= b.getGridSize() - 1) {
                System.out.println("Should be moving at (" + m[0] + ", " + m[1] + ")");
                return m;
            } else {
                return null;
            }
        }
        int tile;
        if ((tile = b.getTile(x, y)) != 1){
            if (tile == -1) {
                System.out.println("exiting after computer tile found");
                return null;
            } else if (!seenBlankTile){
                System.out.println("Setting move to (" + x + ", " + y + ")");
                seenBlankTile = true;
                m = new int[2];
                m[0] = x;
                m[1] = y;
            } else if (seenBlankTile) {
                System.out.println("Exiting after second blank");
                return null;
            }
        }
        return checkPlayerWin(x + xDir, y + yDir, xDir, yDir, count + 1, seenBlankTile, m, b);
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