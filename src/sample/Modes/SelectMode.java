package sample.Modes;

import javafx.scene.layout.Pane;
import sample.CustomShapes.GeneralizationLine;

public class SelectMode extends Mode{
    public SelectMode(Pane pane, int mode_num) {
        super(pane, mode_num);
    }

    @Override
    public void init() {
        double startX = paneWidth-10;
        double startY = paneHeight-10;
        double endX = 10;
        double endY = 10;
        GeneralizationLine arrow = new GeneralizationLine(10, startX, startY, endX, endY);
        pane.getChildren().add(arrow);
    }
}
