package com.coreelements.de.quizapp;


public class Exercise {
    int mFirstNumber, mSecondNumber, mSolution, mWrongSolution1, mWrongSolution2;

    public Exercise(int firstNumber, int secondNumber, int solution, int wrongSolution1, int wrongSolution2) {
        mFirstNumber = firstNumber;
        mSecondNumber = secondNumber;
        mSolution = solution;
        mWrongSolution1 = wrongSolution1;
        mWrongSolution2 = wrongSolution2;
    }

    public int getFirstNumber() {
        return mFirstNumber;
    }

    public int getSecondNumber() {
        return mSecondNumber;
    }

    public int getSolution() {
        return mSolution;
    }

    public int getWrongSolution1() {
        return mWrongSolution1;
    }

    public int getWrongSolution2() {
        return mWrongSolution2;
    }

}
