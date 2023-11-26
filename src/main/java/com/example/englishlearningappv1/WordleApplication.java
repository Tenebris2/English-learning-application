package com.example.englishlearningappv1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;


public class WordleApplication extends Application {

    private static Stage stageReference;

    final private static String HOME_PAGE_FXML_FILE_PATH = "src/main/resources/com/example/englishlearningappv1/fxml/HomePage.fxml";


    @Override
    public void start(Stage stage) throws IOException {
        stageReference = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(WordleApplication.class.getResource("fxml/wordle.fxml"));
        Parent root = fxmlLoader.load();

        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();
        Scene scene = new Scene(root, 500, 720);
        stage.setMinWidth(500);
        stage.setMinHeight(720);
        stage.setMaxWidth(screenWidth);
        stage.setMaxHeight(screenHeight);
        stage.setScene(scene);
        stage.show();

        WordleController wordleController = fxmlLoader.getController();
        wordleController.initializeWordLists();
        wordleController.createUI();
        wordleController.getRandomWord();
        wordleController.gridRequestFocus();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void showNotifyNotFound() {
        Notify.makeTextNotFound(stageReference);
    }

    public static void showNotifyShort() {
        Notify.makeTextShort(stageReference);
    }

    public static void quit() {
        try {
            URL url = new File(HOME_PAGE_FXML_FILE_PATH).toURI().toURL();
            Parent root = FXMLLoader.load(url);
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
