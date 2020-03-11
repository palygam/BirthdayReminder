package com.example.birthdayreminder.ui.show_database;

import android.os.Handler;

import com.example.birthdayreminder.CustomApplication;
import com.example.birthdayreminder.base.BasePresenter;
import com.example.birthdayreminder.base.BaseView;
import com.example.birthdayreminder.data.model.Birthday;

import java.util.ArrayList;
import java.util.List;

public class ShowDatabasePresenter implements BasePresenter {
    private ShowDatabaseAdapter adapter;
    private BaseView view;
    private List<Birthday> birthdays = new ArrayList<>();

    public ShowDatabasePresenter(BaseView view, ShowDatabaseAdapter adapter) {
        this.view = view;
        this.adapter = adapter;
    }

    public void loadBirthdaysList() {
        final Handler handler = new Handler();
        Thread backgroundThread = new Thread(() -> {
            birthdays = CustomApplication.getRepository().getAllBirthdays();
            handler.post(() -> adapter.setBirthdays(birthdays));
        });
        backgroundThread.start();
    }

    @Override
    public void onDestroy() {
    }
}