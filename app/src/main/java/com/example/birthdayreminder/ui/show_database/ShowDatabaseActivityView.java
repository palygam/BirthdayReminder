package com.example.birthdayreminder.ui.show_database;

import android.content.Context;
import android.content.Intent;

import com.example.birthdayreminder.base.BaseView;

public class ShowDatabaseActivityView implements BaseView {

    @Override
    public void showNewScreen(Context context, Class nextActivity) {
        Intent intent = new Intent(context, nextActivity );
        context.startActivity(intent);
    }


}
