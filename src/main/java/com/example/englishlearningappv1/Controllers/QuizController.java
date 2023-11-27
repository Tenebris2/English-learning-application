package com.example.englishlearningappv1.Controllers;

import com.example.englishlearningappv1.Utils.GameBackgroundEffects;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
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

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class QuizController extends TitlebarController implements ControllerInterface {

    String[] questions = {
            "The little boy pleaded _____ not to leave him alone in the dark.",
            "_____, the people who come to this club are in their twenties and thirties.",
            "The TV station, in _______ to massive popular demand, decided not to discontinue the soap opera.",
            "His emotional problems _______ from the attitudes he encountered as a child, I think.",
            "Pete was born and brought up in Cornwall and he knows the place like the _________.",
            "British and Australian people share the same language, but in other respects they are as different as _____.",
            "Rows and silences are ______ and parcel of any marriage.",
            "Ancient Egyptians mummified their dead through the use of chemicals, ______ ancient Peruvians did through natural processes.",
            "Don’t _____ to any conclusion before you know the full facts.",
            "A few animals sometimes fool their enemies _______ to be dead."
    };

    String[][] options = {
            {"on his mother", "his mother", "with his mother", "at his mother"},
            {"By and large", "Altogether", "To a degree", "Virtually"},
            {"reaction", "response", "answer", "rely"},
            {"stem", "flourish", "root", "sprout"},
            {"nose on his face", "back of his hand", "hairs on his head", "teeth of his mouth"},
            {"cats and dogs", "salt and pepper", "chalk and cheese", "here and there"},
            {"package", "stamps", "packet", "part"},
            {"because", "whereas", "even though", "whether or not"},
            {"rush", "dive", "leap", "fly"},
            {"have been appearing", "to be appearing", "to appear", "by appearing"}
    };

    char[] answers = {
            'C',
            'A',
            'B',
            'A',
            'B',
            'C',
            'D',
            'B',
            'C',
            'D'
    };

    public static int correct = 0;
    private int index = 0;
    private int totalSec = 15;

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

    @FXML
    public void backToMenu(ActionEvent event) throws Exception {
        SceneController sceneController = new SceneController();
        sceneController.switchScene(event, GAME_HUB_FXML_FILE_PATH);
    }

    public void playAgain() {
        index = 0;
        correct = 0;
    }

    Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
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
            totalSec = 15;
            timer.setText("Time Left: " + totalSec);

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
}



