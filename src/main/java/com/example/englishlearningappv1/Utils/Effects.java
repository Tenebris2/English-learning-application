package com.example.englishlearningappv1.Utils;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public interface Effects {
    public void inEffects1(MouseEvent keyEvent, String baseStyle);
    public void outEffects1(MouseEvent keyEvent, String baseStyle);
    public void inEffects3(MouseEvent keyEvent);
    public void outEffects3(MouseEvent keyEvent);
    public void inEffects2(MouseEvent event, String baseStyle);
    public void outEffects2(MouseEvent event, String baseStyle);


}
