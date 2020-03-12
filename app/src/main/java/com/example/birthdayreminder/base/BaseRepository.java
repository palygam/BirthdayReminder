package com.example.birthdayreminder.base;


import com.example.birthdayreminder.data.database.ContactDao;
import com.example.birthdayreminder.data.model.Contact;

import java.util.List;

public class BaseRepository {
    private ContactDao contactDao;

    public BaseRepository(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    public List<Contact> getAllBirthdays() {
        return contactDao.getAll();
    }

    public void insert (final Contact contact) {
        contactDao.insert(contact);
    }
}

