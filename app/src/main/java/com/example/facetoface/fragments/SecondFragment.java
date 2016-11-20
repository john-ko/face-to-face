package com.example.facetoface.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        View theView = (View) inflater.inflate(R.layout.fragment_second, container, false);
        /*if (stats != null){
            return theView;
        }*/
        Log.e("writing ", "second");
        System.out.println(" p------------- here");

        TextView meanText = (TextView) theView.findViewById(R.id.mean);
        meanText.setText(String.valueOf(stats.mean()));

        TextView medianText = (TextView) theView.findViewById(R.id.median);
        medianText.setText(String.valueOf(stats.median()));

        TextView modeText = (TextView) theView.findViewById(R.id.mode);
        modeText.setText(String.valueOf(stats.mode()));

        TextView ttestText = (TextView) theView.findViewById(R.id.t_test);
        ttestText.setText(String.valueOf("value"));

        return theView;
    }


    @Override
    public void setStats(Statistics s) {
        this.stats = s;
    }
}
