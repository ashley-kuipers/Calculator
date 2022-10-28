package com.example.calculatorapp2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class Welcome extends Fragment {
    Button b_start;

    public Welcome() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);

        // assign value to button
        b_start = view.findViewById(R.id.b_start);

        // add on click listener to button, dismisses the fragment when clicked
        b_start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Get instance of main activity
                MainActivity mainAct = (MainActivity) getActivity();

                // removes layout that contains the fragment
                view.findViewById(R.id.welcome_layout).setVisibility(View.GONE);
                container.removeView(view);
                container.setLayoutParams(new LinearLayout.LayoutParams(0, 0));

                // runs the custom method in Main Activity - lets main activity know the fragment has been dismissed
                mainAct.fragmentClicked(true);
            }
        });

        return view;
    }
}