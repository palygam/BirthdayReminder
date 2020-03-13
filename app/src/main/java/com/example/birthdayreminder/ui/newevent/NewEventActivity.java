package com.example.birthdayreminder.ui.newevent;

import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.birthdayreminder.R;
import com.example.birthdayreminder.base.BaseActivity;
import com.example.birthdayreminder.data.model.Event;
import com.example.birthdayreminder.ui.showevents.ShowEventsActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class NewEventActivity extends BaseActivity implements NewEventActivityView {
    private NewEventActivityPresenter presenter;
    private Event event;
    private ProgressBar progressBar;
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
        initComponents();
    }

    public void initComponents() {
        presenter = new NewEventActivityPresenter(this);
        progressBar = findViewById(R.id.progress_bar);
        lastNameWrapper = findViewById(R.id.last_name_wrapper);
        birthdayWrapper = findViewById(R.id.age_wrapper);
        textInputLastName = findViewById(R.id.text_input_last_name);
        textInputFirstName = findViewById(R.id.text_input_first_name);
        setupToolbar();
        setDatePicker();
        setButton();
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

    private void setDatePicker() {
        textInputBirthday = findViewById(R.id.text_input_birthday);
        textInputBirthday.setOnClickListener(v -> presenter.onDateClicked());
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
            event = new Event(firstName, lastName, date);
            presenter.insertContacts(event, progressBar);
            presenter.onClick(NewEventActivity.this, ShowEventsActivity.class);
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_new_event;
    }

    @Override
    public void displayDatePickerDialog(int year, int month, int day) {
        DatePickerDialog datePicker = new DatePickerDialog(NewEventActivity.this, (view, year1, monthOfYear, dayOfMonth) -> {
            presenter.onDateSet(year1, monthOfYear, dayOfMonth);
            date = presenter.onCalendarSet(year1, monthOfYear, dayOfMonth).getTimeInMillis();
        }, year, month, day);
        datePicker.show();
    }

    @Override
    public void setDateText(String date) {
        textInputBirthday.setText(date);
    }

    public void navigateToNewActivity(Context context, Class nextActivity) {
        Intent intent = new Intent(context, nextActivity);
        context.startActivity(intent);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}
