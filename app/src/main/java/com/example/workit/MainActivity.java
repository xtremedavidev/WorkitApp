package com.example.workit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {

    private TextView countdownText, instruction, description, skip;
    private long timeLeftInMilliseconds = 60000; // 60 seconds

    private GifImageView gifImageView;
    int[] progress = {0};
    private dayexerciseList dayexerciseList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instruction = findViewById(R.id.exercise);
        description = findViewById(R.id.exercisetxt);
        gifImageView = findViewById(R.id.exerciseimg);

        dayexerciseList dayexerciseList  = new dayexerciseList();
        skip = findViewById(R.id.skiptxt);
        skip.setOnClickListener(v -> {


                Intent i = new Intent(this, DaysActivity.class);
                startActivity(i);
                finish();

        });

        int position = getIntent().getIntExtra("position", 0);

        ArrayList<exercise> exercises =  dayexerciseList.getDaylist1();

        if (position == 1) {
            exercises = dayexerciseList.getDaylist1();
        } else if (position == 2 || position == 22) {
            exercises = dayexerciseList.getDaylist2();
        } else if (position == 3 || position == 24) {
            exercises = dayexerciseList.getDaylist3();
        } else if (position == 4 || position == 25) {
            exercises = dayexerciseList.getDaylist4();
        } else if (position == 5 || position == 26) {
            exercises = dayexerciseList.getDaylist5();
        } else if (position == 6 || position == 27) {
            exercises = dayexerciseList.getDaylist6();
        } else if (position == 7 || position == 28) {
            exercises = dayexerciseList.getDaylist7();
        } else if (position == 8 || position == 29) {
            exercises = dayexerciseList.getDaylist8();
        } else if (position == 9  || position == 30) {
            exercises = dayexerciseList.getDaylist9();
        } else if (position == 10  || position == 23) {
            exercises = dayexerciseList.getDaylist10();
        } else if (position == 11 || position == 16) {
            exercises = dayexerciseList.getDaylist11();
        } else if (position == 12 || position == 17) {
            exercises = dayexerciseList.getDaylist12();
        } else if (position == 13 || position == 18 || position == 21) {
            exercises = dayexerciseList.getDaylist13();
        } else if (position == 14 || position == 19) {
            exercises = dayexerciseList.getDaylist14();
        } else if (position == 15 || position == 20) {
            exercises = dayexerciseList.getDaylist15();
        }






        countdownText = findViewById(R.id.timer);





        Handler handler = new Handler();
        ArrayList<exercise> finalExercises = exercises;
        Runnable runnable = new Runnable() {
                @Override
                public void run() {

                    boolean completed = false;


                    if (progress[0] < finalExercises.size()){

                        completed = false;

                        instruction.setText(finalExercises.get(progress[0]).getInstruction());
                        description.setText(finalExercises.get(progress[0]).getDescription());
                        gifImageView.setBackgroundResource(finalExercises.get(progress[0]).getGiflink());

                        // start the countdown timer
                        startCountdown(finalExercises.get(progress[0]).getTime());


                        handler.postDelayed(this, ((finalExercises.get(progress[0]).getTime()) * 1000));// change after this time

                        progress[0] = progress[0] + 1;


                    }
                    if (progress[0] == finalExercises.size()){

                        handler.postDelayed(() -> {
                            Intent i = new Intent(MainActivity.this, completionActivity.class);
                            SharedPrefManager sharedPrefManager = SharedPrefManager.getInstance(MainActivity.this);
                            sharedPrefManager.setDayCompleted(position);
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            String currentDate = sdf.format(new Date());
                            sharedPrefManager.setDateCompleted(position, currentDate);
                            startActivity(i);
                            finish();
                        }, (finalExercises.get(progress[0] - 1).getTime()) * 1000L);

                    }

                }
            };

        handler.postDelayed(runnable, 1000);// Repeat after 200 milliseconds






    }



    public void startCountdown(int seconds) {
        final TextView countdownText = findViewById(R.id.timer);
        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.beep);


        new CountDownTimer(seconds * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                long secondsRemaining = millisUntilFinished / 1000;


                String countdown = String.format(Locale.getDefault(), "%02d:%02d",
                        secondsRemaining / 60, secondsRemaining % 60);
                countdownText.setText(countdown);

                if (secondsRemaining <= 5) {
                    countdownText.setTextColor(Color.RED);
                     mediaPlayer.start();

                }else {
                    countdownText.setTextColor(getResources().getColor(R.color.dark_blue));
                }
            }

            public void onFinish() {
                countdownText.setText("00:00");
                mediaPlayer.stop();
            }
        }.start();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i = new Intent(this, DaysActivity.class);
        startActivity(i);
        finish();
    }
}