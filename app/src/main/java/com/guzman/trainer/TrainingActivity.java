package com.guzman.trainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class TrainingActivity extends AppCompatActivity {

    public static final int EXTRA_INTENSITY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        Intent intent = getIntent();
        int i = intent.getIntExtra("EXTRA_INTENSITY", EXTRA_INTENSITY);

        System.out.println(i);
    }


}
