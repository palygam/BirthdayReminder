package com.example.birthdayreminder.base;

import android.content.Context;
import android.widget.ProgressBar;

import com.example.birthdayreminder.data.model.Contact;

public interface BasePresenter <V extends BaseView>{

    void loadBirthdaysList();
  //  void attachView(V baseView);

/*    void viewIsReady();

    void detachView();*/


    void insertContacts(Contact contact, ProgressBar progressBar);
    void onDestroy();

    void onClick(Context context, Class newActivity);

    void onMenuClicked(Context context, Class newActivity);
}