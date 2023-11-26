package com.example.englishlearningappv1.API;

import java.io.IOException;
import java.net.ProtocolException;
import java.sql.SQLException;

public interface APInterface {
    String sendQuery(String input) throws IOException, SQLException;
}
