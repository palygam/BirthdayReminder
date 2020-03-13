package com.example.birthdayreminder.ui.showevents;

import android.content.Context;
import android.os.Handler;
import com.example.birthdayreminder.CustomApplication;
import com.example.birthdayreminder.base.BasePresenter;
import com.example.birthdayreminder.base.BaseView;
import com.example.birthdayreminder.data.model.Event;


import java.util.ArrayList;
import java.util.List;


public class ShowEventsPresenter<T extends BaseView> implements BasePresenter {
    private ShowEventsActivityView view;

    public ShowEventsPresenter (ShowEventsActivityView view){
        this.view = view;
    }

    private List<Event> events = new ArrayList<>();

    public List loadBirthdaysList() {
        final Handler handler = new Handler();
        Thread backgroundThread = new Thread(() -> {
            events = CustomApplication.getRepository().getAllBirthdays();
            handler.post(() ->
                    view.setEvents(events));
        });
        backgroundThread.start();
        return events;
    }

    public void onMenuClicked(Context context, Class nextActivity) {
        view.navigateToNewActivity(context, nextActivity);
    }

    public void onClickRecyclerView(Context context, Class nextActivity, Event event) {
        view.launchEditBirthdayActivity(context, nextActivity, event);
    }


    public void onClickPositiveButton(int position, Event event) {
        events.remove(position);
        final Handler handler = new Handler();
        Thread backgroundThread = new Thread(() -> {
            CustomApplication.getRepository().delete(event);
            handler.post(() -> view.setEvents(events));
        });
        backgroundThread.start();
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void onClick(Context context, Class newActivity) {

    }

}