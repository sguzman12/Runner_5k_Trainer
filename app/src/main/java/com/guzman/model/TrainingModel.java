package com.guzman.model;

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
