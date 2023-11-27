package com.example.englishlearningappv1;

import com.example.englishlearningappv1.Database.WordServiceImpl;
import com.example.englishlearningappv1.Functions.CRUDFunctions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Formatter;
import java.util.Map;
import java.util.TreeMap;

public class Dictionary extends CRUDFunctions {

    private final String SPLITTING_CHAR = "<html>";
    private final String E_V_FILE_PATH = "C:\\Users\\admin\\Desktop\\BTL\\English-learning-application\\src\\main\\resources\\text.txt";
    private static Map<String, Word> wordList = new TreeMap<>();

    public Dictionary()
    {

    }
    //Load từ và nghĩa từ file vào TreeMap

    public void createWordList() throws IOException, SQLException {
        wordList = WordServiceImpl.getInstance().createWordList();
    }


    //Lấy ra danh sách từ a.k.a wordList
    public Map<String, Word> getWordList() {
        return wordList;
    }

    public String getDef(String word) throws SQLException {
        return CRUDFunctions.CRUDsearchWord(word);
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

    public void setWordListToNull() {
        wordList = null;
    }

}

