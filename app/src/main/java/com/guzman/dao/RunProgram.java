package com.guzman.dao;
//Sigfredo Guzman
//6/2/2020

/**
 * RunProgram object
 */
public class RunProgram {
    int id;
    double totalTime;
    RunTimes run;
    RunTimes schedule[];

    public RunProgram(){}

    public RunProgram(int id){
        this.id = id;
    }

    public RunProgram(double totalTime){
        this.totalTime = totalTime;
    }

    public RunProgram(int id, double totalTime){
        this.id = id;
        this.totalTime = totalTime;
    }

    public RunProgram(double totalTime, RunTimes run){
        this.totalTime = totalTime;
        this.run = run;
    }

    public RunProgram(int id, double totalTime, RunTimes schedule[]){
        this.id = id;
        this.totalTime = totalTime;
        this.schedule = schedule;
    }

    public int getID() {
        return id;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public RunTimes getRuns() {
        return run;
    }

    public RunTimes[] getSchedule() {
        return schedule;
    }

    // Setters
    public void setID(int id) {
        this.id = id;
    }

    public void setTotalTime(double totalTime) {
        this.totalTime = totalTime;
    }
    public void setRun(RunTimes run) {
        this.run = run;
    }

    public void setSchedule(RunTimes schedule[]){
        this.schedule = schedule;
    }
}
