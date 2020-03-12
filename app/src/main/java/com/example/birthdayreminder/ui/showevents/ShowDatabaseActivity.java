package com.example.birthdayreminder.ui.showevents;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.birthdayreminder.R;
import com.example.birthdayreminder.base.BaseActivity;
import com.example.birthdayreminder.base.BasePresenter;
import com.example.birthdayreminder.base.BaseView;

import com.example.birthdayreminder.data.model.Contact;
import com.example.birthdayreminder.ui.Constants;
import com.example.birthdayreminder.ui.editevent.EditBirthdayActivity;
import com.example.birthdayreminder.ui.newevent.NewBirthdayActivity;

public class ShowDatabaseActivity extends BaseActivity implements BaseView {
    private ContactsListAdapter adapter;
    private BasePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initRecyclerView();
        presenter = new ShowDatabasePresenter(this, adapter);
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
                presenter.onMenuClicked(ShowDatabaseActivity.this, NewBirthdayActivity.class);
                return true;
            case R.id.settings:
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void initRecyclerView() {
        adapter = new ContactsListAdapter(ShowDatabaseActivity.this);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(ShowDatabaseActivity.this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Contact contact = adapter.getContactAtPosition(position);
                        presenter.onClickRecyclerView(ShowDatabaseActivity.this, EditBirthdayActivity.class, contact);
                    }
                    @Override
                    public void onLongItemClick(View view, int position) {
                        AlertDialog.Builder alert = new AlertDialog.Builder(ShowDatabaseActivity.this);
                        alert.setTitle( getResources().getString(R.string.alert_title));
                        alert.setPositiveButton(getResources().getString(R.string.positive_button), (dialog, whichButton) -> {
                            Contact contact = adapter.getContactAtPosition(position);
                            presenter.onClickPositiveButton(position, contact);
                            recyclerView.removeViewAt(position);
                            adapter.notifyItemRemoved(position);
                        });
                        alert.setNegativeButton(getResources().getString(R.string.negative_button), (dialog, whichButton) -> {
                        });
                        alert.show();
                    }
                })
        );
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public  void launchEditBirthdayActivity(Context context, Class nextActivity, Contact contact){
        Intent intent = new Intent(context, nextActivity );
        Bundle extras = new Bundle();
        extras.putInt(Constants.ID_KEY, contact.getId());
        extras.putString(Constants.FIRST_NAME_KEY,contact.getName());
        extras.putString(Constants.LAST_NAME_KEY, contact.getLastName());
        extras.putLong(Constants.BIRTHDAY_KEY, contact.getDateOfBirth());
        intent.putExtras(extras);
        startActivity(intent);
    }

    @Override
    public void showNewScreen(Context context, Class nextActivity) {
        Intent intent = new Intent(context, nextActivity);
        startActivity(intent);
    }

    @Override
    public void showProgressBar() {
    }

    @Override
    public void hideProgressBar() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}