package com.task_3_1c.model;

import java.util.List;

public class QuizModel {
    int id;
    String questionTitle;
    String question;
    List<String> optionList;
    String correctAnswer;

    public QuizModel(int id,String questionTitle, String question, List<String> optionList, String correctAnswer) {
        this.id = id;
        this.question = question;
        this.optionList = optionList;
        this.questionTitle = questionTitle;
        this.correctAnswer = correctAnswer;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptionList() {
        return optionList;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }
}
