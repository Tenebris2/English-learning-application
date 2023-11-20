package com.example.englishlearningappv1.Controllers;

import com.example.englishlearningappv1.Utils.FunctionEffects;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class DictionaryController extends HomePageController{
    private static final int width = 24;
    private static final int height = 24;
    private Button fix;
    private Button speak;
    private Button change;
    private Button add;

    private static final String sourcePath = "C:\\files and projects\\English-learning-application\\src\\main\\resources\\com\\example\\englishlearningappv1\\fxml\\image\\";
    private static final String fixButtonImageURL = sourcePath + "icons8-fix-unscreen(1).gif";
    private static final String fixButtonImageURLDefault = sourcePath + "icons8-fix-unscreen(1).png";
    private static final String speakButtonImageURL = sourcePath + "icons8-voice-unscreen.gif";
    private static final String speakButtonImageURLDefault = sourcePath + "icons8-voice-unscreen.png";
    private static final String addButtonImageURL = sourcePath + "icons8-add-to-favorites-unscreen.gif";
    private static final String addButtonImageURLDefault = sourcePath + "icons8-add-to-favorites-unscreen.png";
    private FunctionEffects functionEffects = new FunctionEffects();

    @FXML
    public void fixButtonInEffects(MouseEvent event) {
        functionEffects.inEffects4(event, fixButtonImageURL, width, height);
    }
    @FXML
    public void fixButtonOutEffects(MouseEvent event) {
        functionEffects.outEffects4(event, fixButtonImageURLDefault, width, height);
    }

    @FXML
    public void speakButtonInEffects(MouseEvent event) {
        functionEffects.inEffects4(event, speakButtonImageURL, width, height);
    }
    @FXML
    public void speakButtonOutEffects(MouseEvent event) {
        functionEffects.outEffects4(event, speakButtonImageURLDefault, width, height);
    }

    @FXML
    public void addButtonInEffects(MouseEvent event) {
        functionEffects.inEffects4(event, addButtonImageURL, width, height);
    }
    @FXML
    public void addButtonOutEffects(MouseEvent event) {
        functionEffects.inEffects4(event, addButtonImageURLDefault, width, height);
    }

}
