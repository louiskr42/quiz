package com.coreelements.de.quizapp;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView    equationTextView;
    Button      submitButton;
    RadioButton radioAnswer1, radioAnswer2, radioAnswer3;
    Quiz        quiz;
    int         positionSolution, positionWrongSolution1, positionWrongSolution2;
    Exercise    exercise;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.grey));
        }

        quiz = new Quiz();

        equationTextView = (TextView)findViewById(R.id.tvEquation);

        radioAnswer1 = (RadioButton)findViewById(R.id.radioAnswer1);
        radioAnswer1.setOnClickListener(this);
        radioAnswer2 = (RadioButton)findViewById(R.id.radioAnswer2);
        radioAnswer2.setOnClickListener(this);
        radioAnswer3 = (RadioButton)findViewById(R.id.radioAnswer3);
        radioAnswer3.setOnClickListener(this);


        submitButton = (Button)findViewById(R.id.btnSubmit);
        submitButton.setOnClickListener(this);

        run();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.radioAnswer1:
                radioAnswer2.setChecked(false);
                radioAnswer3.setChecked(false);
                break;
            case R.id.radioAnswer2:
                radioAnswer1.setChecked(false);
                radioAnswer3.setChecked(false);
                break;
            case R.id.radioAnswer3:
                radioAnswer1.setChecked(false);
                radioAnswer2.setChecked(false);
                break;
            case R.id.btnSubmit:
                if (radioAnswer1.isChecked() || radioAnswer2.isChecked() || radioAnswer3.isChecked()) {
                    checkIfCorrect();
                    run();
                } else {
                    Toast.makeText(this, "You need to select an answer first!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void checkIfCorrect() {
        if (radioAnswer1.isChecked()) {
            if (positionSolution == 1) {
                makeToast(true);
            } else {
                makeToast(false);
            }
        } else if (radioAnswer2.isChecked()) {
            if (positionSolution == 2) {
                makeToast(true);
            } else {
                makeToast(false);
            }
        } else {
            if (positionSolution == 3) {
                makeToast(true);
            } else {
                makeToast(false);
            }
        }
    }

    private void makeToast(boolean isWon) {
        if (isWon) {
            Toast.makeText(this, "Yes that was the correct answer!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No the correct answer was: " +  exercise.getFirstNumber() + " + " +
                            exercise.getSecondNumber() +  " = " + exercise.getSolution(), Toast.LENGTH_SHORT).show();
        }
    }

    private void run() {
        radioAnswer1.setChecked(false);
        radioAnswer2.setChecked(false);
        radioAnswer3.setChecked(false);
        exercise = getNewExercise();
        setButtons(exercise);
        setTextView(exercise);
    }

    private void setTextView(Exercise exercise) {
        equationTextView.setText("" + exercise.getFirstNumber() + " + " + exercise.getSecondNumber() + " = ?");
    }

    private Exercise getNewExercise() {
        Exercise exercise = quiz.createNewEquation();
        return exercise;
    }

    private void setButtons(Exercise exercise) {
        List<Integer> radios = new ArrayList<>();
        radios.add(1);
        radios.add(2);
        radios.add(3);
        Random random = new Random();
        int randomNumber1 = random.nextInt(radios.size());
        positionSolution = radios.get(randomNumber1);
        radios.remove(randomNumber1);
        int randomNumber2 = random.nextInt(radios.size());
        positionWrongSolution1 = radios.get(randomNumber2);
        radios.remove(randomNumber2);
        positionWrongSolution2 = radios.get(0);

        if (positionSolution == 1) {
            radioAnswer1.setText(exercise.getSolution() + "");
        } else if (positionSolution == 2) {
            radioAnswer2.setText(exercise.getSolution() + "");
        } else if (positionSolution == 3) {
            radioAnswer3.setText(exercise.getSolution() + "");
        }

        if (positionWrongSolution1 == 1) {
            radioAnswer1.setText(exercise.getWrongSolution1() + "");
        } else if (positionWrongSolution1 == 2) {
            radioAnswer2.setText(exercise.getWrongSolution1() + "");
        } else if (positionWrongSolution1 == 3) {
            radioAnswer3.setText(exercise.getWrongSolution1() + "");
        }

        if (positionWrongSolution2 == 1) {
            radioAnswer1.setText(exercise.getWrongSolution2() + "");
        } else if (positionWrongSolution2 == 2) {
            radioAnswer2.setText(exercise.getWrongSolution2() + "");
        } else if (positionWrongSolution2 == 3) {
            radioAnswer3.setText(exercise.getWrongSolution2() + "");
        }


    }
}
