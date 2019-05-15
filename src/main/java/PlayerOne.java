import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PlayerOne {

    private final Pane layer;
    private int tileX;
    private int tileY;
    private int x = 32;
    private int y = 32;
    private Input input;

    private ImageView imageView;

    private Image playerImage;
    private int playerShipMinX = 0;
    private int playerShipMaxX = Settings.SCENE_WIDTH - 32;
    private int playerShipMinY = 0;
    private int playerShipMaxY = Settings.SCENE_HEIGHT - 32;

    public PlayerOne(Pane playfieldLayer, int tileX, int tileY, Input input) {

        this.layer = playfieldLayer;

        playerImage = new Image(GameEngine.class.getResource("player.png").toExternalForm());

        this.imageView = new ImageView(playerImage);
        this.imageView.relocate(x, y);

        this.tileX = tileX;
        this.tileY = tileY;

        this.input = input;

        this.layer.getChildren().add(imageView);
    }

    public void move(){

        if(input.isMoveUp()){
            y -= 32;
            imageView.relocate(x, y);
        }
        if(input.isMoveDown()){
            y += 32;
            imageView.relocate(x, y);
        }
        if(input.isMoveLeft()){
            x -= 32;
            imageView.relocate(x, y);
        }
        if(input.isMoveRight()){
            x += 32;
            imageView.relocate(x, y);
        }

        checkBounds();

    }
    private void checkBounds() {

        // vertical
        if( Double.compare(y, playerShipMinY) < 0) {
            y = playerShipMinY;
        } else if( Double.compare(y, playerShipMaxY) > 0) {
            y = playerShipMaxY;
        }

        // horizontal
        if( Double.compare( x, playerShipMinX) < 0) {
            x = playerShipMinX;
        } else if( Double.compare(x, playerShipMaxX) > 0) {
            x = playerShipMaxX;
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
