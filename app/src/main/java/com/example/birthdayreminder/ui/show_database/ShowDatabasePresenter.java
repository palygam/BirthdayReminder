package com.example.birthdayreminder.ui.show_database;

import com.example.birthdayreminder.data.model.Birthday;

import java.util.List;

public class ShowDatabasePresenter {
    private ShowDatabaseContract showDatabaseContract;
    private ShowDatabaseAdapter adapter;

    ShowDatabasePresenter(ShowDatabaseContract showDatabaseContract, ShowDatabaseAdapter adapter) {
        this.showDatabaseContract = showDatabaseContract;
        this.adapter = adapter;
    }


   /* void onItemClicked(String item) {
        if (mShowDatabaseContract != null) {
            mShowDatabaseContract.showMessage(String.format("%s clicked", item));
        }
    }*/

    void onDestroy() {
        showDatabaseContract = null;
    }

    public void onFinished(List<Birthday> birthdays) {
        if (showDatabaseContract != null) {
            showDatabaseContract.setBirthdays(birthdays);
            showDatabaseContract.hideProgress();
        }
    }

    public ShowDatabaseContract getShowDatabaseContract() {
        return showDatabaseContract;
    }
}