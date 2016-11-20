package com.example.facetoface;

import com.example.facetoface.data.Data;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test() {
        Data d = new Data();



        d.start();

        long timestamp = d.getStart();
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp);
        System.out.println(cal.get(Calendar.MONTH));
        System.out.println(new SimpleDateFormat("M/d/D").format(cal.getTime()));
    }

    @Test
    public void testInt() {

    }
}