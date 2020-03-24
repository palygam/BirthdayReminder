package com.example.birthdayreminder.ui.settings;

import android.content.Context;

import com.example.birthdayreminder.base.BaseView;

import java.util.Calendar;

public interface SetSettingsActivityView extends BaseView {
    void displayTimePickerDialog(int hour, int minute);
    void setTimeText(String time);

    void setAlarm(Calendar calendar, String name, String lastName);

    void navigateToNewActivity(Context context, Class newActivity);
}