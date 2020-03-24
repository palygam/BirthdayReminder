package com.example.birthdayreminder.ui.settings;

import android.content.Context;
import android.os.Handler;

import com.example.birthdayreminder.CustomApplication;
import com.example.birthdayreminder.base.BasePresenter;
import com.example.birthdayreminder.data.model.Event;
import java.util.Calendar;
import java.util.List;

public class SetSettingsActivityPresenter implements BasePresenter {
    private SetSettingsActivityView view;
    Calendar calendar;
    private List<Event> events;

    public SetSettingsActivityPresenter(SetSettingsActivityView view) {
        this.view = view;
    }


    public void onTimeClicked() {
        calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        view.displayTimePickerDialog(hour, minute);
    }


    public void onTimeSet(int hourOfDay, int minutesOfHour) {
        String time = hourOfDay + "." + minutesOfHour;
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minutesOfHour);
        view.setTimeText(time);
    }

    public void onClick(Context context, Class newActivity) {
        view.navigateToNewActivity(context, newActivity);
    }

    public void deliverNotification() {
        Handler handler = new Handler();
        Thread backgroundThread = new Thread(() -> {
            events = CustomApplication.getRepository().getAllBirthdays();
            handler.post(() ->
                    getCurrentBirthdays(events));

         });
        backgroundThread.start();
    }

    public void getCurrentBirthdays(List<Event> events) {
        Calendar calendarNow = Calendar.getInstance();
        calendarNow.setTimeInMillis(System.currentTimeMillis());
        int monthNow = calendarNow.get(Calendar.MONTH);
        int dayNow = calendarNow.get(Calendar.DAY_OF_MONTH);

        Calendar birthdaysCalendar = Calendar.getInstance();
        for (Event event : events) {
            String name = event.getName();
            String lastName = event.getLastName();
            long dateOfBirth = event.getDateOfBirth();
            birthdaysCalendar.setTimeInMillis(dateOfBirth);
            int monthOfBirth = birthdaysCalendar.get(Calendar.MONTH);
            int birthday = birthdaysCalendar.get(Calendar.DAY_OF_MONTH);
            if ((monthOfBirth == monthNow) && (birthday == dayNow)) {
                view.setAlarm(calendar, name, lastName);
            }
        }
    }
}


