package com.hfad.timerappbronson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.sitUps).setOnClickListener(this);
        findViewById(R.id.pullUps).setOnClickListener(this);
        findViewById(R.id.walking).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(), TimerActivity.class);
        switch (view.getId()){
            case R.id.sitUps:
                intent.putExtra("seconds", 120);
                break;
            case R.id.pullUps:
                intent.putExtra("seconds", 30);
                break;
            case R.id.walking:
                intent.putExtra("seconds", 7200);
                break;
        }
        startActivity(intent);
    }
}