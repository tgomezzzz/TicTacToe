import java.awt.Graphics2D;
import java.awt.*;

public class Grid implements Drawable {

    private Drawable[][] grid;
    private int size;

    public Grid(int n){
        grid = new Drawable[n][n];
        this.size = n;
    }

    @Override
    public void paintComponent(Graphics gIn){
        for (Drawable[] arr : grid){
            for (Drawable d : arr){
                if (d != null){
                    d.paintComponent(gIn);
                }
            }
        }
    }

    public void addIcon(int r, int c, Drawable d){
        if (r < 0 || r > size - 1 || c < 0 || c > size - 1){
            return;
        }
        grid[r][c] = d;
    }

    public void removeIcon(int r, int c){
        if (r < 0 || r > size - 1 || c < 0 || c > size - 1){
            return;
        }
        grid[r][c] = null;
    }

}