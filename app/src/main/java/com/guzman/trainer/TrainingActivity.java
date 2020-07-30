package com.guzman.trainer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.Observer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

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
    public int intensity;
    private Intent intent;

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

        TimerFactory_ViewModel factory = new TimerFactory_ViewModel(this.getApplication(), intensity);
        mViewModel = new ViewModelProvider(this, factory).get(Timer_ViewModel.class);

        observeTimer();
       // startLocationService();
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

    /**
     * Callback for the result from requesting permissions. This method
     * is invoked for every call on {@link #requestPermissions(String[], int)}.
     * <p>
     * <strong>Note:</strong> It is possible that the permissions request interaction
     * with the user is interrupted. In this case you will receive empty permissions
     * and results arrays which should be treated as a cancellation.
     * </p>
     *
     * @param requestCode  The request code passed in {@link #requestPermissions(String[], int)}.
     * @param permissions  The requested permissions. Never null.
     * @param grantResults The grant results for the corresponding permissions
     *                     which is either {@link PackageManager#PERMISSION_GRANTED}
     *                     or {@link PackageManager#PERMISSION_DENIED}. Never null.
     * @see #requestPermissions(String[], int)
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startService();
                } else {
                    Toast.makeText(this, "Permissions Needed", Toast.LENGTH_LONG).show();
                }
        }
    }

    private void startLocationService()
    {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //Request Location
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                //Request Location Permission
                startService();
            }
        } else {
            //Start Location Service
            startService();
        }
    }

    private void startService()
    {
        startService(intent);
    }

    private void stopService()
    {
        ArrayList<GeoLocationObjectModel> list = new ArrayList<>();
        list = addLocationsToList();

        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("EXTRA_LOCATION", list);

        startActivity(intent);

       // stopService(intent);
    }

    private ArrayList<GeoLocationObjectModel> addLocationsToList(){
       ArrayList<GeoLocationObjectModel> list = new ArrayList<>();

        GeoLocationObjectModel g1 = new GeoLocationObjectModel(-35.016, 143.321);
        GeoLocationObjectModel g2 = new GeoLocationObjectModel(-34.747, 145.592);
        GeoLocationObjectModel g3 = new GeoLocationObjectModel(-34.364, 147.891);
        GeoLocationObjectModel g4 = new GeoLocationObjectModel(-33.501, 150.217);
        GeoLocationObjectModel g5 = new GeoLocationObjectModel(-32.306, 149.248);
        GeoLocationObjectModel g6 = new GeoLocationObjectModel(-32.491, 147.309);

        list.add(g1);
        list.add(g2);
        list.add(g3);
        list.add(g4);
        list.add(g5);
        list.add(g6);

        return list;
    }

}




