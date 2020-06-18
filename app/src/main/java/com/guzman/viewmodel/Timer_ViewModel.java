package com.guzman.viewmodel;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.guzman.model.RunProgram;
import com.guzman.model.RunTimes;
import com.guzman.repository.ScheduleRunPrograms_Repository;

import java.util.ArrayList;

public class Timer_ViewModel extends ViewModel {
    private RunProgram program = null; //Program object
    private ScheduleRunPrograms_Repository prog;
    private RunTimes runTimes = null;
    private ArrayList<RunTimes> runTimesArrayList;
    private Handler mHandler;
    private int sectionSeconds;
    private boolean running = true;
    private int counter;
    private int[] schedule;

    private MutableLiveData<Long> currentTime = new MutableLiveData<>();

    public Timer_ViewModel(){
        /*
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

         */
    }

    public void init(int intensity){
        prog = new ScheduleRunPrograms_Repository();
        schedule = prog.getProgram(intensity);
    }

    public void runTimer(){

    }

    public LiveData<Long> getTime(){
        return currentTime;
    }

    @Override
    protected void onCleared(){
        super.onCleared();
    }
}
