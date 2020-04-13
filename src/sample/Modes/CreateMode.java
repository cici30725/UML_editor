package sample.Modes;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CreateMode extends Mode {
    public CreateMode(Pane pane, int mode_num) {
        super(pane, mode_num);
    }

    @Override
    public void init() {
        Rectangle obj = new Rectangle(this.paneWidth/2, this.paneHeight/2);
        obj.setTranslateX(this.paneWidth/4);
        obj.setTranslateY(this.paneHeight/4);
        obj.setFill(Color.WHITE);
        obj.setStroke(Color.BLACK);
        this.pane.getChildren().add(obj);
    };
}
