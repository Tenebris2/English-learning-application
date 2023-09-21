import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Formatter;
import java.util.Map;
import java.util.TreeMap;

public class Dictionary {
    private final String SPLITTING_CHAR = "<html>";
    private final String E_V_FILE_PATH = "./file/text.txt";
    private static Map<String, Word> wordList = new TreeMap<>();

    //Load từ và nghĩa từ file vào TreeMap
    public void createWordList() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(E_V_FILE_PATH));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(SPLITTING_CHAR);
            parts[1] = "<html>" + parts[1];
            wordList.put(parts[0], new Word(parts[0], parts[1]));
        }
    }

    //Lấy ra danh sách từ a.k.a wordList
    public Map<String, Word> getWordList() {
        return wordList;
    }

    public String getDef(String word) {
        if(wordList.containsKey(word)){
            return wordList.get(word).getDef();
        }
        return null;
    }

    //Lưu vào file
    public void exportToFile() {
        try {
            Formatter fileOut = new Formatter(E_V_FILE_PATH);
            for (Map.Entry<String, Word> entry : wordList.entrySet()) {
                fileOut.format("%s%s%s", entry.getKey()
                        , entry.getValue().getDef(), "\r\n");
            }
            fileOut.close();
            System.out.println("Saved");
        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }
    }
}

