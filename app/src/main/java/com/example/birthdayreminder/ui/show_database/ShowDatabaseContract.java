package com.example.birthdayreminder.ui.show_database;

import java.util.List;

public interface ShowDatabaseContract {

    void showProgress();

    void hideProgress();

    void setBirthdays(List<String> birthdays);

    void showMessage(String message);
}

