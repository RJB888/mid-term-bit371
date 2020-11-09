package com.hfad.timerappbronson;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TimerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimerFragment extends Fragment implements View.OnClickListener {
    private int seconds, sec, min, hours;
    private boolean paused;
    private TextView tv;
    private FloatingActionButton fab;

    private Drawable PLAY, PAUSE;

    public static TimerFragment newInstance(int seconds, boolean paused){
        TimerFragment tf = new TimerFragment();
        Bundle timerArgs = new Bundle();
        timerArgs.putInt("seconds", seconds);
        timerArgs.putBoolean("paused", paused);
        tf.setArguments(timerArgs);
        return tf;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv = view.findViewById(R.id.timer_text);
        PAUSE = getResources().getDrawable(android.R.drawable.ic_media_pause);
        PLAY = getResources().getDrawable(android.R.drawable.ic_media_play);
        fab = view.findViewById(R.id.playPauseFAB);
        fab.setOnClickListener(this);
        //get saved instance data
        if (savedInstanceState != null){
            paused = savedInstanceState.getBoolean("paused");
            seconds = savedInstanceState.getInt("seconds");
        }else {
            seconds = getArguments().getInt("seconds");
            paused = getArguments().getBoolean("paused");
        }
        fab.setImageDrawable(paused ? PLAY : PAUSE);
        tv.setText(String.format("%02d:%02d:%02d", 0, 0, seconds));
        runTimer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timer, container, false);
    }

    public void runTimer(){
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (!paused) --seconds;
                if (seconds >= 0) {
                    hours = seconds / 3600;
                    min = (seconds % 3600) / 60;
                    sec = seconds % 60;
                    tv.setText(String.format("%02d:%02d:%02d", hours, min, sec));
                    handler.postDelayed(this, 1000);
                }else {
                    tv.setText("Workout complete.  Go back and select another.");
                    fab.setActivated(false);
                    fab.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", seconds);
        outState.putBoolean("paused", paused);
    }

    @Override
    public void onClick(View view) {
        paused = !paused;
        fab.setImageDrawable(paused ? PLAY : PAUSE);
    }
}