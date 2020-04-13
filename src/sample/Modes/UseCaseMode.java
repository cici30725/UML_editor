package sample.Modes;


import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class UseCaseMode extends Mode{
    public UseCaseMode(Pane pane, int mode_num)
    {
        super(pane, mode_num);
        this.MODE_NUM = mode_num;
    }
    @Override
    protected void init() {
        Circle circle = new Circle(paneWidth/2, paneHeight/2, paneWidth/4);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        pane.getChildren().add(circle);
    }
}
