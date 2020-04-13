package sample.CustomShapes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class CompositionLine extends CustomLine{
    private Line mainLine;
    private Polygon arrowhead;
    private double strokeWidth, arrowheadWidth;
    public CompositionLine(double startX, double startY, double endX, double endY)
    {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.strokeWidth = 3.0f;
        this.arrowheadWidth = 5;

        mainLine = new Line(startX, startY, endX, endY);
        arrowhead = new Polygon();

        mainLine.setStrokeWidth(this.strokeWidth);
        arrowhead.setFill(Color.WHITE);
        arrowhead.setStroke(Color.BLACK);
        updateArrow(startX, startY, endX, endY);
        this.getChildren().addAll(this.mainLine, this.arrowhead);
    }

    public void updateArrow(double startX, double startY, double endX, double endY) {
        // setting the line behind the arrow
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;

        double dx = endX - startX;
        double dy = endY - startY;
        double magnitude = Math.sqrt(dx * dx + dy * dy);
        dx /= magnitude;
        dy /= magnitude;
        double headEnd_X = endX;
        double headEnd_Y = endY;
        double headMid_X = headEnd_X - dx * (arrowheadWidth * Math.sqrt(3));
        double headMid_Y = headEnd_Y - dy * (arrowheadWidth * Math.sqrt(3));
        mainLine.setStartX(startX);
        mainLine.setStartY(startY);
        mainLine.setEndX(headMid_X);
        mainLine.setEndY(headMid_Y);

        double head_p1_x = headMid_X - dy * arrowheadWidth;
        double head_p1_y = headMid_Y + dx * arrowheadWidth;
        double head_p2_x = headMid_X + dy * arrowheadWidth;
        double head_p2_y = headMid_Y - dx * arrowheadWidth;
        double head_p3_x = headMid_X - dx * (arrowheadWidth * Math.sqrt(3));
        double head_p3_y = headMid_Y - dy * (arrowheadWidth * Math.sqrt(3));
        arrowhead.getPoints().clear();
        arrowhead.getPoints().addAll(new Double[]{
                headEnd_X, headEnd_Y,
                head_p1_x, head_p1_y,
                head_p3_x, head_p3_y,
                head_p2_x, head_p2_y,
        });
    }
}
