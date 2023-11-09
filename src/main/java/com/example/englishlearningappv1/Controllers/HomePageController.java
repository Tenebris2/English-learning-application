package com.example.englishlearningappv1.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
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
    @FXML
    private Button displayButton;
    @FXML
    private Label initLabel;

    public void gotoHome(ActionEvent event) throws IOException {
        sceneController.switchToHomePage(event);
    }

    public void gotoDictionary(ActionEvent event) throws Exception {
        sceneController.switchtoDictionary(event);

    }
    public void gotoChapterStart(ActionEvent event) throws IOException {
        sceneController.switchToChapterStart(event);
    }

    public void gotoQuiz(ActionEvent event) throws IOException {
        sceneController.switchtoQuiz(event);
    }

    public void gotoWordle(ActionEvent event) throws IOException {
        sceneController.switchtoWordle(event);
    }

    public void gotoGameHub(ActionEvent event) throws IOException {
        sceneController.switchToGameHub(event);
    }

    public void initLabel(ActionEvent event) {
        Object source = event.getSource();

        if (source instanceof Button) {
            Button clickedButton = (Button) source;
            initLabel = new Label(clickedButton.getText());

            VBox vBox = new VBox(10);
            vBox.getChildren().add(initLabel);
        }
    }
}
