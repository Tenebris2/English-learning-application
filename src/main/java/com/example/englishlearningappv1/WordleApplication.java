package com.example.englishlearningappv1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.*;


public class WordleApplication extends Application {

    private static Stage stageReference;

    @Override
    public void start(Stage stage) throws IOException {
        stageReference = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(WordleApplication.class.getResource("wordle.fxml"));
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
        stageReference.close();
    }
}
