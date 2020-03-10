package com.example.birthdayreminder.base;

import com.example.birthdayreminder.CustomApplication;
import com.example.birthdayreminder.data.AppDatabase;

public abstract class BaseRepository {
    CustomApplication application;

    AppDatabase getAppDatabase(){
        return application.database;
    }
   // private static Context context;

   /* public BaseRepository(Context context){
        this.context = context;
    }

    public static Context getContext() {
        return context;}*/

}
