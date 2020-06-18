package com.guzman.viewmodel;

import android.app.Application;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.guzman.model.RunProgram;
import com.guzman.model.RunTimes;
import com.guzman.repository.ScheduleRunPrograms_Repository;

import java.util.ArrayList;

/**
 * ViewModel class which handles the Timer.
 */
public class Timer_ViewModel extends ViewModel {
    private RunProgram program = null; //Program object
    private RunTimes runTimes = null;
    private ArrayList<RunTimes> runTimesArrayList;
    private Handler mHandler;
    private int sectionSeconds;
    private boolean running = true;
    private int counter;
    private MutableLiveData<Integer> currentTime = new MutableLiveData<Integer>();

    /**
     * Default constructor. Set's up and runs the timer. Factory passed in extra values.     *
     *
     * @param application
     * @param intensity   Level of workout
     */
    public Timer_ViewModel(@NonNull Application application, final int intensity) {
        int intense = intensity;

        program = new RunProgram();
        runTimesArrayList = new ArrayList<>();

        program = setRunProgram(intensity);
        runTimer(runTimesArrayList);
    }

    /**
     * Counts down timer from ArrayList of values passed in. Adds value to LiveData variable.
     * Once all values are iterated through, currentTime variable is passed as -1 to tell
     * observer that countdown is done.
     *
     * @param sectors ArrayList of running times in program.
     */
    public void runTimer(final ArrayList<RunTimes> sectors) {
        mHandler = new Handler();
        counter = 0;
        sectionSeconds = (int) sectors.get(counter).getRun();

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                int minutes = sectionSeconds / 60;
                int seconds = sectionSeconds % 60;

                sectionSeconds--;

                if (sectionSeconds >= 0) {
                    currentTime.postValue(sectionSeconds);
                    mHandler.postDelayed(this, 1000);
                } else if (counter < sectors.size() - 1) {
                    counter++;
                    sectionSeconds = (int) sectors.get(counter).getRun();
                    currentTime.postValue(sectionSeconds);
                    mHandler.postDelayed(this, 1000);
                } else {
                    currentTime.postValue(-1);
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
    private RunProgram setRunProgram(int intensity) {
        int choice = 0;
        int totalTime = 0;

        ScheduleRunPrograms_Repository prog = new ScheduleRunPrograms_Repository();
        int[] schedule = prog.getProgram(intensity);
        runTimesArrayList = new ArrayList();

        for (int r : schedule) {
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
        RunProgram setProgram = new RunProgram(intensity, totalTime, runTimesArrayList);

        return setProgram;
    }

    /**
     * Returns the currentTime value to the observer.
     *
     * @return currentTime
     */
    public LiveData<Integer> getTime() {
        return currentTime;
    }

    /**
     * Clears LiveData
     */
    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
