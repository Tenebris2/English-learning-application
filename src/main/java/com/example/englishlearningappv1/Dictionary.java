package com.example.englishlearningappv1;

import com.example.englishlearningappv1.Functions.CRUDFunctions;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Formatter;
import java.util.Map;
import java.util.TreeMap;

public class Dictionary extends CRUDFunctions {

    private final String SPLITTING_CHAR = "<html>";
    private final String E_V_FILE_PATH = "C:\\Users\\Asus\\IdeaProjects\\English-learning-app-v1\\src\\main\\resources\\files\\text.txt";
    private static Map<String, Word> wordList = new TreeMap<>();

    public Dictionary()
    {

    }
    //Load từ và nghĩa từ file vào TreeMap

    public void createWordList() throws IOException, SQLException {
        String sql = "SELECT * FROM wordlist";

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        String line;

        while (result.next()){
            String word = result.getString("english");
            String definition = result.getString("definition");
            line = word + definition;

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
        if (wordList.containsKey(word)) {
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

