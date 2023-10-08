package com.example.demo2;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class MainController {

    private final MainFunction mainFunction = MainFunction.getInstance();

    @FXML
    public GridPane gridPane;
    @FXML
    public GridPane keyboardRow1;
    @FXML
    public GridPane keyboardRow2;
    @FXML
    public GridPane keyboardRow3;
    @FXML
    public HBox titleHBox;
    @FXML
    public ImageView restartIcon;

    public void createUI() {
        createGrid();
        createKeyboard();
        createTitleHBox();
    }

    public void createGrid() {
        mainFunction.createGrid(gridPane);
    }

    public void createKeyboard() {
        mainFunction.createKeyboard(keyboardRow1, keyboardRow2, keyboardRow3);
    }

    public void gridRequestFocus() {
        gridPane.requestFocus();
    }

    @FXML
    protected void onKeyPressed(KeyEvent keyEvent) {
        mainFunction.onKeyPressed(gridPane, keyboardRow1, keyboardRow2, keyboardRow3, keyEvent);
    }

    public void getRandomWord() {
        mainFunction.getRandomWord();
    }

    public void createTitleHBox() {
        mainFunction.createTitleHBox(titleHBox);
    }

    public void restart() {
        mainFunction.restart(restartIcon, gridPane, keyboardRow1, keyboardRow2, keyboardRow3);
    }

}
