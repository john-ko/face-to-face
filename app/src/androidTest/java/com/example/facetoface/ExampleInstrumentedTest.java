package com.example.facetoface;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.facetoface.data.Data;
import com.example.facetoface.data.DataDBHandler;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.facetoface", appContext.getPackageName());
    }

    @Test
    public void getLatestEntry() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        DataDBHandler dbHandler = new DataDBHandler(appContext, null, null, 1);
        Data d = dbHandler.getLatestEntry();

        Log.d(" -- DEBUG -- ", " - - - - - - - - - - - - - - - ");

        assertEquals("com.example.facetoface", appContext.getPackageName());
        assertTrue(d.getEnd() >= 0);
        assertTrue(d.getStart() >= 0);

        Log.d(" -- DEBUG -- ", "START: " + Long.toString(d.getStart()));
        Log.d(" -- DEBUG -- ", "END: " + Long.toString(d.getEnd()));
        Log.d(" -- DEBUG -- ", "PATIENT: " + Long.toString(d.getPatient()));

    }

    @Test
    public void getAll() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        DataDBHandler dbHandler = new DataDBHandler(appContext, null, null, 1);
        ArrayList<Data> dataList = dbHandler.getAll();

        Log.d(" -- DEBUG -- ", " - - - - - - - - - - - - - - - ");

        assertEquals("com.example.facetoface", appContext.getPackageName());
        assertTrue(dataList.size() >= 0);

        Log.d(" -- DEBUG -- ", "PATIENT: " + Integer.toString(dataList.size()));

    }

    @Test
    public void testUtility() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        DataDBHandler dbHandler = new DataDBHandler(appContext, null, null, 1);
        ArrayList<Data> dataList = dbHandler.getAll();
        HashMap<String, Float> averages = Utility.formatDataList(dataList);

        Log.d(" -- DEBUG -- ", " - - - - - - - - - - - - - - - ");

        for (Map.Entry<String, Float> entry : averages.entrySet()) {
            Log.d(" -- ", entry.getKey() + ": " + Float.toString(entry.getValue()));
        }


    }
}
