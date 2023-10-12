package com.example.englishlearningappv1.Controllers;


import com.example.englishlearningappv1.MultipleChoice;
import com.example.englishlearningappv1.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class MultipleChoiceController extends MultipleChoice{
    static boolean started = true;
    private int score = 0;
    private List<String> answerList;
    private List<Question> questionList;
    private static int questionNumber = 0;
    private static int lives = 5;

    private double progress = 0;

    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label lifeLabel;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label questionLabel;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;

    @FXML
    public void startGame(ActionEvent event) throws SQLException, IOException {
        if (started == true) {
            started = false;
            initQuestionList();
            questionList = MultipleChoice.getQuestionList();
            answerList = questionList.get(questionNumber).getAnswerList();
            reload(event);
        }
    }

    @FXML
    public void optClicked(ActionEvent event) throws IOException {
        // Get the source button of the event
        Button source = (Button) event.getSource();
        // Find the index of the source button in the opts array or list
        Character ans = source.getText().charAt(0);

        Question ques = questionList.get(questionNumber);

        if (checkAnswer(ans, ques)) {
            questionNumber = questionNumber + 1;
            score = score + 1;
            progress = (double) (questionNumber)/(questionList.size());
            progressBar.setProgress(progress);
            reload(event);
        }
    }

    public boolean checkAnswer(Character answer, Question question) {
        if (Character.toLowerCase(answer) == Character.toLowerCase(question.getCorrentAnswer().charAt(0))) {
            return true;
        }
        return false;
    }

    public void reload(ActionEvent event) throws IOException {
        questionList = MultipleChoice.getQuestionList();
        answerList = questionList.get(questionNumber).getAnswerList();

        questionLabel.setText(questionList.get(questionNumber).getQuestion());
        button1.setText(answerList.get(0));
        button2.setText(answerList.get(1));
        button3.setText(answerList.get(2));
        button4.setText(answerList.get(3));
        scoreLabel.setText(String.valueOf(score));

        lifeLabel.setText(String.valueOf(lives));

        if (questionNumber >= questionList.size() - 1) {
            winGame();
        }
    }

    public void endGame(ActionEvent event) throws Exception {
        SceneController sceneController = new SceneController();
        sceneController.switchtoDictionary(event);
    }

    public void winGame() {
        questionLabel.setText(String.valueOf(score));
    }


}
