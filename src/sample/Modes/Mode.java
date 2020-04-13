package sample.Modes;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sample.Canvas;
import sample.ModeController;

public abstract class Mode {
    protected double paneWidth, paneHeight;
    protected int MODE_NUM;
    protected Pane pane;
    protected Canvas canvas = Canvas.getCanvas();
    protected Rectangle background;
    public Mode(Pane pane, int mode_num){
        this.paneWidth = pane.getWidth();
        this.paneHeight = pane.getHeight();
        this.MODE_NUM = mode_num;
        this.pane = pane;
        this.background = new Rectangle(this.paneWidth, this.paneHeight);
        background.setFill(Color.WHITE);
        background.setStroke(Color.BLACK);
        this.pane.getChildren().add(background);
        this.pane.setOnMouseClicked(mouseEvent -> {
            canvas.setMode(MODE_NUM);
            ModeController.getModeController().onModeChange(this.MODE_NUM);
        });
        init();
    }
    public int getModeNum(){
        return this.MODE_NUM;
    }
    public void setBackgroundColor(Color color) {
        this.background.setFill(color);
    }
    protected abstract void init();

}
