package com.example.englishlearningappv1.Controllers;

import com.example.englishlearningappv1.API.ChatBot;
import com.example.englishlearningappv1.API.SaA;
import com.example.englishlearningappv1.Functions.CRUDFunctions;
import com.example.englishlearningappv1.Root;
import com.example.englishlearningappv1.Utils.BackgroundEffects;
import com.example.englishlearningappv1.Utils.Utils;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import javafx.util.Duration;
import org.json.JSONException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.example.englishlearningappv1.API.ChatBot.asyncSendQuery;
import static com.example.englishlearningappv1.API.ChatBot.sendQuery;

public class FavoritesPageController extends HomePageController {
    private static int currentIndex = 0;
    private List<String> listOfFavoriteWords;
    private final PauseTransition cooldownTransition = new PauseTransition(Duration.millis(5000)); // 3 seconds cooldown

    @FXML
    private Button favoritesPage;
    @FXML
    private Label favoriteWordDisplay;
    @FXML
    private Pane favoriteWordContainer;
    @FXML
    private Label favoriteWordLabel;
    @FXML
    private Button askDefButton;
    @FXML
    private TextArea answerContainerLabel;
    @FXML
    private Button askForUseButton;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button askSynonymAndAnotonymButton;
    @FXML
    private Button askForCollocations;
    @FXML
    private Button backButton;
    @FXML
    private Button nextButton;

    private static final String secondBaseStyle = """
                    -fx-background-color: linear-gradient(to right top, #ffcbf2, #ec38bc, #7303c0, #03001e);
                                                                              -fx-background-radius: 10px; /* Adjust the radius as needed */
            -fx-border-radius: 100px; /* Adjust the radius as needed */
            -fx-box-shadow: 0 4px 15px 0 rgba(252, 104, 110, 0.75);
                        """;
    @Override
    public void initialize() throws SQLException {
        BackgroundEffects backgroundEffects = new BackgroundEffects();
        backgroundEffects.backgroundEffects(mainPane, 500);
        backgroundEffects.shootingStarsEffect(mainPane);

        favoritesPage.setStyle(secondBaseStyle);
        listOfFavoriteWords = CRUDFunctions.createFavoriteWordList();
        favoriteWordDisplay.setText(listOfFavoriteWords.get(currentIndex));
    }


    public void askSynonymAndAnotonym(ActionEvent event) throws IOException {
        answerContainerLabel.setText("...");

        disableButtons();

        // Start the cooldown transition
        cooldownTransition.playFromStart();

        CompletableFuture<String> queryResult = CompletableFuture.supplyAsync(() -> {
            String ans = null;
            try {
                ans = SaA.sendRequest(favoriteWordDisplay.getText());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println(ans);
            return ans;
        });

        // Wait for the result before updating the UI
        queryResult.thenAcceptAsync(ans -> {
            System.out.println(ans);
            answerContainerLabel.setText(String.valueOf(ans));

            // Re-enable the button when the query is complete
            enableButtons();
        });
    }
    public void revealOptions(ActionEvent event) {

    }
    
    public void disableButtons() {
        askForUseButton.setDisable(true);
        askDefButton.setDisable(true);
        askSynonymAndAnotonymButton.setDisable(true);
        askForCollocations.setDisable(true);
    }
    
    public void enableButtons() {
        askForUseButton.setDisable(false);
        askDefButton.setDisable(false);
        askSynonymAndAnotonymButton.setDisable(false);
        askForCollocations.setDisable(false);
    }

    public void askChatGPTQuestions(ActionEvent event) {
        Button button = (Button) (event.getSource());

        answerContainerLabel.setText("...");

        disableButtons();

        // Start the cooldown transition
        cooldownTransition.playFromStart();

        CompletableFuture<String> queryResult = CompletableFuture.supplyAsync(() -> {
            String question = "";
            System.out.println(button.getId());
            String ans = String.valueOf(sendQuery(button.getText() + favoriteWordDisplay.getText() + ", make it quick"));
            System.out.println(button.getText() + favoriteWordDisplay.getText() + ", answer quickly");
            System.out.println(ans);
            if (Objects.equals(ans, "Error: JSONObject[\"error\"] not a string.")) {
                return "Sorry, i don't know right now!";
            }
            return ans;
        });

        // Wait for the result before updating the UI
        queryResult.thenAcceptAsync(ans -> {
            System.out.println(ans);
            answerContainerLabel.setText(String.valueOf(ans));

            // Re-enable the button when the query is complete
            enableButtons();
        });
    }

    public void next(ActionEvent event) {
        if (currentIndex < listOfFavoriteWords.size() - 1) {
            nextButton.setDisable(false);
            currentIndex = currentIndex + 1;
            favoriteWordDisplay.setText(listOfFavoriteWords.get(currentIndex));
            answerContainerLabel.setText("");
        } else {
            nextButton.setDisable(true);
        }
    }

    public void back(ActionEvent event) {
        if (currentIndex > 1) {
            currentIndex = currentIndex - 1;
            favoriteWordDisplay.setText(listOfFavoriteWords.get(currentIndex));
            answerContainerLabel.setText("");
            backButton.setDisable(false);
        } else {
            backButton.setDisable(true);
        }
    }

}
