package com.example.englishlearningappv1;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Stream;

public class WordleController {

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
    public HBox titleHBox;
    @FXML
    public ImageView restartIcon;

    public void createUI() {
        createGrid();
        createKeyboard();
        createTitleHBox();
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

    public void createTitleHBox() {
        wordleFunction.createTitleHBox(titleHBox);
    }

    public void restart() {
        wordleFunction.restart(restartIcon, gridPane, keyboardRow1, keyboardRow2, keyboardRow3);
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
}
