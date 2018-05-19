package Lib;
/**
 * Created by ksenobait09 on 13.05.18.
 */

import java.util.ArrayList;

public final class Data {
    public class Answer {
        // Конструктор Answer
        Answer(String answer, boolean isRight) {
            text = answer;
            this.isRight = isRight;
        }

        String text;
        boolean isRight;
    }

    public class Question {
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

        public String text;
        public ArrayList<Answer> answers;
        public int rightAnswersCount = 0;
        public int answersCount = 0;
        public int rightAnswerId;
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
    public ArrayList<Question> questions;
    public String testName;
}
