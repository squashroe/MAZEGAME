import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
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
    private Stage primaryStage;
    private Scene scene2;


    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
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
        root.getChildren().add(GameEngine.createMenu(primaryStage));

        scene = new Scene(root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("Maze Game");


        GameEngine.createScoreLayer(scoreLayer);
        GameEngine.createPlayers(scene, playfieldLayer, players);
        GameEngine.createLevelEditor(levelEditorLayer, scene);

        GameEngine.createMainMenuLayer(mainMenu);

        Label label1 = new Label("Start Game");
        Label label2 = new Label("Level Editor");
        Button button1 = new Button("Return to Game");
        VBox layout1 = new VBox(30);
        layout1.getChildren().addAll(label1, label2, button1);
        scene2 = new Scene(layout1, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
        button1.setOnAction(e -> doshit());



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

    void doshit(){
        primaryStage.setScene(scene);
        GameEngine.createPlayers(scene2, playfieldLayer, players);
        System.out.println("HELLO");
    }
}

