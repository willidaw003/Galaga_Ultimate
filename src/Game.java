import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by willidaw003 on 2/28/2017.
 */
public class Game extends JPanel implements ActionListener, MouseMotionListener, MouseListener, KeyListener {

    Timer timer;
    ArrayList<Entity> things;
    int PlayerShipX = 695;
    int PlayerShipY = 720;
    int mouseX, mouseY;
    boolean upPressed, downPressed, tabPressed;

    public static void main(String[] args) {
        Game game = new Game();
        game.init();
        game.run();
    }

    public Game() {
        //step 1, create a frame
        JFrame frame = new JFrame("Galaga!");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Agario");
        setPreferredSize(new Dimension(1400,750));
        frame.setResizable(false);
        setBackground(Color.BLACK);

        addKeyListener(this);
        frame.add(this);
        frame.addMouseMotionListener(this);
        frame.addMouseListener(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public void init() {
        things = new ArrayList<>();
        things.add(new PlayerShip(this, PlayerShipX, PlayerShipY, 10, 10, 20, 0, 50, Color.GREEN, "player"));
        for(int col = 0; col < 6; col++) {
            for (int row = 0; row < 6; row++) {
                things.add(new Invader(this, 5+70*col, 5+70*row, 50, 50, 1, 0, 50, Color.GREEN,
                        "invader"));
            }
        }
    }

    public void run() {

        timer = new Timer(1000/60,this);
        timer.start();

    }

    public int collision(Entity e, Entity other) {
        if(e.getBounds().intersects(other.getBounds())) {

            if(other instanceof Invader && e.getType().equals("player")) {
                die();
            }

            if(other instanceof Invader && e.getType().equals("bullet")) {
                for (int i = 0; i < things.size(); i++) {
                    if(things.get(i).getType().equals("bullet")) {
                        things.remove(i);
                        die();
                    }
                }
            }

            if(e.getWidth() > other.getWidth() * 1.2) {
                    e.setWidth(e.getWidth() + other.getWidth() * .25);
                    e.setHeight(e.getHeight() + other.getHeight() * .25);
                    e.setDx(e.getDx() < 0 ? e.getDx() + other.getWidth()*.00007 : e.getDx() - other.getWidth()*.00007);
                    e.setDy(e.getDy() < 0 ? e.getDy() + other.getWidth()*.00007 : e.getDy() - other.getWidth()*.00007);
                    return 1;
                }

            else if(other.getWidth() > e.getWidth() * 1.2) {
                    other.setWidth(other.getWidth() + e.getWidth() * .25);
                    other.setHeight(other.getHeight() + e.getHeight() * .25);
                    other.setDx(other.getDx() < 0 ? other.getDx() + e.getWidth()*.00007 : other.getDx() - e.getWidth()*.00007);
                    other.setDy(other.getDy() < 0 ? other.getDy() + e.getWidth()*.00007 : other.getDy() - e.getWidth()*.00007);
                    return 2;
                }

        }

        return -1;
    }

    public void paint(Graphics g) {
        super.paint(g);
        for(Entity obj : things) {
            //obj.setColor(new Color((int)(100+Math.random()*150),(int)(100+Math.random()*150),(int)(100+Math.random()*150)));
            obj.paint(g);
        }
        repaint();
    }

    private void printSimpleString(String s, int width, int XPos, int YPos, Graphics g2d) {
        int stringLen = (int)g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();
        int start = width/2 - stringLen/2;
        g2d.drawString(s,start + XPos, YPos);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        things.get(0).playerMove(mouseX, mouseY);
        for (int i = 0; i < things.size(); i++) {
                things.get(i).move(things, other);
            }
            repaint();
        }


    public void die() {
        JOptionPane.showMessageDialog(this, "You died!");
        System.exit(0);
    }


    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX() - 3;
        mouseY = e.getY() - 25;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        things.add(new Bullet(this, mouseX, PlayerShipY,
                4, 4, 0, -10, 1, Color.BLUE, "bullet"));
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e)  {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP) {upPressed = true;}
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {downPressed = true;}
        if(e.getKeyCode() == KeyEvent.VK_TAB) {tabPressed = true;}
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP) {upPressed = false;}
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {downPressed = false;}
        if(e.getKeyCode() == KeyEvent.VK_TAB) {tabPressed = false;}
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isTabPressed() {return tabPressed;}

}
