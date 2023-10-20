package com.example.englishlearningappv1.Controllers;

import com.example.englishlearningappv1.DictionaryApp;
import com.example.englishlearningappv1.WordleApplication;
import com.example.englishlearningappv1.WordleController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class SceneController {

    private Stage stage;
    private Scene scene;
    @FXML
    private Parent root;

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
        URL url = new File(DICTIONARY_FXML_FILE_PATH).toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        DictionaryApp dictionaryApp = new DictionaryApp();
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
}
