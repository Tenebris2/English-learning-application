package com.example.englishlearningappv1.API;

import java.io.IOException;
import java.sql.SQLException;

public interface APInterface {
    String sendQuery(String input) throws IOException, SQLException;
}
