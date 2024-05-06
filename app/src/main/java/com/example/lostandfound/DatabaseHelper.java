package com.example.lostandfound;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {

    private Context context;
    private static DatabaseHelper instance;
    private LostAndFoundDatabase lostAndFoundDatabase;

    private DatabaseHelper(Context context) {
        this.context = context;

        lostAndFoundDatabase = Room.databaseBuilder(context, LostAndFoundDatabase.class, "lost_and_found_database3")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context);
        }
        return instance;
    }

    public LostAndFoundDatabase getLostAndFoundDatabase() {
        return lostAndFoundDatabase;
    }
}
