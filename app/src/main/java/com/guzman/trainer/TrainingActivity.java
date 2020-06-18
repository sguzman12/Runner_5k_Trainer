package com.guzman.trainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.guzman.viewmodel.TimerFactory_ViewModel;
import com.guzman.viewmodel.Timer_ViewModel;

/**
 * UI class for the majority of the application running. Calls timer and observes changes.
 */
public class TrainingActivity extends AppCompatActivity {
    private Timer_ViewModel mViewModel;
    public static final int EXTRA_INTENSITY = 0; //User intensity level
    public int intensity;

    /**
     * Method retrieves the intensity of the intended workout. Passes value to the
     * TimerFactory class which in turn is used to pass along extra parameters to the
     * Timer_ViewModel class. Observes any changes and updates UI to display counter.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        Intent intent = getIntent();
        intensity = intent.getIntExtra("EXTRA_INTENSITY", EXTRA_INTENSITY);

        TimerFactory_ViewModel factory = new TimerFactory_ViewModel(this.getApplication(), intensity);
        mViewModel = new ViewModelProvider(this, factory).get(Timer_ViewModel.class);

        observeTimer();
    }

    /**
     * Observes LiveData from the Timer_ViewModel. CurrentTime object is retrieved and displayed
     * to TextViews.
     */
    private void observeTimer() {
        final Observer elapsedTimeObserver = new Observer() {
            /**
             * Called when the data is changed.
             *
             * @param o The new data
             */
            @Override
            public void onChanged(Object o) {
                final TextView sectionCountdown = (TextView) findViewById(R.id.countdownTimer);
                // final TextView sectionActivity = (TextView) findViewById(R.id.runMessage);

                int sectionSeconds = (int) o;
                int minutes = sectionSeconds / 60;
                int seconds = sectionSeconds % 60;

                String time = String.format("%02d:%02d", minutes, seconds);
                sectionCountdown.setText(time);

                if (sectionSeconds == -1) {
                    sectionCountdown.setText("Finished");
                }

            }

        };

        mViewModel.getTime().observe(this, elapsedTimeObserver);
    }

}
