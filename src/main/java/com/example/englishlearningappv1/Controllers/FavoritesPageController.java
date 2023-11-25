package com.example.englishlearningappv1.Controllers;

import com.example.englishlearningappv1.API.ChatBot;
import com.example.englishlearningappv1.API.SaA;
import com.example.englishlearningappv1.Functions.CRUDFunctions;
import com.example.englishlearningappv1.Root;
import com.example.englishlearningappv1.Utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class FavoritesPageController extends HomePageController {
    @FXML
    private Button favoritesPage;
    @FXML
    private Pane favoriteWordContainer;
    @FXML
    private Label favoriteWordLabel;
    @FXML
    private Button askDefButton;
    @FXML
    private TextArea answerContainerLabel;
    @FXML
    private Button askForUseButton;
    private List<String> listOfFavoriteWords;

    private static final String secondBaseStyle = """
                    -fx-background-color: linear-gradient(to right top, #ffcbf2, #ec38bc, #7303c0, #03001e);
                                                                              -fx-background-radius: 10px; /* Adjust the radius as needed */
            -fx-border-radius: 100px; /* Adjust the radius as needed */
            -fx-box-shadow: 0 4px 15px 0 rgba(252, 104, 110, 0.75);
                        """;
    @Override
    public void initialize() throws SQLException {
        favoritesPage.setStyle(secondBaseStyle);
        listOfFavoriteWords = CRUDFunctions.createFavoriteWordList();
        favoriteWordLabel.setText(Utils.        HTMLParser("hellos\t<html><i>hello /hə'lou/ (halloa) /hə'lou/ (hello) /'he'lou/</i><br/><ul><li><b><i> thán từ</i></b><ul><li><font color='#cc0000'><b> chào anh!, chào chị!</b></font></li></ul><ul><li><font color='#cc0000'><b> này, này</b></font></li></ul><ul><li><font color='#cc0000'><b> ô này! (tỏ ý ngạc nhiên)</b></font><ul></li></ul></ul></li></ul><ul><li><b><i> danh từ</i></b><ul><li><font color='#cc0000'><b> tiếng chào</b></font></li></ul><ul><li><font color='#cc0000'><b> tiếng gọi \"này, này\" !</b></font></li></ul><ul><li><font color='#cc0000'><b> tiếng kêu ô này \"! (tỏ ý ngạc nhiên)</b></font><ul></li></ul></ul></li></ul><ul><li><b><i> nội động từ</i></b><ul><li><font color='#cc0000'><b> chào</b></font></li></ul><ul><li><font color='#cc0000'><b> gọi \"này, này\"</b></font></li></ul><ul><li><font color='#cc0000'><b> kêu \"ô này\" (tỏ ý ngạc nhiên)</b></font></li></ul></li></ul></html>\t346383\n"));
    }

    public void askDef(ActionEvent event) throws SQLException {
        answerContainerLabel.setText("");
        String ans = ChatBot.sendQuery("Tell me the definition of " + favoriteWordLabel.getText() + ", make it quick");
        System.out.println(ans);
        answerContainerLabel.setText(ans);
    }

    public void askSynonymAndAnotonym(ActionEvent event) throws IOException {
        answerContainerLabel.setText("");
        String ans = SaA.sendRequest(favoriteWordLabel.getText());
        System.out.println(ans);
        answerContainerLabel.setText(ans);
    }
    public void revealOptions(ActionEvent event) {

    }

    public void askForUse(ActionEvent event) {
        answerContainerLabel.setText("");
        String ans = ChatBot.sendQuery("How can i use " + favoriteWordLabel.getText() + "in a sentence, make it quick");
        System.out.println(ans);
        answerContainerLabel.setText(ans);
    }

}
