import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.awt.event.*;

public class Board extends Pane {


    public void paint(Graphics g){
        Background b = null;
        super.setBackground(b);

        g.setColor(Color.BLUE);
        g.fillRect(50, 50, 20, 20);
    }
}
