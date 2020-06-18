package com.guzman.repository;

/**
 * Holds the Arrays for the RunTime Schedules
 */
public class ScheduleRunPrograms_Repository {
    //Program Array Schedules
    int programOne[] = {
            5, 5, 60, 60, 60, 60, 60, 60, 60, 60,
            60, 60, 60, 60, 60, 60, 60, 60, 60, 60
    };

    int testProgram[] = {
            3, 4, 5, 6
    };

    public ScheduleRunPrograms_Repository(){}

    /**
     * Returns Run values as an array.
     * @param intensity
     * @return Run Program schedule
     */
    public int[] getProgram(int intensity) {
        switch (intensity) {
            case 1:
                return programOne;
            case 2:
                return testProgram;
        }
        return null;
    }
}
