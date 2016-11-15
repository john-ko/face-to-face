package com.example.facetoface.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * DataDBHandler
 *
 * database handler class
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
        // string to create database
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

    // only used on development
    private void dropTables() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /**
     * save
     *
     * Saves Data object to local storage
     * @param data
     * @return Long
     */
    public long save(Data data) {

        ContentValues values = new ContentValues();

        // store values
        values.put(COLUMN_START, data.getStart());
        values.put(COLUMN_END, data.getEnd());
        values.put(COLUMN_PATIENT, data.getPatient());

        SQLiteDatabase db = getWritableDatabase();

        // store into database
        return db.insert(TABLE_NAME, null, values);
    }

    /**
     * getLatestEntry
     *
     * @return
     */
    public Data getLatestEntry() {

        Data data = new Data();

        SQLiteDatabase db = getWritableDatabase();

        // string gets the latest entry from SQLite
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_ID + " DESC LIMIT 1";
        Cursor c = db.rawQuery(query, null);

        // checks to see if data exists
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

        // gets all data from SQLite
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor c = db.rawQuery(query, null);

        // move cursor to the beginning
        c.moveToFirst();

        // while there is still data...
        while(!c.isAfterLast()) {

            // create a new Data object and insert into dataList
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
