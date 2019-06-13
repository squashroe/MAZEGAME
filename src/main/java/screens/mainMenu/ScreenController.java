package screens.mainMenu;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.HashMap;

public class ScreenController {
    private HashMap<Integer, Pane> screenMap = new HashMap<>();
    private Scene main;

    public ScreenController(Scene main) {
        this.main = main;
    }

    public void addScreen(Integer menuPlace, Pane pane){
        screenMap.put(menuPlace, pane);
    }

    public void removeScreen(Integer menuPlace){
        screenMap.remove(menuPlace);
    }

    public void activate(Integer menuPlace){
        main.setRoot( screenMap.get(menuPlace) );
    }
}