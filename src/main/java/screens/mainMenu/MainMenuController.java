package screens.mainMenu;

import configuration.Settings;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class MainMenuController {

    private VBox menuBox;

    public MainMenuController() {
    }

    public Pane createContent() {
        Pane root = new Pane();
        root.setPrefSize(Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);

        Rectangle bg = new Rectangle(Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);

        ContentFrame frame = new ContentFrame(createBoxContent());

        HBox hbox = new HBox(frame);
        hbox.setTranslateX(220);
        hbox.setTranslateY(100);

        MenuItem itemExit = new MenuItem("QUIT GAME");
        itemExit.setOnActivate(() -> System.exit(0));

        menuBox = new VBox(10.0,
                new MenuItem("PLAY GAME"),
                new MenuItem("LEVEL EDITOR"),
                new MenuItem("OPTIONS"),
                itemExit);
        menuBox.setAlignment(Pos.TOP_CENTER);
        menuBox.setTranslateX(250);
        menuBox.setTranslateY(400);


        getMenuItem(0).setActive(true);

        root.getChildren().addAll(bg, hbox, menuBox);
        return root;
    }

    public MenuItem getMenuItem(int index) {
        return (MenuItem) menuBox.getChildren().get(index);
    }

    private Node createBoxContent() {
        Circle logo = new Circle(50);
        logo.setStroke(Color.YELLOW);
        HBox icon = new HBox(0);
        icon.setAlignment(Pos.CENTER);
        icon.getChildren().add(logo);
        return icon;
    }

    public VBox getMenuBox() {
        return menuBox;
    }

    private static class ContentFrame extends StackPane {
        private ContentFrame(Node content) {
            setAlignment(Pos.CENTER);

            Rectangle frame = new Rectangle(200, 200);
            frame.setArcWidth(25);
            frame.setArcHeight(25);
            frame.setStroke(Color.WHITESMOKE);

            getChildren().addAll(frame, content);
        }
    }


}
