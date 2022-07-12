package com.appslet.roomtest;

import android.app.Application;

import androidx.room.Room;

public class RoomTestApp extends Application {

    public RoomAppDB db;

    @Override
    public void onCreate() {
        super.onCreate();

        db = Room.databaseBuilder(getApplicationContext(),
                RoomAppDB.class, DbHelper.DATABASE_NAME)
                .allowMainThreadQueries()
                .build();

    }

    @Override
    public void onTerminate() {
        if (db.isOpen()){
            db.close();
        }
        super.onTerminate();
    }
}
