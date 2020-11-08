package com.hfad.timerappbronson;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TimerFragment extends Fragment implements View.OnClickListener {

    NavController navController;
    private  int time, sec, min, hours;
    private boolean paused = true;
    private TextView tv;
    private FloatingActionButton fab;

    private Drawable PLAY;
    private Drawable PAUSE;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        tv = view.findViewById(R.id.countdown);
        fab = view.findViewById(R.id.playPauseFAB);
        PAUSE = getResources().getDrawable(android.R.drawable.ic_media_pause);
        PLAY = getResources().getDrawable(android.R.drawable.ic_media_play);
        view.findViewById(R.id.playPauseFAB).setOnClickListener(this);

        if (savedInstanceState != null){
            paused = savedInstanceState.getBoolean("paused");
            time = savedInstanceState.getInt("time");
        }else {
            time = getArguments().getInt("seconds");
        }

        runTimer();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", time);
        outState.putBoolean("paused", paused);
    }

    public void runTimer(){
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (!paused) --time;
                if (time >= 0) {
                    hours = time / 3600;
                    min = (time % 3600) / 60;
                    sec = time % 60;
                    tv.setText(String.format("%02d:%02d:%02d", hours, min, sec));
                    handler.postDelayed(this, 1000);
                }else {
                    //notify timer done. disable button ?
                }
            }
        });
    }

    public void playPauseToggle(View view){
        paused = !paused;
        Drawable icon = paused ? PLAY : PAUSE;
        fab.setImageDrawable(icon);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timer, container, false);
    }

    @Override
    public void onClick(View view) {
        playPauseToggle(view);
    }
}