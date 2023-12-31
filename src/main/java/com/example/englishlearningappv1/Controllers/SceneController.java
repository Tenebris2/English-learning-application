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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class SceneController implements ControllerInterface {

    private Stage stage;
    private Scene scene;
    @FXML
    private Parent root;
    private DictionaryApp dictionaryApp;

    public void switchScene(ActionEvent event, String filepath) throws Exception {
        // Load the FXML file
        URL url = new File(filepath).toURI().toURL();
        Parent root = FXMLLoader.load(url);

        // Get the current stage
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the new scene on the existing stage
        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();
    }

    public void switchtoDictionary(ActionEvent event) throws Exception {
        URL url = new File(DICTIONARY_FXML_FILE_PATH).toURI().toURL();
        Parent root = FXMLLoader.load(url);

        // Get the current stage
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the new scene on the existing stage
        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();

        dictionaryApp = new DictionaryApp();

        dictionaryApp.start(stage);
    }

    public void switchToHomePage(ActionEvent event) throws Exception {
        switchScene(event, HOME_PAGE_FXML_FILE_PATH);
        if (!GoogleTranslateController.mic.getState().toString().equals("CLOSED")) {
            GoogleTranslateController.mic.close();
        }

    }

    public void switchtoWordle(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WordleApplication.class.getResource("fxml/wordle.fxml"));
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

    public void switchToGameHub(ActionEvent event) throws Exception {
        switchScene(event, GAME_HUB_FXML_FILE_PATH);
    }

    public void gotoHome(ActionEvent event) throws Exception {
        switchToHomePage(event);
    }

    public void gotoDictionary(ActionEvent event) throws Exception {
        switchtoDictionary(event);
    }

    public void gotoGameHub(ActionEvent event) throws Exception {
        switchToGameHub(event);
    }

    public void openGoogleTranslate(ActionEvent event) throws Exception {
        switchScene(event, GOOGLE_TRANSLATE_WINDOW_FXML_FILE_PATH);
    }

    public void switchToHello(ActionEvent event) throws Exception {
        switchScene(event, HELLO_FXML_FILE_PATH);
    }
    public void switchToFavoritesPage(ActionEvent event) throws Exception {
        switchScene(event, FAVORITES_PAGE_FXML_FILE_PATH);
    }

    public void switchToScannerPage(ActionEvent event) throws Exception {
        switchScene(event, SCANNER_FXML_FILE_PATH);
    }

    public void openImage(MouseEvent event) throws IOException {
        // Load the FXML file
        URL url = new File(IMAGE_FXML_FILE_PATH).toURI().toURL();
        Parent root = FXMLLoader.load(url);

        // Get the current stage
        Stage stage = new Stage();

        // Set the new scene on the existing stage
        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();
    }
}
