package com.example.birthdayreminder.ui.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.birthdayreminder.R;
import com.example.birthdayreminder.base.BaseActivity;

public class SetSettingsActivity extends BaseActivity implements SetSettingsActivityView{
    private SetSettingsActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        Button setAlarm =  findViewById(R.id.button_alarm);
        setAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_set_settings;
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }
}
