package com.example.birthdayreminder.view.ui.show_database;

import java.util.List;

public interface ShowDatabaseView {

    void showProgress();

    void hideProgress();

    void setBirthdays(List<String> birthdays);

    void showMessage(String message);
}

