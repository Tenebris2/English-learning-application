package com.example.englishlearningappv1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Stream;

public class WordleController {

    private Stage stage;
    private Scene scene;

    private static final String HOME_PAGE_FXML_FILE_PATH = "src/main/resources/com/example/englishlearningappv1/fxml/HomePage.fxml";


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
        InputStream winning_words = getClass().getResourceAsStream("winning-words.txt");
        InputStream dictionary = getClass().getResourceAsStream("dictionary.txt");

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
    public void backClicked(ActionEvent event) {
        try {
            restart();

            URL url = new File(HOME_PAGE_FXML_FILE_PATH).toURI().toURL();
            Parent root = FXMLLoader.load(url);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
