package com.example.englishlearningappv1.Functions;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class VipFunction {
    private Button vipBtn;
    private final String VIP_TAG = "#vip";

    public VipFunction(Scene scene){
        vipBtn = (Button) scene.lookup(VIP_TAG);
    }

    public void setVipBtn(){
        vipBtn.setOnMouseClicked(e -> {
            showVipInformation();
        });
    }

    public void showVipInformation(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("V.I.P");
        alert.setHeaderText("Want to become VIP? Pay 1.000$ to become VIP.1!");
        alert.setContentText("-No more ads! \n"+"-Can store up to 50 words! \n" + "-And more amazing functions!");

        alert.showAndWait();

    }
}
