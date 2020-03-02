package com.example.birthdayreminder.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.birthdayreminder.data.model.Birthday;
import com.example.birthdayreminder.ui.show_database.ShowDatabasePresenter;

@Database(entities = {Birthday.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract BirthdayDao birthdayDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "table_birthdays")
                        .build();
            }
        }
        return INSTANCE;
    }
}

