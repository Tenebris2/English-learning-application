package com.example.englishlearningappv1;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class User {
    private Button userBtn;
    private final String USER_TAG = "#user";

    public User(Scene scene){
        userBtn = (Button) scene.lookup(USER_TAG);
    }

    public void setUserBtn() {
        userBtn.setOnMouseClicked(e -> {
            showInformation();
        });
    }

    public void showInformation(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User");
        alert.setHeaderText("The \"User\" function will be availble in next version!");
        alert.setContentText("You will be able to create your own account!");
        alert.showAndWait();

    }

}
