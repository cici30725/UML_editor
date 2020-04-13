package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editor.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(loader.load(), 1200, 700));
        primaryStage.show();
        /*
        Circle c1 = new Circle(30);
        Circle c2 = new Circle(30);
        Group root = new Group(c1, c2);
        c1.setCenterX(100);
        c1.setCenterY(100);
        c2.setCenterX(200);
        c2.setCenterY(200);
        c1.addEventFilter(MouseEvent.DRAG_DETECTED, e -> {
            c1.startFullDrag();
            System.out.println("started");
        });
        c2.setOnMousePressed(mouseEvent -> {
            System.out.println("Pressed on c2");
        });
        c2.addEventFilter(MouseDragEvent.MOUSE_DRAG_RELEASED, e->{
           System.out.println("c2 finished");
        });
        c2.setOnMouseReleased(mouseEvent -> {
            System.out.println("released on c2");
        });
        Scene scene = new Scene(root, 1200, 700);
        scene.addEventFilter(MouseDragEvent.MOUSE_DRAG_RELEASED, e->{
            System.out.println("scene finished");
        });
        primaryStage.setScene(scene);
        primaryStage.show();
         */

        EditorController editorController = (EditorController)loader.getController();
        editorController.initMode();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
