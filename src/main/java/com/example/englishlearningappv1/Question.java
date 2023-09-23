package com.example.englishlearningappv1;

import java.util.List;

public class Question {
    private String question;
    private String correntAnswer;
    private List<String> answerList;

    Question(String que,String corAns,List<String> ansList) {
        question = que;
        correntAnswer = corAns;
        answerList = ansList;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswerList() {
        return answerList;
    }

    public String getCorrentAnswer() {
        return correntAnswer;
    }

    public void setAnswerList(List<String> answerList) {
        this.answerList = answerList;
    }

    public void setCorrentAnswer(String correntAnswer) {
        this.correntAnswer = correntAnswer;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
