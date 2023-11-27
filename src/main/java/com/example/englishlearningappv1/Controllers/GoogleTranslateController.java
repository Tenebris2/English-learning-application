package com.example.englishlearningappv1.Controllers;

import com.example.englishlearningappv1.API.APIController;
import com.example.englishlearningappv1.API.API_KEY;
import com.example.englishlearningappv1.STT.microphone.Microphone;
import com.example.englishlearningappv1.STT.recognizer.GSpeechDuplex;
import com.example.englishlearningappv1.STT.recognizer.GSpeechResponseListener;
import com.example.englishlearningappv1.STT.recognizer.GoogleResponse;
import com.example.englishlearningappv1.Utils.BackgroundEffects;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import net.sourceforge.javaflacencoder.FLACFileWriter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GoogleTranslateController extends HomePageController implements ControllerInterface {
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
    private Button ttsButton;
    @FXML
    private Button button;
    @FXML
    private ImageView imageView;
    private Timeline translationTimeline = new Timeline();
    @FXML
    private ComboBox<String> translatingChoiceBox;
    @FXML
    private ComboBox<String> translatedChoiceBox;
    private static String langFrom;
    private static String langTo;
    private Map<String, String> languageCodes = new HashMap<>();
    private Map<String, Integer> languageCodesIndex = new HashMap<>();

    public static Microphone mic = new Microphone(FLACFileWriter.FLAC);
    private GSpeechDuplex duplex = new GSpeechDuplex(API_KEY.getAPIKey("stt-api-key").getKey());

    public GoogleTranslateController() throws SQLException {
    }

    @FXML
    public void initialize() {

        stopBtn.setVisible(false);
        stopBtn.setDisable(true);

        langFrom = "en";
        langTo = "vi";

        duplex.setLanguage(langFrom);
        duplex.addResponseListener(new GSpeechResponseListener() {
            String last;
            String old_text = "";

            public void onResponse(GoogleResponse gr) {
                String output = "";
                output = gr.getResponse();
                if (gr.getResponse() == null) {
                    this.old_text = translatingTextArea.getText();
                    if (this.old_text.contains("("))
                        this.old_text = this.old_text.substring(0, this.old_text.indexOf('('));
                    System.out.println("Paragraph Line Added");
                    this.old_text = String.valueOf(translatingTextArea.getText()) + "\n";
                    this.old_text = this.old_text.replace(")", "").replace("( ", "");
                    translatingTextArea.setText(this.old_text);
                    return;
                }
                if (output.contains("("))
                    output = output.substring(0, output.indexOf('('));
                if (!gr.getOtherPossibleResponses().isEmpty())
                    output = String.valueOf(output) + " (" + (String) gr.getOtherPossibleResponses().get(0) + ")";
                System.out.println(output);
                translatingTextArea.setText("");
                translatingTextArea.appendText(this.old_text);
                translatingTextArea.appendText(output);
            }
        });

        // Populate the HashMap with language codes
        languageCodes.put("English", "en");
        languageCodes.put("Chinese", "zh");
        languageCodes.put("French", "fr");
        languageCodes.put("German", "de");
        languageCodes.put("Indonesian", "id");
        languageCodes.put("Vietnamese", "vi");
        languageCodes.put("Korean", "ko");
        languageCodes.put("Japanese", "ja");

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

        translatingTextArea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                translationTimeline.stop();
                // Start the timeline to wait for a pause
                translationTimeline.getKeyFrames().setAll(
                        new KeyFrame(Duration.seconds(0.25), e -> {
                            // Perform translation logic here
                            translatedTextArea.setText(APIController.translate(translatingTextArea.getText(), langFrom, langTo));
                        })
                );
                translationTimeline.playFromStart();
            }
        });
    }

    public void translateFunction(KeyEvent event) {// Initialize the translation timeline
        // Reset the timeline on every key typed
        translationTimeline.stop();
        // Start the timeline to wait for a pause
        translationTimeline.getKeyFrames().setAll(
                new KeyFrame(Duration.seconds(0.25), e -> {
                    // Perform translation logic here
                    translatedTextArea.setText(APIController.translate(translatingTextArea.getText(), langFrom, langTo));
                })
        );
        translationTimeline.playFromStart();
    }

    @FXML
    public void ttsTranslating(ActionEvent event) {
        APIController.speak(translatingTextArea.getText());
    }
    @FXML
    public void ttsTranslated(ActionEvent event) {
        APIController.speak(translatedTextArea.getText());
    }

    public void inEffects2(MouseEvent event) {
        functionEffects.inEffects3(event);
    }

    public void outEffects2(MouseEvent event) {
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
        duplex.setLanguage(langFrom);
    }
    @FXML
    public void setLangTo(ActionEvent event) {
        langTo = languageCodes.get(translatedChoiceBox.getValue());
        translatedTextArea.setText(APIController.translate(translatingTextArea.getText(), langFrom, langTo));
    }



    @FXML
    private Button recordBtn;
    @FXML
    private Button stopBtn;

    @FXML
    public void record() {
        duplex.setLanguage(langFrom);

        if (mic.getState() == Microphone.CaptureState.CLOSED) {
            mic.open();
            duplex.openSpeechRecognition();
        }

        recordBtn.setDisable(true);
        recordBtn.setVisible(false);
        stopBtn.setDisable(false);
        stopBtn.setVisible(true);

        new Thread(() -> {
            try {
                duplex.recognize(mic.getTargetDataLine(), mic.getAudioFormat());
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                recordBtn.setDisable(false);
                stopBtn.setDisable(true);
            }
        }).start();
    }

    @FXML
    public void stop() {
        try {
            mic.close();
            duplex.stopSpeechRecognition();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            recordBtn.setDisable(false);
            recordBtn.setVisible(true);
            stopBtn.setDisable(true);
            stopBtn.setVisible(false);
        }
    }


}
