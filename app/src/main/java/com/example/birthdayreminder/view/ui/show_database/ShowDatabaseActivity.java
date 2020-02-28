package com.example.birthdayreminder.view.ui.show_database;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.birthdayreminder.R;

import java.util.List;

public class ShowDatabaseActivity implements ShowDatabaseView{
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ShowDatabasePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_database);
        recyclerView = findViewById(R.id.recycler_view);
     //   progressBar = findViewById(R.id.progress);
        presenter = new ShowDatabasePresenter(this, new FindItemsInteractor());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setBirthdays(List<String> birthdays) {
        recyclerView.setAdapter(new ShowDatabaseAdapter(birthdays, presenter::onItemClicked));
    }


  /*  private void intiRecyclerView() {
        adapter = new ContactsListAdapter(ShowDatabaseActivity.this);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    private void loadContacts() {
        final Handler handler = new Handler();
        Thread backgroundThread = new Thread(() -> {
            contacts = AppDatabase.getINSTANCE(ShowDatabaseActivity.this).contactDao().getAll();
            handler.post(() -> adapter.setContacts(contacts));
        });
        backgroundThread.start();
    }*/

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}

