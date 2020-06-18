package com.guzman.trainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import com.guzman.model.RunProgram;
import com.guzman.model.RunTimes;
import com.guzman.viewmodel.Timer_ViewModel;
import java.util.ArrayList;

public class TrainingActivity extends AppCompatActivity {
    private Timer_ViewModel mViewModel;
    public static final int EXTRA_INTENSITY = 0; //User intensity level
    private RunProgram program = null; //Program object
    private RunTimes runTimes = null;
    private ArrayList<RunTimes> runTimesArrayList;
    private Handler mHandler;
    private int sectionSeconds;
    private boolean running = true;
    private int counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        mViewModel = new ViewModelProvider(this).get(Timer_ViewModel.class);
        Intent intent = getIntent();
        int i = intent.getIntExtra("EXTRA_INTENSITY", EXTRA_INTENSITY);

        setRunProgram(i);

        runTimer(runTimesArrayList);

        System.out.println("Done");
    }

    /**
     * Handler class that runs the countdown and updates the UI. If the seconds in
     * the section are still above 0 then one is subtracted. Once they reach zero then
     * the next item in the ArrayList is the new sectionSeconds variable.
     *
     * @param sectors ArrayList of program times.
     */
    private void runTimer(final ArrayList<RunTimes> sectors) {
        final TextView sectionCountdown = (TextView) findViewById(R.id.countdownTimer);
        final TextView sectionActivity = (TextView) findViewById(R.id.runMessage);

        mHandler = new Handler();
        counter = 0;
        sectionSeconds = (int) sectors.get(counter).getRun();

        mHandler.post(new Runnable() {
            @Override
            public void run() {

                int minutes = sectionSeconds / 60;
                int seconds = sectionSeconds % 60;

                String time = String.format("%02d:%02d", minutes, seconds);
                sectionCountdown.setText(time);
                sectionActivity.setText(String.valueOf(sectors.get(counter).getActive()));
                sectionSeconds--;

                if (sectionSeconds >= 0) {
                    mHandler.postDelayed(this, 1000);
                } else if (counter < sectors.size() - 1) {
                    counter++;
                    sectionSeconds = (int) sectors.get(counter).getRun();
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
        int choice = 0;
        int totalTime = 0;
        int[] array = null;
        runTimesArrayList = new ArrayList();

        array = runProgramHelper(intensity);

        for (int r : array) {
            runTimes = new RunTimes(r);
            totalTime += r;

            if (choice == 0) {
                runTimes.setActive(RunTimes.RunMovement.Run);
                choice = 1;
            } else {
                choice = 0;
                runTimes.setActive(RunTimes.RunMovement.Walk);
            }
            runTimesArrayList.add(runTimes);
        }
        program = new RunProgram(intensity, totalTime, runTimesArrayList);
    }

    /**
     * Returns array according to intensity level.
     * @param intense Spinner value selected in the MainActivity class
     * @return array
     */
    private int[] runProgramHelper(int intense) {
        switch (intense) {
            case 1:
                return programOne;
            case 2:
                return testProgram;
        }
        return null;
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
