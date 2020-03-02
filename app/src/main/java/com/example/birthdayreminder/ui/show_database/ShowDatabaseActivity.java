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

public class ShowDatabaseActivity extends BaseActivity {
    private ShowDatabaseAdapter adapter;
    private List<Birthday> birthdays = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_database);
        setupToolbar();
        intiRecyclerView();
        loadContacts();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_show_database;
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            toolbar.setNavigationOnClickListener(v -> onBackPressed());
        }
    }

    private void loadContacts() {
        final Handler handler = new Handler();
        Thread backgroundThread = new Thread(() -> {
            birthdays = AppDatabase.getINSTANCE(ShowDatabaseActivity.this).contactDao().getAll();
            handler.post(() -> adapter.setBirthdays(birthdays));
        });
        backgroundThread.start();
    }

    private void intiRecyclerView() {
        adapter = new RecyclerViewAdapter(ShowDatabaseActivity.this);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }
}