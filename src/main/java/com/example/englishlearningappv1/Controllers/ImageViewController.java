package com.example.englishlearningappv1.Controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.io.File;

import static com.example.englishlearningappv1.Controllers.ScannerController.imageViewLink;

public class ImageViewController {
    @FXML
    private ImageView imageView;
    @FXML
    private AnchorPane pane;
    @FXML
    public void initialize() {
        System.out.println(imageViewLink);
        try {
            Image image = new Image(imageViewLink);
            imageView.setImage(image);
            pane.setPrefWidth(image.getWidth());
            pane.setPrefHeight(image.getHeight());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
