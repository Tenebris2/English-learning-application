package com.example.englishlearningappv1.Controllers;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.sql.Time;

public class GameHubController extends HomePageController{

    static final String quizImageURL = "src\\main\\resources\\com\\example\\englishlearningappv1\\image\\quiz-image.png";
    static final String wordleImageURL = "src\\main\\resources\\com\\example\\englishlearningappv1\\image\\wordle-image.png";

    @FXML
    private AnchorPane pane1;
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
    private AnchorPane anchorPane1;
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
        Image image = new Image(new File(quizImageURL).toURI().toString());
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(imageView.opacityProperty(), 1.0)),
                new KeyFrame(Duration.seconds(0.2), new KeyValue(imageView.opacityProperty(), 0.0)),
                new KeyFrame(Duration.seconds(0.2), e -> {
                    imageView.setImage(image);
                    gameLabel.setText("A game where one must take their knowledge to the test.");
//                    pane1.setStyle(
//                            "-fx-background-color: linear-gradient(to bottom, #02022E, #36108B, BLACK);"
//                    );
//                    anchorPane1.setStyle(
//                            "-fx-background-color: linear-gradient(to bottom, #02022E, #36108B, BLACK);"
//                    );
                }),
                new KeyFrame(Duration.seconds(1), new KeyValue(imageView.opacityProperty(), 1.0))
        );
        timeline.setCycleCount(1);
        timeline.play();
    }
    @FXML
    public void clickOnGame2(ActionEvent event) throws IOException {
        currentGame = 1;
        Image image = new Image(new File(wordleImageURL).toURI().toString());
        gameLabel.setTextFill(Color.WHITESMOKE);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(imageView.opacityProperty(), 1.0)),
                new KeyFrame(Duration.seconds(0.2), new KeyValue(imageView.opacityProperty(), 0.0)),
                new KeyFrame(Duration.seconds(0.2), e -> {
                    imageView.setImage(image);
                    gameLabel.setText("Your challenge is to guess a five-letter word in six attempts.");
//                    pane1.setStyle(
//                            "-fx-background-color: linear-gradient(to bottom, #003C8C, #001D29, BLACK);"
//                    );
//                    anchorPane1.setStyle(
//                            "-fx-background-color: linear-gradient(to bottom, #003C8C, #001D29, BLACK);"
//                    );
                }),
                new KeyFrame(Duration.seconds(1), new KeyValue(imageView.opacityProperty(), 1.0))
        );
        timeline.setCycleCount(1);
        timeline.play();
    }

    private final Duration animationDuration = Duration.millis(150); // 0.15 seconds

    private void applyAnimation(TranslateTransition transition) {
        transition.setCycleCount(1);
        transition.setAutoReverse(false);
        transition.play();
    }

    @FXML
    public void inEffects3(MouseEvent event) {
        TranslateTransition transition = new TranslateTransition(animationDuration, gotoGames);
        transition.setToY(0);
        applyAnimation(transition);
        gotoGames.setStyle("-fx-background-color: #1A1A1A; -fx-text-fill: #fff; -fx-box-shadow: rgba(0, 0, 0, 0.25) 0 8px 15px;");
    }

    @FXML
    public void outEffects3(MouseEvent event) {
        TranslateTransition transition = new TranslateTransition(animationDuration, gotoGames);
        transition.setToY(0);
        applyAnimation(transition);
        gotoGames.setStyle("-fx-background-color: white; -fx-text-fill: #3B3B3B; -fx-box-shadow: none;");
    }

}
