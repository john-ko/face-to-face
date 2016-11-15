package com.example.facetoface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.facetoface.data.DataDBHandler;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;

public class AnalyticActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytic);

        DataDBHandler dbHandler = new DataDBHandler(this, null, null, 1);

        PieChart pieChart1 = (PieChart) findViewById(R.id.pieChart_1);
        Utility.setPieChartData(pieChart1, dbHandler.getLatestEntry());
//        Utility.setChartData(pieChart1,2);

        LineChart lineChart2 = (LineChart) findViewById(R.id.lineChart_3);
        Utility.setLineChartData(lineChart2, Utility.formatDataList(dbHandler.getAll()));
//        Utility.setPieChartData(lineChart2, );
//        Utility.setChartData(lineChart2,1);
    }
}
