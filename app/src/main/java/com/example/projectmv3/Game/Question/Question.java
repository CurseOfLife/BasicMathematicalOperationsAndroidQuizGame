package com.example.projectmv3.Game.Question;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/*
 *  Question base class
 */

public abstract class Question implements Cloneable {

    protected int firstNumber; //first number in the operation
    protected int secondNumber; //second number in the operation
    protected float answer; //answer of the operation
    protected float[] answersArray; //possible answer choices
    protected int answerPosition;  //correct answer position
    protected int upperLimit; //upper limit of first and second number
    protected String question; //string output for the question -> "5+5"

    //Empty ctor
    private Question() {
    }

    //Ctor takes in the upper limit for both numbers
    public Question(int upperLimit) {
        this.upperLimit = upperLimit;
    }

    //method for creating a new question; abs as the different operations have different implementations
    public abstract void newQuestion();

    //shuffling the array of answers
    //https://stackoverflow.com/questions/1519736/random-shuffling-of-an-array
    protected float[] shuffleArray(float[] array) {
        int index;
        float temp;

        Random randomNumberGenerator = new Random();

        for (int i = array.length - 1; i > 0; i--) {
            index = randomNumberGenerator.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];

            array[i] = temp;
        }
        return array;
    }


    protected static float getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        // return r.nextLong((max - min) + 1) + min;
        return r.nextFloat() % (max - min) + max;
    }

    //creating the four wrong answers and populating the array
    //min and max are represent the range in which the number can be
    //answer is the correct answer used for validation so we dont get the same wrong answer as the right one
    protected static float[] getWrongAnswers(int min, int max, float answer) {
        float[] wrongAnswers = new float[]{0, 0, 0, 0};

        /*
        change to hashset
        HashSet lista = new HasSet();
        float answer;
        do {
             answer = randomBroj();
             lista.add(answer);
        } while ( lista.count() < 4);
        */
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();

        for (int i = 0; i < 4; i++) {

            do {
                wrongAnswers[i] = r.nextFloat() * (max - min) + min;

                if (contains(wrongAnswers, wrongAnswers[i])) {
                    wrongAnswers[i] = r.nextFloat() * (max - min) + min;
                }

            } while (answer == wrongAnswers[i]);
        }

        return wrongAnswers;
    }

    //checking if the array already holds the wrong answer inside of it
    //we want to get 4 distinct wrong answers
    private static boolean contains(final float[] array, final float v) {

        boolean result = false;

        for (float i : array) {
            if (i == v) {
                result = true;
                break;
            }
        }

        return result;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    // GET SET
    public float getFirstNumber() {
        return firstNumber;
    }

    public float getSecondNumber() {
        return secondNumber;
    }

    public float getAnswer() {
        return answer;
    }

    public float[] getAnswersArray() {
        return answersArray;
    }

    public int getUpperLimit() {
        return upperLimit;
    }

    public String getQuestion() {
        return question;
    }

    public void setFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }

    public void setSecondNumber(int secondNumber) {
        this.secondNumber = secondNumber;
    }

    public void setAnswer(float answer) {
        this.answer = answer;
    }

    public void setAnswersArray(float[] answersArray) {
        this.answersArray = answersArray;
    }

    public void setUpperLimit(int upperLimit) {
        this.upperLimit = upperLimit;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}