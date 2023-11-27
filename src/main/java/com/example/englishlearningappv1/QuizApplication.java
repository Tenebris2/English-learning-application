package com.example.englishlearningappv1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;


public class QuizApplication extends Application {

    private static Stage stageReference;

    @Override
    public void start(Stage stage) throws IOException {
        stageReference = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(QuizApplication.class.getResource("fxml/home.fxml"));
        Parent root = fxmlLoader.load();

        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();

        Scene scene = new Scene(root, 996, 611);
        stage.setMinWidth(611);
        stage.setMinHeight(996);
        stage.setMaxWidth(screenWidth);
        stage.setMaxHeight(screenHeight);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}