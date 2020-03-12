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

    public void delete (final Contact contact) {contactDao.deleteBirthday(contact);}

    public void update (String name, String lastName, long dateOfBirth, int id){
        contactDao.update(name,lastName,dateOfBirth,id);
    }
}

