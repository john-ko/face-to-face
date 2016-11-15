package com.example.facetoface;
import com.example.facetoface.data.Data;
import com.example.facetoface.data.DataDBHandler;
import com.github.mikephil.charting.charts.*;
import com.github.mikephil.charting.data.*;
import com.github.mikephil.charting.interfaces.datasets.*;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Utility {

    private static ArrayList<String> getPieChartLabels() {
        ArrayList<String> labels = new ArrayList<>();
        labels.add("patient");
        labels.add("other");
        return labels;
    }

    public static HashMap<String, Float> formatDataList(ArrayList<Data> dataList) {
        HashMap<String, Float> averages = new HashMap<>();
        HashMap<String, ArrayList<Float>> map = new HashMap<>();


        for (Data data: dataList) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(data.getStart());
            String key = new SimpleDateFormat("D").format(cal.getTime());

            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<Float>());
            }

            map.get(key).add((float) data.getPatient() / data.getTotal());


        }

        for (Map.Entry<String, ArrayList<Float>> entry : map.entrySet()) {
            float sum = 0;
            for (float time : entry.getValue()) {
                sum += time;
            }

            averages.put(entry.getKey(), sum / entry.getValue().size());
        }

        return averages;

    }

    public static void setPieChartData(Chart Chart, Data d) {
        ArrayList<Entry> entries = new ArrayList<>();
        DataSet dataset;
        ChartData data;

        entries.add(new Entry((float) d.getTotal()/1000, 0));
        entries.add(new Entry((float) d.getPatient()/1000, 1));

        dataset = new PieDataSet(entries, "Patient Data");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);

        data = new PieData(Data.getLabels(), (IPieDataSet) dataset);

        Chart.animateY(3000);
        Chart.setData(data);

    }

    public static void setLineChartData(Chart Chart, HashMap<String, Float> averages) {
        ArrayList<Entry> entries = new ArrayList<>();
        DataSet dataset;
        ChartData data;

        int index = 0;

        for (Map.Entry<String, Float> entry : averages.entrySet()) {
            entries.add(new Entry(entry.getValue(), index));
            index++;
        }

        dataset = new LineDataSet(entries, "Happiness Level");
        data= new LineData(new ArrayList<String>(averages.keySet()), (ILineDataSet) dataset);

        Chart.animateY(3000);
        Chart.setData(data);
    }
}
