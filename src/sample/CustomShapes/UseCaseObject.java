package sample.CustomShapes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class UseCaseObject extends CustomShape {
    Circle circle;

    public UseCaseObject(double radius){
        circle = new Circle(radius);
        this.shapeWidth = circle.getRadius()*2;
        this.shapeHeight = circle.getRadius()*2;
        this.text = new Text(shapeWidth/2-10, shapeHeight/2, "Use Case");
        System.out.println("width ,height "+shapeWidth+" "+shapeHeight);
        this.circle.setTranslateX(PORT_WIDTH+shapeWidth/2);
        this.circle.setTranslateY(PORT_WIDTH+shapeHeight/2);
        this.circle.setFill(Color.TRANSPARENT);
        this.circle.setStroke(Color.BLACK);

        initPorts();
        this.getChildren().addAll(this.circle, this.text);
    }
}
