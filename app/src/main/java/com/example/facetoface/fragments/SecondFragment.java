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

import java.text.SimpleDateFormat;

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
        Log.d("writing ", "second");
        System.out.println(" p------------- here");

        TextView startText = (TextView) theView.findViewById(R.id.starttime);
        startText.setText(String.valueOf(new SimpleDateFormat("HH/dd/yy HH:mm:ss").format(stats.getCurrent().getStart())));

        TextView endText = (TextView) theView.findViewById(R.id.endtime);
        endText.setText(String.valueOf(new SimpleDateFormat("MM/dd/yy HH:mm:ss").format(stats.getCurrent().getEnd())));

        TextView patientText = (TextView) theView.findViewById(R.id.patienttime);
        patientText.setText(String.valueOf(new SimpleDateFormat("mmm.ss.SS").format(stats.getCurrent().getPatient())));

        TextView currentText = (TextView) theView.findViewById(R.id.recent);
        currentText.setText(String.valueOf(Statistics.percentageFormat(stats.getCurrent().getPercentage())));

        TextView meanText = (TextView) theView.findViewById(R.id.mean);
        meanText.setText(String.valueOf(Statistics.percentageFormat(stats.mean())));

        TextView medianText = (TextView) theView.findViewById(R.id.median);
        medianText.setText(String.valueOf(Statistics.percentageFormat(stats.median())));

        TextView stdText = (TextView) theView.findViewById(R.id.stddev);
        stdText.setText(String.valueOf(Statistics.percentageFormat(stats.stdDev())));

        TextView ttestText = (TextView) theView.findViewById(R.id.t_test);
        ttestText.setText(String.valueOf(stats.ttest()));

        return theView;
    }


    @Override
    public void setStats(Statistics s) {
        this.stats = s;
    }
}
