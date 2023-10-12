package com.example.englishlearningappv1.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;

import java.io.IOException;


public class HomePageController {
    @FXML
    private Circle introductionChapterCircle;
    private SceneController sceneController = new SceneController();
    @FXML
    private Button learnButton;
    @FXML
    private Button gamesButton;
    @FXML
    private Button dictionaryButton;
    @FXML
    private Button startChapterButton;

    public void gotoHome(ActionEvent event) throws IOException {
        sceneController.switchToHomePage(event);
    }

    public void gotoDictionary(ActionEvent event) throws Exception {
        sceneController.switchtoDictionary(event);

    }

    public void gotoGames(ActionEvent event) throws IOException {
        sceneController.switchtoMPC(event);
    }

    public void gotoChapterStart(ActionEvent event) throws IOException {
        sceneController.switchToChapterStart(event);
    }
}
