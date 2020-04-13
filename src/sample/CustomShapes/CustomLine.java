package sample.CustomShapes;

import javafx.scene.Group;

public abstract class CustomLine extends Group {
    protected double startX, startY, endX, endY;
    public abstract void updateArrow(double startX, double startY, double endX, double endY);
    public void updateArrowEnd(double endX, double endY){
        updateArrow(this.startX, this.startY, endX, endY);
    }
    public void updateArrowStart(double startX, double startY){
        updateArrow(startX, startY, this.endX, this.endY);
    }
}
