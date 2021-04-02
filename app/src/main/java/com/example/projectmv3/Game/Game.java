package com.example.projectmv3.Game;

import com.example.projectmv3.Game.Question.Question;
import com.example.projectmv3.Game.Question.QuestionFactory;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Question> questions;
    //private int total_questions;
    private Question current_question;
    private int correct_number_count;
    private int incorrect_number_count;
    private int score;

    public Game() {
        this.questions = new ArrayList<>();
        this.current_question = null;
        this.correct_number_count = 0;
        this.incorrect_number_count = 0;
        this.score = 0;
    }

    public void createNewQuestions() {
        current_question = QuestionFactory.createQuestion(); // we need to send the upper limit and what question it is

        try {
            questions.add((Question) current_question.clone());
        } catch (CloneNotSupportedException ex) {
        }
    }

    // change to iscorrect or rename to have to do with score
    public boolean evaluateAnswer(float sub_answer) {
        boolean isCorrect;
    //if (current_question.isValid(sub_answer) )

        if (current_question.getAnswer() == sub_answer) {
            correct_number_count++;
            isCorrect = true;
        } else {
            incorrect_number_count++;
            isCorrect = false;
        }
        score = correct_number_count * 10 - incorrect_number_count * 30;

        return isCorrect;
    }

    public List<Question> getQuestions() {
        return questions;
    }
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
    public int getTotal_questions() {
        return questions.size();
    }
    public Question getCurrent_question() {
        return current_question;
    }
    public void setCurrent_question(Question current_question) {
        this.current_question = current_question;
    }
    public int getCorrect_number_count() {
        return correct_number_count;
    }
    public void setCorrect_number_count(int correct_number_count) {
        this.correct_number_count = correct_number_count;
    }
    public int getIncorrect_number_count() {
        return incorrect_number_count;
    }
    public void setIncorrect_number_count(int incorrect_number_count) {
        this.incorrect_number_count = incorrect_number_count;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
}
