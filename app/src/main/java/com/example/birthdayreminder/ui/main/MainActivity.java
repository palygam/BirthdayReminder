package com.example.birthdayreminder.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

import com.example.birthdayreminder.R;
import com.example.birthdayreminder.ui.add_new_birthday.NewBirthdayActivity;

public class MainActivity extends AppCompatActivity implements MainActivityContract{
    //главная активити, а которой находятся кнопки 1) "Добавить день рождения"
    //2)"показать ближайшие дни рождения"
    //The only thing that the view will do is calling a presenter method every time there is a user action (a button click for example).
    //

    private MainActivityPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButtonMainActivity();
    }

    private void addButtonMainActivity() {
        final Handler handler = new Handler();
        presenter = new MainActivityPresenter(handler,this);
        final Button addNewBirthday = findViewById(R.id.button_new_input);
        addNewBirthday.setOnClickListener(view -> {
          navigateTo();
        });
        final Button showBirthdaysList = findViewById(R.id.button_show_birthdays_list);
        showBirthdaysList.setOnClickListener(v -> {
            presenter.showBirthdaysList();

        });
    }

    @Override
    public void navigateTo() {
        startActivity(new Intent(this, NewBirthdayActivity.class));
        finish();
    }

    @Override
    public void loadBirthdays() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
