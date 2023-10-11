package com.example.englishlearningappv1;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class HomeQuizController {

    @FXML
    private Button playquizbtn;

    private Stage stage;

    private Scene scene;

    final private static String QUIZ_FXML_FILE_PATH = "src/main/resources/com/example/englishlearningappv1/quiz.fxml";

    @FXML
    private void initialize() {

        playquizbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {

                    URL url = new File(QUIZ_FXML_FILE_PATH).toURI().toURL();
                    Parent root = FXMLLoader.load(url);
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);

                    stage.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }



            }
        });

    }

}