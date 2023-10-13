package com.example.englishlearningappv1;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class QuizController {

     String[] questions = {
            "1. How many consonants are there in the English alphabet?",
            "2. Who invented the Light bulb?",
            "3. In the Solar System, farthest planet from the Sun is",
            "4. Largest moon in the Solar System?",
            "5. Which of these is 'not' a property of metal?",
            "6. Who discovered Pasteurisation?",
            "7. Hydrochloric acid (HCl) is produced by -?",
            "8. The fastest animal in the world is -",
            "9. Complementary colour of Red is -",
            "10. World Environment Day is on -"
    };

     String[][] options = {
            {"19", "20", "21", "22"},
            {"Thomas Alva Edison", "Alexander Fleming", "Charles Babbage", "Albert Einstein"},
            {"Jupiter", "Saturn", "Uranus", "Neptune"},
            {"Titan", "Ganymede", "Moon", "Europa"},
            {"Good Conduction", "Malleable", "Non Ductile", "Sonourous"},
            {"Alexander Fleming", "Louis Pasteur", "Simon Pasteur", "William Pasteur"},
            {"Small Intestine", "Liver", "Oesophagus", "Stomach"},
            {"Lion", "Blackbuck", "Cheetah", "Quarter Horse"},
            {"Blue", "Green", "Yellow", "Pink"},
            {"5th June", "5th July", "15th June", "25th June"}
    };

    char[] answers = {
            'C',
            'A',
            'D',
            'B',
            'C',
            'B',
            'D',
            'C',
            'B',
            'A'
    };

    public static int correct = 0;
    public static int wrong = 0;
    private int index = 0;
    private int totalSec = 15;

    private Stage stage;

    private Scene scene;

    final private static String RESULT_FXML_FILE_PATH = "src/main/resources/com/example/englishlearningappv1/result.fxml";

    @FXML
    public Label timer, question;

    @FXML
    public Button opt1, opt2, opt3, opt4;

    @FXML
    private void initialize() {
        loadQuestions();
        timeline.play();
    }

    public void playAgain() {
        index = 0;
        correct = 0;
        wrong = 0;
    }

    Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    totalSec--;
                    timer.setText("Time Left: " + String.valueOf(totalSec));

                    if (totalSec <= 0) {
                        displayAnswer('A'); //need repair
                    }
                }
            })
    );

    private void loadQuestions() {
        if (index == 10) {
            try {
                URL url = new File(RESULT_FXML_FILE_PATH).toURI().toURL();
                Parent root = FXMLLoader.load(url);
                Stage stage = (Stage) timer.getScene().getWindow(); // Assuming 'timer' is a JavaFX element in the same scene
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
            totalSec = 15;
            timer.setText("Time Left: " + String.valueOf(totalSec));

            opt1.setDisable(false);
            opt2.setDisable(false);
            opt3.setDisable(false);
            opt4.setDisable(false);

            index++;
            loadQuestions();
        });
        pauseTransition.play();
    }

    @FXML
    public void opt1clicked(ActionEvent event) {
        Character c = (Character) opt1.getUserData();
        displayAnswer(c);
    }



    @FXML
    public void opt2clicked(ActionEvent event) {

        Character c = (Character) opt2.getUserData();
        displayAnswer(c);
    }

    @FXML
    public void opt3clicked(ActionEvent event) {
        Character c = (Character) opt3.getUserData();
        displayAnswer(c);
    }

    @FXML
    public void opt4clicked(ActionEvent event) {
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
                opt3.getStyleClass().clear();
                opt3.getStyleClass().setAll("null");
                opt4.getStyleClass().clear();
                opt4.getStyleClass().setAll("null");
            } else if (c.equals('C')) {
                opt2.getStyleClass().clear();
                opt2.getStyleClass().setAll("null");
                opt3.getStyleClass().clear();
                opt3.getStyleClass().setAll("wrong-answer");
                opt4.getStyleClass().clear();
                opt4.getStyleClass().setAll("null");
            } else if (c.equals('D')) {
                opt2.getStyleClass().clear();
                opt2.getStyleClass().setAll("null");
                opt3.getStyleClass().clear();
                opt3.getStyleClass().setAll("null");
                opt4.getStyleClass().clear();
                opt4.getStyleClass().setAll("wrong-answer");
            } else {
                opt2.getStyleClass().clear();
                opt2.getStyleClass().setAll("null");
                opt3.getStyleClass().clear();
                opt3.getStyleClass().setAll("null");
                opt4.getStyleClass().clear();
                opt4.getStyleClass().setAll("null");
            }
        } else if (answers[index] == 'B') {
            opt2.getStyleClass().clear();
            opt2.getStyleClass().setAll("correct-answer");
            if (c.equals('A')) {
                opt1.getStyleClass().clear();
                opt1.getStyleClass().setAll("wrong-answer");
                opt3.getStyleClass().clear();
                opt3.getStyleClass().setAll("null");
                opt4.getStyleClass().clear();
                opt4.getStyleClass().setAll("null");
            } else if (c.equals('C')) {
                opt1.getStyleClass().clear();
                opt1.getStyleClass().setAll("null");
                opt3.getStyleClass().clear();
                opt3.getStyleClass().setAll("wrong-answer");
                opt4.getStyleClass().clear();
                opt4.getStyleClass().setAll("null");
            } else if (c.equals('D')) {
                opt1.getStyleClass().clear();
                opt1.getStyleClass().setAll("null");
                opt3.getStyleClass().clear();
                opt3.getStyleClass().setAll("null");
                opt4.getStyleClass().clear();
                opt4.getStyleClass().setAll("wrong-answer");
            } else {
                opt1.getStyleClass().clear();
                opt1.getStyleClass().setAll("null");
                opt3.getStyleClass().clear();
                opt3.getStyleClass().setAll("null");
                opt4.getStyleClass().clear();
                opt4.getStyleClass().setAll("null");
            }
        } else if (answers[index] == 'C') {
            opt3.getStyleClass().clear();
            opt3.getStyleClass().setAll("correct-answer");
            if (c.equals('A')) {
                opt1.getStyleClass().clear();
                opt1.getStyleClass().setAll("wrong-answer");
                opt2.getStyleClass().clear();
                opt2.getStyleClass().setAll("null");
                opt4.getStyleClass().clear();
                opt4.getStyleClass().setAll("null");
            } else if (c.equals('B')) {
                opt1.getStyleClass().clear();
                opt1.getStyleClass().setAll("null");
                opt2.getStyleClass().clear();
                opt2.getStyleClass().setAll("wrong-answer");
                opt4.getStyleClass().clear();
                opt4.getStyleClass().setAll("null");
            } else if (c.equals('D')) {
                opt1.getStyleClass().clear();
                opt1.getStyleClass().setAll("null");
                opt2.getStyleClass().clear();
                opt2.getStyleClass().setAll("null");
                opt4.getStyleClass().clear();
                opt4.getStyleClass().setAll("wrong-answer");
            } else {
                opt1.getStyleClass().clear();
                opt1.getStyleClass().setAll("null");
                opt2.getStyleClass().clear();
                opt2.getStyleClass().setAll("null");
                opt4.getStyleClass().clear();
                opt4.getStyleClass().setAll("null");
            }
        } else if (answers[index] == 'D') {
            opt4.getStyleClass().clear();
            opt4.getStyleClass().setAll("correct-answer");
            if (c.equals('A')) {
                opt1.getStyleClass().clear();
                opt1.getStyleClass().setAll("wrong-answer");
                opt2.getStyleClass().clear();
                opt2.getStyleClass().setAll("null");
                opt3.getStyleClass().clear();
                opt3.getStyleClass().setAll("null");
            } else if (c.equals('B')) {
                opt1.getStyleClass().clear();
                opt1.getStyleClass().setAll("null");
                opt2.getStyleClass().clear();
                opt2.getStyleClass().setAll("wrong-answer");
                opt3.getStyleClass().clear();
                opt3.getStyleClass().setAll("null");
            } else if (c.equals('C')) {
                opt1.getStyleClass().clear();
                opt1.getStyleClass().setAll("null");
                opt2.getStyleClass().clear();
                opt2.getStyleClass().setAll("null");
                opt3.getStyleClass().clear();
                opt3.getStyleClass().setAll("wrong-answer");
            } else {
                opt1.getStyleClass().clear();
                opt1.getStyleClass().setAll("null");
                opt2.getStyleClass().clear();
                opt2.getStyleClass().setAll("null");
                opt3.getStyleClass().clear();
                opt3.getStyleClass().setAll("null");
            }
        }
    }
}

