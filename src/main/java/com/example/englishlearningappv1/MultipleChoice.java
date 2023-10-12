package com.example.englishlearningappv1;

import com.example.englishlearningappv1.Functions.CRUDFunctions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import static com.example.englishlearningappv1.Functions.CRUDFunctions.*;

public class MultipleChoice{
    private static Question question;
    private static List<Question> questionList = new ArrayList<>();
    static HashMap<Character,String> hashMap;

    private static int questionNo = 0;

    private static int score = 0;
    public static void printQuestion() {
        for (String answers : question.getAnswerList()) {
            System.out.println(answers);
        }
    }
    public static void answerQuestion(Character answer, Question question_) {

        if (Character.toLowerCase(answer) == Character.toLowerCase(question_.getCorrentAnswer().charAt(0))) {
            System.out.println("Correct");
            score++;
        } else {
            System.out.println("Incorrect");
        }
    }

    public static void play() {
        Collections.shuffle(questionList);
        Scanner scanner = new Scanner(System.in);
        while (questionNo < questionList.size()) {
            System.out.println(questionList.get(questionNo).getQuestion() + "\n" + questionList.get(questionNo).getAnswerList() + "\n");
            answerQuestion(scanner.next().charAt(0),questionList.get(questionNo));
            questionNo++;
        }
        System.out.println(score);
    }

    public static void questionMaker() throws SQLException {
        System.out.println("Enter number of questions: ");
        Scanner scanner = new Scanner(System.in);
        int numberOfQuestions = scanner.nextInt();
        scanner.nextLine();
        String inputFile = "";
        for (int i = 0; i < numberOfQuestions;i++) {
            System.out.println("Enter the question: ");
            String questionToAdd = scanner.nextLine();
            String answerList = "";
            System.out.println("Enter the answer list: ");
            for (int j = 0; j < 4;j++) {
                String tempAnswerList = scanner.nextLine();
                answerList += tempAnswerList + "\t";
            }
            System.out.println("Enter the correct answer: ");
            String correctAnswer = scanner.next();
            CRUDFunctions.CRUDAddQuestion(questionToAdd,answerList,correctAnswer);
        }
    }
    public static List<Question> getQuestionList() {
        return questionList;
    }
    public static List<String> getAnswerListFromString(String s) {
        List<String> list = new ArrayList<>();
        String[] tmp = s.split("\t");
        for (String i : tmp) list.add(i);
        return list;
    }
    public static void initQuestionList() throws SQLException {
        String sql = "SELECT * FROM question_list";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()){
            String question = result.getString("question");
            String answers = result.getString("answer_list");
            String correctAnswer = result.getString("correct_answer");
            Question questionTMP = new Question(question,correctAnswer,getAnswerListFromString(answers));
            questionList.add(questionTMP);
        }
        Collections.shuffle(questionList);
    }

}
