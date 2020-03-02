package com.example.birthdayreminder.base;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        setView();
    }


    protected abstract int getLayoutId();
    public void setView(){}

    public void navigateTo(){
        startActivity(new Intent());
    }
}
