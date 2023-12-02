package com.example.englishlearningappv1.Controllers;

import com.example.englishlearningappv1.API.APIController;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.sourceforge.tess4j.TesseractException;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.*;


public class ScannerController extends HomePageController {
    @FXML
    private Button file_chooser;
    @FXML
    private TextArea textarea;
    @FXML
    private ImageView image;
    public static String imageViewLink = null;

    private static int currentIndex = 0;
    private static int time_out_length = 30;
    private java.util.List<String> listOfFavoriteWords;
    private List<Button> listOfButtons;
    private static final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    private final PauseTransition cooldownTransition = new PauseTransition(Duration.millis(5000)); // 3 seconds cooldown
    public void scan(ActionEvent event) throws TesseractException, MalformedURLException {
        FileChooser fc = new FileChooser();

        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.bmp", "*.jpeg"));
        if (fc != null) {
            File file = fc.showOpenDialog((Stage) ((Node) event.getSource()).getScene().getWindow());

            String txt = APIController.scan(file);
            System.out.println(txt);
            imageViewLink = file.toURI().toString();
            image.setImage(new Image(file.toURI().toString()));

            textarea.setText(txt);
        }
    }

    @FXML
    public void translate(ActionEvent event) {

        long startTime = System.currentTimeMillis();

        CompletableFuture<String> queryResult = CompletableFuture.supplyAsync(() -> {
            String[] texts = textarea.getText().split("\n");
            System.out.println(texts);
            String trans = "";
            for (int i = 0; i < texts.length; i++) {
                trans += (APIController.translate(texts[i], "en", "vi")) + "\n";
            }
            return trans;
        });
        // Periodically log the elapsed time
        ScheduledFuture<?> loggingFuture = executorService.scheduleAtFixedRate(() -> {
            long elapsedTime = System.currentTimeMillis() - startTime;
            System.out.println("Elapsed Time: " + elapsedTime / 1000 + " seconds");
            if (elapsedTime >= time_out_length * 1000) {
                queryResult.completeExceptionally(new TimeoutException("API Request Timed Out"));
            }
            if (elapsedTime / 1000 % 2 == 0) {
                textarea.setText("....");
            } else if (elapsedTime / 1000 % 2 != 0){
                textarea.setText("...");
            }
        }, 1, 1, TimeUnit.SECONDS);
        // Wait for the result before updating the UI

        queryResult.thenAcceptAsync(trans -> {
            loggingFuture.cancel(true);
            textarea.setText(trans);
            // Re-enable the button when the query is complete
        }).exceptionally(ex -> {
            // Handle exception (e.g., timeout)
            loggingFuture.cancel(true);
            System.err.println("Error: " + ex.getMessage());
            textarea.setText("Looks like we could not summarize it");


            return null;
        });
    }
    @FXML
    public void summarize(ActionEvent event) {
        if (textarea.getText().length() >= 15) {
            // Record the start time
            long startTime = System.currentTimeMillis();

            CompletableFuture<String> queryResult = CompletableFuture.supplyAsync(() -> {
                String question = "";
                String ans = String.valueOf(APIController.chatBotSendQuery("Summarize this text: " + textarea.getText()));
                if (ans.contains("Error:")) {
                    return "Sorry, i don't know right now!";
                }
                return ans;
            });
            // Periodically log the elapsed time
            ScheduledFuture<?> loggingFuture = executorService.scheduleAtFixedRate(() -> {
                long elapsedTime = System.currentTimeMillis() - startTime;
                System.out.println("Elapsed Time: " + elapsedTime / 1000 + " seconds");
                if (elapsedTime >= time_out_length * 1000) {
                    queryResult.completeExceptionally(new TimeoutException("API Request Timed Out"));
                }
                if (elapsedTime / 1000 % 2 == 0) {
                    textarea.setText("....");
                } else if (elapsedTime / 1000 % 2 != 0) {
                    textarea.setText("...");
                }
            }, 1, 1, TimeUnit.SECONDS);
            // Wait for the result before updating the UI

            queryResult.thenAcceptAsync(ans -> {
                loggingFuture.cancel(true);
                System.out.println(ans);
                textarea.setText(String.valueOf(ans));
                // Re-enable the button when the query is complete
            }).exceptionally(ex -> {
                // Handle exception (e.g., timeout)
                loggingFuture.cancel(true);
                System.err.println("Error: " + ex.getMessage());
                textarea.setText("Looks like we could not summarize it");


                return null;
            });
        } else {
            textarea.setText("This is too short!");
        }
    }
    public void inEffects2(MouseEvent event) {
        functionEffects.inEffects3(event);
    }

    public void outEffects2(MouseEvent event) {
        functionEffects.outEffects3(event);
    }

    public void openImage(MouseEvent event) throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.openImage(event);
    }
}
