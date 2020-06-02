package com.guzman.trainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.guzman.dao.RunProgram;
import com.guzman.dao.RunTimes;

import java.util.ArrayList;

public class TrainingActivity extends AppCompatActivity {

    public static final int EXTRA_INTENSITY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        Intent intent = getIntent();
        int i = intent.getIntExtra("EXTRA_INTENSITY", EXTRA_INTENSITY);

        setRunProgram(i);

    }

    /**
     * Running program created according to the user selected level.
     * @param intensity Spinner value selected in the MainActivity class
     */
    private void setRunProgram(int intensity){
        RunTimes runTimes =  null;
        ArrayList<RunProgram> schedule = null;

        if(intensity > 0){
            switch (intensity){
                case 1:
                    double array[] = {
                            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                            1, 1, 1, 1, 1, 1, 1, 1, 1, 1
                    };
                    schedule = new ArrayList();

                    for(double r: array){
                        runTimes = new RunTimes(r);
                       // schedule.add(runTimes);
                    }
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

}
