import javax.swing.*;
import java.awt.*;

/**
 * Created by willidaw003 on 3/1/2017.
 */
public abstract class Entity {

    //test in html window
    Game game;
    private double x, y, width, height;
    private double dx, dy;
    private Color color;
    private String type;
    private double slowDown;

    public Entity(Game game, double x, double y, double width, double height, double dx, double dy, double slowDown, Color color, String type) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.dx = dx;
        this.dy = dy;
        this.slowDown = slowDown;
        this.color = color;
        this.type = type;
    }

    public void playerMove(int mouseX, int mouseY) {

        mouseX -= width/2;
        double rx = mouseX - x;
        double ry = mouseY - y;
        double dx = rx;
        x = x + dx;

    }

    public void move() {

        double nextTop = y + dy;
        double nextRight = x + dx + width;
        double nextBottom = y + dy + height;
        double nextLeft = x + dx;

        if(nextLeft < 0 || nextRight + width > game.getWidth()) dx *= -1;
        if(nextTop < 0 || nextBottom + height > game.getHeight()) dy *= -1;

        x+=dx;
        y+=dy;

    }

    public boolean collides(Entity other) { return getBounds().intersects(other.getBounds()); }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, (int)width, (int)height);
    }

    public abstract void paint(Graphics g);

    public void speed() {
        double speed = .05 + .07 * Math.random();
        setDx(speed);
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public double getSlowDown() {
        return slowDown;
    }

    public void setSlowDown(double slowDown) {
        this.slowDown = slowDown;
    }

}