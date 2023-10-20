package com.example.englishlearningappv1.Controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.IOException;

public class GameHubController extends HomePageController{

    static final String quizImageURL = "src\\main\\resources\\com\\example\\englishlearningappv1\\image\\quiz-image.png";
    static final String wordleImageURL = "src\\main\\resources\\com\\example\\englishlearningappv1\\image\\wordle-image.png";

    @FXML
    private Button gameButton1;
    @FXML
    private Button gameButton2;
    @FXML
    private Button gotoGames;
    @FXML
    private ImageView imageView;
    @FXML
    private Label gameLabel;
    @FXML
    private static int currentGame = 0;

    @FXML
    public void gotoGames(ActionEvent event) throws IOException {
        SceneController sceneController = new SceneController();
        if (currentGame == 0) {
            sceneController.switchtoQuiz(event);
        }
        if (currentGame == 1) {
            sceneController.switchtoWordle(event);
        }
    }
    @FXML
    public void clickOnGame1(ActionEvent event) throws IOException {
        currentGame = 0;
        gameLabel.setText("A game where one must take their knowledge to the test.");
        Image image = new Image(new File(quizImageURL).toURI().toString());
        imageView.setImage(image);
    }
    @FXML
    public void clickOnGame2(ActionEvent event) throws IOException {
        currentGame = 1;
        gameLabel.setText("Your challenge is to guess a five-letter word in six attempts.");
        gameLabel.setTextFill(Color.WHITESMOKE);
        Image image = new Image(new File(wordleImageURL).toURI().toString());
        imageView.setImage(image);
    }

}
