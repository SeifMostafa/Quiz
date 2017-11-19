package com.seif.quiz.model;

/**
 * Created by azizax on 18/11/17.
 */

public class Question {
    String title;
    QuestionType type;
    int []answerIndex;
    String[]answers;

    public Question(String title, QuestionType type, int []answerIndex, String[] answers) {
        this.title = title;
        this.type = type;
        this.answerIndex = answerIndex;
        this.answers = answers;
    }

    public Question(String tittle, QuestionType Type) {
        this.title=tittle;
        this.type=Type;
    }

    public boolean checkAnswer(int[] index){
        return answerIndex==index;
    }
    public boolean checkAnswer(String answer){
        /*
        To contain text analysis
         */
        return true;
    }
    public String getTitle() {
        return title;
    }

    public QuestionType getType() {
        return type;
    }

    public int[] getAnswerIndex() {
        return answerIndex;
    }

    public String[] getAnswers() {
        return answers;
    }
}
