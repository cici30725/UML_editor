package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import sample.CustomShapes.BasicObj;


import java.net.URL;
import java.util.ResourceBundle;

public class EditorController implements Initializable {

    private ModeController mode;
    private Canvas canvas_wrap;
    @FXML
    private MenuItem newItem;
    @FXML
    private AnchorPane canvas;
    @FXML
    private Pane selectOption;
    @FXML
    private Pane assoLineOption;
    @FXML
    private Pane generalLineOption;
    @FXML
    private Pane compositLineOption;
    @FXML
    private Pane createOption;
    @FXML
    private Pane usecaseOption;

    TextInputDialog td;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Now initializing");
        canvas_wrap = Canvas.getCanvas();
        canvas_wrap.init(canvas);
        mode = ModeController.getModeController();
        mode.init(canvas_wrap);
        td = new TextInputDialog();
    }

    public void initMode(){
        // Initialize options and toolbar icons
        mode.assignAssoLineOption(assoLineOption);
        mode.assignCompositOption(compositLineOption);
        mode.assignGeneralLineOption(generalLineOption);
        mode.assignSelectOption(selectOption);
        mode.assignUseCaseOption(usecaseOption);
        mode.assignCreateOption(createOption);
    }

    public void groupElements(ActionEvent actionEvent) {
        canvas_wrap.onGroup();
    }

    public void UnGroupElements(ActionEvent actionEvent) {
        canvas_wrap.onUnGroup();
    }

    public void changeObjectName(ActionEvent actionEvent) {
        td.showAndWait();
        canvas_wrap.onChangeName(td.getEditor().getText());
    }
}
