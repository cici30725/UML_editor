package sample;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sample.CustomShapes.BasicObj;
import sample.CustomShapes.CustomShape;
import sample.CustomShapes.Port;
import sample.CustomShapes.UseCaseObject;

import javax.swing.*;
import java.util.ArrayList;

public class Canvas {
    private static Canvas instance = null;
    private AnchorPane canvas;
    private Rectangle dragBox;

    private ArrayList<CustomShape> shapeList;
    private double mouseDownX, mouseDownY;
    private Port startPort, endPort;
    private final int SELECT_MODE=0, ASSO_MODE=1, GENERAL_MODE=2, COMPOSIT_MODE=3, CREATE_MODE=4, USE_CASE_MODE=5;

    private Canvas(){}

    public static Canvas getCanvas(){
        if(instance==null)
            instance = new Canvas();
        return instance;
    }

    public void init(AnchorPane canvas){
        this.canvas = canvas;
        this.shapeList = new ArrayList<CustomShape>();
        dragBox = new Rectangle(0,0,0,0);
        dragBox.setFill(Color.TRANSPARENT);
        dragBox.setStroke(Color.BLACK);
        dragBox.setVisible(false);
        addObjectToCanvas(dragBox);
    }

    public void addObjectToCanvas(Node obj){
        this.canvas.getChildren().add(obj);
    }

    public void removeObjectFromCanvas(Node obj){
        this.canvas.getChildren().remove(obj);
    }

    public void setMode(int mode){
        switch(mode){
            case SELECT_MODE:
               setSelectMode();
               break;
            case ASSO_MODE:
                setAssoMode();
                break;
            case COMPOSIT_MODE:
                setCompositMode();
                break;
            case GENERAL_MODE:
                setGeneralMode();
                break;
            case CREATE_MODE:
                setCreateMode();
                break;
            case USE_CASE_MODE:
                setUseCaseMode();
                break;
            default:
                System.out.println("No such mode");
                break;
        }
    }


    private void clearMouseEvents(){
        canvas.setOnMousePressed(null);
        canvas.setOnMouseDragged(null);
        canvas.setOnMouseReleased(null);
        canvas.setOnMouseClicked(null);
        canvas.setOnMouseDragReleased(null);
    }

    private void clearShapeEvents(){
        for(CustomShape shape : shapeList)
            shape.unregisterMouseEvents();
    }

    private void setUseCaseMode() {
        clearMouseEvents();
        clearShapeEvents();
        canvas.setOnMouseClicked(mouseEvent -> {
            System.out.println("Now creating use case object");
            UseCaseObject obj = new UseCaseObject(50);
            obj.setTranslateX(mouseEvent.getX());
            obj.setTranslateY(mouseEvent.getY());
            this.addObjectToCanvas(obj);
            this.shapeList.add(obj);
        });
    }

    private void setCreateMode(){
        clearMouseEvents();
        clearShapeEvents();
        canvas.setOnMouseClicked(mouseEvent -> {
            System.out.println("Now creating object");
            BasicObj obj = new BasicObj(100, 200);
            obj.setTranslateX(mouseEvent.getX());
            obj.setTranslateY(mouseEvent.getY());
            this.addObjectToCanvas(obj);
            this.shapeList.add(obj);
        });
    }

    private void setGeneralMode() {
        clearMouseEvents();
        clearShapeEvents();
        for(CustomShape shape : shapeList)
            shape.registerGeneralModeEvents();
        this.canvas.setOnMouseReleased(this.lineModeMouseReleaseHandler);
    }

    private void setCompositMode() {
        clearMouseEvents();
        clearShapeEvents();
        for(CustomShape shape : shapeList)
            shape.registerCompositModeEvents();
        this.canvas.setOnMouseReleased(this.lineModeMouseReleaseHandler);

    }

    private void setAssoMode() {
        clearMouseEvents();
        clearShapeEvents();
        for(CustomShape shape : shapeList)
            shape.registerAssoModeEvents();
        this.canvas.setOnMouseReleased(this.lineModeMouseReleaseHandler);

    }

    private void setSelectMode() {
        clearMouseEvents();
        clearShapeEvents();
        for(CustomShape shape : shapeList)
            shape.registerSelectModeEvents();

        canvas.setOnMousePressed(mouseEvent -> {
            mouseDownX = mouseEvent.getX();
            mouseDownY = mouseEvent.getY();
            dragBox.setX(mouseDownX);
            dragBox.setY(mouseDownY);
            dragBox.setVisible(true);
            dragBox.setWidth(0);
            dragBox.setHeight(0);
            for(CustomShape shape : shapeList)
                    shape.setUnselect();
        });

        canvas.setOnMouseDragged(mouseEvent -> {
            if(dragBox.isVisible()) {
                dragBox.setX(Math.min(mouseDownX, mouseEvent.getX()));
                dragBox.setWidth(Math.abs(mouseDownX - mouseEvent.getX()));
                dragBox.setY(Math.min(mouseDownY, mouseEvent.getY()));
                dragBox.setHeight(Math.abs(mouseDownY - mouseEvent.getY()));
                Bounds parentBound = dragBox.getBoundsInParent();
                for(CustomShape shape : shapeList){
                    if(parentBound.contains(shape.getBounds()))
                        shape.setSelect();
                    else
                        shape.setUnselect();
                }
            }
        });
        canvas.setOnMouseReleased(mouseEvent -> {
            dragBox.setVisible(false);
        });
    }

    EventHandler<MouseEvent> lineModeMouseReleaseHandler =
            e -> {
                System.out.println("canvas release");
                // Remove Line from start port
                startPort=null;
            };

    public void setStartPort(Port p){
        this.startPort = p;
    }
    public Port getStartPort() {return this.startPort;}

    public void onGroup(){
        System.out.println("Grouping elements");
        Group g = new Group();
        for(CustomShape shape : shapeList){
            if(shape.isSelected()==true) {
                g.addGroupMember(shape);
                shape.joinGroup(g);
            }
        }
    }
    public void onUnGroup(){
        System.out.println("Ungrouping elements");
        for(CustomShape shape : shapeList){
            if(shape.isSelected()==true) {
                Group curGroup = shape.getGroup();
                if(curGroup!=null) {
                    curGroup.removeGroupMember(shape);
                    shape.leaveCurrentGroup();
                }
            }
        }
    }

    public ArrayList<CustomShape> getShapeList() {
        return shapeList;
    }

    public void onChangeName(String text) {
            for(CustomShape shape : shapeList) {
                if(shape.isSelected())
                    shape.setText(text);
            }
    }
}
