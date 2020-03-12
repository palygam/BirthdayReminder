package com.example.birthdayreminder.ui.newevent;

import android.content.Context;
import android.os.Handler;
import android.widget.ProgressBar;

import com.example.birthdayreminder.CustomApplication;
import com.example.birthdayreminder.base.BasePresenter;
import com.example.birthdayreminder.base.BaseView;
import com.example.birthdayreminder.data.model.Contact;

public class NewBirthdayActivityPresenter implements BasePresenter {
    private BaseView view;

    public NewBirthdayActivityPresenter(BaseView view) {
        this.view = view;
    }

    @Override
    public void onClick(Context context, Class newActivity) {
        view.showNewScreen(context, newActivity);

    }

    public void insertContacts(Contact contact, ProgressBar progressBar) {
        final Handler handler = new Handler();
        view.showProgressBar();
        Thread backgroundThread = new Thread(() -> {
            CustomApplication.getRepository().insert(contact);
            handler.post(() -> view.hideProgressBar());

        });
        backgroundThread.start();
    }

    @Override
    public void onMenuClicked(Context context, Class newActivity) {
        view.showNewScreen(context, newActivity);
    }

    @Override
    public void onClickRecyclerView(Context context, Class nextActivity, Contact contact) {

    }

    @Override
    public void updateContact(String firstName, String lastName, long date, int id) {

    }

    @Override
    public void loadBirthdaysList() {
    }

    @Override
    public void onClickPositiveButton(int position, Contact contact) {
    }

    @Override
    public void onDestroy() {
    }
}
