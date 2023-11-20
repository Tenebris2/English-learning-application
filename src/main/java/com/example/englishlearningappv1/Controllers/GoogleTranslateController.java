package com.example.englishlearningappv1.Controllers;

import com.example.englishlearningappv1.GoogleTranslate;
import com.example.englishlearningappv1.Utils.BackgroundEffects;
import com.example.englishlearningappv1.Utils.FunctionEffects;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
    @FXML
    private ComboBox<String> translatingChoiceBox;
    @FXML
    private ComboBox<String> translatedChoiceBox;
    private static String langFrom;
    private static String langTo;
    private Map<String, String> languageCodes = new HashMap<>();
    private Map<String, Integer> languageCodesIndex = new HashMap<>();

    @FXML
    public void initialize() {
            langFrom = "en";
            langTo = "vi";

            // Populate the HashMap with language codes
            languageCodes.put("English", "en");
            languageCodes.put("Chinese", "zh");
            languageCodes.put("French", "fr");
            languageCodes.put("German", "de");
            languageCodes.put("Indonesian", "id");
            languageCodes.put("Vietnamese", "vi");

            int i = 0;
            for (String key : languageCodes.keySet()) {
                languageCodesIndex.put(languageCodes.get(key), i);
                i++;
            }

            ArrayList<String> arrayList = new ArrayList<>(languageCodes.keySet());
            BackgroundEffects backgroundEffects = new BackgroundEffects();
            backgroundEffects.backgroundEffects(mainPane, 500);
            backgroundEffects.shootingStarsEffect(mainPane);

            translatingChoiceBox.setItems(FXCollections.observableArrayList(arrayList));
            translatedChoiceBox.setItems(FXCollections.observableArrayList(arrayList));

            translatingChoiceBox.getSelectionModel().select(languageCodesIndex.get(langFrom));
            translatedChoiceBox.getSelectionModel().select(languageCodesIndex.get(langTo));


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
        String tmp = langTo;
        langTo = langFrom;
        langFrom = tmp;

        String tmpTo = langTo;
        String tmpFrom = langFrom;

        translatingChoiceBox.getSelectionModel().select(languageCodesIndex.get(tmpTo));
        translatedChoiceBox.getSelectionModel().select(languageCodesIndex.get(tmpFrom));
    }

    @FXML
    public void setLangFrom(ActionEvent event) {
        langFrom = languageCodes.get(translatingChoiceBox.getValue());
        System.out.println(translatingChoiceBox.getValue());
    }
    @FXML
    public void setLangTo(ActionEvent event) {
        langTo = languageCodes.get(translatedChoiceBox.getValue());
    }

}
