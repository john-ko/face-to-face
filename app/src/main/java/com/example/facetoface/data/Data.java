package com.example.facetoface.data;

import java.util.ArrayList;

/**
 * Created by john on 11/14/16.
 */

public class Data {

    private final Object mLock = new Object();

    private long start;
    private long end;
    private long last;
    private long patient;

    public static ArrayList<String> getLabels() {
        ArrayList<String> labels = new ArrayList<>();
        labels.add("time without");
        labels.add("time with patient");
        return labels;
    }

    public Data() {
        this.start = 0;
        this.end = 0;
        this.last = 0;
        this.patient = 0;
    }

    public void start() {
        this.start = System.currentTimeMillis();
        this.last = this.start;
    }

    public void end() {
        this.end = System.currentTimeMillis();
    }

    public long getTotal() {
        return this.end - this.start;
    }

    public void faceIsIn() {
        synchronized (mLock) {
            long timestamp = System.currentTimeMillis();
            if (this.last != 0) {
                this.patient += timestamp - this.last;
            }
            this.last = timestamp;
        }
    }

    public void faceIsOut() {
        synchronized (mLock) {
            long timestamp = System.currentTimeMillis();
            if (this.last != 0) {
                this.patient += timestamp - this.last;
            } else {
                this.last = 0;
            }
        }
    }

    public void zero() {
        synchronized (mLock) {
            this.last = 0;
        }
    }

    public void save() {

    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public long getLast() {
        return last;
    }

    public void setLast(long last) {
        this.last = last;
    }

    public long getPatient() {
        return this.patient;
    }

    public void setPatient(long patient) {
        this.patient = patient;
    }
}
