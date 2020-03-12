package com.example.birthdayreminder.ui.add_new_birthday;

import android.content.Context;
import android.content.Intent;

import com.example.birthdayreminder.base.BaseView;

public class NewBirthdayActivityView implements BaseView {

    @Override
    public void showNewScreen(Context context, Class nextActivity) {
        Intent intent = new Intent(context, nextActivity);
        context.startActivity(intent);
    }
}
