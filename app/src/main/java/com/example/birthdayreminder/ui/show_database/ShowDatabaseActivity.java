package com.example.birthdayreminder.ui.show_database;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.birthdayreminder.R;
import com.example.birthdayreminder.base.BaseActivity;
import com.example.birthdayreminder.data.database.AppDatabase;
import com.example.birthdayreminder.data.model.Birthday;

import java.util.ArrayList;
import java.util.List;

public class ShowDatabaseActivity extends BaseActivity implements ShowDatabaseContract {
    ShowDatabasePresenter presenter;
    ShowDatabaseContract contract;
    private ShowDatabaseAdapter adapter;
    private List<Birthday> birthdays = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_database);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_show_database;
    }

    @Override
    public void loadBirthdays() {
        contract.loadBirthdays();
    }

    @Override
    public void setupToolbar() {
        contract.setupToolbar();

    }

    @Override
    public void initRecyclerView() {
        contract.initRecyclerView();
    }

}