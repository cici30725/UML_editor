package sample.CustomShapes;

import javafx.scene.Group;
import javafx.scene.shape.Line;


public class AssociationLine extends CustomLine {
    private Line mainLine, arrow1, arrow2;
    private double degree;
    private double strokeWidth;
    public AssociationLine(double degree, double startX, double startY, double endX, double endY){
        this.strokeWidth = 3.0f;
        this.degree = degree;

        mainLine = new Line(startX, startY, endX, endY);
        arrow1 = new Line();
        arrow2 = new Line();

        mainLine.setStrokeWidth(strokeWidth);
        arrow2.setStrokeWidth(strokeWidth);
        arrow1.setStrokeWidth(strokeWidth);

        updateArrow(startX, startY, endX, endY);
        this.getChildren().addAll(mainLine, arrow1, arrow2);
    }

    public void updateArrow(double startX, double startY, double endX, double endY) {
        // Save coordinate changes
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        mainLine.setStartX(this.startX);
        mainLine.setStartY(this.startY);
        mainLine.setEndX(this.endX);
        mainLine.setEndY(this.endY);

        // Draw two arrows
        double factor = 20;
        double dx = startX - endX;
        double dy = startY - endY;
        double magnitude  = Math.sqrt(dx*dx+dy*dy);
        dx/=magnitude;
        dy/=magnitude;
        dx*=factor;
        dy*=factor;
        double new_endX_1 = endX + dx*Math.cos(Math.toRadians(this.degree)) - dy*Math.sin(Math.toRadians(this.degree));
        double new_endY_1 = endY + dx*Math.sin(Math.toRadians(this.degree)) + dy*Math.cos(Math.toRadians(this.degree));
        double new_endX_2 = endX + dx*Math.cos(Math.toRadians(-this.degree)) - dy*Math.sin(Math.toRadians(-this.degree));
        double new_endY_2 = endY + dx*Math.sin(Math.toRadians(-this.degree)) + dy*Math.cos(Math.toRadians(-this.degree));

        arrow1.setStartX(endX);
        arrow1.setStartY(endY);
        arrow1.setEndX(new_endX_1);
        arrow1.setEndY(new_endY_1);

        arrow2.setStartX(endX);
        arrow2.setStartY(endY);
        arrow2.setEndX(new_endX_2);
        arrow2.setEndY(new_endY_2);
    }
}
