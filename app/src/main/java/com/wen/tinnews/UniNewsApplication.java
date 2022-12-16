package com.wen.tinnews;

import android.app.Application;

import androidx.room.Room;

import com.wen.tinnews.model.UniNewsDatabase;

public class UniNewsApplication extends Application {

    private static UniNewsDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(this, UniNewsDatabase.class, "uninews_db").build();
    }

    public static UniNewsDatabase getDatabase() {
        return database;
    }
}