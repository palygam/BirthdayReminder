package com.example.birthdayreminder.ui.add_new_birthday;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.birthdayreminder.R;
import com.example.birthdayreminder.base.BaseActivity;
import com.example.birthdayreminder.ui.show_database.ShowDatabaseActivity;

public class NewBirthdayActivity extends BaseActivity implements NewBirthdayActivityContract{
    private NewBirthdayActivityPresenter presenter;
    private Button sendBirthdayToDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_birthday);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_birthday;
    }

    @Override
    public void setPresenter(@NonNull NewBirthdayActivityContract.Presenter presenter){
        presenter = checkNotNull(presenter);
    }

    @Override
    public void navigateTo(){
        startActivity(new Intent(this, ShowDatabaseActivity.class));
    }
}
