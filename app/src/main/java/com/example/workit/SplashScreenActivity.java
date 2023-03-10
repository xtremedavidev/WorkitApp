package com.example.workit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        Handler handler = new Handler();
        handler.postDelayed(() -> {

            Intent i = new Intent(this, LoadingActivity.class);
            startActivity(i);
            finish();

        }, 3000);


    }
}