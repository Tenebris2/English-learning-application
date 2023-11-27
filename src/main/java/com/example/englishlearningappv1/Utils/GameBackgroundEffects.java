package com.example.englishlearningappv1.Utils;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class GameBackgroundEffects implements Effects {
    private static final int numberOfShootingStars = 100;
    private static final double minDistance = 2;

    private static final int heightOfMainPane = 720;
    private static final int widthOfMainPane = 1080;
    @Override
    public void inEffects1(MouseEvent keyEvent, String baseStyle) {
    }

    @Override
    public void outEffects1(MouseEvent keyEvent, String baseStyle) {

    }

    @Override
    public void inEffects3(MouseEvent keyEvent) {

    }

    @Override
    public void outEffects3(MouseEvent keyEvent) {

    }

    @Override
    public void inEffects2(MouseEvent event, String baseStyle) {

    }

    @Override
    public void outEffects2(MouseEvent event, String baseStyle) {

    }

    public void backgroundEffects(Pane pane, int numberOfStars) {
        for (int i = 0; i < numberOfStars; i++) {
            Circle star = new Circle(0.5, Color.WHITESMOKE);

            // Check for overlapping stars and reposition if needed
            while (hasOverlap(star, pane.getChildren())) {
                double randomOpacity = Math.random();
                double x = Math.random() * widthOfMainPane;
                double y = Math.random() * heightOfMainPane;
                star.setOpacity(randomOpacity);
                star.setCenterX(x);
                star.setCenterY(y);

                FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), star);
                fadeTransition.setFromValue(randomOpacity);
                fadeTransition.setToValue(0.1);
                fadeTransition.setCycleCount(FadeTransition.INDEFINITE);
                fadeTransition.setAutoReverse(true);
                fadeTransition.play();
            }


            pane.getChildren().add(0, star);
        }
    }

    public void shootingStarsEffect(Pane pane) {
        for (int i = 0; i < numberOfShootingStars; i++) {

            Circle star = new Circle(0.5, Color.WHITESMOKE);
            // Check for overlapping stars and reposition if needed
            while (hasOverlap(star, pane.getChildren())) {
                double randomOpacity = Math.random();
                double x = Math.random() * widthOfMainPane;
                double y = Math.random() * heightOfMainPane;
                star.setCenterX(x);
                star.setCenterY(y);
                star.setOpacity(randomOpacity);


                // Create a path for the shooting star to follow (diagonal line)
                Line path = new Line(x, y, x + widthOfMainPane, y + heightOfMainPane);

                // Create a PathTransition for the shooting star
                PathTransition pathTransition = new PathTransition();
                pathTransition.setNode(star);
                pathTransition.setPath(path);
                pathTransition.setInterpolator(javafx.animation.Interpolator.EASE_OUT);
                pathTransition.setCycleCount(PathTransition.INDEFINITE);
                pathTransition.setDuration(Duration.seconds(7));

                FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), star);
                fadeTransition.setFromValue(randomOpacity);
                fadeTransition.setToValue(0.3);
                fadeTransition.setCycleCount(FadeTransition.INDEFINITE);
                fadeTransition.setAutoReverse(true);

                // Combine both transitions in a ParallelTransition
                ParallelTransition parallelTransition = new ParallelTransition(pathTransition, fadeTransition);

                // Start the shooting star animation
                parallelTransition.play();
            }
            pane.getChildren().add(0, star);
        }
    }

    private boolean hasOverlap(Circle newStar, ObservableList<Node> stars) {
        for (Node star : stars) {
            if (star instanceof Circle) {
                double distance = calculateDistance(newStar, (Circle) star);
                if (distance < BackgroundEffects.minDistance) {
                    return true; // Overlapping stars
                }
            }
        }
        return false; // No overlapping stars
    }

    private double calculateDistance(Circle star1, Circle star2) {
        double dx = star1.getCenterX() - star2.getCenterX();
        double dy = star1.getCenterY() - star2.getCenterY();
        return Math.sqrt(dx * dx + dy * dy);
    }
}
