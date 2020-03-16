package com.example.birthdayreminder.base;


import com.example.birthdayreminder.data.database.EventDao;
import com.example.birthdayreminder.data.model.Event;

import java.util.List;

public class BaseRepository {
    private EventDao eventDao;
    public BaseRepository(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    public List<Event> getAllBirthdays() {
        return eventDao.getAllSorted();
    }

    public void insert (final Event event) {
        eventDao.insert(event);
    }

    public void delete (final Event event) {
        eventDao.deleteBirthday(event);}

    public void update (String name, String lastName, long dateOfBirth, long daysLeft, int id){
        eventDao.update(name,lastName,dateOfBirth, daysLeft, id);
    }
}

