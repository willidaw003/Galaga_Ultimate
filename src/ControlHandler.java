/**
 * Created by Dawson on 3/17/2017.
 */
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

    public class ControlHandler implements KeyListener {
        public boolean upPressed;
        public boolean downPressed;
        public boolean leftPressed;
        public boolean rightPressed;
        public boolean spacePressed;


        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == 38) {
                this.upPressed = true;
            }

            if(e.getKeyCode() == 40) {
                this.downPressed = true;
            }

            if(e.getKeyCode() == 37) {
                this.leftPressed = true;
            }

            if(e.getKeyCode() == 39) {
                this.rightPressed = true;
            }

            if(e.getKeyCode() == 32) {
                this.spacePressed = true;
            }

        }

        public void keyReleased(KeyEvent e) {
            if(e.getKeyCode() == 38) {
                this.upPressed = false;
            }

            if(e.getKeyCode() == 40) {
                this.downPressed = false;
            }

            if(e.getKeyCode() == 37) {
                this.leftPressed = false;
            }

            if(e.getKeyCode() == 39) {
                this.rightPressed = false;
            }

            if(e.getKeyCode() == 32) {
                this.spacePressed = false;
            }
        }

        public void keyTyped(KeyEvent e) {
        }
    }

