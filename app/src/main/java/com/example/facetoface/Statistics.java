package com.example.facetoface;

import com.example.facetoface.data.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Collection;

public class Statistics {

    private Data dataObject;
    private ArrayList<Data> data;
    //start, end, last, patient, total
    public Statistics() {
        data = new ArrayList<>();
    }

    public Statistics(List<Data> data) {
        data = data;
    }

    /**
     * mean - return the mean if the percentage in the data list
     * @return float
     */
    public float mean() {
        // todo change return type and return mean
        int percentage = 0;

        for(int i=0; i<this.data.size(); i++)
        {
            percentage += this.data.get(i).getPercentage();
        }

        return (float)percentage/this.data.size();
    }

    /**
     * median - return the median number of the percentage of the data list
     * @return float
     */
    public float median() {
        // todo change return type and return median
        Collections.sort(data);
        int middle = this.data.size()/2;
        if(this.data.size()%2 == 1){
            return this.data.get(middle).getPercentage();
        }
        else
        {
            return (float)((this.data.get(middle-1).getPercentage() + this.data.get(middle).getPercentage())/2.0);
        }
    }

    /**
     * mode - return the most frequent percentage in the data list
     * @return float
     */
    public float mode(){
        float maxValue = 0;
        int maxCount = 0;

        for(int i=0; i<this.data.size(); i++)
        {
            int count = 0;
            for(int j=0; j<this.data.size(); j++)
            {
                if(this.data.get(i) == this.data.get(j)){
                    ++count;
                }
            }
            if(count > maxCount)
            {
                maxCount = count;
                maxValue = this.data.get(i).getPercentage();
            }
        }
        return maxValue;


    }

    public void setFilter(Filter filter) {
        if (filter == Filter.WEEK) {

        } else if (filter == Filter.DAY) {

        } else {
            // Filter.ALLTIME is the default

        }
    }


}
