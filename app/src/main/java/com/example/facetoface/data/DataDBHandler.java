package com.example.facetoface.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by john on 11/14/16.
 */

public class DataDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "data.db";
    private static final String TABLE_NAME = "data";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_START = "start";
    private static final String COLUMN_END = "end";
    private static final String COLUMN_PATIENT = "patient";


    public DataDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_START + " INTEGER," +
                COLUMN_END + " INTEGER," +
                COLUMN_PATIENT + " INTEGER" +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void dropTables() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
//        System.out.println(" DROPING TABLE 0---");
//        System.out.println(db.delete(TABLE_NAME, null, null));

    }
    public long save(Data data) {

        ContentValues values = new ContentValues();

        values.put(COLUMN_START, data.getStart());
        values.put(COLUMN_END, data.getEnd());
        values.put(COLUMN_PATIENT, data.getPatient());

        SQLiteDatabase db = getWritableDatabase();
        return db.insert(TABLE_NAME, null, values);
    }

    public Data getLatestEntry() {
        Data data = new Data();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_ID + " DESC LIMIT 1";
        Cursor c = db.rawQuery(query, null);
        if (c.moveToFirst() && c.getCount() > 0) {

            data.setStart(c.getLong(c.getColumnIndex(COLUMN_START)));
            data.setEnd(c.getLong(c.getColumnIndex(COLUMN_END)));
            data.setPatient(c.getLong(c.getColumnIndex(COLUMN_PATIENT)));
        }

        db.close();

        // wondering if throwing an exception, or returning null if empty
        return data;
    }

    public ArrayList<Data> getAll() {
        ArrayList<Data> dataList = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor c = db.rawQuery(query, null);

        c.moveToFirst();

        while(!c.isAfterLast()) {
            Data data = new Data();

            data.setStart(c.getLong(c.getColumnIndex(COLUMN_START)));
            data.setEnd(c.getLong(c.getColumnIndex(COLUMN_END)));
            data.setPatient(c.getLong(c.getColumnIndex(COLUMN_PATIENT)));

            dataList.add(data);

            c.moveToNext();
        }

        db.close();


        return dataList;
    }
}
