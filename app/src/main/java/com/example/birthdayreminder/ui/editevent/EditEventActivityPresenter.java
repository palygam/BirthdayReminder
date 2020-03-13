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

    public void updateContact(String name, String lastName, long date, int id) {
        final Handler handler = new Handler();
        view.showProgressBar();
        Thread backgroundThread = new Thread(() -> {
            CustomApplication.getRepository().update(name, lastName, date, id);
            handler.post(() ->
                    view.hideProgressBar());
        });
        backgroundThread.start();
    }

    public void onDateSet(int year, int month, int dayOfMonth){
        String dateOfBirth = dayOfMonth + "/" +( month + 1 ) + "/" + year;
        view.setDateText(dateOfBirth);
    }

    public void onDateClicked (){
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        view.displayDatePickerDialog(year, month, day);
    }

    public Calendar onCalendarSet(int year1, int monthOfYear, int dayOfMonth) {
        calendar.set(year1, monthOfYear, dayOfMonth);
        return calendar;
    }

    @Override
    public void onClick(Context context, Class newActivity) {
    }

    @Override
    public void onMenuClicked(Context context, Class newActivity) {
        view.navigateToNewActivity(context, newActivity);
    }

    @Override
    public void onDestroy() {
    }
}
