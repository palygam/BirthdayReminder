package com.example.birthdayreminder.base;

public interface BasePresenter<V extends BaseView> {

    void onDestroy();
}