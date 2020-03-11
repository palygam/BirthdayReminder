package com.example.birthdayreminder.ui.add_new_birthday;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.birthdayreminder.R;
import com.example.birthdayreminder.base.BaseActivity;

public class NewBirthdayActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_show_database;
    }
}
