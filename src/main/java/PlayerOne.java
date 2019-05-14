import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PlayerOne {

    private final Pane layer;
    private int tileX;
    private int tileY;

    private ImageView imageView;

    private Image playerImage;

    public PlayerOne(Pane playfieldLayer, int tileX, int tileY) {

        this.layer = playfieldLayer;

        playerImage = new Image(GameEngine.class.getResource("player.png").toExternalForm());

        this.imageView = new ImageView(playerImage);

        this.tileX = tileX;
        this.tileY = tileY;

        this.layer.getChildren().add(imageView);
    }

    public void move(int dx, int dy){

        tileX += dx;
        tileY += dy;
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
