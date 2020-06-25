package com.guzman.model;

/**
 * Model class used to create objects for the timer. These are observed in the
 * TrainingActivity.
 */
public class TrainingModel {

    public TrainingModel(Integer seconds, String activity){
        this.seconds = seconds;
        this.activity = activity;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    private Integer seconds;
    private String activity;


}
