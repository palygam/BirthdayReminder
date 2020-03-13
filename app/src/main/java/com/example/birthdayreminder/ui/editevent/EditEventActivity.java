package com.example.birthdayreminder.ui.editevent;

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
import com.example.birthdayreminder.ui.showevents.ShowEventsActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EditEventActivity extends BaseActivity implements EditEventActivityView {
    private ProgressBar progressBar;
    private EditEventActivityPresenter presenter;
    private TextInputEditText textEditLastName;
    private TextInputEditText textEditFirstName;
    private TextInputEditText textEditBirthday;
    private TextInputLayout lastNameWrapper;
    private TextInputLayout birthdayWrapper;
    private String lastName;
    private String firstName;
    private long date;
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initComponents();
    }

    public void initComponents(){
        presenter = new EditEventActivityPresenter(this);
        setupToolbar();
        progressBar = findViewById(R.id.progress_bar);
        textEditLastName = findViewById(R.id.text_input_last_name);
        textEditFirstName = findViewById(R.id.text_input_first_name);
        textEditBirthday = findViewById(R.id.text_input_birthday);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            firstName = extras.getString(Constants.FIRST_NAME_KEY);
            lastName = extras.getString(Constants.LAST_NAME_KEY);
            long birthday = extras.getLong(Constants.BIRTHDAY_KEY);
            id = extras.getInt(Constants.ID_KEY);
            String pattern = "dd/MM/yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(new Date(birthday));
            lastNameWrapper = findViewById(R.id.last_name_wrapper);
            birthdayWrapper = findViewById(R.id.age_wrapper);
            textEditFirstName.setText(firstName);
            textEditLastName.setText(lastName);
            textEditBirthday.setText(date);
        }
        setDatePicker();
        setEditButton();
    }

    private void setDatePicker() {
        textEditBirthday.setOnClickListener(v -> presenter.onDateClicked());
    }

    private void setEditButton() {
        final Button buttonEditData = findViewById(R.id.button_change);
        buttonEditData.setOnClickListener(view -> {
            lastName = textEditLastName.getText().toString();
            firstName = textEditFirstName.getText().toString();
            if (lastName.isEmpty()) {
                lastNameWrapper.setError("Enter your Last Name");
                return;
            }
            if (TextUtils.isEmpty(textEditBirthday.getText())) {
                birthdayWrapper.setError("Enter your Age");
                return;
            }
            lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
            firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
            presenter.updateContact(firstName, lastName, date, id);
            presenter.onMenuClicked(EditEventActivity.this, ShowEventsActivity.class);
        });
    }


    @Override
    public void displayDatePickerDialog(int year, int month, int day){
        DatePickerDialog datePicker = new DatePickerDialog(EditEventActivity.this, (view, year1, monthOfYear, dayOfMonth) -> {
            presenter.onDateSet(year1, monthOfYear, dayOfMonth);
            date = presenter.onCalendarSet(year1, monthOfYear, dayOfMonth).getTimeInMillis();
        }, year, month, day);
        datePicker.show();
    }

    @Override
    public void setDateText (String date){
        textEditBirthday.setText(date);
    }

    @Override
    public void navigateToNewActivity(Context context, Class nextActivity) {
        Intent intent = new Intent(context, nextActivity);
        startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_birthday;
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
