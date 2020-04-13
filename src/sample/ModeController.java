package sample;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sample.CustomShapes.AssociationLine;
import sample.CustomShapes.CompositionLine;
import sample.CustomShapes.GeneralizationLine;
import sample.Modes.*;

import java.util.ArrayList;

public class ModeController {
    private static ModeController modeController;
    private Pane selectOption;
    private Pane assoLineOption;
    private Pane generalLineOption;
    private Pane compositLineOption;
    private Pane createOption;
    private Pane usecaseOption;
    private ArrayList<Mode> modes;
    private final int SELECT_MODE=0, ASSO_MODE=1, GENERAL_MODE=2, COMPOSIT_MODE=3, CREATE_MODE=4, USE_CASE_MODE=5;
    private Canvas canvas;


    public void init(Canvas canvas){
        this.canvas = canvas;
        this.modes = new ArrayList<>();
        canvas.setMode(SELECT_MODE);
    }

    private ModeController(){}
    public static ModeController getModeController() {
        if(modeController==null)
            modeController = new ModeController();
        return modeController;
    }

    public void onModeChange(int mode_num){
        for(Mode m : modes){
            if(m.getModeNum()==mode_num)
                m.setBackgroundColor(Color.DODGERBLUE);
            else
                m.setBackgroundColor(Color.WHITE);

        }
    }
    public void assignSelectOption(Pane selectOption){
        modes.add(new SelectMode(selectOption, SELECT_MODE));
    }
    public void assignAssoLineOption(Pane assoLineOption){
        modes.add(new AssoLineMode(assoLineOption, ASSO_MODE));
    }
    public void assignGeneralLineOption(Pane generalLineOption){
        modes.add(new GeneralLineMode(generalLineOption, GENERAL_MODE));
    }
    public void assignCompositOption(Pane compositLineOption){
        modes.add(new CompositLineMode(compositLineOption, COMPOSIT_MODE));
    }
    public void assignCreateOption(Pane createOption){
        modes.add(new CreateMode(createOption, CREATE_MODE));
    }
    public void assignUseCaseOption(Pane usecaseOption){
        modes.add(new UseCaseMode(usecaseOption, USE_CASE_MODE));
    }

    /*
    public void initToolBarIcons() {
        double width = this.assoLineOption.getWidth();
        double height = this.assoLineOption.getHeight();
        double startX = 0+width/10;
        double startY = height/2;
        double endX = width-width/10;
        double endY = height/2;
        initSelectOption(width-10, height-10, 10, 10);
        initAssoLineIcon(startX, startY, endX, endY);
        initGeneralLineIcon(startX, startY, endX, endY);
        initCompositLineIcon(startX, startY, endX, endY);
        initCreateOption(width/2, height/2);
    }

    private void initCreateOption(double width, double height) {
        Rectangle obj = new Rectangle(width, height);
        Rectangle background = new Rectangle(this.createOption.getWidth(), this.createOption.getHeight());
        background.setFill(Color.WHITE);
        obj.setTranslateX(width/2);
        obj.setTranslateY(height/2);
        obj.setFill(Color.WHITE);
        obj.setStroke(Color.BLACK);
        this.createOption.getChildren().add(background);
        this.createOption.getChildren().add(obj);
        this.createOption.setOnMouseClicked(mouseEvent -> {
           //mode.setCurrentMode(CREATE_MODE);
            canvas.setMode(CREATE_MODE);
        });
    }

    private void initCompositLineIcon(double startX, double startY, double endX, double endY) {
        CompositionLine arrow = new CompositionLine(startX, startY, endX, endY);
        this.compositLineOption.getChildren().add(arrow);
        this.compositLineOption.setOnMouseClicked(mouseEvent -> {
            //mode.setCurrentMode(COMPOSIT_MODE);
            canvas.setMode(COMPOSIT_MODE);
        });
    }

    private void initSelectOption(double startX, double startY, double endX, double endY) {
        GeneralizationLine arrow = new GeneralizationLine(10, startX, startY, endX, endY);
        this.selectOption.getChildren().add(arrow);
        this.selectOption.setOnMouseClicked(mouseEvent -> {
            //mode.setCurrentMode(SELECT_MODE);
            canvas.setMode(SELECT_MODE);
        });
    }


    private void initGeneralLineIcon(double startX, double startY, double endX, double endY) {
        GeneralizationLine arrow = new GeneralizationLine(10, startX, startY, endX, endY);
        this.generalLineOption.getChildren().add(arrow);
        this.generalLineOption.setOnMouseClicked(mouseEvent -> {
            //mode.setCurrentMode(GENERAL_MODE);
            canvas.setMode(GENERAL_MODE);
        });
    }

    private void initAssoLineIcon(double startX, double startY, double endX, double endY) {
        AssociationLine arrow = new AssociationLine(45, startX, startY, endX, endY);
        this.assoLineOption.getChildren().add(arrow);
        this.assoLineOption.setOnMouseClicked(mouseEvent -> {
            //mode.setCurrentMode(ASSO_MODE);
            canvas.setMode(ASSO_MODE);
        });
    }
     */
}

