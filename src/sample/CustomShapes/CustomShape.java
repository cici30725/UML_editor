package sample.CustomShapes;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import sample.Canvas;
import sample.Group;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class CustomShape extends Pane {

    protected ArrayList<Port> ports;
    protected Stack<Group> groupTree = new Stack<>();
    protected double orgSceneX, orgSceneY, orgTranslateX, orgTranslateY;
    protected double[] coordinates;
    protected final double PORT_WIDTH=10;
    protected boolean isSelected = false;
    protected double shapeWidth, shapeHeight;
    protected Text text;
    EventHandler<MouseEvent> objOnMouseClickedEventHandler = event -> {
        if(event.isStillSincePress()) {
            event.consume();
            isSelected ^= true;
            for(CustomShape shape : Canvas.getCanvas().getShapeList())
            {
                if(shape!=this)
                    shape.setUnselect();
            }
            if(isSelected)
                setSelect();
            else
                setUnselect();
        }
    };

    EventHandler<MouseEvent> objOnMousePressedEventHandler = event -> {
        event.consume();
        orgSceneX = event.getSceneX();
        orgSceneY = event.getSceneY();
        Group g = this.getGroup();
        if(g!=null){
            for(CustomShape shape : this.getGroup().getGroupMembers())
                shape.saveCurrentPosition();
        }
        else
            this.saveCurrentPosition();
    };

    EventHandler<MouseEvent> objOnMouseDraggedEventHandler = event -> {
        event.consume();
        double offsetX = event.getSceneX() - orgSceneX;
        double offsetY = event.getSceneY() - orgSceneY;
        Group g = this.getGroup();
        if(g!=null){
            System.out.println("Updating group members");
            for(CustomShape shape : this.getGroup().getGroupMembers())
                shape.updateShapePositon(offsetX, offsetY);
        }
        else
            this.updateShapePositon(offsetX, offsetY);
    };

    public void updateShapePositon(double offsetX, double offsetY){
        double newTranslateX = this.orgTranslateX + offsetX;
        double newTranslateY = this.orgTranslateY + offsetY;

        this.setTranslateX(newTranslateX);
        this.setTranslateY(newTranslateY);

        // Iterating over obj ports and adjust lines connecting those ports
        for(int i=0; i<ports.size(); i++){
            Port p = ports.get(i);
            if(p.getLine()==null)
                continue;
            if(p.getIsLineStart())
                p.getLine().updateArrowStart(coordinates[2 * i] + newTranslateX, coordinates[2 * i + 1] + newTranslateY);
            else
                p.getLine().updateArrowEnd(coordinates[2 * i] + newTranslateX, coordinates[2 * i + 1] + newTranslateY);
        }
    }
    public void saveCurrentPosition(){
        this.orgTranslateX = this.getTranslateX();
        this.orgTranslateY = this.getTranslateY();
    }


    public void setUnselect() {
        this.isSelected = false;
        for(Port p : ports)
            p.setFill(Color.WHITE);
    }

    public boolean isSelected(){
        return this.isSelected;
    }

    public Bounds getBounds() {
        return this.getBoundsInParent();
    }

    public void setSelect(){
        this.isSelected = true;
        for(Port p : ports)
            p.setFill(Color.BLACK);
    }

    public void registerSelectModeEvents() {
        // Registering events in select mode so that the object could move
        this.setOnMousePressed(objOnMousePressedEventHandler);
        this.setOnMouseDragged(objOnMouseDraggedEventHandler);
        this.setOnMouseClicked(objOnMouseClickedEventHandler);
    }

    public void registerAssoModeEvents() {
        for(Port port : ports)
            port.registerAssoModeEvents();
    }

    public void registerCompositModeEvents() {
        for(Port port : ports)
            port.registerCompositModeEvents();
    }

    public void registerGeneralModeEvents() {
        for(Port port : ports)
            port.registerGeneralModeEvents();
    }

    protected void addChild(Node n)
    {
        this.getChildren().add(n);
    }

    public void unregisterMouseEvents() {
        this.setUnselect();
        this.setOnMousePressed(null);
        this.setOnMouseDragged(null);
        this.setOnMouseClicked(null);
        for(Port p : ports)
            p.unregisterMouseEvents();
    }

    protected void initPorts()
    {
        ports = new ArrayList<>();
        double x_mid = this.shapeWidth/2-PORT_WIDTH/2;
        double y_mid = this.shapeHeight/2-PORT_WIDTH/2;
        // ugly code here
        // top, bot, left, right
        double[] coordinates = {x_mid+10, 0, x_mid+10, this.shapeHeight+PORT_WIDTH, 0, y_mid+10, this.shapeWidth+PORT_WIDTH, y_mid+10};
        System.out.println(Arrays.toString(coordinates));
        this.coordinates = coordinates;
        for(int i=0; i<coordinates.length; i+=2){
            Port p = new Port(PORT_WIDTH, PORT_WIDTH);
            ports.add(p);
            addChild(p);
            p.setTranslateX(coordinates[i]);
            p.setTranslateY(coordinates[i+1]);
            p.setFill(Color.WHITE);
        }
    }

    public Group getGroup() {
        if(groupTree.isEmpty())
            return null;
        else
            return groupTree.peek();
    }
    public void joinGroup(Group g){
        groupTree.push(g);
    }
    public void leaveCurrentGroup(){
        groupTree.pop();
    }

    public void setText(String text) {
        this.text.setText(text);
    }
}
