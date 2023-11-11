package com.example.englishlearningappv1.Controllers;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;


public class HomePageController {

    enum Buttons {
        startChapterButton,
        getHelloChapterButton,
        getSchoolChapterButton
    }

    private boolean clicked;
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
    @FXML
    private AnchorPane popupPane;

    @FXML
    private Button startingButton;

    @FXML
    private Button getHelloChapterButton;

    @FXML
    private Button getSchoolChapterButton;
    @FXML
    private Label logoText;

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

        Button source = (Button) event.getSource();
        initLabel.setText(source.getText());
        displayButton.setText(source.getText());

        popupPane.setLayoutX(source.getLayoutX() - 25);
        popupPane.setLayoutY(source.getLayoutY() + 135);


        double popupPanePrefHeight = 118;
        double popupPanePrefWidth = 207;

        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.5), popupPane);

        popupPane.setOpacity(1);

        double move = 0;

        String id = source.getId();
        if (!clicked) {

            // Set the scaling factors
            scaleTransition.setToX(1.0);
            scaleTransition.setToY(1.0);
            move = 0;
        } else {

            // Set the scaling factors
            scaleTransition.setToX(0);
            scaleTransition.setToY(0);
            move = -popupPane.getPrefHeight() * 0.5;
        }

        // Play the animation
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), popupPane);
        translateTransition.setToY(move); // Move the full height upwards

        // Create a ParallelTransition to play both animations in parallel
        ParallelTransition parallelTransition = new ParallelTransition(scaleTransition, translateTransition);

        clicked  = !clicked;

        if (clicked) {
            // Set opacity to 1 (visible) when expanding
            parallelTransition.setOnFinished(e -> popupPane.setOpacity(1));
        } else {
            // Set opacity to 0 (invisible) when shrinking
            parallelTransition.setOnFinished(e -> popupPane.setOpacity(0));
        }

        // Play the animation
        parallelTransition.play();

    }
}
