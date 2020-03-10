package com.example.birthdayreminder.ui.add_new_birthday;

import androidx.annotation.NonNull;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.birthdayreminder.R;
import com.example.birthdayreminder.base.BaseActivity;
import com.example.birthdayreminder.data.AppDatabase;
import com.example.birthdayreminder.ui.show_database.ShowDatabaseActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class NewBirthdayActivity extends BaseActivity implements NewBirthdayActivityContract{
    private NewBirthdayActivityPresenter presenter;
    private Button sendBirthdayToDatabase;

    private TextInputEditText textInputLastName;
    private TextInputEditText textInputFirstName;
    private TextInputEditText textInputBirthday;
    private TextInputLayout lastNameWrapper;
    private TextInputLayout ageWrapper;
    private String lastName;
    private String firstName;
    //private long date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        AppDatabase db = AppDatabase.getInstance(getApplication());
       // presenter = new NewBirthdayActivityPresenter(this, db.birthdayDao());
       // setView();
    }

    @Override
    public int getLayoutId(){
        return R.layout.activity_main;
    }


  /*  @Override
    public void setView() {
        lastNameWrapper = findViewById(R.id.last_name_wrapper);
        ageWrapper = findViewById(R.id.age_wrapper);
        textInputLastName = findViewById(R.id.text_input_last_name);
        textInputFirstName = findViewById(R.id.text_input_first_name);
        textInputBirthday= findViewById(R.id.text_input_birthday);
        textInputBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loadDatepicker();
                textInputBirthday.setText(presenter.datepicker);
            }

        });


        final Button buttonSendData = findViewById(R.id.button_send_to_database);
        buttonSendData.setOnClickListener(view -> {

            lastName = textInputLastName.getText().toString();
            firstName = textInputFirstName.getText().toString().substring(0, 1).toUpperCase();
            if (lastName.isEmpty()) {
                lastNameWrapper.setError("Enter your Last Name");
                return;
            }
            if (TextUtils.isEmpty(textInputBirthday.getText())) {
                ageWrapper.setError("Enter your Age");
                return;
            }

        });

    }*/


}
