package com.example.calculatorapp2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Objects;

public class Settings extends Fragment {
    Button b_save;

    public Settings() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // shows action bar
//        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        container.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        b_save = view.findViewById(R.id.b_save);

        b_save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                view.findViewById(R.id.settings_layout).setVisibility(View.GONE);
                container.removeView(view);
                container.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
            }
        });

        return view;
    }
}