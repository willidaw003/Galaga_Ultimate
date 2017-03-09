import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by willidaw003 on 3/1/2017.
 */
public class Invader extends Entity {

    public Invader(Game game, double x, double y, double width, double height, double dx, double dy, double slowDown, Color color, String type) {
        super(game, x, y, width, height, dx, dy, slowDown, color, "invader");
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(getColor());
        //g.drawImage(getGame().alien, getX(),getY(),50,50);
        g.fillOval((int)getX(),(int)getY(),(int)getWidth(),(int)getHeight());

    }

}
