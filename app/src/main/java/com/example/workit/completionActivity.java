package com.example.workit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class completionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completion);

        Button continuebtn = findViewById(R.id.continuebtn);

        continuebtn.setOnClickListener(v -> {
            Intent i = new Intent(this, CategoryActivity.class);
            startActivity(i);
            finish();
        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i = new Intent(this, CategoryActivity.class);
        startActivity(i);
        finish();
    }
}