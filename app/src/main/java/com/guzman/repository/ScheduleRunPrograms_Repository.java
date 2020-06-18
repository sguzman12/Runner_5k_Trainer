package com.guzman.repository;

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
