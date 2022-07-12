package com.appslet.roomtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;

import com.appslet.roomtest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper = new DbHelper(this);

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        binding.tv1.setOnClickListener(v -> {
            ContentValues values = new ContentValues();
            values.put(DbHelper.Entry.COLUMN_NAME_TITLE, "hii");
            values.put(DbHelper.Entry.COLUMN_NAME_SUBTITLE, "How are you");

            long newRowId = db.insert(DbHelper.Entry.TABLE_NAME, null, values);

            loadDataToTv();
        });

        binding.btNext.setOnClickListener(v -> {
            startActivity(new Intent(this, ViewActivity.class));
        });

        loadDataToTv();

    }

    private void loadDataToTv(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                DbHelper.Entry.COLUMN_NAME_TITLE,
                DbHelper.Entry.COLUMN_NAME_SUBTITLE
        };

        Cursor cursor = db.query(
                DbHelper.Entry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        while(cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.Entry.COLUMN_NAME_TITLE));
            String sub = cursor.getString(
                    cursor.getColumnIndexOrThrow(DbHelper.Entry.COLUMN_NAME_SUBTITLE));

            binding.tv1.setText(binding.tv1.getText().toString() + title + sub + " ");
        }

        String[] projection1 = {
                BaseColumns._ID,
                DbHelper.SubEntry.COLUMN_NAME_SUBTITLE
        };
        Cursor cursor1 = db.query(
                DbHelper.SubEntry.TABLE_NAME,   // The table to query
                projection1,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        while(cursor1.moveToNext()) {
            String sub = cursor1.getString(
                    cursor1.getColumnIndexOrThrow(DbHelper.SubEntry.COLUMN_NAME_SUBTITLE));

            binding.tv1.setText(binding.tv1.getText().toString() + sub + " ");
        }

        cursor.close();
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}