package com.example.englishlearningappv1.Controllers;

import com.example.englishlearningappv1.QuizController;
import com.example.englishlearningappv1.Utils.GameBackgroundEffects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class HelloResultController {

    @FXML
    public Label marks;

    @FXML
    public ProgressIndicator correct_progress;

    @FXML
    private AnchorPane mainPane;

    private Stage stage;
    private Scene scene;

    final private static String MENU_FXML_FILE_PATH = "src/main/resources/com/example/englishlearningappv1//fxml/homepage.fxml";

    int correct;

    @FXML
    private void initialize() {
        correct = HelloController.correct;
        float correctf = (float) correct/10;
        correct_progress.setProgress(correctf);
        GameBackgroundEffects gameBackgroundEffects = new GameBackgroundEffects();
        gameBackgroundEffects.backgroundEffects(mainPane, 500);
        gameBackgroundEffects.shootingStarsEffect(mainPane);
    }

    @FXML
    public void mainMenuClicked(ActionEvent event) {
        try {
            QuizController quizController = new QuizController();
            quizController.playAgain();
            URL url = new File(MENU_FXML_FILE_PATH).toURI().toURL();
            Parent root = FXMLLoader.load(url);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
