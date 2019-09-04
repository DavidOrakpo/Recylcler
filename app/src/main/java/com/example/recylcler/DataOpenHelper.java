package com.example.recylcler;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Test.db";
    public static final int DATABASE_VERSION = 1;
    public DataOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MemoryKeeperContract.ClassOne.SQL_CREATE_TABLE);
        db.execSQL(MemoryKeeperContract.ClassTwo.SQL_CREATE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {

    }
    public boolean insertDataClass1(String courses, String course_id){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(MemoryKeeperContract.ClassOne.COLUMN_COURSE_TITLE, courses);
        values.put(MemoryKeeperContract.ClassOne.COLUMN_COURSE_ID, course_id);
        long rowID = db.insert(MemoryKeeperContract.ClassOne.TABLE_NAME, null, values);
        return rowID != -1;
    }
    public boolean insertDataClass2(String noteTitle, String noteText, String courseID) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(MemoryKeeperContract.ClassTwo.COLUMN_NOTE_TITLE, noteTitle);
        values.put(MemoryKeeperContract.ClassTwo.COLUMN_NOTE_TEXT, noteText);
        values.put(MemoryKeeperContract.ClassTwo.COLUMN_COURSE_ID, courseID);
       long rowID = db.insert(MemoryKeeperContract.ClassTwo.TABLE_NAME, null, values);
        return rowID != -1;
    }

}


