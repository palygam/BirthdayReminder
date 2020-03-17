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
import com.example.birthdayreminder.ui.Constants;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NewEventActivity extends BaseActivity implements NewEventActivityView {
    private NewEventActivityPresenter presenter;
    private ProgressBar progressBar;
    private TextInputEditText textInputLastName;
    private TextInputEditText textInputFirstName;
    private TextInputEditText textInputBirthday;
    private TextInputLayout lastNameWrapper;
    private TextInputLayout birthdayWrapper;
    private String lastName;
    private String firstName;
    private long birthday;
    private int id;
    private long date;
    private long daysLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initComponents();
        Intent intent = getIntent();
        if (intent.getExtras()!=null){
            unpackIntent(intent);
            setDatePicker();
            setEditButton();
        }
        else{
            setDatePicker();
            setSendButton();
        }
    }

    public void initComponents() {
        presenter = new NewEventActivityPresenter(this);
        progressBar = findViewById(R.id.progress_bar);
        lastNameWrapper = findViewById(R.id.last_name_wrapper);
        birthdayWrapper = findViewById(R.id.time_wrapper);
        textInputLastName = findViewById(R.id.text_input_last_name);
        textInputFirstName = findViewById(R.id.text_input_first_name);
        textInputBirthday = findViewById(R.id.text_input_birthday);
        setupToolbar();
    }

    private void unpackIntent(Intent intent){
        Bundle extras = intent.getExtras();
        if (extras != null) {
            firstName = extras.getString(Constants.FIRST_NAME_KEY);
            lastName = extras.getString(Constants.LAST_NAME_KEY);
            birthday = extras.getLong(Constants.BIRTHDAY_KEY);
            id = extras.getInt(Constants.ID_KEY);
            String pattern = "dd/MM/yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String dateOfBirth = simpleDateFormat.format(new Date(birthday));
            lastNameWrapper = findViewById(R.id.last_name_wrapper);
            birthdayWrapper = findViewById(R.id.time_wrapper);
            textInputFirstName.setText(firstName);
            textInputLastName.setText(lastName);
            textInputBirthday.setText(dateOfBirth);
            setEditButton();
        }
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
        textInputBirthday.setOnClickListener(v -> presenter.onDateClicked());
    }

    private void setSendButton() {
        final Button buttonSendData = findViewById(R.id.button_send);
        buttonSendData.setText(R.string.button_send);
        buttonSendData.setOnClickListener(view -> {
            lastName = textInputLastName.getText().toString();
            firstName = textInputFirstName.getText().toString();
            if (lastName.isEmpty()) {
                lastNameWrapper.setError("Введите фамилию");
                return;
            }
            if (TextUtils.isEmpty(textInputBirthday.getText())) {
                birthdayWrapper.setError("Введите дату события");
                return;
            }
            lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
            firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
            daysLeft = presenter.getDaysLeft(date);
            Event event = new Event(firstName, lastName, date, daysLeft);
            presenter.insertContacts(event);
           // presenter.onClick(NewEventActivity.this, ShowEventsActivity.class);
        });
    }

    private void setEditButton() {
        final Button buttonEditData = findViewById(R.id.button_send);
        buttonEditData.setText(R.string.button_edit);
        buttonEditData.setOnClickListener(view -> {
            lastName = textInputLastName.getText().toString();
            firstName = textInputFirstName.getText().toString();
            if (lastName.isEmpty()) {
                lastNameWrapper.setError("Введите фамилию");
                return;
            }
            if (TextUtils.isEmpty(textInputBirthday.getText())) {
                birthdayWrapper.setError("Введите дату события");
                return;
            }
            lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
            firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
            long daysLeft = presenter.getDaysLeft(birthday);
            presenter.updateContact(firstName, lastName, date, daysLeft, id);
           // presenter.onClick(NewEventActivity.this, ShowEventsActivity.class);
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

    @Override
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
