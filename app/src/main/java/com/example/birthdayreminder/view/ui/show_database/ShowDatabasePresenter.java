package com.example.birthdayreminder.view.ui.show_database;

import java.util.List;

public class ShowDatabasePresenter {
    private ShowDatabaseView showDatabaseView;
    private FindItemsInteractor findItemsInteractor;

    ShowDatabasePresenter(ShowDatabaseView showDatabaseView, FindItemsInteractor findItemsInteractor) {
        this.showDatabaseView = showDatabaseView;
        this.findItemsInteractor = findItemsInteractor;
    }

    void onResume() {
        if (showDatabaseView != null) {
            showDatabaseView.showProgress();
        }

        findItemsInteractor.findItems(this::onFinished);
    }

    void onItemClicked(String item) {
        if (showDatabaseView != null) {
            showDatabaseView.showMessage(String.format("%s clicked", item));
        }
    }

    void onDestroy() {
        showDatabaseView = null;
    }

    public void onFinished(List<String> birthdays) {
        if (showDatabaseView != null) {
            showDatabaseView.setBirthdays(birthdays);
            showDatabaseView.hideProgress();
        }
    }

    public ShowDatabaseView getShowDatabaseView() {
        return showDatabaseView;
    }
}