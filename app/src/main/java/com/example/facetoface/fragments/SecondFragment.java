package com.example.facetoface.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.facetoface.AnalyticActivity;
import com.example.facetoface.R;
import com.example.facetoface.Statistics;

public class SecondFragment extends Fragment implements StatsHandler {
    Statistics stats;

    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (stats == null) {
            stats = ((AnalyticActivity) getActivity()).getStats();
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }


    @Override
    public void setStats(Statistics s) {
        this.stats = s;
    }
}
