package com.example.englishlearningappv1;

import java.util.HashMap;
import java.util.List;

public class MultipleChoice{
    private static Question question;
    private static List<Question> questionList;
    static HashMap<Character,String> hashMap;
    MultipleChoice(Question question) {
        this.question = question;
    };
    MultipleChoice(List<Question> questions) {
        this.questionList = questions;
    };
    public static void makeQuestion() {
        for (String answers : question.getAnswerList()) {
            System.out.println(answers);
        }
    }
    public static void answerQuestion(Character answer) {

        if (answer == question.getCorrentAnswer().charAt(0)) {
            System.out.println("Correct");
        } else {
            System.out.print("Incorrect");
        }
    }

}
