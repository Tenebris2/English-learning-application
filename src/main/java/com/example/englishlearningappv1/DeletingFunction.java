package com.example.englishlearningappv1;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.sql.SQLException;
import java.util.Optional;

import static com.example.englishlearningappv1.CRUDFunctions.CRUDdeleteWord;

public class DeletingFunction extends Root {
    private final Button dltBtn;
    private final String DELETE_TAG = "#delete";

    //Khởi tạo nút xóa
    public DeletingFunction(Scene scene) {
        dltBtn = (Button) scene.lookup(DELETE_TAG);
    }

    //Tạo bắt sự kiện cho nút xóa
    public void setDeletingFunction(WordViewList wordViewList, DefView defView) {
        dltBtn.setOnMouseClicked(e -> {
            if(!getCurrent().equals("") && getCurrent() != null){
                try {
                    showAlert(wordViewList, defView);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    //Hiển thị cảnh báo hỏi xem có muốn xóa không
    private void showAlert(WordViewList wordViewList, DefView defView) throws SQLException {
        Alert dltAlert = new Alert(Alert.AlertType.CONFIRMATION);
        dltAlert.setTitle("Delete word");
        dltAlert.setHeaderText("Are you sure want to delete \"" + getCurrent() + "\"?");
        dltAlert.setContentText("This word will not be availble anymore");

        Optional<ButtonType> option = dltAlert.showAndWait();

        if (getCurrent()!= null && option.get() == ButtonType.OK) {
            removeWord();
            wordViewList.loadWords();
            defView.clearDefView();
            setChanged();
        }
    }

    //Xóa từ ra khỏi từ điển
    private void removeWord() throws SQLException {
        getWordList().remove(getCurrent());
        CRUDdeleteWord(getCurrent());
        setCurrent("");
    }

}
