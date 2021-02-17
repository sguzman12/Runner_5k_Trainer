package com.guzman.trainer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.guzman.viewmodel.GPS_Service;
//Sigfredo Guzman
//Date 5-31-2020
//Purpose: Android App allows the user to train incrementally towards the goal
//of running a 5k.

/**
 * Welcome page once application is started.
 */
public class MainActivity extends AppCompatActivity
{
    private boolean permission = false;
    private final int PERMISSION_REQUEST_CODE = 111;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Check if Permission granted
        if (ContextCompat.checkSelfPermission(this, GPS_Service.PERMISSION_STRING)
                != PackageManager.PERMISSION_GRANTED) {
            //If not, ask for permission
            ActivityCompat.requestPermissions(this, new String[]{GPS_Service.PERMISSION_STRING},
                    PERMISSION_REQUEST_CODE);
        } else {
            permission = true;
        }
    }

    /**
     * Called from button click. Adds the run intensity as an Extra Intent to
     * the Training Activity.
     *
     * @param view
     */
    public void onStart(View view)
    {
        int intensity = runIntensity();

        Intent intent = new Intent(this, TrainingActivity.class);
        intent.putExtra("EXTRA_INTENSITY", intensity);
        intent.putExtra("EXTRA_PERMISSION_GRANTED", permission);
        startActivity(intent);
    }

    /**
     * Find the intensity of the run selected.
     */
    private int runIntensity()
    {
        int intensity = 0;

        Spinner run = (Spinner) findViewById(R.id.intensity);
        if (run.getSelectedItemPosition() > 0) {
            try {
                String s = String.valueOf(run.getSelectedItem());

                intensity = Integer.parseInt(String.valueOf(s.charAt(0)));

                return intensity;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Else statement");
        }
        return intensity;
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
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults.length > 0) {
                   permission = true;
                } else {
                    Toast.makeText(this, "Permissions Needed To Track Run on Map", Toast.LENGTH_LONG).show();
                }
        }
    }

}
