package sample.CustomShapes;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import sample.Canvas;

public class Port extends Rectangle  {
    private CustomLine line;
    private boolean isLineStart;
    public Port(double width, double height){
        super(width, height);
    }

    public void unregisterMouseEvents() {
        this.setOnDragDetected(null);
        this.setOnMouseDragReleased(null);
    }

    public CustomLine getLine() {
        return this.line;
    }
    public boolean getIsLineStart(){return this.isLineStart;}

    public void registerSelectModeEvents() {
        unregisterMouseEvents();
    }

    public void registerAssoModeEvents() {

        this.setOnDragDetected(e->{
                Node source = (Node) e.getSource();
                System.out.println("start dragging");
                double x = e.getSceneX() - 137.6;
                double y = e.getSceneY() - 27.2;
                line = new AssociationLine(45, x, y, x, y);
                Canvas.getCanvas().setStartPort(this);
                this.isLineStart = true;
                source.startFullDrag();
        });

        this.setOnMouseDragReleased(this.mouseReleaseHandler);

        /*
        this.setOnMouseDragged(mouseEvent -> {
            // Hard coded coordinates relative to Canvas
            double x = mouseEvent.getSceneX()-137.6;
            double y = mouseEvent.getSceneY()-27.2;
            if(this.line!=null)
                this.line.updateArrowEnd(x, y);
        });
         */
    }

    public void registerCompositModeEvents() {

        this.setOnDragDetected(e->{
            Node source = (Node) e.getSource();
            System.out.println("start dragging");
            double x = e.getSceneX() - 137.6;
            double y = e.getSceneY() - 27.2;
            line = new CompositionLine(x, y, x, y);
            Canvas.getCanvas().setStartPort(this);
            this.isLineStart = true;
            source.startFullDrag();
        });

        this.setOnMouseDragReleased(this.mouseReleaseHandler);
    }

    public void registerGeneralModeEvents() {
        this.setOnDragDetected(e->{
            Node source = (Node) e.getSource();
            System.out.println("start dragging");
            double x = e.getSceneX() - 137.6;
            double y = e.getSceneY() - 27.2;
            line = new GeneralizationLine(5, x, y, x, y);
            this.isLineStart = true;
            Canvas.getCanvas().setStartPort(this);
            source.startFullDrag();
        });

        this.setOnMouseDragReleased(this.mouseReleaseHandler);
    }


    EventHandler<MouseEvent> mouseReleaseHandler =
           e -> {
               e.consume();
               System.out.println("relesed on node");
               double x = e.getSceneX()-137.6;
               double y = e.getSceneY()-27.2;
               Port startPort = Canvas.getCanvas().getStartPort();
               if(startPort!=null) {
                   System.out.println("Added line to end point node");
                   CustomLine line = Canvas.getCanvas().getStartPort().getLine();
                   Canvas.getCanvas().addObjectToCanvas(line);
                   line.updateArrowEnd(x, y);
                   this.line = line;
                   this.isLineStart = false;
                   Canvas.getCanvas().setStartPort(null);
               }
           };
}
