package com.appslet.roomtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;

import com.appslet.roomtest.databinding.ActivityViewBinding;

import java.util.List;

public class ViewActivity extends AppCompatActivity {

    private ActivityViewBinding binding;
    private RoomAppDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = ((RoomTestApp) getApplication()).db;

        binding.tv2.setOnClickListener(v-> {
            SubDao userDao = db.userDao();
            SubEntry subEntry = new SubEntry();
            subEntry.setSub("Im good");
            userDao.insertSub(subEntry);

            loadTvData();

        });
        loadTvData();
    }

    private void loadTvData(){
        SubDao userDao = db.userDao();
        for(SubEntry subEntry : userDao.getAll()){
            binding.tv2.setText(binding.tv2.getText().toString() + subEntry.getSub() + " ");
        }
    }
}