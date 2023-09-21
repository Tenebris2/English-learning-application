import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DictionaryApp extends Application {

    private static final String FXML_FILE_PATH = "JavaFx.fxml";
    private static Controller controller = new Controller();
    private Dictionary dic = new Dictionary();
    private HistorySearch his = new HistorySearch();

    public static void main(String[] args) {
        Application.launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource(FXML_FILE_PATH));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dictionary");
        primaryStage.show();

        controller.init(scene);
        controller.initData();
        controller.hideSearchView(root);
        controller.addFunction();

    }

    @Override
    public void stop() throws Exception {
        super.stop();
        if(controller.getChanged()){
            his.saveHistory();
            dic.exportToFile();
            System.out.println("Save changed");
        }else{
            System.out.println("nothing changed");
        }

    }
}
