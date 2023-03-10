package com.example.workit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        RelativeLayout stepcounter, dailyWorkout;

        stepcounter = findViewById(R.id.relstepcounter);
        dailyWorkout = findViewById(R.id.reldailyexercise);


        stepcounter.setOnClickListener(v -> {


            Intent i = new Intent(this, StepCounterActivity.class);

            startActivity(i);
        });

        dailyWorkout.setOnClickListener(v -> {

            Intent i = new Intent(this, DaysActivity.class);

           startActivity(i);
        });

    }

}