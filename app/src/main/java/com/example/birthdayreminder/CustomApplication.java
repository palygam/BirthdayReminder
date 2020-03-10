package com.example.birthdayreminder;

import android.app.Application;
import android.content.Context;

import com.example.birthdayreminder.data.AppDatabase;
import com.facebook.stetho.Stetho;

public class CustomApplication extends Application {
    public AppDatabase database;
    private static CustomApplication instance;

    public static CustomApplication getInstance() {
        return instance;
    }

    public static Context getContext(){
        return instance;
        // or return instance.getApplicationContext();
    }

    public AppDatabase getDatabase() {
        return database;
    }

    public void onCreate() {
        super.onCreate();
        database = AppDatabase.getInstance(this);
        Stetho.initializeWithDefaults(this);
    }
}