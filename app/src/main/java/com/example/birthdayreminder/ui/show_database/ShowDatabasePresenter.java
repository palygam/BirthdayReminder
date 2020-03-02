package com.example.birthdayreminder.ui.show_database;

import android.os.Handler;

import com.example.birthdayreminder.base.BasePresenter;
import com.example.birthdayreminder.base.BaseView;
import com.example.birthdayreminder.data.database.AppDatabase;
import com.example.birthdayreminder.data.model.Birthday;

import java.util.ArrayList;
import java.util.List;

public class ShowDatabasePresenter {
    AppDatabase appDatabase = AppDatabase.getInstance(BasePresenter.getContext());
    private List<Birthday> birthdays = new ArrayList<>();
    private ShowDatabaseContract showDatabaseContract;
    private ShowDatabaseAdapter adapter;

    ShowDatabasePresenter(ShowDatabaseContract showDatabaseContract, ShowDatabaseAdapter adapter) {
        this.showDatabaseContract = showDatabaseContract;
        this.adapter = adapter;
    }

    public void loadBirthdaysList(){
        final Handler handler = new Handler();
        Thread backgroundThread = new Thread(() -> {
            birthdays = appDatabase.birthdayDao().getAll();
            handler.post(() -> adapter.setBirthdays(birthdays));
        });
        backgroundThread.start();

    }

    public ShowDatabaseContract getShowDatabaseContract() {
        return showDatabaseContract;
    }
}