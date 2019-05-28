import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

import java.awt.*;
import java.util.List;

public class GameEngine {

    private static Text collisionText = new Text();
    private static PlayerOne playerOne;

    static void createScoreLayer(Pane scoreLayer) {

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

    static void createLevelEditor(Pane levelEditorLayer, Scene scene){
        // make a box
        // put some text in it
        // add it to the menu layer


    }

    static void createPlayers(Scene scene, Pane playfieldLayer, List<PlayerOne> players) {
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

    static void updateScore() {
        if (Settings.MAP.getMap(playerOne.getTileX(), playerOne.getTileY()).equals("e")) {
            collisionText.setText("Game Winner!!");
        } else if (Settings.MAP.getMap(playerOne.getTileX(), playerOne.getTileY()).equals("s")) {
            collisionText.setText("Start");
        } else{
            collisionText.setText("");
        }
    }

    static MenuBar createMenu() {
        //make the menu bar
        javafx.scene.control.MenuBar menuBar = new javafx.scene.control.MenuBar();

        //create the menu
        javafx.scene.control.Menu menu = new javafx.scene.control.Menu("File");

        //create the items in the menu
        javafx.scene.control.MenuItem m1 = new javafx.scene.control.MenuItem("New");
        javafx.scene.control.MenuItem m2 = new javafx.scene.control.MenuItem("Save");
        javafx.scene.control.MenuItem m3 = new MenuItem("Exit");

        //add them to the menu
        menu.getItems().add(m1);
        menu.getItems().add(m2);
        menu.getItems().add(m3);

        //add the menu to the menubar
        menuBar.getMenus().add(menu);
        return menuBar;
    }
}
