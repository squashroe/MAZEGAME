import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

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

        root.getChildren().add(playfieldLayer);
        root.getChildren().add(scoreLayer);

        scene = new Scene(root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.show();

        loadGame();

        GameEngine.createScoreLayer();
        GameEngine.createPlayers(scene);

        AnimationTimer gameLoop =  new AnimationTimer() {
            @Override
            public void handle(long now) {

                //player Input
                players.forEach(Player::processInput);

                //add enemies
                GameEngine.spawnEnemies();

                //movement
                players.forEach(sprite -> sprite.move());
                enemies.forEach(sprite -> sprite.move());

                //check collisions
                checkCollisions();

                //update sprites in scene
                players.forEach(sprite -> sprite.updateUI());
                enemies.forEach(sprite -> sprite.updateUI());

                // check if sprite can be removed
                enemies.forEach(sprite -> sprite.checkRemovability());

                //remove removables from list, layer, etc
                removeSprites(enemies);

                //update the score, health etc
                updateScore();
            }
        };
        gameLoop.start();

    }
    private static void loadGame() {
        playerImage = new Image(getClass().getResource("player.png").toExternalForm());
        enemyImage = new Image(getClass().getResource("coin.png").toExternalForm());
    }


    public static void main(String[] args) {
        launch(args);
    }

}
