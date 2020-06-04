package com.guzman.trainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.guzman.dao.RunProgram;
import com.guzman.dao.RunTimes;

import java.util.ArrayList;

public class TrainingActivity extends AppCompatActivity {

    public static final int EXTRA_INTENSITY = 0; //User intensity level
    private RunProgram program = null; //Program object
    private int sectionSeconds = 0;
    private int fullTimeSeconds = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        Intent intent = getIntent();
        int i = intent.getIntExtra("EXTRA_INTENSITY", EXTRA_INTENSITY);

        setRunProgram(i);

        runTimer(program);
    }

    private void runTimer(final RunProgram prog) {
        //System.out.println(prog.getTotalTime())
        final TextView sectionCountdown = (TextView) findViewById(R.id.countdownTimer);
        final Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
              sectionSeconds = prog.getSchedule().indexOf(0);

            }
        });
    }

    private void runTimerHelper(int seconds){
        int hours = seconds/3600;
    }

    /**
     * Running program created according to the user selected level.
     *
     * @param intensity Spinner value selected in the MainActivity class
     */
    private void setRunProgram(int intensity) {
        RunTimes runTimes = null;
        ArrayList<RunTimes> schedule = null;

        if (intensity > 0) {
            switch (intensity) {
                case 1:
                    double totalTime = 1200;

                    schedule = new ArrayList();

                    for (double r : programOne) {
                        runTimes = new RunTimes(r);
                        schedule.add(runTimes);
                    }
                    program = new RunProgram(intensity, totalTime, schedule);
                    //System.out.println(program.toString());
                    break;

                case 2:
                    System.out.println("Second");
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
    double programOne[] = {
            60, 60, 60, 60, 60, 60, 60, 60, 60, 60,
            60, 60, 60, 60, 60, 60, 60, 60, 60, 60
    };
}
