package com.example.birthdayreminder.data.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.birthdayreminder.data.model.Event;

import java.util.List;

@Dao
public interface EventDao {

    @Query("SELECT * FROM table_events")
    List<Event> getAll();

    @Query("SELECT name FROM table_events")
    List<String> getFirstNames();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Event event);

    @Query("DELETE FROM table_events")
    void deleteAll();

    @Query("UPDATE table_events SET name = :name, last_name = :lastName, birthday = :dateOfBirth, days_left = :daysLeft WHERE id =:id")
    void update(String name, String lastName, long dateOfBirth, long daysLeft, int id);

    @Delete
    void deleteBirthday(Event event);

    @Query("SELECT * FROM table_events ORDER BY days_left ASC ")
    List<Event> getAllSorted();
}
