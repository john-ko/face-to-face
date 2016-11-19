package com.example.facetoface;

import com.example.facetoface.data.Data;

import java.util.ArrayList;
import java.util.List;

public class Statistics {

    private ArrayList<Data> data;

    public Statistics() {
        data = new ArrayList<>();
    }

    public Statistics(List<Data> data) {
        data = data;
    }

    public void mean() {
        // todo change return type and return mean
    }

    public void median() {
        // todo change return type and return median
    }

    public void mode() {
        // " " " "
    }

    public void setFilter(Filter filter) {
        if (filter == Filter.WEEK) {

        } else if (filter == Filter.DAY) {

        } else {
            // Filter.ALLTIME is the default

        }
    }


}
