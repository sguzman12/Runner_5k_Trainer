package com.guzman.trainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
//Sigfredo Guzman
//Date 5-31-2020
//Purpose: Android App allows the user to train incrementally towards the goal
//of running a 5k.

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStart(View view){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}
