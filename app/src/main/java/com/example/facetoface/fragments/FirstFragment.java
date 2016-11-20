package com.example.facetoface.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.facetoface.R;
import com.example.facetoface.Utility;
import com.example.facetoface.data.DataDBHandler;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;

public class FirstFragment extends Fragment implements AdapterView.OnItemSelectedListener {

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

        Spinner spinner = (Spinner) testView.findViewById(R.id.filter_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.filterChoicesArray, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.e("hi,", String.valueOf(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return testView;
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        System.out.println(parent.getItemAtPosition(pos));
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
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
