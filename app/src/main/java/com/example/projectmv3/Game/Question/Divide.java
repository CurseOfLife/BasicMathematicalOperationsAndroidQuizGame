package com.example.projectmv3.Game.Question;

import java.text.NumberFormat;
import java.util.Random;

public class Divide extends Question {

    public Divide(int upperLimit) {
        super(upperLimit);
    }

    public String symbol() {
        return "/";
    }

    @Override
    public void newQuestion() {
        Random randomNumberGenerator = new Random();

        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(3);

        this.firstNumber = randomNumberGenerator.nextInt(upperLimit);

        do {
            this.secondNumber = randomNumberGenerator.nextInt(upperLimit);
        } while (this.secondNumber == 0);


        // improvement get one number from 1 to range times 2
        // cant be the same as the real answer or other false answers
        // int maxResult = upperLimit+upperLimit;
        // random1 = rand(1,maxResult);

        this.answer = (float )this.firstNumber / (float)this.secondNumber;
        this.question = firstNumber + "/" + secondNumber;
        this.answerPosition = randomNumberGenerator.nextInt(4);

        float[] wrongAnswers = getWrongAnswers(0,upperLimit, this.answer);

        this.answersArray = new float[]{0, 1, 2, 3};
        this.answersArray[0] =  wrongAnswers[0]; // 1  to random number check above in comment
        this.answersArray[1] =  wrongAnswers[1];
        this.answersArray[2] =  wrongAnswers[2];
        this.answersArray[3] =  wrongAnswers[3];
        // if answer is 10, a variation would be {11, 5, 16, 6}
        // check if numbers are same

        this.answersArray = shuffleArray(this.answersArray);

        answersArray[answerPosition] = answer;
    }
}