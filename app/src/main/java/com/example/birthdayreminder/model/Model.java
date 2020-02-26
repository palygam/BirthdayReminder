package com.example.birthdayreminder.model;

import com.example.birthdayreminder.MainContract;

public class Model implements MainContract.Model {
    private Model model;

    public Model(Model model) {
        this.model = model;
    }


    @Override
    public String loadContacts() {
        return null;
    }

    @Override
    public String insertContacts() {
        return null;
    }

    @Override
    public void createContact(String name, String lastName, int birthday) {
        model.saveContact(new Contact(name, lastName, birthday));
    }

    @Override
    public Contact getContact() {
        return model.getContact();
    }

}
