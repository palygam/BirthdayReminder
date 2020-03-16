package com.example.birthdayreminder.ui.showevents;

import android.content.Context;

import com.example.birthdayreminder.base.BaseView;
import com.example.birthdayreminder.data.model.Event;

import java.util.List;

public interface ShowEventsActivityView extends BaseView {

    void launchEditBirthdayActivity(Context context, Class nextActivity, Event event);

    void navigateToNewActivity(Context context, Class nextActivity);

    @Override
    void showProgressBar();

    @Override
    void hideProgressBar();

    void setEvents(List<Event> events);
}
