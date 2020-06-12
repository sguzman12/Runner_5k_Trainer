package com.guzman.model;
//Sigfredo Guzman
//6/2/2020

/**
 * Runtime objects created
 */
public class RunTimes {
    double run;
    RunMovement active;

    public RunTimes(double run){
        this.run = run;
    }

    public RunTimes(double run, RunMovement active){
        this.run = run;
        this.active = active;
    }

    public double getRun(){
        return run;
    }

    public RunMovement getActive(){
        return active;
    }

    public void setRun(double run){
        this.run = run;
    }

    public void setActive(RunMovement active) {this.active = active; }

    public String toString(){
        return String.valueOf(run);
    }

    public enum RunMovement{
        Run, Walk;
    }
}
