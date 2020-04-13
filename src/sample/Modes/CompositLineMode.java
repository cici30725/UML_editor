package sample.Modes;

import javafx.scene.layout.Pane;
import sample.CustomShapes.CompositionLine;
import sample.CustomShapes.GeneralizationLine;

public class CompositLineMode extends Mode {
    public CompositLineMode(Pane pane, int mode_num) {
        super(pane, mode_num);
    }

    @Override
    public void init() {
        double startX = 0+paneWidth/10;
        double startY = paneHeight/2;
        double endX = paneWidth-paneWidth/10;
        double endY = paneHeight/2;
        CompositionLine arrow = new CompositionLine(startX, startY, endX, endY);
        this.pane.getChildren().add(arrow);
    };
}
