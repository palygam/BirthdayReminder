package com.example.birthdayreminder.ui.show_database;

import com.example.birthdayreminder.base.BaseView;

import java.util.List;

public interface ShowDatabaseContract extends BaseView {
    void loadBirthdays();
    void setupToolbar();
    void initRecyclerView();
}

