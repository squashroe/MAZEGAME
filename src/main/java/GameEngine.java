import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

import java.util.List;

public class GameEngine {

    private static Text collisionText = new Text();
    private static PlayerOne playerOne;

    public static void createScoreLayer(Pane scoreLayer) {

        collisionText.setFont(Font.font(null, FontWeight.BOLD, 64));
        collisionText.setStroke(Color.BLACK);
        collisionText.setFill(Color.YELLOW);

        scoreLayer.getChildren().add(collisionText);

        double x = (90);
        double y = (120);
        collisionText.relocate(x, y);
        collisionText.setText("");
        collisionText.setBoundsType(TextBoundsType.VISUAL);
    }

    public static void createMenu(Pane menuLayer){

    }

    public static void createPlayers(Scene scene, Pane playfieldLayer, List<PlayerOne> players) {
        // player input
        Input input = new Input(scene);

        // register input listeners
        input.addListeners(); // TODO: remove listeners on game over

        // create player
        playerOne = new PlayerOne(playfieldLayer, 32, 32, input);

        Settings.MAP.setPlayerOne(playerOne);
        // register player
        players.add(playerOne);

    }

    public static void updateScore() {
        if (Settings.MAP.getMap(playerOne.getTileX(), playerOne.getTileY()).equals("e")) {
            collisionText.setText("Game Winner!!");
        } else if (Settings.MAP.getMap(playerOne.getTileX(), playerOne.getTileY()).equals("s")) {
            collisionText.setText("Start");
        } else{
            collisionText.setText("");
        }
    }
}
