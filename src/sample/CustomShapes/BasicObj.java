package sample.CustomShapes;

import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

public class BasicObj extends CustomShape {
    private Rectangle rect;

    public BasicObj(double rectWidth, double rectHeight){
        this.shapeWidth = rectWidth;
        this.shapeHeight = rectHeight;
        this.text = new Text(45, 20, "Class");
        this.rect = new Rectangle(this.shapeWidth, this.shapeHeight);
        this.rect.setTranslateX(PORT_WIDTH);
        this.rect.setTranslateY(PORT_WIDTH);
        rect.setFill(Color.TRANSPARENT);
        rect.setStroke(Color.BLACK);
        initPorts();
        this.getChildren().addAll(this.rect, this.text);
    }
}
