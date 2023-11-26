package com.example.englishlearningappv1;

import com.example.englishlearningappv1.Controllers.Controller;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class DictionaryApp extends Application {

    private static final String FXML_FILE_PATH = "com/example/englishlearningappv1/fxml/JavaFx.fxml";
    private static Controller controller = new Controller();
    private Dictionary dic = new Dictionary();
    private HistorySearch his = new HistorySearch();

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Create a background task for data loading
        URL url = new File("src/main/resources/com/example/englishlearningappv1/fxml/JavaFx.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dictionary");
        Task<Void> dataLoadingTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                controller.init(scene);
                controller.initData();
                controller.hideSearchView(root);
                controller.addFunction();
                return null;
            }
        };

        // Set up listeners for the data loading task
        dataLoadingTask.setOnSucceeded(workerStateEvent -> {
            notifyPreloader(new Preloader.StateChangeNotification(Preloader.StateChangeNotification.Type.BEFORE_START));
        });

        // Start the data loading task in a separate thread
        new Thread(dataLoadingTask).start();

        primaryStage.show();
    }
        // Function to sleep for a specified duration in milliseconds
        private static void sleep(long milliseconds) {
            try {
                Thread.sleep(milliseconds);
            } catch (InterruptedException e) {
                // Handle the InterruptedException if needed
                e.printStackTrace();
            }
        }

    @Override
    public void stop() throws Exception {
        super.stop();
        if(controller.getChanged()){
            his.saveHistory();
            dic.exportToFile();
            dic.setWordListToNull();
            System.out.println("Save changed");
        }else{
            System.out.println("nothing changed");
        }

    }
}
