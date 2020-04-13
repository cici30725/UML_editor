package sample.Modes;

import javafx.scene.layout.Pane;
import sample.CustomShapes.AssociationLine;
import sample.CustomShapes.GeneralizationLine;

public class AssoLineMode extends Mode{
    public AssoLineMode(Pane pane, int mode_num) {
        super(pane, mode_num);
    }

    @Override
    protected void init() {
        double startX = 0+paneWidth/10;
        double startY = paneHeight/2;
        double endX = paneWidth-paneWidth/10;
        double endY = paneHeight/2;
        AssociationLine arrow = new AssociationLine(45, startX, startY, endX, endY);
        this.pane.getChildren().add(arrow);
    };
}
