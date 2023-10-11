package com.example.englishlearningappv1.Controllers;


import com.example.englishlearningappv1.MultipleChoice;
import com.example.englishlearningappv1.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MultipleChoiceController extends MultipleChoice {
    static boolean started = true;
    private String winner = "You've won!";
    private int score = 0;
    private List<String> answerList;
    private List<Question> questionList;
    private static int questionNumber = 0;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label questionLabel;
    @FXML
    private RadioButton radioButton1;
    @FXML
    private RadioButton radioButton2;
    @FXML
    private RadioButton radioButton3;
    @FXML
    private RadioButton radioButton4;
    @FXML
    public void startGame(ActionEvent event) throws SQLException {
        if (started == true) {
            started = false;
            initQuestionList();
            questionList = MultipleChoice.getQuestionList();
            answerList = questionList.get(questionNumber).getAnswerList();

            radioButton1.setDisable(false);
            radioButton2.setDisable(false);
            radioButton3.setDisable(false);
            radioButton4.setDisable(false);

            radioButton1.setOpacity(1);
            radioButton2.setOpacity(1);
            radioButton3.setOpacity(1);
            radioButton4.setOpacity(1);

            questionLabel.setText(questionList.get(questionNumber).getQuestion());
            radioButton1.setText(answerList.get(0));
            radioButton2.setText(answerList.get(1));
            radioButton3.setText(answerList.get(2));
            radioButton4.setText(answerList.get(3));
        }
    }

    public void getAnswer(ActionEvent event) throws IOException {
        Character correctAnswer = questionList.get(questionNumber).getCorrentAnswer().charAt(0);
        RadioButton[] radioButtons = {radioButton1, radioButton2, radioButton3, radioButton4};
        for (RadioButton rb : radioButtons) {
            if (rb.isSelected()) {
                // Check if the selected radio button has the correct answer
                if (rb.getText().charAt(0) == correctAnswer) {
                    score++;
                } else {
                    score--;
                }
            }
        }
        questionNumber++;
        reload(event);
    }

    public void reload(ActionEvent event) throws IOException {
        questionList = MultipleChoice.getQuestionList();
        answerList = questionList.get(questionNumber).getAnswerList();

        questionLabel.setText(questionList.get(questionNumber).getQuestion());
        radioButton1.setText(answerList.get(0));
        radioButton2.setText(answerList.get(1));
        radioButton3.setText(answerList.get(2));
        radioButton4.setText(answerList.get(3));
        scoreLabel.setText(String.valueOf(score));

        if (questionNumber == questionList.size() - 1) questionNumber = 0;
    }

    public void endGame(ActionEvent event) throws Exception {
        SceneController sceneController = new SceneController();
        sceneController.switchtoDictionary(event);
    }

    public void winGame() {
        radioButton1.setVisible(false);
        radioButton2.setVisible(false);
        radioButton3.setVisible(false);
        radioButton4.setVisible(false);
        questionLabel.setText(winner);
    }


}
