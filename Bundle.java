import java.awt.Graphics2D;
public class Bundle implements Drawable {

    private Drawable[] bundle;

    public Bundle(int n){
        bundle = new Drawable[n*n];
    }

    @Override
    public void draw(Graphics2D g){
        for (Drawable d : bundle){
            if (d != null){
                d.draw(g);
            }
        }
    }

    @Override
    public void mouseOver(Graphics2D g){

    }

    public void addIcon(int i, Drawable d){
        if (i < 0 || i > bundle.length - 1){
            return;
        }
        bundle[i] = d;
    }

}