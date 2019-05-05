import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class GameEngine {

    private static boolean collision = false;
    private static Text collisionText = new Text();

    private static Random rnd = new Random();

    private Image playerImage;
    private Image enemyImage;

    public static void createScoreLayer(Pane scoreLayer) {

//test
        collisionText.setFont(Font.font(null, FontWeight.BOLD, 64));
        collisionText.setStroke(Color.BLACK);
        collisionText.setFill(Color.RED);

        scoreLayer.getChildren().add(collisionText);

        // TODO: quick-hack to ensure the text is centered; usually you don't have that; instead you have a health bar on top
        collisionText.setText("Collision");
        double x = (Settings.SCENE_WIDTH - collisionText.getBoundsInLocal().getWidth()) / 2;
        double y = (Settings.SCENE_HEIGHT - collisionText.getBoundsInLocal().getHeight()) / 2;
        collisionText.relocate(x, y);
        collisionText.setText("");

        collisionText.setBoundsType(TextBoundsType.VISUAL);

    }

    public static void createPlayers(Scene scene, Pane playfieldLayer, List<Player> players) {

        Image playerImage = new Image(GameEngine.class.getResource("player.png").toExternalForm());

        // player input
        Input input = new Input(scene);

        // register input listeners
        input.addListeners(); // TODO: remove listeners on game over

        // center horizontally, position at 70% vertically
        double x = (Settings.SCENE_WIDTH - playerImage.getWidth()) / 2.0;
        double y = Settings.SCENE_HEIGHT * 0.7;

        // create player
        Player player = new Player(playfieldLayer, playerImage, 0, 0, 0, 0, 0, 0, Settings.PLAYER_SHIP_HEALTH, 0, Settings.PLAYER_SHIP_SPEED, input);

        // register player
        players.add(player);

    }

    public static void spawnEnemies(Pane playfieldLayer, List<Enemy> enemies) {
        if (rnd.nextInt(Settings.ENEMY_SPAWN_RANDOMNESS) != 0) {
            return;
        }

        //image
        Image enemyImage = new Image(GameEngine.class.getResource("coin.png").toExternalForm());

        // random speed
        double speed = 0 ; // rnd.nextDouble() * 1.0 + 2.0;

        // x position range: enemy is always fully inside the screen, no part of it is outside
        // y position: right on top of the view, so that it becomes visible with the next game iteration
        double x = rnd.nextDouble() * (Settings.SCENE_WIDTH - enemyImage.getWidth());
        double y = rnd.nextDouble() * (Settings.SCENE_HEIGHT - enemyImage.getHeight());

        // create a sprite
        Enemy enemy = new Enemy(playfieldLayer, enemyImage, x, y, 0, 0, speed, 1, 1, 0);

        // manage sprite
        enemies.add(enemy);

    }

    public static void removeSprites(List<? extends SpriteBase> spriteList) {
        Iterator<? extends SpriteBase> iter = spriteList.iterator();
        while (iter.hasNext()) {
            SpriteBase sprite = iter.next();

            if (sprite.isRemovable()) {

                // remove from layer
                sprite.removeFromLayer();

                // remove from list
                iter.remove();
            }
        }
    }

    public static void checkCollisions(List<Player> players, List<Enemy> enemies) {

        collision = false;

        for (Player player : players) {
            for (Enemy enemy : enemies) {
                if (player.collidesWith(enemy)) {
                    collision = true;
                    player.clipWithEnemy(player.getY(), player.getX(), enemy.getY(), enemy.getX());
                }
            }
        }
    }

    // this part looks pointless
    public static void updateScore() {
        if (collision) {
            collisionText.setText("Collision");
        } else {
            collisionText.setText("");
        }
    }

    public static Canvas createMap(){
        Canvas canvas = new Canvas(Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
//        gc.setFill(Color.RED);
//        gc.fillOval(10, 60, 30, 30);
        Map m = new Map();

        for (int y = 0; y < Settings.TILE_AMOUNT_HEIGHT; y++) {
            for (int x = 0; x < Settings.TILE_AMOUNT_WIDTH; x++) {
                if(m.getMap(x , y).equals("f")){
                    gc.drawImage(m.getFloor(), x * Settings.TILE_SIZE, y * Settings.TILE_SIZE);
                }
                if(m.getMap(x , y).equals("w")){
                    gc.drawImage(m.getWall(), x * Settings.TILE_SIZE, y * Settings.TILE_SIZE);
                }

            }

        }


        return canvas;
    }
}
