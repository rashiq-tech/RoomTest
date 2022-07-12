package com.appslet.roomtest;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SubDao {

    @Query("SELECT * FROM tb_subEntry")
    List<SubEntry> getAll();

    @Insert
    void insertSub(SubEntry subEntry);

}
