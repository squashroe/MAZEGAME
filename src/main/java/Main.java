import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private Pane playfieldLayer;
    private Pane scoreLayer;

    private Image playerImage;
    private Image enemyImage;


    private List<Player> players = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();

    private Scene scene;

    public void start(Stage primaryStage) {

        Group root = new Group();

        //create the layers
        playfieldLayer = new Pane();
        scoreLayer = new Pane();

        //Create the map

        root.getChildren().add(GameEngine.createMap());

        root.getChildren().add(playfieldLayer);
        root.getChildren().add(scoreLayer);

        scene = new Scene(root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.show();


        GameEngine.createScoreLayer(scoreLayer);
        GameEngine.createPlayers(scene, playfieldLayer, players);

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {

                //player Input
                players.forEach(Player::processInput);

                //add enemies
               // GameEngine.spawnEnemies(playfieldLayer, enemies);

                //movement
                players.forEach(sprite -> sprite.move());
                //enemies.forEach(sprite -> sprite.move());

                //check collisions
                GameEngine.checkCollisions(players, enemies);

                //update sprites in scene
                players.forEach(sprite -> sprite.updateUI());
                enemies.forEach(sprite -> sprite.updateUI());

                // check if sprite can be removed
               // enemies.forEach(sprite -> sprite.checkRemovability());

                //remove removables from list, layer, etc
               // GameEngine.removeSprites(enemies);

                //update the score, health etc
                GameEngine.updateScore();
            }
        };
        gameLoop.start();

    }




    public static void main(String[] args) {
        launch(args);
    }

}
