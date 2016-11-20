package com.example.facetoface;

import com.example.facetoface.data.Data;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Collection;
import java.util.Map;

public class Statistics {

    private ArrayList<Data> data;
    private ArrayList<Data> fullData;
    private Data current;

    //start, end, last, patient, total
    public Statistics() {
        data = new ArrayList<>();
    }

    public ArrayList<Data> getData(){
        return this.data;
    }

    /**
     * getfake - return the arraylist of fake data
     * @return ArrayList<Data>
     */
// function to return a fake data set
    public ArrayList<Data> getFakeData(){

        ArrayList<Data> fakeData = new ArrayList<>();
        long[] patientValues = new long[] {5000l, 10000l, 15000l, 20000l, 25000l, 30000l, 35000l,
                40000l, 45000l, 48000l};

        for (int i=0; i<10; i++) {
            Data d = new Data();
            d.setEnd(50000l);
            d.setPatient(patientValues[i]);
            fakeData.add(d);
        }

        return fakeData;
    }

    public Statistics(ArrayList<Data> data) {
        this.fullData = (ArrayList<Data>)data.clone();
        this.data = (ArrayList<Data>) fullData.clone();
    }

    /**
     * mean - return the mean if the percentage in the data list
     * @return float
     */
    public float mean(ArrayList<Data> temp) {
        // todo change return type and return mean
        float percentage = 0;

        for (int i = 0; i < temp.size(); i++) {
            percentage += temp.get(i).getPercentage();
        }

        return (float) percentage / temp.size();
    }

    /**
     * median - return the median number of the percentage of the data list
     * @return float
     */
    public float median(ArrayList<Data> temp) {
        // todo change return type and return median
        Collections.sort(data);
        int middle = temp.size()/2;
        if(temp.size()%2 == 1){
            return temp.get(middle).getPercentage();
        }
        else
        {
            return (float)((temp.get(middle-1).getPercentage() + temp.get(middle).getPercentage())/2.0);
        }
    }

    /**
     * variance - return the variance of the percentage of the data list
     * @return float
     */
    public float variance(ArrayList<Data> temp)
    {
        float mean = mean(temp);
        double t = 0;
        for(int i=0; i<temp.size(); i++)
        {
            float data = temp.get(i).getPercentage();
            t += (data-mean)*(data-mean);
        }
        return (float)t/temp.size();
    }

    /**
     * stdDev - return the standard deviation of the pecentage of the data list
     * @return float
     */
    public float stdDev(ArrayList<Data> temp)
    {
        return (float)Math.sqrt(variance(temp));
    }


    /**
     * ttest - return the t-test by comparing two group of data list
     * @return
     */
    public float ttest(ArrayList<Data> temp)
    {
        ArrayList<Data> realData = new ArrayList<>();
        ArrayList<Data> fakeData = new ArrayList<>();

        //get the last ten data from realdata;
        for(int i=temp.size()-1; i>temp.size()-11; i--)
        {
            realData.add(temp.get(i));
        }

        fakeData = getFakeData();
        //get all the data for for t-test: variance, mean, stdDev, sampleSize=10
        float m1 = mean(realData);
        float m2 = mean(fakeData);

        float v1 = variance(temp);
        float v2 = variance(fakeData);

        int sampleSize = 10;

        float s1 = stdDev(realData);
        float s2 = stdDev(fakeData);

        return ((float)Math.abs(m1 - m2))/(float)(Math.sqrt((1f/sampleSize)/(s1*s1 + s2*s2)));
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

    public void setCurrent(Data d) {
        this.current = d;
    }

    public Data getCurrent() {
        return this.current;
    }

    public ArrayList<Data> getAll() {
        return fullData;
    }


}
