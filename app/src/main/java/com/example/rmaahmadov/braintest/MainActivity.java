package com.example.rmaahmadov.braintest;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    ArrayList<Integer> answears = new ArrayList<>();
    private Button startButton;
    private TextView quessions;
    private int locationOfCorrectAnswear;
    private Button answearButton1;
    private Button answearButton2;
    private Button answearButton3;
    private Button answearButton4;
    private Button playAgain;
    private TextView points;
    private int score = 0;
    private TextView sumTextView;
    private TextView timer;
    private int numberOfQuessions = 0;
    private GridLayout gridLayout;
    
    public void timer(){
        new CountDownTimer(60100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                sumTextView.setText("Your score! "+Integer.toString(score) + "/" + Integer.toString(numberOfQuessions));
                disableToFinish();
            }
        }.start();
    }
    
    
    public void disabletToBeginning(){
        playAgain.setVisibility(View.GONE);
        gridLayout.setVisibility(View.GONE);
        timer.setVisibility(View.GONE);
        quessions.setVisibility(View.GONE);
        points.setVisibility(View.GONE);
        sumTextView.setVisibility(View.GONE);
    }
    
    public void enableToTryAgain(){
        gridLayout.setVisibility(View.VISIBLE);
        timer.setVisibility(View.VISIBLE);
        quessions.setVisibility(View.VISIBLE);
        points.setVisibility(View.VISIBLE);
        sumTextView.setVisibility(View.VISIBLE);

    }

    public void disableToFinish() {
        answearButton1.setEnabled(false);
        answearButton2.setEnabled(false);
        answearButton3.setEnabled(false);
        answearButton4.setEnabled(false);
        timer.setEnabled(false);
        points.setEnabled(false);
        quessions.setTextColor(Color.parseColor("#ADADAD"));
        playAgain.setVisibility(View.VISIBLE);
    }

    public void generateQuessions() {
        Random rando = new Random();
        int a = rando.nextInt(21);
        int b = rando.nextInt(21);
        quessions.setText(Integer.toString(a) + "+" + Integer.toString(b));
        locationOfCorrectAnswear = rando.nextInt(4);
        answears.clear();
        int incorrectAnswear;
        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswear) {
                gridLayout.setClickable(true);
                answears.add(a + b);
            } else {
                incorrectAnswear = rando.nextInt(41);
                while (incorrectAnswear == a + b) {
                    incorrectAnswear = rando.nextInt(41);
                }
                answears.add(incorrectAnswear);
            }
        }
        answearButton1.setText(Integer.toString(answears.get(0)));
        answearButton2.setText(Integer.toString(answears.get(1)));
        answearButton3.setText(Integer.toString(answears.get(2)));
        answearButton4.setText(Integer.toString(answears.get(3)));

        
    }
    
    public void playAgain(View view){
        generateQuessions();
        answearButton1.setEnabled(true);
        answearButton2.setEnabled(true);
        answearButton3.setEnabled(true);
        answearButton4.setEnabled(true);
        timer.setEnabled(true);
        points.setEnabled(true);
        quessions.setTextColor(Color.parseColor("#000000"));
        playAgain.setVisibility(View.INVISIBLE);
        sumTextView.setText("");
        timer();
        points.setText("0/0");
        score=0;
        numberOfQuessions=0;
        
    }
    
    
    public void chooseAnswear(View view) {
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswear))) {
            score++;
            sumTextView.setText("Correct!");
        } else {
            sumTextView.setText("Wrong!");
        }
        numberOfQuessions++;
        points.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuessions));
        generateQuessions();
    }


    public void start(View view) {
        gridLayout.setVisibility(View.VISIBLE);
        timer.setVisibility(View.VISIBLE);
        quessions.setVisibility(View.VISIBLE);
        points.setVisibility(View.VISIBLE);
        sumTextView.setVisibility(View.VISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        startButton.setVisibility(View.INVISIBLE);
        generateQuessions();
        timer();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = findViewById(R.id.btnStart);
        quessions = findViewById(R.id.textViewQuessions);
        answearButton1 = findViewById(R.id.answearButton1);
        answearButton2 = findViewById(R.id.answearButton2);
        answearButton3 = findViewById(R.id.answearButton3);
        answearButton4 = findViewById(R.id.answearButton4);
        sumTextView = findViewById(R.id.sumTextView);
        points = findViewById(R.id.textViewAnswears);
        timer = findViewById(R.id.textViewTimeToLeft);
        playAgain=findViewById(R.id.btnPlayAgain);
        gridLayout = findViewById(R.id.gridlayoutForAnswearButtons);
        disabletToBeginning();
        
    }
}
