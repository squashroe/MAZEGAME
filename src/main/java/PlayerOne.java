import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.awt.event.KeyEvent;

public class PlayerOne {

    private final Pane layer;
    private int tileX;
    private int tileY;
    private Input input;

    private ImageView imageView;

    private Image playerImage;

    public PlayerOne(Pane playfieldLayer, int tileX, int tileY, Input input) {

        this.layer = playfieldLayer;

        playerImage = new Image(GameEngine.class.getResource("player.png").toExternalForm());

        this.imageView = new ImageView(playerImage);

        this.tileX = tileX;
        this.tileY = tileY;

        this.input = input;

        this.layer.getChildren().add(imageView);
    }

    public void move(){

        if(input.isMoveUp()){
            System.out.println("you typed up");
            //  player.move(0, -1);
        }
        if(input.isMoveDown()){
            System.out.println("you typed down");

        }
        if(input.isMoveLeft()){
            System.out.println("you typed left");
            //  player.move(0, -1);
        }
        if(input.isMoveRight()){
            System.out.println("you typed right");
            //  player.move(0, -1);
        }

    }

    public Image getPlayerImage() {
        return playerImage;
    }

    public int getTileX() {
        return tileX;
    }

    public int getTileY() {
        return tileY;
    }

    public void updateUI(){
        imageView.relocate(tileX, tileY);
    }

}
