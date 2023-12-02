package com.example.englishlearningappv1.API;

import com.example.englishlearningappv1.Database.DBConnection;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.awt.*;
import java.io.File;
import java.sql.SQLException;

import static com.example.englishlearningappv1.API.API_KEY.connectDB;

public class TextRecog {
    private static Tesseract tesseract = new Tesseract();
    static {
        tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata");
    }

    public String scan(File file) throws TesseractException {
        String text = tesseract.doOCR(file);

        return text;
    }
}
