import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class ChangingFunction extends Root {
    private Button changingBtn;
    private String CHANGE_TAG = "#change";

    //Khởi tạo thay đổi nghĩa của từ
    public ChangingFunction(Scene scene) {
        changingBtn = (Button) scene.lookup(CHANGE_TAG);
    }

    //Cập nhật nghĩa mới cho từ
    private void updateWord(Word word) {
        getWordList().put(word.getWord(), word);
    }

    //Thêm bắt sự kiện click cho nút
    public void setChangingBtn(WordViewList wordViewList, DefView defView) {
        changingBtn.setOnMouseClicked(e -> {
            if(!getCurrent().equals("")){
                showChangingDialog(wordViewList, defView);
                setChanged();
            }
        });
    }

    //Hiện bảng đổi từ
    public void showChangingDialog(WordViewList wordViewList, DefView defview) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Change word's definition to dictionary");

        Label label = new Label("Change the definition of \"" + getCurrent() + "\"");
        Label label1 = new Label("New defination:");
        TextArea textField1 = new TextArea();

        GridPane grid = new GridPane();
        grid.add(label, 1, 1);
        grid.add(label1, 1, 2);
        grid.add(textField1, 1, 3);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

        Button okBtn = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
        okBtn.addEventFilter(
                ActionEvent.ACTION, event -> {
                    String word = getCurrent();
                    String def = "<html><i>" + word + "</i><br/>" + "<ul><li><font color='#cc0000'><b>"
                            + textField1.getText() + "</b></font></li></ul></html>";
                    updateWord(new Word(word, def));
                    wordViewList.loadWords();
                    defview.loadNewDef(def);
                    setCurrent(word);
                }
        );

        dialog.showAndWait();
    }

}
