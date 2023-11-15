package com.example.englishlearningappv1.Controllers;

import com.example.englishlearningappv1.GoogleTranslate;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.sql.Time;

public class GoogleTranslateController extends GoogleTranslate {
    @FXML
    private TextArea translatingTextArea;
    @FXML
    private TextArea translatedTextArea;
    @FXML
    private Label translatingLabel;
    @FXML
    private Label translatedLabel;
    @FXML
    private Button button;
    @FXML
    private ImageView imageView;
    private Timeline translationTimeline = new Timeline();

    private static String langFrom;
    private static String langTo;

    public void translateFunction(KeyEvent event) {// Initialize the translation timeline
        // Reset the timeline on every key typed
        if (langFrom == null) {
            langFrom = "en";
        }
        if (langTo == null) {
            langTo = "vi";
        }
        translationTimeline.stop();
        // Start the timeline to wait for a pause
        translationTimeline.getKeyFrames().setAll(
                new KeyFrame(Duration.seconds(0.5), e -> {
                    // Perform translation logic here
                    translatedTextArea.setText(translate(translatingTextArea.getText(), langFrom, langTo));
                })
        );
        translationTimeline.playFromStart();
    }
    public void inEffects2(MouseEvent event) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(150), imageView);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.7);
        fadeTransition.setCycleCount(0);
        fadeTransition.setAutoReverse(true);
        ParallelTransition parallelTransition = new ParallelTransition(fadeTransition);
        parallelTransition.play();
    }

    public void outEffects2(MouseEvent event) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(150), imageView);
        fadeTransition.setFromValue(0.7);
        fadeTransition.setToValue(1);
        fadeTransition.setCycleCount(0);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();
        ParallelTransition parallelTransition = new ParallelTransition(fadeTransition);
        parallelTransition.play();
    }

    public void changeTranslationDirection(MouseEvent event) {

        if (langFrom == "en") {
            translatedLabel.setText("English");
        } else {
            translatedLabel.setText("Vietnamese");
        }
        if (langTo == "vi") {
            translatingLabel.setText("Vietnamese");
        } else {
            translatingLabel.setText("English");
        }

        String tmp = langTo;
        langTo = langFrom;
        langFrom = tmp;
    }

}
