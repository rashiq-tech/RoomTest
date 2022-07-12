package com.appslet.roomtest;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = DbHelper.Entry.TABLE_NAME)
public class Entry {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    public int uid;

    @ColumnInfo(name = DbHelper.Entry.COLUMN_NAME_TITLE)
    private String title;

    @ColumnInfo(name = DbHelper.Entry.COLUMN_NAME_SUBTITLE)
    private String sub;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }
}
