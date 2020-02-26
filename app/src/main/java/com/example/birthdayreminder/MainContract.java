package com.example.birthdayreminder;

import com.example.birthdayreminder.model.Contact;

public interface MainContract {
    interface View {
        String getFirstName();
        String getLastName();
        int getBirthday();
        void showInputError();
        void setFirstName(String firstName);
        void setLastName(String lastName);
        void setBirthday();

    }

    interface Presenter {
        void onButtonWasClicked();
        void onDestroy();
    }

    interface Model {
        String loadContacts();
        String insertContacts();
        void createContact(String name, String lastName, int birthday);

        Contact getContact();
    }

}
