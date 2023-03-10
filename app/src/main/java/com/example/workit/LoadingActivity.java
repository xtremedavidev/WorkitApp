package com.example.workit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);


        boolean loaded = increaseProgress();




    }

    public boolean increaseProgress(){

        final int[] progress = {0};
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (progress[0] < 100) {
                    progress[0] = progress[0] + 1;
                    // Update progress bar, text view, or any other UI element here
                    TextView loading = findViewById(R.id.loadingtxt);


                    loading.setText( "Loading...  " + progress[0] + "%");

                    ProgressBar progressBar = findViewById(R.id.loading);
                    progressBar.setMax(100);

                    progressBar.setProgress(progress[0]);

                    handler.postDelayed(this, 100);// Repeat after 200 milliseconds


                    if (progress[0] == 100){
                        Intent i = new Intent(LoadingActivity.this, CategoryActivity.class);
                        startActivity(i);
                        finish();
                    }
                }

            }
        };
        handler.postDelayed(runnable, 100);

        return progress[0] == 100;
    }


}