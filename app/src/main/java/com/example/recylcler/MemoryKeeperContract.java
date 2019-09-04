package com.example.recylcler;

import android.provider.BaseColumns;

public final class MemoryKeeperContract implements BaseColumns {
    private MemoryKeeperContract() { }
    public static final class ClassOne{
        //---------TABLE CREATION AND COLUMN DEFINITION----
        public static final String TABLE_NAME = "course_info";
        public static final String COLUMN_COURSE_TITLE = "Courses";
        public static final String COLUMN_COURSE_ID = "course_id";
        //-----Create table using this format
        /*
        CREATE TABLE Table_Name (column 1, column 2, column 3,..., column x)

        [SQL Keywords are put in all caps by coding conventions. Hence why the keyword, CREATE TABLE
         is in all caps]

        So for our table above, that would look like:
        CREATE TABLE TABLE_NAME (COLUMN_COURSE_TITLE, COLUMN_COURSE_ID)

        We need a constant to create the table using the stated constants

         */
        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " ( "
                        + COLUMN_COURSE_TITLE + " TEXT NOT NULL, "
                        + _ID + " INTEGER PRIMARY KEY,"
                        + COLUMN_COURSE_ID + " TEXT NOT NULL UNIQUE)";

    }
    public static final class ClassTwo{
        public static final String TABLE_NAME = "table_notes";
        public static final String COLUMN_NOTE_TITLE = "note_title";
        public static final String COLUMN_NOTE_TEXT = "note_text";
        public static final String COLUMN_COURSE_ID = "course_id";


        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE" + TABLE_NAME + " ("
                        + COLUMN_NOTE_TITLE + " TEXT NOT NULL ,"
                        + COLUMN_NOTE_TEXT + "TEXT ,"
                        + _ID + " INTEGER PRIMARY KEY,"
                        + COLUMN_COURSE_ID + " TEXT NOT NULL)";

    }
}
