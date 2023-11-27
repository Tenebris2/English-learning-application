package com.example.englishlearningappv1.Controllers;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class GameHubController extends HomePageController implements ControllerInterface {

    static final String quizImageURL = "src\\main\\resources\\com\\example\\englishlearningappv1\\image\\quiz-image.png";
    static final String wordleImageURL = "src\\main\\resources\\com\\example\\englishlearningappv1\\image\\wordle-image.png";

    @FXML
    private Button gotoGames;
    @FXML
    private ImageView imageView;
    @FXML
    private Label gameLabel;
    @FXML
    private static int currentGame;

    @FXML
    public void gotoGames(ActionEvent event) throws IOException {
        SceneController sceneController = new SceneController();
        if (currentGame == 0) {
            sceneController.switchtoQuiz(event);
        }
        if (currentGame == 1) {
            sceneController.switchtoWordle(event);
        }
        currentGame = 0;
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
    }

    @FXML
    public void outEffects3(MouseEvent event) {
        TranslateTransition transition = new TranslateTransition(animationDuration, gotoGames);
        transition.setToY(0);
        applyAnimation(transition);
    }

    private String baseStyle = "    -fx-background-color: rgba(255, 188, 231, 0.6);\n" +
            "    -fx-background-radius: 30px;\n" +
            "    -fx-border-style: solid;\n" +
            "    -fx-text-fill: white;";

    public void inEffects(MouseEvent event) {
        functionEffects.inEffects3(event, baseStyle + "-fx-border-style: none");
    }

    public void outEffects(MouseEvent event) {
        functionEffects.outEffects3(event, baseStyle);
    }
}
