import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;

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

    static MenuBar createMenu(Stage primaryStage) {
        //make the menu bar
        MenuBar menuBar = new javafx.scene.control.MenuBar();

        //create the menu
        Menu menu = new Menu("File");

        //create the items in the menu
        MenuItem m1 = new javafx.scene.control.MenuItem("New");
        MenuItem m2 = new javafx.scene.control.MenuItem("Save");
        MenuItem m3 = new MenuItem("Exit");

        m3.setOnAction(e -> primaryStage.close());

        //add them to the menu
        menu.getItems().add(m1);
        menu.getItems().add(m2);
        menu.getItems().add(m3);




       // primaryStage.setScene(scene2);




        //add the menu to the menubar
        menuBar.getMenus().add(menu);
        return menuBar;
    }

    static void createMainMenuLayer(Pane mainMenu) {
        Text startGameText = new Text();
        Text levelSelectText = new Text();
        Text optionsText = new Text();
        Image mazebackdrop = new Image("mazegamebackdrop.jpg");
        javafx.scene.canvas.Canvas canvas = new Canvas(Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();;
        gc.drawImage(mazebackdrop, 0, 0);
        gc.moveTo(640,640);

        mainMenu.getChildren().add(gc.getCanvas());
    }

    static void showMainMenu(Group root, Pane mainMenu){

        if(Settings.ESCPRESSED){
            root.getChildren().remove(mainMenu);
        }
        else if (!Settings.ESCPRESSED) {
            root.getChildren().add(mainMenu);
        }
    }

}
