package com.example.englishlearningappv1.Utils;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;


import java.util.List;

public class FunctionEffects implements Effects {

    @Override
    public void inEffects1(MouseEvent keyEvent, String baseStyle) {
        ((Node) keyEvent.getSource()).setStyle(baseStyle);
    }

    @Override
    public void outEffects1(MouseEvent keyEvent, String baseStyle) {
        ((Node) keyEvent.getSource()).setStyle(baseStyle);
    }

    @Override
    public void inEffects3(MouseEvent keyEvent) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(150), (ImageView) keyEvent.getSource());
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0.7);
        fadeTransition.setCycleCount(0);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();
        ParallelTransition parallelTransition = new ParallelTransition(fadeTransition);
        parallelTransition.play();
    }

    @Override
    public void outEffects3(MouseEvent keyEvent) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(150), (ImageView) keyEvent.getSource());
        fadeTransition.setFromValue(0.7);
        fadeTransition.setToValue(1);
        fadeTransition.setCycleCount(0);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();
        ParallelTransition parallelTransition = new ParallelTransition(fadeTransition);
        parallelTransition.play();
    }

    @Override
    public void inEffects2(MouseEvent event, String baseStyle) {
        Button button = (Button) event.getSource();
        button.setStyle(baseStyle);
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(150), button);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.7);
        fadeTransition.setCycleCount(0);
        fadeTransition.setAutoReverse(true);
        ParallelTransition parallelTransition = new ParallelTransition(fadeTransition);
        parallelTransition.play();
    }

    @Override
    public void outEffects2(MouseEvent event, String baseStyle) {
        Button button = (Button) event.getSource();
        button.setStyle(baseStyle);
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(150), button);
        fadeTransition.setFromValue(0.7);
        fadeTransition.setToValue(1);
        fadeTransition.setCycleCount(0);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();
        ParallelTransition parallelTransition = new ParallelTransition(fadeTransition);
        parallelTransition.play();
    }

    public void inEffects3(MouseEvent event, String baseStyle) {
        Button button = (Button) event.getSource();
        button.setStyle(baseStyle);
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(150), button);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0.9);
        fadeTransition.setCycleCount(0);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();
    }

    public void inEffects4(MouseEvent event, String URL, int sizeX, int sizeY) {
        Button button = (Button) event.getSource();
        ImageView image = new ImageView(new Image(URL));
        image.setFitWidth(sizeX);
        image.setFitHeight(sizeY);
        button.setGraphic(image);
    }

    public void outEffects4(MouseEvent event, String URL, int sizeX, int sizeY) {
        Button button = (Button) event.getSource();
        ImageView image = new ImageView(new Image(URL));
        image.setFitWidth(sizeX);
        image.setFitHeight(sizeY);
        button.setGraphic(image);
    }

    public void outEffects3(MouseEvent event, String baseStyle) {
        Button button = (Button) event.getSource();
        button.setStyle(baseStyle);
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(150), button);
        fadeTransition.setFromValue(0.9);
        fadeTransition.setToValue(1);
        fadeTransition.setCycleCount(0);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();
    }

    public void createFadeEffectsIn(ActionEvent event, List<Button> buttons) {
        for (Button b : buttons) {
            createFadeTransition(b, 0, 1).play();
        }
    }

    public void createFadeEffectsOut(ActionEvent event, List<Button> buttons) {
        for (Button b : buttons) {
            createFadeTransition(b, 1, 0).play();
        }
    }

    public <T> FadeTransition createFadeTransition(T generic, double fromValue, double toValue) {
        // Create a FadeTransition
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), (Node) generic);
        fadeTransition.setFromValue(fromValue);
        fadeTransition.setToValue(toValue);

        return fadeTransition;
    }
}
