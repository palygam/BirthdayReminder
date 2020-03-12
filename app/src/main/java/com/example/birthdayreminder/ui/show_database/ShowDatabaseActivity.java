package com.example.birthdayreminder.ui.show_database;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.birthdayreminder.R;
import com.example.birthdayreminder.base.BaseActivity;
import com.example.birthdayreminder.base.BasePresenter;
import com.example.birthdayreminder.base.BaseView;

import com.example.birthdayreminder.ui.add_new_birthday.NewBirthdayActivity;

public class ShowDatabaseActivity extends BaseActivity implements BaseView {
    private ContactsListAdapter adapter;
    private BasePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
      //  presenter.attachView(this);
        presenter = new ShowDatabasePresenter(this, adapter);
        initRecyclerView();
        setupToolbar();
        presenter.loadBirthdaysList();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_show_database;
    }

    @Override
    public void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            toolbar.setNavigationOnClickListener(v -> onBackPressed());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_new_notification_menu:
                showNewScreen(this, NewBirthdayActivity.class);
                return true;
            case R.id.settings:
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void initRecyclerView() {
        adapter = new ContactsListAdapter(ShowDatabaseActivity.this);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void showNewScreen(Context context, Class nextActivity) {
        presenter.onMenuClicked(context, nextActivity);
    }

}