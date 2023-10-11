package com.example.englishlearningappv1;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import static com.example.englishlearningappv1.Functions.CRUDFunctions.*;

public class FindField extends Root {
    private final TextField findTextField;
    private final Button findingBtn;
    private final ListView<String> searchView;

    //private Map<String, Word> searchList = new TreeMap<>();
    private List<String> searchList = new ArrayList<>();

    private final String findTexFieldTag = "#textField";
    private final String findingBtnTag = "#find";
    private final String searchViewTag = "#searchView";

    //Khởi tạo
    public FindField(Scene scene) {
        findTextField = (TextField) scene.lookup(findTexFieldTag);
        findingBtn = (Button) scene.lookup(findingBtnTag);
        searchView = (ListView<String>) scene.lookup(searchViewTag);
        hideSearchView();
    }

    //Lấy text từ textField
    public String getFindFieldValue() {
        return findTextField.getText();
    }

    //Click để tìm kiếm
    public void clickToFindDef(DefView defView, WordViewList wordViewList) {
        findingBtn.setOnAction(e -> {
            hideSearchView();
            String word = getFindFieldValue();
            setCurrent(word);
            addToHistorySearch(word);
            wordViewList.jumpTo(word);
            String def = findDef(getCurrent());
            defView.representDef(def);
        });
    }

    //Enter để tìm kiếm
    public void enterToFindDef(DefView defView, WordViewList wordViewList) {
        findTextField.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER){
                hideSearchView();
                String word = getFindFieldValue();
                setCurrent(word);
                addToHistorySearch(word);
                wordViewList.jumpTo(word);
                String def = findDef(getCurrent());
                defView.representDef(def);
            }
        });
    }

    //Bắt sự kiện khi nhập từ
    public void addListenerToTextField(DefView defView, WordViewList wordViewList) {
        findTextField.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (!newValue.equals("")) {
                        try {
                            searchWord(newValue);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        loadSearchWords();
                        loadSearchViewList(defView, wordViewList);
                        showSearchView();
                    } else {
                        searchList.clear();
                        loadHistory();
                    }
                }
        );

    }

    //Tìm từ
    private void searchWord(String word) throws SQLException {
        searchList.clear();

        String tmp ='"' + word + "%"  +'"';
        String sql = "SELECT * FROM wordlist WHERE english LIKE " + tmp + "";

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()){
            String searchingWord = result.getString("english");
            searchList.add(searchingWord);
        }
    }

    //Load danh sách các từ cần tìm
    private void loadSearchWords() {
        searchView.getItems().clear();
        searchView.getItems().addAll(searchList);
    }

    //Load lịch sử tìm kiếm
    private void loadHistory() {
        searchView.getItems().clear();
        searchView.getItems().addAll(getHistory());
    }

    //Hiển thị lich sử tìm kiếm và bắt sự kiện khi click vào từ
    public void loadSearchViewList(DefView defView, WordViewList wordViewList) {
        searchView.setOnMouseClicked(e -> {
            String temp = searchView.getSelectionModel().getSelectedItem();
            addToHistorySearch(temp);
            wordViewList.jumpTo(temp);
            setCurrent(temp);
            String def = findDef(getCurrent());
            findTextField.setText(getCurrent());
            defView.representDef(def);
            searchView.setVisible(false);
        });
    }

    //Bắt sự kiện khi ấn vào textField
    public void setMouseEventToSearchView() {
        findTextField.setOnMouseClicked(e -> {
            showSearchView();
            if(getFindFieldValue().equals("")){
                loadHistory();
            }
        });
    }

    //Hiện trang tìm kiếm
    public void showSearchView() {
        searchView.setVisible(true);
    }

    //Ẩn trang tìm kiếm
    public void hideSearchView() {
        searchView.setVisible(false);
    }


}
