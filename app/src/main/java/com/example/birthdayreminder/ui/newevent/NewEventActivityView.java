package com.example.birthdayreminder.ui.newevent;

import android.content.Context;

import com.example.birthdayreminder.base.BaseView;
import com.example.birthdayreminder.data.model.Event;

import java.util.List;

public interface NewEventActivityView extends BaseView {

    void navigateToNewActivity(Context context, Class nextActivity, Enum screenType);

    @Override
    void showProgressBar();

    @Override
    void hideProgressBar();

    void setDateText(String date);

    void displayDatePickerDialog(int year, int month, int day);
}
