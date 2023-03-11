package com.example.workit;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StepCounterActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor stepSensor;
    private int stepCount = 0;
    Button start;
    TextView stepCountTextView;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);
        stepCountTextView = findViewById(R.id.step_count_text_view);

        start = findViewById(R.id.buttonstart);


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);




        start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (stepCount == 0) {
                        sensorManager.registerListener(StepCounterActivity.this, stepSensor, SensorManager.SENSOR_DELAY_NORMAL);
                        Toast.makeText(StepCounterActivity.this, "Step counter started", Toast.LENGTH_SHORT).show();
                    }else {
                        sensorManager.unregisterListener(StepCounterActivity.this);


                        stepCount = 0;
                        stepCountTextView.setText("0");
                        sensorManager.registerListener(StepCounterActivity.this, stepSensor, SensorManager.SENSOR_DELAY_NORMAL);


                    }
                }
            });





    }

    @Override
    protected void onResume() {
        super.onResume();

        // Register the step sensor listener
        if (stepSensor != null) {
            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_NORMAL);
//            if (stepCount != 0){
//                start.setText("Restart");
//                start.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        sensorManager.unregisterListener(StepCounterActivity.this);
//
//                        stepCountTextView.setText("0");
//                        sensorManager.registerListener(StepCounterActivity.this, stepSensor, SensorManager.SENSOR_DELAY_NORMAL);
//
//                    }
//                });
//
//            }else{
//                start.setText("Start");
//                start.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        sensorManager.unregisterListener(StepCounterActivity.this);
//
//                        stepCount = 0;
//                        stepCountTextView.setText("0");
//
//
//                    }
//                });
//            }

        } else {

          android.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);

            // Set the message show for the Alert time
            builder.setMessage("Step sensor is not available on your device!");


            // Set Alert Title
            builder.setTitle("Alert !");

            // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
            builder.setCancelable(false);

            // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
            builder.setPositiveButton("Go Back", (DialogInterface.OnClickListener) (dialog, which) -> {
                // When the user click yes button then app will close

                Intent i = new Intent(this, CategoryActivity.class);

                startActivity(i);

            });




            // Create the Alert dialog
            AlertDialog alertDialog = builder.create();
            // Show the Alert Dialog box
            alertDialog.show();




        }
    }



    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            // Update the step count
            stepCountTextView = findViewById(R.id.step_count_text_view);
            stepCount = (int) event.values[0];
            start = findViewById(R.id.buttonstart);

            // Display the step count on a TextView
            stepCountTextView.setText(String.valueOf(stepCount));





        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do nothing
    }
}