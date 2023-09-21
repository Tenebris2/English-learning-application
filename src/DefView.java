import javafx.scene.Scene;
import javafx.scene.web.WebView;

public class DefView extends Root{
    private WebView defView;
    private String tag = "#defView";

    //Khởi tạo WebView xem nghĩa
    public DefView(Scene scene){
        defView = (WebView) scene.lookup(tag);
    }

    //Hiển thị nghĩa
    public void representDef(String def){
        if(def != null){
            defView.getEngine().loadContent(def, "text/html");
        }else {
            clearDefView();
        }
    }

    //xóa hết nd trên webview
    public void clearDefView(){
        defView.getEngine().loadContent("", "text/html");
    }

    //Hiển thị nghĩa mới lên
    public void loadNewDef(String word){
        defView.getEngine().loadContent(word, "text/html");
    }
}
