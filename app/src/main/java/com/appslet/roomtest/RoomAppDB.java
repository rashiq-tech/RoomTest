package com.appslet.roomtest;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {SubEntry.class, Entry.class}, version = DbHelper.DATABASE_VERSION)
public abstract class RoomAppDB extends RoomDatabase {
    public abstract SubDao userDao();
}
