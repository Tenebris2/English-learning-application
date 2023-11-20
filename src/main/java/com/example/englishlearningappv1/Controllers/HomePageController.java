package com.example.englishlearningappv1.Controllers;

import com.example.englishlearningappv1.Utils.BackgroundEffects;
import com.example.englishlearningappv1.Utils.FunctionEffects;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.sql.Time;

public class HomePageController extends TitlebarController {

    private final FunctionEffects functionEffects = new FunctionEffects();
    private boolean clicked;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Circle introductionChapterCircle;
    private final SceneController sceneController = new SceneController();
    @FXML
    private BorderPane homePage;
    @FXML
    private Button learnButton;
    @FXML
    private Button gamesButton;

    @FXML
    private Button dictionaryButton;

    @FXML
    private Button startChapterButton;

    @FXML
    private Button displayButton;

    @FXML
    private Label initLabel;

    @FXML
    private AnchorPane popupPane;

    @FXML
    private Button startingButton;

    @FXML
    private Button getHelloChapterButton;

    @FXML
    private Button getSchoolChapterButton;

    @FXML
    private Label logoText;
    @FXML
    private static final String firstBaseStyle =
            "-fx-border-radius: 100px;\n" +
            "-fx-background-color: transparent;";
    private static final String secondBaseStyle = """
                    -fx-background-color: linear-gradient(to right top, #ffcbf2, #ec38bc, #7303c0, #03001e);
                                                                              -fx-background-radius: 100px; /* Adjust the radius as needed */
            -fx-border-radius: 100px; /* Adjust the radius as needed */
            -fx-box-shadow: 0 4px 15px 0 rgba(252, 104, 110, 0.75);
                        """;

    public void initialize() {
        BackgroundEffects backgroundEffects = new BackgroundEffects();
        backgroundEffects.backgroundEffects(mainPane, 500);
        backgroundEffects.shootingStarsEffect(mainPane);
    }

    public void gotoHome(ActionEvent event) throws IOException {
        sceneController.switchToHomePage(event);
    }

    public void gotoDictionary(ActionEvent event) throws Exception {
        sceneController.switchtoDictionary(event);
    }

    public void gotoChapterStart(ActionEvent event) throws IOException {
        sceneController.switchToChapterStart(event);
    }

    public void gotoQuiz(ActionEvent event) throws IOException {
        sceneController.switchtoQuiz(event);
    }

    public void gotoWordle(ActionEvent event) throws IOException {
        sceneController.switchtoWordle(event);
    }

    public void gotoGameHub(ActionEvent event) throws IOException {
        sceneController.switchToGameHub(event);
    }

    public void openGoogleTranslate(ActionEvent event) throws IOException {
        sceneController.openGoogleTranslate(event);
    }

    public void initLabel(ActionEvent event) {
        Button source = (Button) event.getSource();
        initLabel.setText(source.getText());
        displayButton.setText(source.getText());
        popupPane.setLayoutX(source.getLayoutX() - 25);
        popupPane.setLayoutY(source.getLayoutY() + 135);
        double popupPanePrefHeight = 118;
        double popupPanePrefWidth = 207;
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.20), popupPane);
        popupPane.setOpacity(1);
        double move = 0;
        String id = source.getId();
        if (!clicked) {
            // Set the scaling factors
            scaleTransition.setToX(1.0);
            scaleTransition.setToY(1.0);
            move = 0;
        } else {
            // Set the scaling factors
            scaleTransition.setToX(0);
            scaleTransition.setToY(0);
            move = -popupPane.getPrefHeight() * 0.5;
        }
        // Play the animation
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.20), popupPane);
        translateTransition.setToY(move); // Move the full height upwards
        // Create a ParallelTransition to play both animations in parallel
        ParallelTransition parallelTransition = new ParallelTransition(scaleTransition, translateTransition);
        clicked  = !clicked;
        if (clicked) {
            // Set opacity to 1 (visible) when expanding
            parallelTransition.setOnFinished(e -> popupPane.setOpacity(1));
        } else {
            // Set opacity to 0 (invisible) when shrinking
            parallelTransition.setOnFinished(e -> popupPane.setOpacity(0));
        }
        // Play the animation
        parallelTransition.play();
    }

    public void inEffects1(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle(firstBaseStyle
                + "-fx-background-color: linear-gradient(to right top, #ffcbf2, #ec38bc, #7303c0, #03001e)");
    }

    public void outEffects1(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle(firstBaseStyle);
    }

    public void inEffects2(MouseEvent event) {
        functionEffects.inEffects2(event, secondBaseStyle);
    }

    public void outEffects2(MouseEvent event) {
        functionEffects.outEffects2(event, secondBaseStyle);
    }

    @FXML
    public void handleClickMovement(MouseEvent mouseEvent) {
        super.handleClickMovement(mouseEvent,homePage);
    }

    @FXML
    public void handleMovementAction(MouseEvent mouseEvent) {
        super.handleMovementAction(mouseEvent, homePage);
    }

    @FXML
    public void goToQuiz(ActionEvent event) throws IOException {
        SceneController sceneController = new SceneController();
        sceneController.switchtoQuiz(event);
    }

}
