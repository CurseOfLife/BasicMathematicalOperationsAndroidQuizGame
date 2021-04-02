package com.example.projectmv3.Game.Question;

import java.util.ArrayList;
import java.util.Random;

public final class QuestionFactory {

    public static ArrayList<Question> list = new ArrayList<Question>();

    public static Question createQuestion() {
        Question question = null;

        Random r = new Random();
        int ind =  r.nextInt((list.size() - 1) + 1) + 1;
        ind--;
        question = list.get(ind);
        question.newQuestion();
        return question;
    }
}