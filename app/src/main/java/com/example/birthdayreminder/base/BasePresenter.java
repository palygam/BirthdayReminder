package com.example.birthdayreminder.base;

import android.content.Context;

public interface BasePresenter<V extends BaseView> {

    void onDestroy();
}