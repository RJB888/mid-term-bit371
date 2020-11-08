package com.hfad.timerappbronson;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainFragment extends Fragment implements View.OnClickListener{

    NavController navController;
    private int seconds;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.button).setOnClickListener(this);
        navController = Navigation.findNavController(view);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onClick(View view) {
        seconds = 3600;

        Bundle bundle = new Bundle();

        try {
            bundle.putInt("seconds", seconds);
            navController.navigate(R.id.action_mainFragment_to_timerFragment, bundle);
        }catch (NumberFormatException ex){
            Log.i("INFO", ex.getMessage());
        }
    }
}