package com.example.englishlearningappv1;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Root {
    private static Dictionary dictionary = new Dictionary();
    private static HistorySearch historySearch = new HistorySearch();
    private static boolean changed = false;

    private static String current = "";

    //Set từ hiện tại
    public void setCurrent(String current) {
        this.current = current;
    }

    //Lấy ra từ hiện tại
    public String getCurrent(){
        return current;
    }

    //Lấy ra wordList
    public static Map<String, Word> getWordList() {
        return dictionary.getWordList();
    }

    //Khởi tạo wordList
    public void createWordList() throws IOException, SQLException {
        dictionary.createWordList();
    }

    //Tìm nghĩa
    public String findDef(String word) {
        return dictionary.getDef(word);
    }

    //Load lịch siwr tìm kiếm
    public void loadHistorySearch() throws IOException {
        historySearch.loadHistory();
    }

    //Thêm vào lịch sử tìm kiếm
    public void addToHistorySearch(String word){
        historySearch.addToHistory(word);
    }

    //Lấy ra lịch sử tìm kiếm
    public List<String> getHistory(){
        return historySearch.getHistory();
    }

    //Đặt thay đổi để lưu file
    public void setChanged(){
        changed = true;
    }

    public boolean getChanged(){
        return changed;
    }
}
