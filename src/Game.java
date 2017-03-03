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
    int mouseX, mouseY;
    boolean upPressed, downPressed, tabPressed, mouseIsClicked;

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
        frame.pack();
        frame.setLocationRelativeTo(null);

    }

    public void init(){
        things = new ArrayList<>();
        things.add(new PlayerShip(this,695,375,10,10,3.5,3.5,50,Color.GREEN, "player"));
        if(mouseIsClicked == true){
            things.add(new Bullet(this, mouseX, mouseY,
                    4, 4, .02, .02, 0, Color.RED));
            things.speed();

        }


//        for(int i = 0; i < 8; i++)
//            things.add(new Invader(this,(int)(25 + (getWidth()-100) * Math.random()),(int)(25 + (getHeight()-100) * Math.random()),
//                    10,10,.045,.045, 0,Color.CYAN, "enemy"));
    }

    public void run() {

        timer = new Timer(1000/60,this);
        timer.start();

    }

    public int collision(Entity e, Entity other, boolean isBattle) {

        if(e.getBounds().intersects(other.getBounds())) {

            if(other instanceof Invader && e.getType().equals("player")) {
                die();
            }
            else if(other instanceof Invader) {
                if(other.getType().equals("bullet")) {

                }

            }
            if(isBattle) {
                if(e.getWidth() > other.getWidth() * 1.2) {
                    e.setWidth(e.getWidth() + other.getWidth() * .25);
                    e.setHeight(e.getHeight() + other.getHeight() * .25);
                    e.setDx(e.getDx() < 0 ? e.getDx() + other.getWidth()*.00007 : e.getDx() - other.getWidth()*.00007);
                    e.setDy(e.getDy() < 0 ? e.getDy() + other.getWidth()*.00007 : e.getDy() - other.getWidth()*.00007);
                    e.setSlowDown(e.getSlowDown() + other.getWidth() / 5);
                    return 1;
                }
                else if(other.getWidth() > e.getWidth() * 1.2) {
                    other.setWidth(other.getWidth() + e.getWidth() * .75);
                    other.setHeight(other.getHeight() + e.getHeight() * .75);
                    other.setDx(other.getDx() < 0 ? other.getDx() + e.getWidth()*.00007 : other.getDx() - e.getWidth()*.00007);
                    other.setDy(other.getDy() < 0 ? other.getDy() + e.getWidth()*.00007 : other.getDy() - e.getWidth()*.00007);
                    other.setSlowDown(other.getSlowDown() + e.getWidth() / 5);
                    return 2;
                }
                System.out.println("battle");
            }

        }

        return -1;
    }

    public void paint(Graphics g) {
        super.paint(g);
        for(Entity obj : things) {
            obj.paint(g);
        }
    }

    private void printSimpleString(String s, int width, int XPos, int YPos, Graphics g2d) {

        int stringLen = (int)g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();
        int start = width/2 - stringLen/2;
        g2d.drawString(s,start + XPos, YPos);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        things.get(0).playerMove(mouseX, mouseY);
        boolean iBlob = false, jBlob = false;

        for(int i = 0; i < things.size(); i++) {
            for(int j = 1; j < things.size(); j++) {

                if(things.get(i).getType().equals("player") || things.get(i).getType().equals("enemy")) iBlob = true;
                if(things.get(j).getType().equals("player") || things.get(j).getType().equals("enemy")) jBlob = true;

                things.get(j).move();
                if(things.get(i).collides(things.get(j)) && i != j) {


                    if(iBlob && jBlob) {
                        int delete = collision(things.get(j), things.get(i), true);
                        if(delete == 1) {
                            if(things.get(i).getType().equals("lletplayer")) die();
                            things.remove(i);
                        }
                        else if(delete == 2) {
                            if(things.get(j).getType().equals("player")) die();
                            things.remove(j);
                        }
                    }
                }

                iBlob = false;
                jBlob = false;

            }

        }
        repaint();

    }

    public void die() {
        JOptionPane.showMessageDialog(this, "You died!");
        System.exit(0);
    }


    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX() - 3;
        mouseY = e.getY() - 25;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e)  {

        if (e.getButton() == 1) {
            mouseIsClicked = true;
        }

    }



    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_UP) {
            upPressed = true;
        }

        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            downPressed = true;
        }

        if(e.getKeyCode() == KeyEvent.VK_TAB) {
            tabPressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_UP) {
            upPressed = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if(e.getKeyCode() == KeyEvent.VK_TAB) {
            tabPressed = false;
        }

    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isTabPressed() {
        return tabPressed;
    }


}
