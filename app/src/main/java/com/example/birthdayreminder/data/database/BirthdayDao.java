package com.example.birthdayreminder.data.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.birthdayreminder.data.model.Birthday;

import java.util.List;

@Dao
public interface BirthdayDao {
    // запрос, отсортировать по ближайшей дате

    @Query("SELECT * FROM table_birthdays")
    List<Birthday> getAll();

    @Delete
    void deleteBirthday(Birthday Birthday);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Birthday birthday);


}
