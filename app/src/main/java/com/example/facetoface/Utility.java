package com.example.facetoface;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.example.facetoface.data.Data;
import com.example.facetoface.data.DataDBHandler;
import com.github.mikephil.charting.charts.*;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.*;
import com.github.mikephil.charting.interfaces.datasets.*;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Utility class
 *
 * handles data use to render the line and pie charts
 * public static helper functions
 */
public class Utility {

    /**
     * formatDataList
     *
     * formats a ArrayList< Data > to a hashmap of averages
     * helper function for setLineChartData(...)
     * @param dataList
     * @return HashMap
     */
    public static TreeMap<String, Float> formatDataList(ArrayList<Data> dataList) {

        // hashmap
        TreeMap<String, Float> averages = new TreeMap<>();
        TreeMap<String, ArrayList<Data>> map = Utility.getMapOfData(dataList);

        // this loop gets the averages
        for (Map.Entry<String, ArrayList<Data>> entry : map.entrySet()) {
            float sum = 0;
            for (Data d : entry.getValue()) {
                sum += d.getPercentage();
            }

            averages.put(entry.getKey(), sum / entry.getValue().size());
        }

        return averages;
    }

    public static TreeMap<String, ArrayList<Data>> getMapOfData(ArrayList<Data> dataList) {
        TreeMap<String, ArrayList<Data>> map = new TreeMap<>();
        for (Data data: dataList) {

            // calendar converts the day into the number of day of the year
            // example Nov 15 = 320th day of the year, 0 index based (319)
            // ans use 320 as key in hashmap
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(data.getStart());
            String key = new SimpleDateFormat("M/d").format(cal.getTime());

            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<Data>());
            }

            // patient percentages
            map.get(key).add(data);
        }

        return map;
    }


    /**
     * setPieChartData
     *
     * just sets the pie chart data given Chart and Data
     * @param Chart
     * @param d
     */
    public static void setPieChartData(Chart Chart, Data d) {

        // null case
        if (d.getStart() == 0 && d.getEnd() == 0) {
            return;
        }

        // set up variables
        ArrayList<Entry> entries = new ArrayList<>();
        DataSet dataset;
        ChartData data;

        // divide entries by 1000 since data is in milliseconds
        entries.add(new Entry((float) d.getTimeWithoutPatient()/1000, 0));
        entries.add(new Entry((float) d.getPatient()/1000, 1));

        dataset = new PieDataSet(entries, "Patient Data");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);

        data = new PieData(Data.getLabels(), (IPieDataSet) dataset);

        Chart.animateY(3000);
        Chart.setData(data);

    }

    /**
     * setLineChartData
     *
     * sets linechart data given Chart and a map of averages
     * @param Chart
     */
    public static void setLineChartData(Chart Chart, ArrayList<Data> dataList) {

        TreeMap<String, Float> averages = Utility.formatDataList(dataList);

        // null case
        if (averages.size() == 0) {
            return;
        }

        // set up variables
        ArrayList<Entry> entries = new ArrayList<>();
        DataSet dataset;
        ChartData data;

        int index = 0;
        for (Map.Entry<String, Float> entry : averages.entrySet()) {
            // entries require (float, index position)
            entries.add(new Entry(entry.getValue(), index));
            index++;
        }

        // label for line chart
        dataset = new LineDataSet(entries, "Happiness Level");

        // LineData requires ( Array of Strings as labels, dataset)
        data = new LineData(new ArrayList<String>(averages.keySet()), (ILineDataSet) dataset);

        Chart.animateY(3000);
        Chart.setData(data);

    }

    public static void setCandleChartData(Chart chart, ArrayList<Data> dataList) {

        ArrayList<CandleEntry> yVals = new ArrayList<CandleEntry>();
        TreeMap<String, ArrayList<Data>> treeMap = Utility.getMapOfData(dataList);
        ArrayList<String> xVals = new ArrayList<String>(treeMap.keySet());

        int i = 0;
        for (Map.Entry<String, ArrayList<Data>> entry : treeMap.entrySet()) {
            yVals.add(Utility.setCandleEntryData(i, entry.getValue()));
            i++;
        }

        CandleDataSet set1 = new CandleDataSet(yVals, "Data Set");

        CandleData data = new CandleData(xVals, set1);

        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        set1.setShadowColor(Color.DKGRAY);
        set1.setShadowWidth(1.7f);
        set1.setIncreasingColor(Color.rgb(122, 242, 84));
        set1.setIncreasingPaintStyle(Paint.Style.STROKE);
        set1.setNeutralColor(Color.BLUE);
        chart.setData(data);
    }

    public static CandleEntry setCandleEntryData(int index, ArrayList<Data> data) {

        Collections.sort(data);
        float low = data.get(0).getPercentage();
        float high = data.get(0).getPercentage();
        float firstQuartile = 0;
        float thirdQuartile = 0;
        int quartile = data.size()/4;
        int i = 0;
        for (Data d: data) {
            float percentage = d.getPercentage();
            if (percentage < low && percentage != 0) {
                low = percentage;
            }

            if (percentage > high) {
                high = percentage;
            }

            if (i == quartile) {
                firstQuartile = percentage;
            }

            if (i == quartile*3) {
                thirdQuartile = percentage;
            }

            i++;
        }

        return new CandleEntry(index, high, low, firstQuartile, thirdQuartile);
    }

}
