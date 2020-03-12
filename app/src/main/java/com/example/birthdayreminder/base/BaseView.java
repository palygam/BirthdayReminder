package com.example.birthdayreminder.base;

import android.content.Context;
import android.content.Intent;

import com.example.birthdayreminder.data.model.Contact;

public interface BaseView {

    void launchEditBirthdayActivity(Context context, Class nextActivity, Contact contact);

    void showNewScreen(Context context, Class nextActivity);

    void showProgressBar();

    void hideProgressBar();
}
