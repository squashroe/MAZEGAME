import javafx.event.EventHandler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ActionListener implements EventHandler<javafx.scene.input.KeyEvent> {

    public void keyPressed(KeyEvent e){
        int keycode = e.getKeyCode();

        if(keycode == KeyEvent.VK_UP){
            System.out.println("you typed up");
          //  player.move(0, -1);
        }
        if(keycode == KeyEvent.VK_DOWN){
           // player.move(0, 1);
        }
        if(keycode == KeyEvent.VK_LEFT){
          //  player.move(-1, 0);
        }
        if(keycode == KeyEvent.VK_RIGHT){
          //  player.move(1, 0);
        }

    }

    public void keyReleased(KeyEvent e){

    }

    public void keyTyped(KeyEvent e){

    }

    @Override
    public void handle(javafx.scene.input.KeyEvent event) {
        System.out.println("hello there");
    }
}
