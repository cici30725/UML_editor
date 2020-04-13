package sample.CustomShapes;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class GeneralizationLine extends CustomLine{
    private Line mainLine;
    private Polygon arrowhead;
    private double arrowheadWidth;
    public GeneralizationLine(double arrowheadWidth, double startX, double startY, double endX, double endY){
        this.arrowheadWidth = arrowheadWidth;

        mainLine = new Line();
        arrowhead = new Polygon();

        mainLine.setStrokeWidth(3.0f);
        arrowhead.setFill(Color.WHITE);
        arrowhead.setStroke(Color.BLACK);

        updateArrow(startX, startY, endX, endY);
        this.getChildren().addAll(mainLine, arrowhead);
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
        double headStart_X = headEnd_X - dx * (arrowheadWidth * Math.sqrt(3));
        double headStart_Y = headEnd_Y - dy * (arrowheadWidth * Math.sqrt(3));
        mainLine.setStartX(startX);
        mainLine.setStartY(startY);
        mainLine.setEndX(headStart_X);
        mainLine.setEndY(headStart_Y);

        double head_p1_x = headStart_X - dy * arrowheadWidth;
        double head_p1_y = headStart_Y + dx * arrowheadWidth;
        double head_p2_x = headStart_X + dy * arrowheadWidth;
        double head_p2_y = headStart_Y - dx * arrowheadWidth;
        arrowhead.getPoints().clear();
        arrowhead.getPoints().addAll(new Double[]{
                headEnd_X, headEnd_Y,
                head_p1_x, head_p1_y,
                head_p2_x, head_p2_y,
        });
    }
}
