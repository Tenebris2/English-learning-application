package com.example.englishlearningappv1;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.animation.AnimationTimer;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;


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

    public int totalSec = 15;

    private Stage stage;

    private Scene scene;

    final private static String RESULT_FXML_FILE_PATH = "src/main/resources/com/example/englishlearningappv1/result.fxml";

    @FXML
    public Label timer;

    @FXML
    public Label question;

    @FXML
    public Button opt1, opt2, opt3, opt4;

    static int counter = 0;
    static int correct = 0;
    static int wrong = 0;

    @FXML
    private void initialize() {
        loadQuestions();
        timeline.play();
    }

    public void playAgain() {
        counter = 0;
        correct = 0;
        wrong = 0;
    }

    Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    timer.setText("Time Left: " + String.valueOf(totalSec));
                    totalSec--;
                    if (totalSec <= 0) {
                        timeline.stop(); // Dừng đếm ngược khi đạt 0 giây
                    }
                }
            })
    );



    private void loadQuestions() {
        totalSec = 15;
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        if (counter < questions.length) {
            question.setText(questions[counter]);
            opt1.setText(options[counter][0]);
            opt2.setText(options[counter][1]);
            opt3.setText(options[counter][2]);
            opt4.setText(options[counter][3]);
        }
    }


    private void displayAnswer() {
        timeline.stop();

        Timeline pause = new Timeline();


        loadQuestions();
    }


    boolean checkAnswer(String answer) {

        if (counter == 0) {
            if (answer.equals("21")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 1) {
            if (answer.equals("Thomas Alva Edison")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 2) {
            if (answer.equals("Neptune")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 3) {
            if (answer.equals("Ganymede")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 4) {
            if (answer.equals("Non Ductile")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 5) {
            if (answer.equals("Louis Pasteur")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 6) {
            if (answer.equals("Stomach")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 7) {
            if (answer.equals("Cheetah")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 8) {
            if (answer.equals("Green")) {
                return true;
            } else {
                return false;
            }
        }
        if (counter == 9) {
            if (answer.equals("5th June")) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }


    @FXML
    public void opt1clicked(ActionEvent event) {
        displayAnswer();
        checkAnswer(opt1.getText().toString());
        if (checkAnswer(opt1.getText().toString())) {
            correct++;
        } else {
            wrong++;
        }
        if (counter == 9) {
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
            counter++;
            displayAnswer();
        }

    }



    @FXML
    public void opt2clicked(ActionEvent event) {
        checkAnswer(opt2.getText().toString());
        if (checkAnswer(opt2.getText().toString())) {
            correct = correct + 1;
        } else {
            wrong = wrong + 1;
        }
        if (counter == 9) {
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
            counter++;
            displayAnswer();
        }
    }

    @FXML
    public void opt3clicked(ActionEvent event) {
        checkAnswer(opt3.getText().toString());
        if (checkAnswer(opt3.getText().toString())) {
            correct = correct + 1;
        } else {
            wrong = wrong + 1;
        }
        if (counter == 9) {
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
            counter++;
            displayAnswer();
        }
    }

    @FXML
    public void opt4clicked(ActionEvent event) {
        checkAnswer(opt4.getText().toString());
        if (checkAnswer(opt4.getText().toString())) {
            correct = correct + 1;
        } else {
            wrong = wrong + 1;
        }
        if (counter == 9) {
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
            counter++;
            displayAnswer();
        }
    }
}

