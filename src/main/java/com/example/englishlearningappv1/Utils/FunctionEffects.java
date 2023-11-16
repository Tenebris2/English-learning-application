package com.example.englishlearningappv1.Utils;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.Effect;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

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
}
