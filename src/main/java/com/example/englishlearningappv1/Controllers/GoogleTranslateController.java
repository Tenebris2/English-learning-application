package com.example.englishlearningappv1.Controllers;

import com.example.englishlearningappv1.GoogleTranslate;
import com.example.englishlearningappv1.Utils.BackgroundEffects;
import com.example.englishlearningappv1.Utils.FunctionEffects;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.sql.Time;
import java.util.Objects;

import static com.example.englishlearningappv1.GoogleTranslate.translate;

public class GoogleTranslateController extends HomePageController{
    @FXML
    private TextArea translatingTextArea;
    @FXML
    private TextArea translatedTextArea;
    @FXML
    private Label translatingLabel;
    @FXML
    private Label translatedLabel;
    @FXML
    private AnchorPane container;
    @FXML
    private Button button;
    @FXML
    private ImageView imageView;
    private Timeline translationTimeline = new Timeline();
    @FXML
    private AnchorPane mainPane;

    private static String langFrom;
    private static String langTo;

    @FXML
    public void initialize() {
            langFrom = "en";
            langTo = "vi";
            BackgroundEffects backgroundEffects = new BackgroundEffects();
            backgroundEffects.backgroundEffects(mainPane, 100);
            backgroundEffects.shootingStarsEffect(mainPane);
    }
    public void translateFunction(KeyEvent event) {// Initialize the translation timeline
        // Reset the timeline on every key typed
        translationTimeline.stop();
        // Start the timeline to wait for a pause
        translationTimeline.getKeyFrames().setAll(
                new KeyFrame(Duration.seconds(0.25), e -> {
                    // Perform translation logic here
                    translatedTextArea.setText(translate(translatingTextArea.getText(), langFrom, langTo));
                })
        );
        translationTimeline.playFromStart();
    }
    public void inEffects2(MouseEvent event) {
        FunctionEffects functionEffects = new FunctionEffects();
        functionEffects.inEffects3(event);
    }

    public void outEffects2(MouseEvent event) {
        FunctionEffects functionEffects = new FunctionEffects();
        functionEffects.outEffects3(event);
    }

    public void changeTranslationDirection(MouseEvent event) {

        if (Objects.equals(langFrom, "en")) {
            translatedLabel.setText("English");
        } else {
            translatedLabel.setText("Vietnamese");
        }
        if (Objects.equals(langTo, "vi")) {
            translatingLabel.setText("Vietnamese");
        } else {
            translatingLabel.setText("English");
        }

        String tmp = langTo;
        langTo = langFrom;
        langFrom = tmp;
    }

}
