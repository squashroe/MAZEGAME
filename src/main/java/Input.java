
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.BitSet;

public class Input {

    /**
     * Bitset which registers if any {@link KeyCode} keeps being pressed or if it is released.
     */
    private BitSet keyboardBitSet = new BitSet();

    // -------------------------------------------------
    // default key codes
    // will vary when you let the user customize the key codes or when you add support for a 2nd player
    // -------------------------------------------------

    private KeyCode upKey = KeyCode.UP;
    private KeyCode downKey = KeyCode.DOWN;
    private KeyCode leftKey = KeyCode.LEFT;
    private KeyCode rightKey = KeyCode.RIGHT;
    private Boolean moveUp = false;
    private Boolean moveDown = false;
    private Boolean moveLeft = false;
    private Boolean moveRight = false;

    Scene scene;

    public Input(Scene scene) {
        this.scene = scene;
    }

    public void addListeners() {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, keyPressedEventHandler);
    }

    public void removeListeners() {
        scene.removeEventFilter(KeyEvent.KEY_PRESSED, keyPressedEventHandler);
        scene.removeEventFilter(KeyEvent.KEY_RELEASED, keyReleasedEventHandler);
    }

    /**
     * "Key Pressed" handler for all input events: register pressed key in the bitset
     */
    private EventHandler<KeyEvent> keyPressedEventHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {

            // register key down
            keyboardBitSet.set(event.getCode().ordinal(), true);

            if (event.getCode() == KeyCode.UP) {
                moveUp = true;
                moveDown = false;
                moveLeft = false;
                moveRight = false;
            }
            if (event.getCode() == KeyCode.DOWN) {
                moveUp = false;
                moveDown = true;
                moveLeft = false;
                moveRight = false;
            }
            if (event.getCode() == KeyCode.LEFT) {
                moveUp = false;
                moveDown = false;
                moveLeft = true;
                moveRight = false;
            }
            if (event.getCode() == KeyCode.RIGHT) {
                moveUp = false;
                moveDown = false;
                moveLeft = false;
                moveRight = true;
            }
        }
    };

    /**
     * "Key Released" handler for all input events: unregister released key in the bitset
     */
    private EventHandler<KeyEvent> keyReleasedEventHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            // register key up
            keyboardBitSet.set(event.getCode().ordinal(), false);
        }
    };

    public Boolean getMoveUp() {
        return moveUp;
    }

    public Boolean getMoveDown() {
        return moveDown;
    }

    public Boolean getMoveLeft() {
        return moveLeft;
    }

    public Boolean getMoveRight() {
        return moveRight;
    }

    public void setMoveUp(Boolean moveUp) {
        this.moveUp = moveUp;
    }

    public void setMoveDown(Boolean moveDown) {
        this.moveDown = moveDown;
    }

    public void setMoveLeft(Boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    public void setMoveRight(Boolean moveRight) {
        this.moveRight = moveRight;
    }
}