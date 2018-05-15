package Lib;
/**
 * Created by ksenobait09 on 13.05.18.
 */

import java.util.ArrayList;

public final class Data {
    class Answer {
        // Конструктор Answer
        Answer(String answer, boolean isRight) {
            text = answer;
            this.isRight = isRight;
        }

        String text;
        boolean isRight;
    }

    class Question {
        // Конструктор Question
        Question(String s) {
            answers = new ArrayList<Answer>();
            text = s;
        }

        int addAnswer(String answer, boolean isRight) {
            int id = answers.size();
            Answer ans = new Answer(answer, isRight);
            answers.add(ans);
            if (isRight) {
                rightAnswerId = id;
            }
            return id;
        }

        String text;
        ArrayList<Answer> answers;
        int rightAnswerId;
    }
    int addQuestion(String question) {
        int id = questions.size();
        Question q = new Question(question);
        questions.add(q);
        return id;
    }
    void setTestName(String testName) {
        this.testName = testName;
    }

    public Data() {
         questions = new ArrayList<Question>();
    }
    ArrayList<Question> questions;
    String testName;
}
