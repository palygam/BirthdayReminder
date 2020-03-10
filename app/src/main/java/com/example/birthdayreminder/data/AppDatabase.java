package com.example.birthdayreminder.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.birthdayreminder.data.database.BirthdayDao;
import com.example.birthdayreminder.data.model.Birthday;

@Database(entities = {Birthday.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract BirthdayDao birthdayDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "table_birthdays")
                       // .addCallback(roomDatabaseCallback)
                        .build();
            }
        }
        return INSTANCE;
    }

/*    private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
     //       databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                BirthdayDao dao = INSTANCE.birthdayDao();

               Birthday birthday = new Birthday("Petrov","Petr", 12122001);
                dao.insert(birthday);
        }
    };*/
}

