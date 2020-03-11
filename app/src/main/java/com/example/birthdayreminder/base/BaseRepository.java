package com.example.birthdayreminder.base;

import com.example.birthdayreminder.CustomApplication;
import com.example.birthdayreminder.data.AppDatabase;
import com.example.birthdayreminder.data.database.BirthdayDao;
import com.example.birthdayreminder.data.model.Birthday;

import java.util.List;

public class BaseRepository {
    private BirthdayDao birthdayDao;

    public BaseRepository(BirthdayDao birthdayDao) {
        this.birthdayDao = birthdayDao;
    }

    public List<Birthday> getAllBirthdays() {
        return birthdayDao.getAll();
    }

    public void insert(final Birthday birthday) {
        birthdayDao.insert(birthday);
    }
}

