package com.example.englishlearningappv1.Controllers;

import com.example.englishlearningappv1.Utils.FunctionEffects;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TitlebarController {
    @FXML
    private ImageView close;
    @FXML
    private ImageView minimize;

    private final static FunctionEffects functionEffects = new FunctionEffects();

    private double offsetX;
    private double offsetY;
    @FXML
    public void closeFunction(MouseEvent mouseEvent) {
        Stage stage = (Stage) close.getScene().getWindow();

        stage.close();
    }

    @FXML
    public void minimizeFunction(MouseEvent mouseEvent) {
        Stage stage = (Stage) minimize.getScene().getWindow();

        stage.setIconified(true);
    }

    @FXML
    public void handleClickMovement(MouseEvent mouseEvent, Pane pane) {
        Stage stage = (Stage) pane.getScene().getWindow();
        offsetX = stage.getX() - mouseEvent.getScreenX();
        offsetY = stage.getY() - mouseEvent.getScreenY();
    }

    @FXML
    public void handleMovementAction(MouseEvent mouseEvent, Pane pane) {
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setX(mouseEvent.getScreenX() + offsetX);
        stage.setY(mouseEvent.getScreenY() + offsetY);
    }

    @FXML
    public void inEffects3(MouseEvent event) {
        functionEffects.inEffects3(event);
    }

    @FXML
    public void outEffects3(MouseEvent event) {
        functionEffects.outEffects3(event);
    }


}
