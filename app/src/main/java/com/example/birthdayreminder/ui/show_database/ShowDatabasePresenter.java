package com.example.birthdayreminder.ui.show_database;

import android.os.Handler;
import android.util.Log;
import com.example.birthdayreminder.base.BasePresenter;
import com.example.birthdayreminder.base.BaseRepository;
import com.example.birthdayreminder.base.BaseView;
import com.example.birthdayreminder.data.AppDatabase;
import com.example.birthdayreminder.data.model.Birthday;

import java.util.ArrayList;
import java.util.List;

public class ShowDatabasePresenter implements BasePresenter {
  //  private ShowDatabaseAdapter adapter;
    private static final String TAG = "DatabasePresenter";
    private BaseView view;
    private BaseRepository repository;
    private List<Birthday> birthdays = new ArrayList<>();
  //  private MainContract.Repository mRepository;

    public ShowDatabasePresenter(BaseView view) {
        this.view = view;
        this.repository = new ShowDatabaseRepository();
        Log.d(TAG, "Constructor" );
    }


    public void loadBirthdaysList(){
        Log.d(TAG, "loadBirthdays()");
        final Handler handler =new Handler();
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                birthdays = AppDatabase.getInstance().birthdayDao().getAll();
                handler.post(() -> adapter.setBirthdays(birthdays));
            }
        });
        backgroundThread.start();
        Log.d(TAG, "loadBirthdaysList");
    }


     public void initRecyclerView(){

     }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy()");
    }

}