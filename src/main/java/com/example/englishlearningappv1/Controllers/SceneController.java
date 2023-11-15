package com.example.englishlearningappv1.Controllers;

import com.example.englishlearningappv1.DictionaryApp;
import com.example.englishlearningappv1.WordleApplication;
import com.example.englishlearningappv1.WordleController;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.action.Action;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class SceneController {

    private Stage stage;
    private Scene scene;
    @FXML
    private Parent root;
    private DictionaryApp dictionaryApp;

    final private static String MULTIPLE_CHOICE_FXML_FILE_PATH = "src/main/resources/com/example/englishlearningappv1/home.fxml";
    private static final String DICTIONARY_FXML_FILE_PATH = "src/main/resources/com/example/englishlearningappv1/fxml/JavaFx.fxml";
    final private static String HOME_PAGE_FXML_FILE_PATH = "src/main/resources/com/example/englishlearningappv1/fxml/HomePage.fxml";
    private static final String QUIZ_FXML_FILE_PATH = "src/main/resources/com/example/englishlearningappv1/home.fxml";
    final private static String CHAPTER_START_FXML_FILE_PATH = "src/main/resources/com/example/englishlearningappv1/fxml/MultipleChoiceGame.fxml";

    final private static String GAME_HUB_FXML_FILE_PATH = "src/main/resources/com/example/englishlearningappv1/fxml/gameHub.fxml";
    public void switchtoMPC(ActionEvent event) throws IOException {
        URL url = new File(MULTIPLE_CHOICE_FXML_FILE_PATH).toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchtoDictionary(ActionEvent event) throws Exception {
        // Load the FXML file
        URL url = new File(DICTIONARY_FXML_FILE_PATH).toURI().toURL();
        Parent root = FXMLLoader.load(url);

        // Get the current stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the new scene on the existing stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        dictionaryApp = new DictionaryApp();
        dictionaryApp.start(stage);

    }

    public void switchToHomePage(ActionEvent event) throws IOException {
        URL url = new File(HOME_PAGE_FXML_FILE_PATH).toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToChapterStart(ActionEvent event) throws IOException {
        URL url = new File(CHAPTER_START_FXML_FILE_PATH).toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchtoWordle(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WordleApplication.class.getResource("wordle.fxml"));
        Parent root = fxmlLoader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        WordleController wordleController = fxmlLoader.getController();
        wordleController.initializeWordLists();
        wordleController.createUI();
        wordleController.getRandomWord();
        wordleController.gridRequestFocus();
    }
    public void switchtoQuiz(ActionEvent event) throws IOException {
        URL url = new File(QUIZ_FXML_FILE_PATH).toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToGameHub(ActionEvent event) throws IOException {
        URL url = new File(GAME_HUB_FXML_FILE_PATH).toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void gotoHome(ActionEvent event) throws IOException {
        switchToHomePage(event);
    }

    public void gotoDictionary(ActionEvent event) throws Exception {
        switchtoDictionary(event);
    }

    public void inEffects1(MouseEvent event) {
        String baseStyle = """
                    -fx-border-radius: 100px;
                    -fx-background-color: transparent;
                """;
        Button button = (Button) event.getSource();
        button.setStyle(baseStyle
                + "-fx-background-color: #0489B0;");
    }

    public void outEffects1(MouseEvent event) {
        String baseStyle = """
                    -fx-border-radius: 100px;
                    -fx-background-color: transparent;
                """;
        Button button = (Button) event.getSource();
        button.setStyle(baseStyle);
    }

    public void inEffects2(MouseEvent event) {
        String baseStyle = """
        -fx-background-color: linear-gradient(to right, #25aae1, #4481eb, #04befe, #3f86ed);
        -fx-background-radius: 200px; /* Adjust the radius as needed */
        -fx-border-radius: 5; /* Adjust the radius as needed */
        -fx-box-shadow: 0 4px 15px 0 rgba(252, 104, 110, 0.75);
                """;
        Button button = (Button) event.getSource();
        button.setStyle(baseStyle);
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(150), button);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.7);
        fadeTransition.setCycleCount(0);
        fadeTransition.setAutoReverse(true);
        ParallelTransition parallelTransition = new ParallelTransition(fadeTransition);
        parallelTransition.play();
    }

    public void outEffects2(MouseEvent event) {
        String baseStyle = """
        -fx-background-color: linear-gradient(to right, #25aae1, #4481eb, #04befe, #3f86ed);
        -fx-background-radius: 200px; /* Adjust the radius as needed */
        -fx-border-radius: 5; /* Adjust the radius as needed */
        -fx-box-shadow: 0 4px 15px 0 rgba(252, 104, 110, 0.75);
                """;
        Button button = (Button) event.getSource();
        button.setStyle(baseStyle);
        button.setStyle(baseStyle);
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(150), button);
        fadeTransition.setFromValue(0.7);
        fadeTransition.setToValue(1);
        fadeTransition.setCycleCount(0);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();
        ParallelTransition parallelTransition = new ParallelTransition(fadeTransition);
        parallelTransition.play();
    }

    public void gotoGameHub(ActionEvent event) throws IOException {
        switchToGameHub(event);
    }
}
