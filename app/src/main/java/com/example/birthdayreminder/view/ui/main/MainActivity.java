package com.example.birthdayreminder.view.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.birthdayreminder.R;

public class MainActivity extends AppCompatActivity {
    //главная активити, а которой находятся кнопки 1) "Добавить день рождения"
    //2)"показать ближайшие дни рождения"
    //The only thing that the view will do is calling a presenter method every time there is a user action (a button click for example).

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButtonMainActivity();
        findViewById(R.id.button).setOnClickListener(v -> {
            validateCredentials();
        });

        presenter = new LoginPresenter(this, new LoginInteractor());
    }

    private void addButtonMainActivity() {
        final Button addNewBirthday = findViewById(R.id.button_add_new_birthday);
        addNewBirthday.setOnClickListener(view -> {
           //новый фрагмент с добавлением даты
        });
    }

    @Override
    public void goToTheFragment() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void validateCredentials() {
        presenter.validateCredentials(username.getText().toString(), password.getText().toString());
    }
}
