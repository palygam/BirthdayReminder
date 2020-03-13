package com.example.birthdayreminder.base;

import android.content.Context;

public interface BasePresenter<V extends BaseView> {

    void onMenuClicked(Context context, Class newActivity);

    void onDestroy();

    void onClick(Context context, Class newActivity);
}