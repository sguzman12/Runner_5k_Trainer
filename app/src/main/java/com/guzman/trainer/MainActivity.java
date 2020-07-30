package com.guzman.trainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
//Sigfredo Guzman
//Date 5-31-2020
//Purpose: Android App allows the user to train incrementally towards the goal
//of running a 5k.

/**
 * Welcome page once application is started.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Called from button click. Adds the run intensity as an Extra Intent to
     * the Training Activity.
     * @param view
     */
    public void onStart(View view) {
        int intensity = runIntensity();

        Intent intent = new Intent(this, TrainingActivity.class);
        intent.putExtra("EXTRA_INTENSITY", intensity);


        startActivity(intent);
    }

    /**
     * Find the intensity of the run selected.
     */
    private int runIntensity() {
        int intensity = 0;

        Spinner run = (Spinner) findViewById(R.id.intensity);
        if (run.getSelectedItemPosition() > 0) {
            try {
                String s = String.valueOf(run.getSelectedItem());

                intensity = Integer.parseInt(String.valueOf(s.charAt(0)));

                return intensity;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Else statement");
        }
        return intensity;
    }
}
