package com.example.englishlearningappv1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.List;

public class HistorySearch {
    private static final String HISTORY_FILE_PATH = "C:\\Users\\Asus\\IdeaProjects\\English-learning-app-v1\\src\\main\\resources\\files\\history.txt";
    private static List<String> wordSearchList = new ArrayList<>();
    private List<String> historySearch = new ArrayList<>();
    private final short MAX_HISTORY_WORD = 30;
    private short num = 0;

    //Load lịch sử tìm kiếm từ file
    public void loadHistory() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(HISTORY_FILE_PATH));
        String line;
        while ((line = br.readLine()) != null) {
            wordSearchList.add(line);
            num++;
        }
        updateHistory();
    }

    //Thêm từ vào lịch sử tìm kiếm
    public void addToHistory(String word){
        if(wordSearchList.contains(word)){
            wordSearchList.remove(word);
        }else{
            num++;
            if(num >=MAX_HISTORY_WORD)
                wordSearchList.remove(0);
        }
        wordSearchList.add(word);
        updateHistory();
    }

    //Cập nhật lịch sử tìm kiếm sau khi thêm
    private void updateHistory(){
        historySearch.clear();
        historySearch.addAll(wordSearchList);
        Collections.reverse(historySearch);
    }

    //Lấy lịch sử tìm kiếm
    public List<String> getHistory(){
        return historySearch;
    }

    public static void saveHistory(){
        try {
            Formatter fileOut = new Formatter(HISTORY_FILE_PATH);
            for (String word : wordSearchList) {
                fileOut.format("%s%s", word, "\r\n");
            }
            fileOut.close();
            System.out.println("Saved");
        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }
    }
}
