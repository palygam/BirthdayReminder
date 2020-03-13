package com.example.birthdayreminder.ui.editevent;

import android.content.Context;

import com.example.birthdayreminder.base.BaseView;

public interface EditEventActivityView extends BaseView {

    void navigateToNewActivity(Context context, Class nextActivity);

    @Override
    void showProgressBar();

    @Override
    void hideProgressBar();

    void setDateText(String date);

    void displayDatePickerDialog(int year, int month, int day);
}
