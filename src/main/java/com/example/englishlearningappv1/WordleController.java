package com.example.englishlearningappv1;

import com.example.englishlearningappv1.Controllers.SceneController;
import com.example.englishlearningappv1.Controllers.TitlebarController;
import com.example.englishlearningappv1.Utils.GameBackgroundEffects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.stream.Stream;

public class WordleController extends TitlebarController {

    private Stage stage;
    private Scene scene;

    private static final String GAME_HUB_FXML_FILE_PATH = "src/main/resources/com/example/englishlearningappv1/fxml/gameHub.fxml";

    public static final ArrayList<String> winningWords = new ArrayList<>();
    public static final ArrayList<String> dictionaryWords = new ArrayList<>();

    private final WordleFunction wordleFunction = WordleFunction.getInstance();

    @FXML
    public GridPane gridPane;
    @FXML
    public GridPane keyboardRow1;
    @FXML
    public GridPane keyboardRow2;
    @FXML
    public GridPane keyboardRow3;
    @FXML
    private AnchorPane mainPane;

    public void createUI() {
        createGrid();
        createKeyboard();
    }

    public void createGrid() {
        wordleFunction.createGrid(gridPane);
    }

    public void createKeyboard() {
        wordleFunction.createKeyboard(keyboardRow1, keyboardRow2, keyboardRow3);
    }

    public void gridRequestFocus() {
        gridPane.requestFocus();
    }

    @FXML
    protected void onKeyPressed(KeyEvent keyEvent) {
        wordleFunction.onKeyPressed(gridPane, keyboardRow1, keyboardRow2, keyboardRow3, keyEvent);
    }

    public void getRandomWord() {
        wordleFunction.getRandomWord();
    }

    public void restart() {
        wordleFunction.restart(gridPane, keyboardRow1, keyboardRow2, keyboardRow3);
    }

    public void initializeWordLists() {
        GameBackgroundEffects gameBackgroundEffects = new GameBackgroundEffects();
        gameBackgroundEffects.backgroundEffects(mainPane, 500);
        gameBackgroundEffects.shootingStarsEffect(mainPane);
        InputStream winning_words = getClass().getResourceAsStream("files/winning-words.txt");
        InputStream dictionary = getClass().getResourceAsStream("files/dictionary.txt");

        if (winning_words != null && dictionary != null) {
            Stream<String> winning_words_lines = new BufferedReader(new InputStreamReader(winning_words)).lines();
            winning_words_lines.forEach(winningWords::add);
            Stream<String> dictionary_lines = new BufferedReader(new InputStreamReader(dictionary)).lines();
            dictionary_lines.forEach(dictionaryWords::add);
        } else {
            WordleApplication wordleApplication = new WordleApplication();
            wordleApplication.quit();
        }
    }

    @FXML
    public void backClicked(ActionEvent event) throws Exception {
        SceneController sceneController = new SceneController();
        sceneController.switchScene(event, GAME_HUB_FXML_FILE_PATH);
    }

    @FXML
    public void restartClicked(ActionEvent event) {
        try {
            restart();
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
