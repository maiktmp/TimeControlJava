package com.example.maiknight.timecontroljava;

import android.app.Application;

import com.example.maiknight.timecontroljava.Database.DBTime;
import com.facebook.stetho.Stetho;

import androidx.room.Room;

public class AppTime extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);

        DBTime.db = Room
                .databaseBuilder(
                        this,
                        DBTime.class,
                        "time.db"
                )
                .fallbackToDestructiveMigration()
                .build();
    }
}
