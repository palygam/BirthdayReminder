package com.example.birthdayreminder.ui.showevents;

import android.content.Context;

import com.example.birthdayreminder.base.BaseView;
import com.example.birthdayreminder.data.model.Event;

import java.util.List;

public interface ShowEventsActivityView extends BaseView {

    void launchNewActivity(Context context, Class nextActivity, Event event, Enum screenType);

    void navigateToNewActivity(Context context, Class nextActivity, Enum screenType);

    @Override
    void showProgressBar();

    @Override
    void hideProgressBar();

    void setEvents(List<Event> events);
}
