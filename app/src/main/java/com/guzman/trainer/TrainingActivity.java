package com.guzman.trainer;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.guzman.model.GeoLocationObjectModel;
import com.guzman.model.TrainingModel;
import com.guzman.viewmodel.GPS_Service;
import com.guzman.viewmodel.TimerFactory_ViewModel;
import com.guzman.viewmodel.Timer_ViewModel;

import java.util.ArrayList;

/**
 * UI class for the majority of the application running. Calls timer and observes changes.
 */
public class TrainingActivity extends AppCompatActivity
{
    private Timer_ViewModel mViewModel;
    public static final int EXTRA_INTENSITY = 0; //User intensity level
    public static final boolean EXTRA_PERMISSION_GRANTED = false; //User Location Permission
    private boolean permission = false;
    public int intensity;
    private Intent intent;
    private GPS_Service odometer;
    private boolean bound = false;

    /**
     * Method retrieves the intensity of the intended workout. Passes value to the
     * TimerFactory class which in turn is used to pass along extra parameters to the
     * Timer_ViewModel class. Observes any changes and updates UI to display counter.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        intent = new Intent(TrainingActivity.this, GPS_Service.class);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        Intent intent1 = getIntent();
        intensity = intent1.getIntExtra("EXTRA_INTENSITY", EXTRA_INTENSITY);
        permission = intent1.getBooleanExtra("EXTRA_PERMISSION_GRANTED", EXTRA_PERMISSION_GRANTED);

        TimerFactory_ViewModel factory = new TimerFactory_ViewModel(this.getApplication(), intensity);
        mViewModel = new ViewModelProvider(this, factory).get(Timer_ViewModel.class);

        //If permission given then bindservice
        if(!permission){
            Intent intent = new Intent(this, GPS_Service.class);
            bindService(intent, connection, Context.BIND_AUTO_CREATE);

            startLocationService();
        }

        observeTimer();



    }

    @Override
    protected void onStart()
    {
        super.onStart();

    }

    /**
     * Observes LiveData from the Timer_ViewModel. CurrentTime object is retrieved and displayed
     * to TextViews.
     */

    private void observeTimer()
    {

        final Observer elapsedTimeObserver = new Observer<TrainingModel>()
        {


            /**
             * Called when the data is changed.
             *
             * @param tm The new data
             */

            @Override
            public void onChanged(TrainingModel tm)
            {

                final TextView sectionCountdown = (TextView) findViewById(R.id.countdownTimer);
                final TextView sectionActivity = (TextView) findViewById(R.id.runMessage);

                String activity = tm.getActivity();
                int sectionSeconds = tm.getSeconds();

                int minutes = sectionSeconds / 60;
                int seconds = sectionSeconds % 60;

                String time = String.format("%02d:%02d", minutes, seconds);

                sectionCountdown.setText(time);
                sectionActivity.setText(activity);

                if (sectionSeconds == -1) {
                    sectionCountdown.setText("Finished");
                   stopService();


                }

            }

        };

        mViewModel.getCurrent().observe(this, elapsedTimeObserver);
    }

    private void startLocationService()
    {

        // startService(intent);
                //Request Location Permission
                startService();

    }

    private void startService()
    {
        startService(intent);
    }

    private void stopService()
    {
        ArrayList<GeoLocationObjectModel> list = new ArrayList<>();
       // list = addLocationsToList();

        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("EXTRA_LOCATION", list);

        startActivity(intent);

       // stopService(intent);
    }

    private ServiceConnection connection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder binder)
        {
            GPS_Service.OdometerBinder odometerBinder =
                    (GPS_Service.OdometerBinder) binder;
           // odometer = GPS_Service.OdometerBinder.getOdometer();
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName)
        {
            bound = false;
        }
    };

}




