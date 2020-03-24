package com.example.birthdayreminder.ui.settings;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;
import com.example.birthdayreminder.R;
import com.example.birthdayreminder.base.BaseActivity;
import com.example.birthdayreminder.ui.Constants;
import com.example.birthdayreminder.ui.showevents.ShowEventsActivity;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class SetSettingsActivity extends BaseActivity implements SetSettingsActivityView {
    private SetSettingsActivityPresenter presenter;
    private TextInputEditText textInputTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initComponents();
        createNotificationChannel();
        setTimePicker();
        setButton();
    }

    private void initComponents() {
        AlarmReceiver receiver = new AlarmReceiver();
        registerReceiver(receiver, new IntentFilter(Constants.ACTION_UPDATE_NOTIFICATION));
        textInputTime = findViewById(R.id.text_input_time);
        presenter = new SetSettingsActivityPresenter(this);
        setupToolbar();
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
    public void setAlarm(Calendar calendar, String name, String lastName) {

        Intent notifyIntent = new Intent(this, AlarmReceiver.class);
        notifyIntent.putExtra(Constants.FIRST_NAME_KEY, name);
        notifyIntent.putExtra(Constants.LAST_NAME_KEY, lastName);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        final PendingIntent notifyPendingIntent = PendingIntent.getBroadcast
                (this, Constants.NOTIFICATION_ID, notifyIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating
                (AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), DateUtils.YEAR_IN_MILLIS, notifyPendingIntent);
    }

    @Override
    public void navigateToNewActivity(Context context, Class newActivity) {
        Intent intent = new Intent(context, newActivity);
        startActivity(intent);
    }

    private void setButton() {
        Button setAlarm = findViewById(R.id.button_alarm);
        setAlarm.setOnClickListener(v -> {
            presenter.deliverNotification();
            presenter.onClick(this, ShowEventsActivity.class);
        });
    }

    private void setTimePicker() {
        textInputTime.setOnClickListener(v -> presenter.onTimeClicked());
    }

    @Override
    public void displayTimePickerDialog(int hour, int minutes) {
        TimePickerDialog timePicker = new TimePickerDialog(SetSettingsActivity.this, R.style.DatePickerStyle, (view, hourOfDay, minutesOfHour) -> {
            presenter.onTimeSet(hourOfDay, minutesOfHour);
        }, hour, minutes, true);
        timePicker.show();
    }

    public void createNotificationChannel() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(Constants.PRIMARY_CHANNEL_ID,
                    "BirthdayReminder Notification", NotificationManager
                    .IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification from BirthdayReminder");
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    @Override
    public void setTimeText(String time) {
        textInputTime.setText(time);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_set_settings;
    }

    @Override
    public void showProgressBar() {
    }

    @Override
    public void hideProgressBar() {
    }
}