package com.guzman.trainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.TextView;

import com.guzman.dao.RunProgram;
import com.guzman.dao.RunTimes;

import java.util.ArrayList;

public class TrainingActivity extends AppCompatActivity {

    public static final int EXTRA_INTENSITY = 0; //User intensity level
    private RunProgram program = null; //Program object
    private Handler mHandler;
    private int sectionSeconds;
    private int fullTimeSeconds = 0;
    private boolean running = true;
    private CountDownTimer timer = null;
    private int counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        Intent intent = getIntent();
        int i = intent.getIntExtra("EXTRA_INTENSITY", EXTRA_INTENSITY);

        setRunProgram(i);

        ArrayList<Integer> sectors = new ArrayList<Integer>();

        for (int time : program.getSchedule()) {
            sectors.add(time);
        }
        runTimer(sectors);
        System.out.println("Done");
    }

    /**
     * Handler class that runs the countdown and updates the UI. If the seconds in
     * the section are still above 0 then one is subtracted. Once they reach zero then
     * the next item in the ArrayList is the new sectionSeconds variable.
     *
     * @param sectors ArrayList of program times.
     */
    private void runTimer(final ArrayList<Integer> sectors) {
        final TextView sectionCountdown = (TextView) findViewById(R.id.countdownTimer);
        mHandler = new Handler();
        counter = 0;
        sectionSeconds = sectors.get(counter);

        mHandler.post(new Runnable() {
            @Override
            public void run() {

                int minutes = sectionSeconds / 60;
                int seconds = sectionSeconds % 60;

                String time = String.format("%02d:%02d", minutes, seconds);
                sectionCountdown.setText(time);

                sectionSeconds--;

                if (sectionSeconds >= 0) {
                    mHandler.postDelayed(this, 1000);
                } else if (counter < sectors.size() - 1) {
                    counter++;
                    sectionSeconds = sectors.get(counter);
                    mHandler.postDelayed(this, 1000);
                } else {
                    sectionCountdown.setText("Finished");
                    mHandler.removeCallbacks(this);
                }
            }
        });
    }


    /**
     * Running program created according to the user selected level.
     *
     * @param intensity Spinner value selected in the MainActivity class
     */
    private void setRunProgram(int intensity) {
        RunTimes runTimes = null;
        ArrayList<Integer> schedule = null;
        double totalTime;

        if (intensity > 0) {
            switch (intensity) {
                case 1:
                    totalTime = 1200;

                    schedule = new ArrayList();

                    for (int r : programOne) {
                        //runTimes = new RunTimes(r);
                        schedule.add(r);
                    }
                    program = new RunProgram(intensity, totalTime, schedule);
                    break;

                case 2:
                    totalTime = 1200;

                    schedule = new ArrayList();

                    for (int r : testProgram) {
                        //runTimes = new RunTimes(r);
                        schedule.add(r);
                    }
                    program = new RunProgram(intensity, totalTime, schedule);
                    break;

                case 3:
                    System.out.println("Third");
                    break;

                case 4:
                    System.out.println("Fourth");
                    break;

                case 5:
                    System.out.println("Fifth");

            }
        }
    }

    //Program Array Schedules
    int programOne[] = {
            5, 5, 60, 60, 60, 60, 60, 60, 60, 60,
            60, 60, 60, 60, 60, 60, 60, 60, 60, 60
    };

    int testProgram[] = {
            3, 4, 5, 6
    };
}
