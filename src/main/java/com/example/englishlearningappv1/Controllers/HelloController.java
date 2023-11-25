package com.example.englishlearningappv1.Controllers;

import com.example.englishlearningappv1.Utils.GameBackgroundEffects;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

public class HelloController implements ControllerInterface {

    String[] questions = {
            "What subject is he _____ now?",
            "She is going to _____ television tonight.",
            "She _____ a banana.",
            "He is _____ Vietnam",
            "It is not hot _____ the winter.",
            "I don't like Maths _____ it is difficult.",
            "What's the matter _____ you?",
            "He often _____ to school in the morning.",
            "They are from _____.",
            "His birthday is _____ June 1st.",
            "A _____ works in a hospital.",
            "I _____ reading a book about Santa Claus.",
            "_____ you like to play hide and seek with me?",
            "Did you _____ teacher's Day last month?",
            "Does he _____ a sore-throat?",
            "We went to the cinema _____.",
            "A housewife often does _____.",
            "It's hot in Summer, _____?",
            "What's the matter _____ you?",
            "I have _____ cough."
    };

    String[][] options = {
            {"to learn", "learn", "learning", "learned"},
            {"sing", "play", "stay", "watch"},
            {"wants", "want", "to want", "wantes"},
            {"from", "on", "in", "at"},
            {"on", "in", "at", "under"},
            {"and", "but", "so", "because"},
            {"in", "with", "on", "of"},
            {"gos", "going", "goes", "to go"},
            {"Singapore", "Vietnamese", "English", "American"},
            {"at", "on", "in", "about"},
            {"teacher", "worker", "nurse", "farmer"},
            {"can", "do", "am", "are"},
            {"Would", "Could", "Do", "If"},
            {"celebrated", "celebrating", "celebrate", "celebrater"},
            {"has", "had", "have", "having"},
            {"today", "yesterday", "tomorrow", "next Sunday"},
            {"homework", "housework", "hardwork", "exercises"},
            {"is it", "isn't it", "do it", "does it"},
            {"for", "to", "with", "on"},
            {"the", "a", "an", "any"}
    };

    char[] answers = {
            'C',
            'D',
            'A',
            'A',
            'B',
            'D',
            'B',
            'C',
            'C',
            'B',
            'C',
            'C',
            'A',
            'C',
            'C',
            'B',
            'B',
            'B',
            'C',
            'B'
    };

    public static int correct = 0;
    private int index = 0;
    private int totalSec = 1000000;
    private int cnt = 0;
    private static final String HELLO_RESULT_FXML_FILE_PATH = "src/main/resources/com/example/englishlearningappv1/fxml/HelloResult.fxml";

    @FXML
    public Label timer, question;
    @FXML
    public Button opt1, opt2, opt3, opt4;
    @FXML
    private AnchorPane mainPane;

    @FXML
    private void initialize() {
        loadQuestions();
        timeline.play();
        GameBackgroundEffects gameBackgroundEffects = new GameBackgroundEffects();
        gameBackgroundEffects.backgroundEffects(mainPane, 500);
        gameBackgroundEffects.shootingStarsEffect(mainPane);
    }

    Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(1), new EventHandler<>() {
                @Override
                public void handle(ActionEvent event) {
                    totalSec--;
                    timer.setText("Time Left: " + totalSec);
                    if (totalSec <= 0) {
                        displayAnswer(answers[index]);
                    }
                }
            })
    );

    private void loadQuestions() {
        if (cnt == 10) {
            try {
                URL url = new File(HELLO_RESULT_FXML_FILE_PATH).toURI().toURL();
                Parent root = FXMLLoader.load(url);
                Stage stage = (Stage) timer.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            timer.setText("Time Left: 15");
            opt1.setUserData('A');
            opt2.setUserData('B');
            opt3.setUserData('C');
            opt4.setUserData('D');
            opt1.getStyleClass().clear();
            opt1.getStyleClass().setAll("opt1");
            opt2.getStyleClass().clear();
            opt2.getStyleClass().setAll("opt2");
            opt3.getStyleClass().clear();
            opt3.getStyleClass().setAll("opt3");
            opt4.getStyleClass().clear();
            opt4.getStyleClass().setAll("opt4");
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
            question.setText(questions[index]);
            opt1.setVisible(true);
            opt2.setVisible(true);
            opt3.setVisible(true);
            opt4.setVisible(true);
            opt1.setText(options[index][0]);
            opt2.setText(options[index][1]);
            opt3.setText(options[index][2]);
            opt4.setText(options[index][3]);
        }
    }

    private void displayAnswer(Character c) {
        timeline.stop();
        opt1.setDisable(true);
        opt2.setDisable(true);
        opt3.setDisable(true);
        opt4.setDisable(true);
        updateAnswerStyle(c);
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(2));
        pauseTransition.setOnFinished(event -> {
            totalSec = 1000000;
            timer.setText("Time Left: " + totalSec);
            opt1.setDisable(false);
            opt2.setDisable(false);
            opt3.setDisable(false);
            opt4.setDisable(false);
            cnt++;
            index = gen();
            loadQuestions();
        });
        pauseTransition.play();
    }

    @FXML
    public void opt1clicked() {
        Character c = (Character) opt1.getUserData();
        displayAnswer(c);
    }

    @FXML
    public void opt2clicked() {

        Character c = (Character) opt2.getUserData();
        displayAnswer(c);
    }

    @FXML
    public void opt3clicked() {
        Character c = (Character) opt3.getUserData();
        displayAnswer(c);
    }

    @FXML
    public void opt4clicked() {
        Character c = (Character) opt4.getUserData();
        displayAnswer(c);
    }

    private void updateAnswerStyle(Character c) {
        if (answers[index] == 'A') {
            opt1.getStyleClass().clear();
            opt1.getStyleClass().setAll("correct-answer");
            if (c.equals('B')) {
                opt2.getStyleClass().clear();
                opt2.getStyleClass().setAll("wrong-answer");
                opt3.setVisible(false);
                opt4.setVisible(false);
            } else if (c.equals('C')) {
                opt3.getStyleClass().clear();
                opt3.getStyleClass().setAll("wrong-answer");
                opt2.setVisible(false);
                opt4.setVisible(false);
            } else if (c.equals('D')) {
                opt2.setVisible(false);
                opt3.setVisible(false);
                opt4.getStyleClass().clear();
                opt4.getStyleClass().setAll("wrong-answer");
            } else {
                correct++;
                opt1.getStyleClass().clear();
                opt1.getStyleClass().setAll("correct-answer-pick");
                opt2.setVisible(false);
                opt3.setVisible(false);
                opt4.setVisible(false);
            }
        } else if (answers[index] == 'B') {
            opt2.getStyleClass().clear();
            opt2.getStyleClass().setAll("correct-answer");
            if (c.equals('A')) {
                opt1.getStyleClass().clear();
                opt1.getStyleClass().setAll("wrong-answer");
                opt3.setVisible(false);
                opt4.setVisible(false);
            } else if (c.equals('C')) {
                opt3.getStyleClass().clear();
                opt3.getStyleClass().setAll("wrong-answer");
                opt1.setVisible(false);
                opt4.setVisible(false);
            } else if (c.equals('D')) {
                opt1.setVisible(false);
                opt3.setVisible(false);
                opt4.getStyleClass().clear();
                opt4.getStyleClass().setAll("wrong-answer");
            } else {
                correct++;
                opt2.getStyleClass().clear();
                opt2.getStyleClass().setAll("correct-answer-pick");
                opt1.setVisible(false);
                opt3.setVisible(false);
                opt4.setVisible(false);
            }
        } else if (answers[index] == 'C') {
            opt3.getStyleClass().clear();
            opt3.getStyleClass().setAll("correct-answer");
            if (c.equals('A')) {
                opt1.getStyleClass().clear();
                opt1.getStyleClass().setAll("wrong-answer");
                opt2.setVisible(false);
                opt4.setVisible(false);
            } else if (c.equals('B')) {
                opt2.getStyleClass().clear();
                opt2.getStyleClass().setAll("wrong-answer");
                opt1.setVisible(false);
                opt4.setVisible(false);
            } else if (c.equals('D')) {
                opt1.setVisible(false);
                opt2.setVisible(false);
                opt4.getStyleClass().clear();
                opt4.getStyleClass().setAll("wrong-answer");
            } else {
                correct++;
                opt3.getStyleClass().clear();
                opt3.getStyleClass().setAll("correct-answer-pick");
                opt1.setVisible(false);
                opt2.setVisible(false);
                opt4.setVisible(false);
            }
        } else if (answers[index] == 'D') {
            opt4.getStyleClass().clear();
            opt4.getStyleClass().setAll("correct-answer");
            if (c.equals('A')) {
                opt1.getStyleClass().clear();
                opt1.getStyleClass().setAll("wrong-answer");
                opt2.setVisible(false);
                opt3.setVisible(false);
            } else if (c.equals('B')) {
                opt2.getStyleClass().clear();
                opt2.getStyleClass().setAll("wrong-answer");
                opt1.setVisible(false);
                opt3.setVisible(false);
            } else if (c.equals('C')) {
                opt1.setVisible(false);
                opt2.setVisible(false);
                opt3.getStyleClass().clear();
                opt3.getStyleClass().setAll("wrong-answer");
            } else {
                correct++;
                opt1.setVisible(false);
                opt2.setVisible(false);
                opt3.setVisible(false);
                opt4.getStyleClass().clear();
                opt4.getStyleClass().setAll("correct-answer-pick");
            }
        }
    }

    public int gen() {
        Random random = new Random();
        int minRange = 0;
        int maxRange = 19;
        return random.nextInt(maxRange - minRange + 1) + minRange;
    }
}



