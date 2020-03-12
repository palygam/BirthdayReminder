package com.example.birthdayreminder.data.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.birthdayreminder.data.model.Contact;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM table_contacts")
    List<Contact> getAll();

    @Query("SELECT name FROM table_contacts")
    List<String> getFirstNames();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Contact contact);

    @Query("DELETE FROM table_contacts")
    void deleteAll();

    @Query("UPDATE table_contacts SET name = :name, last_name = :lastName, birthday = :dateOfBirth WHERE id =:id")
    void update (String name, String lastName, long dateOfBirth, int id);

    @Delete
    void deleteBirthday(Contact contact);

    @Query("SELECT * FROM table_contacts ORDER BY birthday DESC Limit 1")
    List <Contact> getAllSorted();

}
