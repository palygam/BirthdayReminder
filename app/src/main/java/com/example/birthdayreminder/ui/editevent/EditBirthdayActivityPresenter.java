package com.example.birthdayreminder.ui.editevent;

import android.content.Context;
import android.os.Handler;
import android.widget.ProgressBar;

import com.example.birthdayreminder.CustomApplication;
import com.example.birthdayreminder.base.BasePresenter;
import com.example.birthdayreminder.base.BaseView;
import com.example.birthdayreminder.data.model.Contact;

public class EditBirthdayActivityPresenter implements BasePresenter {
    private BaseView view;

    public EditBirthdayActivityPresenter(BaseView view) {
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

    @Override
    public void loadBirthdaysList() {

    }

    @Override
    public void onClickPositiveButton(int position, Contact contact) {

    }

    @Override
    public void insertContacts(Contact contact, ProgressBar progressBar) {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onClick(Context context, Class newActivity) {

    }

    @Override
    public void onMenuClicked(Context context, Class newActivity) {
        view.showNewScreen(context, newActivity);
    }

    @Override
    public void onClickRecyclerView(Context context, Class nextActivity, Contact contact) {

    }
}
