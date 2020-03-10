package com.example.birthdayreminder.ui.show_database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.birthdayreminder.R;
import com.example.birthdayreminder.data.model.Birthday;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ShowDatabaseAdapter extends RecyclerView.Adapter<ShowDatabaseAdapter.BirthdaysViewHolder> {
    private Context context;
    private List<Birthday> birthdays = new ArrayList<>();

    public void setBirthdays(@NonNull List<Birthday> birthdays) {
        this.birthdays = birthdays;
        notifyDataSetChanged();
    }

    public ShowDatabaseAdapter(Context context) {
        this.context = context;
    }

    @Override
    public BirthdaysViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.recycler_view, parent, false);
        return new BirthdaysViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BirthdaysViewHolder holder, int position) {
        Birthday currentBirthday = birthdays.get(position);
        if (currentBirthday!= null) {
            String date = null;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");
            date = simpleDateFormat.format(currentBirthday.getDateOfBirth());
            holder.LastNameView.setText(currentBirthday.getLastName());
            holder.FirstNameView.setText(currentBirthday.getFirstName());
            holder.birthday.setText(date);
        } else {
            holder.LastNameView.setText(context.getResources().getString(R.string.no_information));
            holder.FirstNameView.setText(context.getResources().getString(R.string.no_information));
            holder.birthday.setText(context.getResources().getString(R.string.no_information));
        }
    }

    @Override
    public int getItemCount() {
        return birthdays.size();
    }

    public class BirthdaysViewHolder extends RecyclerView.ViewHolder {
        final TextView LastNameView;
        final TextView FirstNameView;
        final TextView birthday;

        public BirthdaysViewHolder(View itemView) {
            super(itemView);
            LastNameView = itemView.findViewById(R.id.last_name_text_view);
            FirstNameView = itemView.findViewById(R.id.first_name_text_view);
            birthday = itemView.findViewById(R.id.date_text_view);
        }
    }
}

