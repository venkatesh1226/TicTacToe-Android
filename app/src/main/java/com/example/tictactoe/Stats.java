package com.example.tictactoe;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Stats extends Fragment {

TextView tv;
SharedPreferences sharedPref ;
    public Stats() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_stats, container, false);
       init(view);
       return view;
    }
    private void init(View v){
        sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        tv=v.findViewById(R.id.txt);
        String s="Total Games Played By X   "+sharedPref.getInt(getString(R.string.Total),0)+
                "\n\n\nTotal Games Won By X       "+sharedPref.getInt(getString(R.string.Win),0)+
                "\n\n\nTotal Games Lost By X        "+sharedPref.getInt(getString(R.string.Lose),0);
        tv.setText(s);
    }
}