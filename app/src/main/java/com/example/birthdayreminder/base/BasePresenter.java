package com.example.birthdayreminder.base;

import android.content.Context;

public class BasePresenter{

    private static Context context;

    public BasePresenter (Context context){
        this.context = context;
    }

    public static Context getContext() {
        return context;
    }
}