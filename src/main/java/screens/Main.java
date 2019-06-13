package screens;

import configuration.Settings;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import screens.game.GameController;
import screens.game.GameEngine;
import screens.game.PlayerOne;
import screens.mainMenu.MainMenuController;
import screens.mainMenu.ScreenController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Main extends Application {

    private ScheduledExecutorService bgThread = Executors.newSingleThreadScheduledExecutor();
    private List<PlayerOne> players = new ArrayList<>();
    private MainMenuController mainMenuController = new MainMenuController();
    GameController gameController = new GameController();
    private int currentItem = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane mainMenu = mainMenuController.createContent();
        Pane gameScene = makeSecondScene();

        Rectangle bg = new Rectangle(Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
        Pane options = new Pane();
        options.getChildren().add(bg);

        Rectangle bgForLvlEdtr = new Rectangle(Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
        bgForLvlEdtr.setFill(Color.YELLOW);
        Pane levelEditorScene = new Pane();
        levelEditorScene.getChildren().add(bgForLvlEdtr);


        Scene scene = new Scene(mainMenu);


        /**
        * Uncomment some of the below so that it will work with the menu selection
         * at the moment it will not work together for some reason
         *
         */

        ScreenController screenController = new ScreenController(scene);
        screenController.addScreen(0, gameScene);
        screenController.addScreen(1, levelEditorScene);
        screenController.addScreen(2, options);


        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                if (currentItem > 0) {
                    mainMenuController.getMenuItem(currentItem).setActive(false);
                    mainMenuController.getMenuItem(--currentItem).setActive(true);
                }
            }

            if (event.getCode() == KeyCode.DOWN) {
                if (currentItem < mainMenuController.getMenuBox().getChildren().size() - 1) {
                    mainMenuController.getMenuItem(currentItem).setActive(false);
                    mainMenuController.getMenuItem(++currentItem).setActive(true);
                }
            }

            if (event.getCode() == KeyCode.ENTER) {
                mainMenuController.getMenuItem(currentItem).activate();
                screenController.activate(currentItem);
            }
        });

        //select the scene
        primaryStage.setScene(scene);

        primaryStage.setOnCloseRequest(event -> {
            bgThread.shutdownNow();
        });
        primaryStage.show();
    }





    public Pane makeSecondScene() {

        Pane gamePane = new Pane();
        gamePane.setPrefSize(Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);

        //create the layers
        Pane playfieldLayer = new Pane();
        Pane scoreLayer = new Pane();

        //creating the scene
      //  Scene scene2 = new Scene(gamePane);

        GameEngine.createScoreLayer(scoreLayer);
       // GameEngine.createPlayers(scene2, playfieldLayer, players);

//        AnimationTimer gameLoop = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                //player screens.game.Input
//                for (PlayerOne players : players) {
//                    players.move();
//                }
//
//                //show when you complete a level
//                GameEngine.updateScore();
//            }
//        };
//        gameLoop.start();

        gamePane.getChildren().addAll(Settings.MAP.getCanvasMap(), playfieldLayer, scoreLayer);

        return gamePane;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
