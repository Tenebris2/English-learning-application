import javafx.scene.Scene;
import javafx.scene.control.ListView;

public class WordViewList extends Root{
    private final String tag = "#wordView";
    private ListView<String> wordView;

    //Khởi tạo wordView để xem từ
    public WordViewList(Scene scene){
        wordView = (ListView<String>) scene.lookup(tag);
    }

    //load các từ lên
    public void loadWords() {
        wordView.getItems().clear();
        wordView.getItems().addAll(getWordList().keySet());
    }

    //Load nghĩa khi click vào từ
    public void loadDef(DefView defView, FindField findField) {
        wordView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    findField.hideSearchView();
                    String selectedWordDef = "";
                    if(newValue != null){
                        selectedWordDef = getWordList().get(newValue).getDef();
                    }
                    defView.representDef(selectedWordDef);
                    setCurrent(newValue);
                }
        );
    }

    //Nhảy đến từ tìm
    public void jumpTo(String word){
        wordView.scrollTo(word);
    }
}
