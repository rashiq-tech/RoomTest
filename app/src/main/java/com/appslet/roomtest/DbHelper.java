package com.appslet.roomtest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DbHelper extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "RoomTest";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_SUB_ENTRY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_DELETE_SUB_ENTRY);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Entry.TABLE_NAME + " (" +
                    Entry._ID + " INTEGER PRIMARY KEY NOT NULL," +
                    Entry.COLUMN_NAME_TITLE + " TEXT," +
                    Entry.COLUMN_NAME_SUBTITLE + " TEXT)";

    private static final String SQL_CREATE_SUB_ENTRY =
            "CREATE TABLE " + SubEntry.TABLE_NAME + " (" +
                    SubEntry._ID + " INTEGER PRIMARY KEY NOT NULL," +
                    SubEntry.COLUMN_NAME_SUBTITLE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Entry.TABLE_NAME;
    private static final String SQL_DELETE_SUB_ENTRY =
            "DROP TABLE IF EXISTS " + SubEntry.TABLE_NAME;

    public static class Entry implements BaseColumns {
        public static final String TABLE_NAME = "tb_entry";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";
    }
    public static class SubEntry implements BaseColumns {
        public static final String TABLE_NAME = "tb_subEntry";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";
    }
}
