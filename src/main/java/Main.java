import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private Pane playfieldLayer;
    private Pane scoreLayer;
    private Pane menuLayer;
    private Pane createLevelLayer;

    private List<PlayerOne> players = new ArrayList<>();

    private Scene scene;


    public void start(Stage primaryStage) {

        Group root = new Group();

        //create the layers
        playfieldLayer = new Pane();
        scoreLayer = new Pane();
        menuLayer = new Pane();
        createLevelLayer = new Pane();

        //Create the map
        root.getChildren().add(Settings.MAP.getCanvasMap());
        root.getChildren().add(playfieldLayer);
        root.getChildren().add(scoreLayer);
        root.getChildren().add(menuLayer);
        root.getChildren().add(createLevelLayer);

        scene = new Scene(root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("Maze Game");

        GameEngine.createScoreLayer(scoreLayer);
        GameEngine.createPlayers(scene, playfieldLayer, players);

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //player Input
                for (PlayerOne players : players) {
                    players.move();
                }
                GameEngine.updateScore();
            }
        };
        gameLoop.start();
    }
}

