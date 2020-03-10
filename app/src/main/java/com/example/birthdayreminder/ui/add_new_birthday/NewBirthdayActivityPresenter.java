package com.example.birthdayreminder.ui.add_new_birthday;

import android.app.DatePickerDialog;
import android.widget.DatePicker;

import java.util.Calendar;

public class NewBirthdayActivityPresenter {
    DatePickerDialog datepicker;
    private long date;

/*    public DatePickerDialog loadDatepicker() {
        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        datepicker = new DatePickerDialog(NewBirthdayActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        //     view.getSpinnersShown();
                        String dateOfBirth = String.valueOf(dayOfMonth)  + "/"+ String.valueOf(monthOfYear + 1) + "/" + String.valueOf(year);
                        textInputBirthday.setText(dateOfBirth);
                        //  Calendar c = Calendar.getInstance();
                        calendar.set(year ,monthOfYear, dayOfMonth);
                        //   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                    }
                }, year, month, day);
        datepicker.show();
        date = calendar.getTimeInMillis();


    }
   */

}
