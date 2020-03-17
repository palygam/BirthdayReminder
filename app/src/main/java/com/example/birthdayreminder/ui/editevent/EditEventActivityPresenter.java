package com.example.birthdayreminder.ui.editevent;

import android.content.Context;
import android.os.Handler;

import com.example.birthdayreminder.CustomApplication;
import com.example.birthdayreminder.base.BasePresenter;

import java.util.Calendar;

public class EditEventActivityPresenter implements BasePresenter {
    private Calendar calendar;
    private EditEventActivityView view;

    public EditEventActivityPresenter(EditEventActivityView view) {
        this.view = view;
    }

    public void updateContact(String name, String lastName, long date, long daysLeft, int id) {
        final Handler handler = new Handler();
        view.showProgressBar();
        Thread backgroundThread = new Thread(() -> {
            CustomApplication.getRepository().update(name, lastName, date, daysLeft, id);
            handler.post(() ->
                    view.hideProgressBar());
        });
        backgroundThread.start();
    }

    public long getDaysLeft() {
        final Calendar todayDate = Calendar.getInstance();
        final Calendar birthdayCountdown = calendar;

        final int monthOfBirthday = birthdayCountdown.get(Calendar.MONTH);
        final int monthToday = todayDate.get(Calendar.MONTH);

        final int dayOfBirthday = birthdayCountdown.get(Calendar.DAY_OF_MONTH);
        final int dayToday = todayDate.get(Calendar.DAY_OF_MONTH);
        birthdayCountdown.set(Calendar.YEAR, todayDate.get(Calendar.YEAR));

        if (monthOfBirthday < monthToday) {
            birthdayCountdown.set(Calendar.YEAR, todayDate.get(Calendar.YEAR) + 1);
        } else if (monthOfBirthday == monthToday) {
            if (dayOfBirthday < dayToday) {
                birthdayCountdown.set(Calendar.YEAR, todayDate.get(Calendar.YEAR) + 1);
            }
        }
        final long daysLeft = (birthdayCountdown.getTimeInMillis() - todayDate.getTimeInMillis()) / 86400000;
        return daysLeft;
    }

    public void onDateSet(int year, int month, int dayOfMonth) {
        String dateOfBirth = dayOfMonth + "/" + (month + 1) + "/" + year;
        view.setDateText(dateOfBirth);
    }

    public void onDateClicked() {
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        view.displayDatePickerDialog(year, month, day);
    }

    public Calendar onCalendarSet(int year, int monthOfYear, int dayOfMonth) {
        calendar.set(year, monthOfYear, dayOfMonth);
        return calendar;
    }

    public void onMenuClicked(Context context, Class newActivity) {
        view.navigateToNewActivity(context, newActivity);
    }
}
