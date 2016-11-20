package com.example.facetoface.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.facetoface.R;
import com.example.facetoface.Utility;
import com.example.facetoface.data.DataDBHandler;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;

public class FirstFragment extends Fragment{

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//
//        DataDBHandler dbHandler = new DataDBHandler(getContext(), null, null, 1);
//
//        PieChart pieChart1 = (PieChart) findViewById(R.id.pieChart_1);
//        Utility.setPieChartData(pieChart1, dbHandler.getLatestEntry());
//
//        LineChart lineChart2 = (LineChart) findViewById(R.id.lineChart_3);
//        Utility.setLineChartData(lineChart2, Utility.formatDataList(dbHandler.getAll()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View testView = (View) inflater.inflate(R.layout.fragment_first, container, false);

        DataDBHandler dbHandler = new DataDBHandler(getContext(), null, null, 1);

        PieChart pieChart1 = (PieChart) testView.findViewById(R.id.pieChart_1);
        Utility.setPieChartData(pieChart1, dbHandler.getLatestEntry());

        LineChart lineChart2 = (LineChart) testView.findViewById(R.id.lineChart_3);
        Utility.setLineChartData(lineChart2, Utility.formatDataList(dbHandler.getAll()));

        CandleStickChart candleStickChart = (CandleStickChart) testView.findViewById(R.id.candleCart);
        Utility.setCandleChartData(candleStickChart, dbHandler.getAll());

        return testView;
    }

}

//import android.os.Bundle;
//import android.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.example.facetoface.R;
//
///**
// * A simple {@link Fragment} subclass.
// */
//public class FirstFragment extends Fragment {
//
//
//    public FirstFragment() {
//        // Required empty public constructor
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_first, container, false);
//    }
//
//}
