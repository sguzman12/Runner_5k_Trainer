package com.guzman.dao;

import android.os.CountDownTimer;
import android.widget.TextView;

import com.guzman.trainer.TrainingActivity;

public class Timer extends CountDownTimer {
    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public Timer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

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

    }

    /**
     * Callback fired when the time is up.
     */
    @Override
    public void onFinish() {

    }
}
