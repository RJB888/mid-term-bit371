package com.hfad.timerappbronson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;


public class TimerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        int seconds = getIntent().getExtras().getInt("seconds");
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.timer_container, TimerFragment.newInstance(seconds, true))
                    .commit();
        }
    }
}