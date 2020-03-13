package com.example.birthdayreminder.ui.showevents;

import android.view.View;

public interface OnItemClickListener {
    void onClick(View view , int position);
    void onLongClick(View view, int position);
}
