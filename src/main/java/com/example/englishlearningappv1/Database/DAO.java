package com.example.englishlearningappv1.Database;

import java.sql.SQLException;
import java.util.List;

public interface DAO {
    String search(String input) throws SQLException;
    void add(String input, String def) throws SQLException;
    void update(String input, String second_input) throws SQLException;
    void delete(String input) throws SQLException;
}
