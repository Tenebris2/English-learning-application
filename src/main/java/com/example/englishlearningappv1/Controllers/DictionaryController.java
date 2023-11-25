package com.example.englishlearningappv1.Controllers;

import com.example.englishlearningappv1.Functions.CRUDFunctions;
import com.example.englishlearningappv1.Root;
import com.example.englishlearningappv1.Utils.FunctionEffects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;

import java.sql.SQLException;
import java.util.List;

public class DictionaryController extends HomePageController implements ControllerInterface {

    @FXML
    private WebView defView;
    private static final String sourcePath = "C:\\files and projects\\English-learning-application\\src\\main\\resources\\com\\example\\englishlearningappv1\\fxml\\image\\";
    private static final String fixButtonImageURL = sourcePath + "icons8-fix-1--unscreen.gif";
    private static final String fixButtonImageURLDefault = sourcePath + "icons8-fix-1--unscreen.png";
    private static final String speakButtonImageURL = sourcePath + "icons8-voice-unscreen.gif";
    private static final String speakButtonImageURLDefault = sourcePath + "icons8-voice-unscreen.png";
    private static final String addButtonImageURL = sourcePath + "icons8-add-unscreen.gif";
    private static final String addButtonImageURLDefault = sourcePath + "icons8-add-unscreen.png";
    private static final String addFavButtonImageURL = sourcePath + "icons8-add-favorite-unscreen.gif";
    private static final String addFavButtonImageURLDefault = sourcePath + "icons8-add-favorite-unscreen.png";
    private final String baseStyle = """
                -fx-background-color: rgba(27, 25, 57, 0.2);

                -fx-border-radius: 16px;
                -fx-border-style: solid;
                -fx-background-radius: 10px;
                -fx-text-fill: white;\
            """;
    @FXML
    public void fixButtonInEffects(MouseEvent event) {
        functionEffects.inEffects4(event, fixButtonImageURL, width, height);
        functionEffects.inEffects2(event, baseStyle);
    }
    @FXML
    public void addToFavorites(ActionEvent event) throws SQLException {
        Root root = new Root();
        List<String> list = CRUDFunctions.createFavoriteWordList();
        String current_word = root.getCurrent();
        if (!list.contains(current_word)) {
            try {
                CRUDFunctions.addFavoriteWord(current_word);
            } catch (SQLException e) {
                System.err.println("Error at addToFavorites in class DictionaryController");
            }
        }
    }

    @FXML
    public void fixButtonOutEffects(MouseEvent event) {
        functionEffects.outEffects4(event, fixButtonImageURLDefault, width, height);
        functionEffects.outEffects2(event, baseStyle);
    }

    @FXML
    public void speakButtonInEffects(MouseEvent event) {
        functionEffects.inEffects4(event, speakButtonImageURL, width, height);
        functionEffects.inEffects2(event, baseStyle);
    }
    @FXML
    public void speakButtonOutEffects(MouseEvent event) {
        functionEffects.outEffects4(event, speakButtonImageURLDefault, width, height);
        functionEffects.outEffects2(event, baseStyle);
    }

    @FXML
    public void addButtonInEffects(MouseEvent event) {
        functionEffects.inEffects4(event, addButtonImageURL, width, height);
        functionEffects.inEffects2(event, baseStyle);
    }
    @FXML
    public void addButtonOutEffects(MouseEvent event) {
        functionEffects.outEffects4(event, addButtonImageURLDefault, width, height);
        functionEffects.outEffects2(event, baseStyle);
    }

    public void addFavButtonInEffects(MouseEvent event) {
        functionEffects.inEffects4(event, addFavButtonImageURL, width, height);
        functionEffects.inEffects2(event, baseStyle);
    }
    public void addFavButtonOutEffects(MouseEvent event) {
        functionEffects.inEffects4(event, addFavButtonImageURLDefault, width, height);
        functionEffects.inEffects2(event, baseStyle);
    }

}
