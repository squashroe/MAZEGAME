package screens.game;

import configuration.Settings;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    private List<PlayerOne> players = new ArrayList<>();
    private Scene scene;

    public GameController() {
    }

    public Parent createScene() {
        Pane gamePane = new Pane();
        gamePane.setPrefSize(Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);

        //create the layers
        Pane playfieldLayer = new Pane();
        Pane scoreLayer = new Pane();


        //Create the map
//        gamePane.getChildren().add(Settings.MAP.getCanvasMap());
//        gamePane.getChildren().add(playfieldLayer);
//        gamePane.getChildren().add(scoreLayer);

        //scene = new Scene(gamePane, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);  - THE PROBLEM

        GameEngine.createScoreLayer(scoreLayer);
        GameEngine.createPlayers(scene, playfieldLayer, players);

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //player screens.game.Input
                for (PlayerOne players : players) {
                    players.move();
                }

                //show when you complete a level
                GameEngine.updateScore();
            }
        };
        gameLoop.start();

        gamePane.getChildren().addAll(Settings.MAP.getCanvasMap(), playfieldLayer, scoreLayer);
        return gamePane;
    }

}

