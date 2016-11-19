package com.example.facetoface.data;

import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Data
 */
public class Data implements Comparable<Data>{

    // lock for multithreaded tasks
    private final Object mLock = new Object();

    private long start;
    private long end;
    private long last;
    private long patient;

    // maybe this should be with Utility class
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

    /**
     * start
     * starts the stopwatch
     */
    public void start() {
        this.start = System.currentTimeMillis();
        this.last = 0;
    }

    /**
     * end - stops the stopwatch
     */
    public void end() {
        this.end = System.currentTimeMillis();
    }

    /**
     * getTotal - returns the total elapsed time in milliseconds
     * @return Long
     */
    public long getTotal() {
        return this.end - this.start;
    }

    /**
     * getMeanPercentage - returns the percentage of percent in milliseconds
     * @return float
     */
    public float getPercentage(){
        return (float)this.getPatient()/this.getTotal();
    }

    /**
     * getTimeWithoutPatient - the total time of when the patient is out of frame
     * @return Long
     */
    public long getTimeWithoutPatient() {
        return this.getTotal() - this.patient;
    }

    /**
     * faceIsIn - when a face is in frame it will keep counting the ticks as long as
     * the face is still in frame
     */
    public void faceIsIn() {
        synchronized (mLock) {
            long timestamp = System.currentTimeMillis();
            if (this.last != 0) {
                this.patient += timestamp - this.last;
            }
            this.last = timestamp;
        }
    }

    /**
     * faceIsOut - when a face is not in frame, it will add the last remaining tick to the
     * patient and set last to zero
     */
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

    /**
     * zero - zero's out last
     */
    public void zero() {
        synchronized (mLock) {
            this.last = 0;
        }
    }

    // --- Getters and setters below ---

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

    @Override
    public int compareTo(Data percentage) {
        int comparePercentage=(int)((Data)percentage).getPercentage();
            /* For Ascending order*/
        return (int)this.getPercentage()-comparePercentage;
    }

}
