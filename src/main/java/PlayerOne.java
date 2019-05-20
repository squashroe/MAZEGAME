import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PlayerOne {

    private final Pane layer;
    private double tileX;
    private double tileY;
    private int x = 32;
    private int y = 32;
    private Input input;
    private Boolean canMoveUp = true;
    private Boolean canMoveDown = true;
    private Boolean canMoveLeft = true;
    private Boolean canMoveRight = true;

    private ImageView imageView;

    private Image playerImage;
    private int playerShipMinX = 16;
    private int playerShipMaxX = Settings.SCENE_WIDTH - 48;
    private int playerShipMinY = 16;
    private int playerShipMaxY = Settings.SCENE_HEIGHT - 48;

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

//        if(input.isMoveUp()){
//            y -= 32;
//            imageView.relocate(x, y);
//        }
//        if(input.isMoveDown()){
//            y += 32;
//            imageView.relocate(x, y);
//        }
//        if(input.isMoveLeft()){
//            x -= 32;
//            imageView.relocate(x, y);
//        }
//        if(input.isMoveRight()){
//            x += 32;
//            imageView.relocate(x, y);
//        }
        if(input.getMoveUp()){
            if(!Settings.MAP.getMap(getTileX(), getTileY() -1).equals("w")) {
                y -= 32;
                imageView.relocate(x, y);
            }
            input.setMoveUp(false);
        }
        if(input.getMoveDown()){
            if(!Settings.MAP.getMap(getTileX(), getTileY()+1).equals("w")) {
                y += 32;

                imageView.relocate(x, y);
            }
            input.setMoveDown(false);
        }
        if(input.getMoveLeft()){
            if(!Settings.MAP.getMap(getTileX() -1, getTileY()).equals("w")) {
                x -= 32;
                imageView.relocate(x, y);
            }
            input.setMoveLeft(false);
        }
        if(input.getMoveRight()){
            if(!Settings.MAP.getMap(getTileX() +1, getTileY()).equals("w")) {
                x += 32;
                imageView.relocate(x, y);
            }
            input.setMoveRight(false);
        }

        checkBounds();

    }

//    public void checkCollisions() {
//        if(getMap(playerOne.getTileX(), playerOne.getTileY()).equals("w")){
//            playerOne.setCanMoveUp(false);
//        }
//
//    }


    private void checkBounds() {

        //not being able to leave the window
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

    public void setCanMoveUp(Boolean canMoveUp) {
        this.canMoveUp = canMoveUp;
    }

    public void setCanMoveDown(Boolean canMoveDown) {
        this.canMoveDown = canMoveDown;
    }

    public void setCanMoveLeft(Boolean canMoveLeft) {
        this.canMoveLeft = canMoveLeft;
    }

    public void setCanMoveRight(Boolean canMoveRight) {
        this.canMoveRight = canMoveRight;
    }

    public Image getPlayerImage() {
        return playerImage;
    }

    public int getTileX() {
        tileX = (double) x / 32;
        //System.out.println(tileX);
        return (int) tileX;
    }

    public int getTileY() {
        tileY = (double) y /32;
        //System.out.println(tileY);
        return (int) tileY;
    }

    public void updateUI(){
        imageView.relocate(tileX, tileY);
    }

}
