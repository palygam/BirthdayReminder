package com.example.birthdayreminder;

import android.app.Application;

import com.example.birthdayreminder.base.BaseRepository;
import com.example.birthdayreminder.data.AppDatabase;
import com.facebook.stetho.Stetho;

public class CustomApplication extends Application {
    private static BaseRepository repository;

    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        AppDatabase database = AppDatabase.getInstance(this);
        repository = new BaseRepository(database.contactDao());
    }

    public static BaseRepository getRepository(){
        return repository;
    }
}