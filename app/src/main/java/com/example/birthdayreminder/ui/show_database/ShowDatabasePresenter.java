package com.example.birthdayreminder.ui.show_database;

import android.content.Context;
import android.os.Handler;
import android.widget.ProgressBar;

import com.example.birthdayreminder.CustomApplication;
import com.example.birthdayreminder.base.BasePresenter;
import com.example.birthdayreminder.base.BaseView;
import com.example.birthdayreminder.data.model.Contact;


import java.util.ArrayList;
import java.util.List;


public class ShowDatabasePresenter <T extends BaseView> implements BasePresenter {
    private ContactsListAdapter adapter;
    private BaseView view;
    private List<Contact> contacts = new ArrayList<>();

    public ShowDatabasePresenter(BaseView view, ContactsListAdapter adapter) {
        this.view = view;
        this.adapter = adapter;
    }


  /*  @Override
    public void attachView(BaseView baseView) {
        this.view = baseView;
    }*/

    public void loadBirthdaysList() {
        final Handler handler = new Handler();
        Thread backgroundThread = new Thread(() -> {
            contacts = CustomApplication.getRepository().getAllBirthdays();
            handler.post(() -> adapter.setContacts(contacts));
        });
        backgroundThread.start();
    }

    public void onMenuClicked(Context context, Class nextActivity) {
        view.showNewScreen(context, nextActivity);
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

}