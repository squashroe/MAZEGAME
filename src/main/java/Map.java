import javafx.scene.image.Image;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.*;

public class Map {

    private Scanner m;
    private String Map[] = new String[Settings.TILE_AMOUNT_WIDTH];

    private javafx.scene.image.Image floor;
    private javafx.scene.image.Image wall;

    public Map(){
        floor = new Image("floor.png");
        wall = new Image("wall.png");

        openFile();
        readFile();
        closeFile();
    }

    public javafx.scene.image.Image getFloor(){
        return floor;
    }

    public javafx.scene.image.Image getWall(){
        return wall;
    }


    public String getMap(int x, int y){
        String index = Map[y].substring(x, x + 1);
        return index;
    }

    public void openFile() {
        try {
            m = new Scanner(new File("C:\\Development\\MAZEGAME\\src\\main\\resources\\Map.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("error loading map");
        }
    }

    public void readFile() {
        //System.out.println("is there something to read?" + m.hasNext());
        while(m.hasNext()){
            for (int i = 0; i < Settings.TILE_AMOUNT_WIDTH; i++) {
                Map[i] = m.next();
            }
        }
    }

    public void closeFile() {
    }

}
