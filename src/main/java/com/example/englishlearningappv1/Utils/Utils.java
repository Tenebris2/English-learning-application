package com.example.englishlearningappv1.Utils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;

public class Utils {
    public static String HTMLParser(String htmlText) {
        Document doc = Jsoup.parse(htmlText);

        String cleanText = doc.text();

        return cleanText;
    }

    public static void main(String[] args) {
        HTMLParser("hellos\t<html><i>hello /hə'lou/ (halloa) /hə'lou/ (hello) /'he'lou/</i><br/><ul><li><b><i> thán từ</i></b><ul><li><font color='#cc0000'><b> chào anh!, chào chị!</b></font></li></ul><ul><li><font color='#cc0000'><b> này, này</b></font></li></ul><ul><li><font color='#cc0000'><b> ô này! (tỏ ý ngạc nhiên)</b></font><ul></li></ul></ul></li></ul><ul><li><b><i> danh từ</i></b><ul><li><font color='#cc0000'><b> tiếng chào</b></font></li></ul><ul><li><font color='#cc0000'><b> tiếng gọi \"này, này\" !</b></font></li></ul><ul><li><font color='#cc0000'><b> tiếng kêu ô này \"! (tỏ ý ngạc nhiên)</b></font><ul></li></ul></ul></li></ul><ul><li><b><i> nội động từ</i></b><ul><li><font color='#cc0000'><b> chào</b></font></li></ul><ul><li><font color='#cc0000'><b> gọi \"này, này\"</b></font></li></ul><ul><li><font color='#cc0000'><b> kêu \"ô này\" (tỏ ý ngạc nhiên)</b></font></li></ul></li></ul></html>\t346383\n");
    }

}
