package com.example.birthdayreminder.ui.add_new_birthday;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import com.example.birthdayreminder.CustomApplication;
import com.example.birthdayreminder.base.BasePresenter;
import com.example.birthdayreminder.base.BaseView;
import com.example.birthdayreminder.data.model.Contact;

public class NewBirthdayActivityPresenter implements BasePresenter {
    private BaseView view;

    @Override
    public void loadBirthdaysList() {
    }

   /* @Override
    public void attachView(BaseView baseView) {

    }*/

    @Override
    public void onDestroy() {

    }

    @Override
    public void onClick(Context context, Class newActivity){
        view.showNewScreen(context,newActivity);

    }

    @Override
    public void onMenuClicked(Context context, Class newActivity) {

    }


    public void insertContacts(Contact contact, ProgressBar progressBar) {
        final Handler handler = new Handler();
        progressBar.setVisibility(View.VISIBLE);
        Thread backgroundThread = new Thread(() -> {
            CustomApplication.getRepository().insert(contact);
            handler.post(() -> {
                        progressBar.setVisibility(View.INVISIBLE);
                    });

        });
        backgroundThread.start();}
}
