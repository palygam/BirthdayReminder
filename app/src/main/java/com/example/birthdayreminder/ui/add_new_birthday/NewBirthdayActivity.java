package com.example.birthdayreminder.ui.add_new_birthday;

import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.birthdayreminder.R;
import com.example.birthdayreminder.base.BaseActivity;
import com.example.birthdayreminder.base.BasePresenter;
import com.example.birthdayreminder.data.model.Contact;
import com.example.birthdayreminder.ui.show_database.ShowDatabaseActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Calendar;
import java.util.Locale;

public class NewBirthdayActivity extends BaseActivity {
    private BasePresenter presenter;
    private Contact contact;
    private ProgressBar progressBar;
    private DatePickerDialog datePicker;
    private TextInputEditText textInputLastName;
    private TextInputEditText textInputFirstName;
    private TextInputEditText textInputBirthday;
    private TextInputLayout lastNameWrapper;
    private TextInputLayout birthdayWrapper;
    private String lastName;
    private String firstName;
    private long date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        presenter = new NewBirthdayActivityPresenter();
        progressBar = findViewById(R.id.progress_bar);
        lastNameWrapper = findViewById(R.id.last_name_wrapper);
        birthdayWrapper = findViewById(R.id.age_wrapper);
        textInputLastName = findViewById(R.id.text_input_last_name);
        textInputFirstName = findViewById(R.id.text_input_first_name);
        setDatePicker();
        setButton();
    }

    private void setDatePicker() {
        textInputBirthday = findViewById(R.id.text_input_age);
        textInputBirthday.setShowSoftInputOnFocus(false);
        Locale locale = getResources().getConfiguration().locale;
        Locale.setDefault(locale);
        textInputBirthday.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);
            datePicker = new DatePickerDialog(NewBirthdayActivity.this,
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        calendar.set(year1, monthOfYear, dayOfMonth);
                        String dateOfBirth = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1;
                        textInputBirthday.setText(dateOfBirth);
                        date = calendar.getTimeInMillis();
                    }, year, month, day);
            datePicker.show();
        });
    }

    private void setButton() {
        final Button buttonSendData = findViewById(R.id.button_send);
        buttonSendData.setOnClickListener(view -> {
            lastName = textInputLastName.getText().toString();
            firstName = textInputFirstName.getText().toString();
            if (lastName.isEmpty()) {
                lastNameWrapper.setError("Enter your Last Name");
                return;
            }
            if (TextUtils.isEmpty(textInputBirthday.getText())) {
                birthdayWrapper.setError("Enter your Age");
                return;
            }
            lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
            firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
            contact = new Contact(firstName, lastName, date);
            presenter.insertContacts(contact, progressBar);
            presenter.onClick(NewBirthdayActivity.this,ShowDatabaseActivity.class);
        });

    }

    @Override
    public void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            toolbar.setNavigationOnClickListener(v -> onBackPressed());
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_new_birthday;
    }
}
