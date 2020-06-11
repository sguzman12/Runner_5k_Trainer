package com.guzman.trainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.TextView;

import com.guzman.dao.RunProgram;
import com.guzman.dao.RunTimes;
import com.guzman.dao.Timer;

import java.util.ArrayList;
import java.util.Locale;

public class TrainingActivity extends AppCompatActivity {

    public static final int EXTRA_INTENSITY = 0; //User intensity level

    private RunProgram program = null; //Program object
    private Handler mHandler;
    private int sectionSeconds = 0;
    private int fullTimeSeconds = 0;
    private Runnable runnable;
    private boolean running = true;
    private CountDownTimer timer = null;
    private int counter = 0;
    private int delay = 0;

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
        //sectionCountdown = (TextView) findViewById(R.id.countdownTimer);
        final ArrayList<Integer> sectors = prog.getSchedule();
        //final Handler handler = new Handler();

        // sectors.forEach((s) -> runTimerHelper(s));
        /*
        for(int i: sectors) {
            runTimerHelper(i, sectors, sectionCountdown);

            System.out.println(i);
        }
*/
        newTime(sectors);


    }

    private void runTimerHelper(final int secs, final ArrayList<Integer> sectors, final TextView sectionCountdown) {


        timer = new CountDownTimer((secs + 1) * 1000, 1000) {

            /**
             * Callback fired on regular interval.
             *
             * @param millisUntilFinished The amount of time until finished.
             */
            @Override
            public void onTick(long millisUntilFinished) {

                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;

                String time = String.format("%02d:%02d", minutes, seconds);
                sectionCountdown.setText(time);

            }

            /**
             * Callback fired when the time is up.
             */
            @Override
            public void onFinish() {
                sectionCountdown.setText("Finished");
                timer.cancel();
                System.out.println(secs);
            }
        }.start();


    }

    private void newTime(final ArrayList<Integer> sectors) {

            final TextView sectionCountdown = (TextView) findViewById(R.id.countdownTimer);
            mHandler = new Handler();
            sectionSeconds = 60;

            mHandler.post(new Runnable() {
                @Override
                public void run() {


                        int minutes = sectionSeconds / 60;
                        int seconds = sectionSeconds % 60;

                        String time = String.format("%02d:%02d", minutes, seconds);
                        sectionCountdown.setText(time);

                        if (sectionSeconds > 0) {
                            sectionSeconds--;

                        }
                        mHandler.postDelayed(this, 1000);
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

        if (intensity > 0) {
            switch (intensity) {
                case 1:
                    double totalTime = 1200;

                    schedule = new ArrayList();

                    for (int r : programOne) {
                        //runTimes = new RunTimes(r);
                        schedule.add(r);
                    }
                    program = new RunProgram(intensity, totalTime, schedule);
                    System.out.println(program.toString());
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
    int programOne[] = {
            5, 5, 60, 60, 60, 60, 60, 60, 60, 60,
            60, 60, 60, 60, 60, 60, 60, 60, 60, 60
    };
}
