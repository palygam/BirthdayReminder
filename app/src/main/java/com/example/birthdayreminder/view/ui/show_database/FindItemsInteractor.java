package com.example.birthdayreminder.view.ui.show_database;

import android.os.Handler;

import com.example.birthdayreminder.view.database.Birthday;

import java.util.Arrays;
import java.util.List;

public class FindItemsInteractor {
    interface OnFinishedListener {
        void onFinished(List<String> birthdays);
    }

    public void findBirthdays(final OnFinishedListener listener) {
        new Handler().postDelayed(() -> listener.onFinished(createArrayList()), 2000);
    }

    private List<Birthday> createArrayList() {
        return Arrays.asList(
                "Item 1",
                "Item 2",
                "Item 3",
                "Item 4",
                "Item 5",
                "Item 6",
                "Item 7",
                "Item 8",
                "Item 9",
                "Item 10"
        );
    }
}

