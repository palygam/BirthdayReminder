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

    public void loadBirthdaysList() {
        final Handler handler = new Handler();
        Thread backgroundThread = new Thread(() -> {
           List <Event> events = CustomApplication.getRepository().getAllBirthdays();
            handler.post(() ->
                    view.setEvents(events));
        });
        backgroundThread.start();
    }

    public void onMenuClicked(Context context, Class nextActivity, Enum screenType) {
        view.navigateToNewActivity(context, nextActivity, screenType);
    }

    public void onClickRecyclerView(Context context, Class nextActivity, Event event, Enum screenType) {
        view.launchNewActivity(context, nextActivity, event, screenType);
    }

    public void onClickPositiveButton(int position, Event event) {
        final Handler handler = new Handler();
        Thread backgroundThread = new Thread(() -> {
            List <Event> events = CustomApplication.getRepository().getAllBirthdays();
            events.remove(position);
            CustomApplication.getRepository().delete(event);
            handler.post(() -> view.setEvents(events));
        });
        backgroundThread.start();
    }
}