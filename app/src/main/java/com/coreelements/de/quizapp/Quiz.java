package com.coreelements.de.quizapp;

import java.util.Random;


public class Quiz {

    public Exercise createNewEquation() {
        int firstNumber = setFirstNumber();
        int secondNumber = setSecondNumber();
        int solution = setSolution(firstNumber, secondNumber);
        int wrongSolution1 = setWrongSolution1(solution);
        int wrongSolution2 = setWrongSolution2(solution);

        Exercise exercise = new Exercise(   firstNumber,
                                            secondNumber,
                                            solution,
                                            wrongSolution1,
                                            wrongSolution2
                                        );
        return exercise;
    }

    private int setWrongSolution2(int solution) {
        Random random = new Random();
        int wrongSolution1 = solution + random.nextInt(5) + 1;
        if (wrongSolution1 == solution) {wrongSolution1++;}
        return wrongSolution1;
    }

    private int setWrongSolution1(int solution) {
        Random random = new Random();
        int wrongSolution2 = solution - random.nextInt(5) + 1;
        if (wrongSolution2 == solution) {wrongSolution2--;}
        return  wrongSolution2;
    }

    private int setFirstNumber() {
        Random random = new Random();
        int firstNumber = random.nextInt(101);
        return firstNumber;
    }

    private int setSecondNumber() {
        Random random = new Random();
        int secondNumber = random.nextInt(101);
        return secondNumber;
    }

    private int setSolution(int firstNumber, int secondNumber) {
        int solution = firstNumber + secondNumber;
        return solution;
    }


}
