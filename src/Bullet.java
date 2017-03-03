import java.awt.*;

/**
 * Created by willidaw003 on 3/1/2017.
 */
public class Bullet extends Entity {

    public Bullet(Game game, double x, double y, double width, double height, double dx, double dy, double slowDown, Color color) {
        super(game, x, y, width, height, dx, dy, slowDown, color, " ");
    }

    @Override
    public void paint(Graphics g) {

        g.setColor(getColor());
        g.fillRect((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());


    }
}
