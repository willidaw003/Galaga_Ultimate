import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.event.ActionListener;

/**
 * Created by willidaw003 on 2/28/2017.
 */
public class Game extends JPanel implements ActionListener, MouseMotionListener, MouseListener, KeyListener {

    Timer timer;
    ArrayList<Entity> things;
    int PlayerShipY = 720;
    int mouseX, mouseY;
    boolean upPressed, downPressed, tabPressed,firePressed;


    public Game() {
        //step 1, create a frame
        JFrame frame = new JFrame("Galaga!");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Agario");
        setPreferredSize(new Dimension(1400, 750));
        frame.setResizable(false);
        setBackground(Color.BLACK);

        addKeyListener(this);
        frame.add(this);
        frame.addMouseMotionListener(this);
        frame.addMouseListener(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.init();
        game.run();
    }

    public void init() {
        things = new ArrayList<>();
        things.add(new PlayerShip(this, mouseX, PlayerShipY, 25, 25, 20, 0, 50, Color.GREEN, "playerShip"));
        for (int col = 0; col < 6; col++) {
            for (int row = 0; row < 6; row++) {
                things.add(new Invader(this, 5 + 70 * col, 5 + 70 * row, 50, 50, 5, 0, 50, Color.GREEN,
                        "invader"));
            }
        }

    }




    public void run() {

        timer = new Timer(1000 / 60, this);
        timer.start();

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
        for (int i = 1; i < things.size(); i++) {
            for(int j = i+1;j < things.size(); j++){
                if(things.get(i).getBounds().intersects(things.get(j).getBounds())){
                    if(things.get(i).getType().equals("invader") && things.get(j).getType().equals("bullet")){
                       things.remove(i);
                        for (i = 0; i < things.size(); i++) {
                            if (things.get(i).getType().equals("bullet")) {
                                things.remove(i);
                            }
                        }
                    }
                }
            }
        }

        System.out.println(firePressed);

        for (int i = 0; i < things.size(); i++) {
                things.get(i).move(things);
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

    }

    @Override
    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e)  {
        things.add(new Bullet(this,mouseX, PlayerShipY-15,
                10, 10, 0, -25, 0, Color.CYAN, "bullet"));
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP) {upPressed = true;}
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {downPressed = true;}
        if(e.getKeyCode() == KeyEvent.VK_TAB) {tabPressed = true;}
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            firePressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_UP) {upPressed = false;}
        if( key == KeyEvent.VK_DOWN) {downPressed = false;}
        if(key == KeyEvent.VK_TAB) {tabPressed = false;}
        if (key == KeyEvent.VK_SPACE) {
            firePressed = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }


    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isTabPressed() {return tabPressed;}

}
