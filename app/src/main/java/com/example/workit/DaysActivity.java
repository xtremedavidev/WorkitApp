package com.example.workit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class DaysActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private dayexerciseList dayexerciseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days);

        recyclerView = findViewById(R.id.daysrec);
        dayAdapter dayAdapter = new dayAdapter();
        dayexerciseList = new dayexerciseList();


        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(dayAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.ClickListener() {
            public void onClick(View itemselected, int position) {

                Intent i = new Intent(DaysActivity.this, MainActivity.class);

                i.putExtra("position", position);

                startActivity(i);
                finish();




            }



            public void onLongClick(View view, int position) {





            }
        }));




    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i = new Intent(this, CategoryActivity.class);
        startActivity(i);
        finish();
    }
}