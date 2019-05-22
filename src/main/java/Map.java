import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

public class Map {

    private Scanner m;
    private String Map[] = new String[Settings.TILE_AMOUNT_WIDTH];
    private Canvas canvas = new Canvas(Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
    private GraphicsContext gc;

    private javafx.scene.image.Image floor;
    private javafx.scene.image.Image wall;
    private javafx.scene.image.Image start;
    private javafx.scene.image.Image end;
    private javafx.scene.image.Image pit;
    private javafx.scene.image.Image lockedDoor;
    private javafx.scene.image.Image unlockedDoor;

    public Map() {
        floor = new Image("floor.png");
        wall = new Image("wall.png");
        start = new Image("startTile.png");
        end = new Image("endTile.png");

        openFile();
        readFile();
        closeFile();
        createMap();
    }

    public javafx.scene.image.Image getFloor() {
        return floor;
    }


    public String getMap(int x, int y) {
        String index = Map[y].substring(x, x + 1);
        return index;
    }

    public void openFile() {
        URL url = getClass().getResource("Map.txt");
        try {
            m = new Scanner(new File(url.getPath()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("error loading map");
        }
    }

    public void readFile() {
        //System.out.println("is there something to read?" + m.hasNext());
        while (m.hasNext()) {
            for (int i = 0; i < Settings.TILE_AMOUNT_WIDTH; i++) {
                Map[i] = m.next();
            }
        }
    }

    public void closeFile() {
    }

    public void createMap() {
        gc = canvas.getGraphicsContext2D();

        for (int y = 0; y < Settings.TILE_AMOUNT_HEIGHT; y++) {
            for (int x = 0; x < Settings.TILE_AMOUNT_WIDTH; x++) {
                if (getMap(x, y).equals("f")) {
                    gc.drawImage(floor, x * Settings.TILE_SIZE, y * Settings.TILE_SIZE);
                }
                if (getMap(x, y).equals("w")) {
                    gc.drawImage(wall, x * Settings.TILE_SIZE, y * Settings.TILE_SIZE);
                }
                if (getMap(x, y).equals("s")) {
                    gc.drawImage(start, x * Settings.TILE_SIZE, y * Settings.TILE_SIZE);
                }
                if (getMap(x, y).equals("e")) {
                    gc.drawImage(end, x * Settings.TILE_SIZE, y * Settings.TILE_SIZE);
                }
            }
        }
    }

    public Canvas getCanvasMap() {
        return canvas;
    }

    public void setPlayerOne(PlayerOne playerOne) {
        PlayerOne playerOne1 = playerOne;
    }
}
