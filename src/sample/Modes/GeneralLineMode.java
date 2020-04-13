package sample.Modes;

import javafx.scene.layout.Pane;
import sample.CustomShapes.AssociationLine;
import sample.CustomShapes.GeneralizationLine;

public class GeneralLineMode extends Mode {
    public GeneralLineMode(Pane pane, int mode_num) {
        super(pane, mode_num);
    }

    @Override
    public void init() {
        double startX = 0+paneWidth/10;
        double startY = paneHeight/2;
        double endX = paneWidth-paneWidth/10;
        double endY = paneHeight/2;
        GeneralizationLine arrow = new GeneralizationLine(10, startX, startY, endX, endY);
        this.pane.getChildren().add(arrow);
    };
}
