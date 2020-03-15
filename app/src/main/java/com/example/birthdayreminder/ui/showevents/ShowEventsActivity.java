package com.example.birthdayreminder.ui.showevents;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.birthdayreminder.R;
import com.example.birthdayreminder.base.BaseActivity;

import com.example.birthdayreminder.data.model.Event;
import com.example.birthdayreminder.ui.Constants;
import com.example.birthdayreminder.ui.editevent.EditEventActivity;
import com.example.birthdayreminder.ui.newevent.NewEventActivity;
import com.example.birthdayreminder.ui.settings.SetSettingsActivity;

import java.util.List;

public class ShowEventsActivity extends BaseActivity implements ShowEventsActivityView {
    private EventsListAdapter adapter;
    private ShowEventsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initRecyclerView();
        presenter = new ShowEventsPresenter(this);
        setupToolbar();
        presenter.loadBirthdaysList();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_show_events;
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


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_new_notification_menu:
                presenter.onMenuClicked(ShowEventsActivity.this, NewEventActivity.class);
                return true;
            case R.id.settings:
                presenter.onMenuClicked(ShowEventsActivity.this, SetSettingsActivity.class);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void initRecyclerView() {
        adapter = new EventsListAdapter(ShowEventsActivity.this);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);
        adapter.SetOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(View itemView, int position) {
                Event event = adapter.getContactAtPosition(position);
                presenter.onClickRecyclerView(ShowEventsActivity.this, EditEventActivity.class, event);
            }

            @Override
            public void onLongClick(View itemView, int position) {
                AlertDialog.Builder alert = new AlertDialog.Builder(ShowEventsActivity.this);
                alert.setTitle(getResources().getString(R.string.alert_title));
                alert.setPositiveButton(getResources().getString(R.string.positive_button), (dialog, whichButton) -> {
                    Event event = adapter.getContactAtPosition(position);
                    presenter.onClickPositiveButton(position, event);
                    recyclerView.removeViewAt(position);
                    adapter.notifyItemRemoved(position);
                });
                alert.setNegativeButton(getResources().getString(R.string.negative_button), (dialog, whichButton) -> {
                });
                alert.show();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }


    @Override
    public void launchEditBirthdayActivity(Context context, Class nextActivity, Event event) {
        Intent intent = new Intent(context, nextActivity);
        Bundle extras = new Bundle();
        extras.putInt(Constants.ID_KEY, event.getId());
        extras.putString(Constants.FIRST_NAME_KEY, event.getName());
        extras.putString(Constants.LAST_NAME_KEY, event.getLastName());
        extras.putLong(Constants.BIRTHDAY_KEY, event.getDateOfBirth());
        intent.putExtras(extras);
        startActivity(intent);
    }

    @Override
    public void navigateToNewActivity(Context context, Class nextActivity) {
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
    public void setEvents(List<Event> events) {
        adapter.setEvents(events);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}