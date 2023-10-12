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
                    timer.setText("Time Left: " + String.valueOf(totalSec));
                    totalSec--;
                    if (totalSec < 0) {
                        timeline.stop(); // Dừng đếm ngược khi đạt 0 giây
                    }
                }
            })
    );

    private void loadQuestions() {

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

        totalSec = 15;
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        if (index < questions.length) {
            question.setText(questions[index]);
            opt1.setText(options[index][0]);
            opt2.setText(options[index][1]);
            opt3.setText(options[index][2]);
            opt4.setText(options[index][3]);
        }
    }

    private void displayAnswer() {
        timeline.stop();

        opt1.setDisable(true);
        opt2.setDisable(true);
        opt3.setDisable(true);
        opt4.setDisable(true);

        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(2));

        pauseTransition.setOnFinished(event -> {
            totalSec = 15;
            timer.setText("Time Left: " + String.valueOf(totalSec));

            opt1.setDisable(false);
            opt2.setDisable(false);
            opt3.setDisable(false);
            opt4.setDisable(false);

            loadQuestions();
        });

        pauseTransition.play();
    }


    boolean checkAnswer(Character c) {
        if (c.equals(answers[index])) {
            return true;
        }
        return false;
    }


    @FXML
    public void opt1clicked(ActionEvent event) {
        Character c = (Character) opt1.getUserData();
        if (checkAnswer(c)) {
            correct++;
        } else {
            wrong++;
        }
        displayAnswer();
        if (index == 9) {
            try {
                URL url = new File(RESULT_FXML_FILE_PATH).toURI().toURL();
                Parent root = FXMLLoader.load(url);
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch(IOException e) {
                e.printStackTrace();
            }
        } else {
            index++;
            displayAnswer();
        }

    }



    @FXML
    public void opt2clicked(ActionEvent event) {
        Character c = (Character) opt1.getUserData();
        if (checkAnswer(c)) {
            correct++;
        } else {
            wrong++;
        }
        if (index == 9) {
            try {

                URL url = new File(RESULT_FXML_FILE_PATH).toURI().toURL();
                Parent root = FXMLLoader.load(url);
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch(IOException e) {
                e.printStackTrace();
            }
        } else {
            index++;
            displayAnswer();
        }
    }

    @FXML
    public void opt3clicked(ActionEvent event) {
        Character c = (Character) opt1.getUserData();
        if (checkAnswer(c)) {
            correct++;
            opt1.getStyleClass().clear();
            opt1.getStyleClass().setAll("null");
            opt2.getStyleClass().clear();
            opt2.getStyleClass().setAll("null");
            opt3.getStyleClass().clear();
            opt3.getStyleClass().setAll("correct-answer");
            opt4.getStyleClass().clear();
            opt4.getStyleClass().setAll("null");

            System.out.println(c);
        } else {
            opt3.getStyleClass().clear();
            opt3.getStyleClass().setAll("wrong-answer");
        }
        if (index == 9) {
            try {
                URL url = new File(RESULT_FXML_FILE_PATH).toURI().toURL();
                Parent root = FXMLLoader.load(url);
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch(IOException e) {
                e.printStackTrace();
            }
        } else {
            index++;
            displayAnswer();
        }
    }

    @FXML
    public void opt4clicked(ActionEvent event) {
        Character c = (Character) opt1.getUserData();
        if (checkAnswer(c)) {
            correct++;
        } else {
            wrong++;
        }
        if (index == 9) {
            try {
                URL url = new File(RESULT_FXML_FILE_PATH).toURI().toURL();
                Parent root = FXMLLoader.load(url);
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch(IOException e) {
                e.printStackTrace();
            }
        } else {
            index++;
            displayAnswer();
        }
    }

    private void updateAnswerStyle() {
        String styleClass;

    }
}

