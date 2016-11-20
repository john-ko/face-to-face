package com.example.facetoface.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.facetoface.R;
import com.example.facetoface.Statistics;
import com.example.facetoface.Utility;
import com.example.facetoface.data.DataDBHandler;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;

//import android.view.LayoutInflater;
//import android.app.Fragment;
public class FirstFragment extends Fragment implements StatsHandler {

    Statistics stats;
    public FirstFragment() {
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
        View testView = (View) inflater.inflate(R.layout.fragment_first, container, false);

//        DataDBHandler dbHandler = new DataDBHandler(getContext(), null, null, 1);

        PieChart pieChart1 = (PieChart) testView.findViewById(R.id.pieChart_1);
        Utility.setPieChartData(pieChart1, stats.getCurrent());

        LineChart lineChart2 = (LineChart) testView.findViewById(R.id.lineChart_3);
        Utility.setLineChartData(lineChart2, Utility.formatDataList(stats.getAll()));

        CandleStickChart candleStickChart = (CandleStickChart) testView.findViewById(R.id.candleCart);
        Utility.setCandleChartData(candleStickChart, stats.getAll());

        return testView;
    }

    @Override
    public void setStats(Statistics s) {
        this.stats = s;
    }
}
