import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private Pane playfieldLayer;
    private Pane scoreLayer;
    private Pane levelEditorLayer;
    private Pane mainMenu;

    private List<PlayerOne> players = new ArrayList<>();

    private Scene scene;


    public void start(Stage primaryStage) {

        Group root = new Group();

        //create the layers
        playfieldLayer = new Pane();
        scoreLayer = new Pane();
        levelEditorLayer = new Pane();
        mainMenu = new Pane();

        //Create the map
        root.getChildren().add(Settings.MAP.getCanvasMap());
        root.getChildren().add(playfieldLayer);
        root.getChildren().add(scoreLayer);

        //add the below when it is time to create a level
        // root.getChildren().add(levelEditorLayer);

        //add the menubar to the window
        root.getChildren().add(GameEngine.createMenu());

        scene = new Scene(root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("Maze Game");

        GameEngine.createScoreLayer(scoreLayer);
        GameEngine.createPlayers(scene, playfieldLayer, players);
        GameEngine.createLevelEditor(levelEditorLayer, scene);

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //player Input
                for (PlayerOne players : players) {
                    players.move();
                }

                //show when you complete a level
                GameEngine.updateScore();

                //access the menu

                //access the level editor
                //should be reading the file constantly to see any of the changes made
                //should be able to edit the file to make changes / edit the map
                //save the map and then be able to select from the menu
            }
        };
        gameLoop.start();
    }
}

