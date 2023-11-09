package com.example.englishlearningappv1;

import com.example.englishlearningappv1.Controllers.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomePage extends Application{
    final private static String HOME_PAGE_FXML_FILE_PATH = "fxml/HomePage.fxml";

    private static Controller controller = new Controller();

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource(HOME_PAGE_FXML_FILE_PATH));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dictionary");
        primaryStage.show();
        
    }

}
