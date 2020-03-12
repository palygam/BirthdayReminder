package com.example.birthdayreminder.ui.edit_birthday;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.birthdayreminder.R;
import com.example.birthdayreminder.base.BaseActivity;

public class EditBirthdayActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_birthday;
    }
}
