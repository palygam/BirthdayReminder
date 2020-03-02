package com.example.birthdayreminder.ui.show_database;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.birthdayreminder.R;

import java.util.List;

public class ShowDatabaseAdapter extends RecyclerView.Adapter<ShowDatabaseAdapter.ShowDatabaseViewHolder>{
    public ShowDatabaseAdapter(List<String> items, Listener listener) {
        this.birthdays = birthdays;
        this.listener = listener;
    }

    interface Listener {
        void onItemClicked(String item);
    }

    private List<String> birthdays;
    private Listener listener;

    @NonNull
    @Override
    public ShowDatabaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view, parent, false);
        return new ShowDatabaseViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowDatabaseViewHolder holder, int position) {
        final String item = birthdays.get(position);
        holder.textView.setText(item);
        holder.textView.setOnClickListener(v -> listener.onItemClicked(item));
    }

    @Override
    public int getItemCount() {
        return birthdays.size();
    }

    static class ShowDatabaseViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        ShowDatabaseViewHolder(TextView textView) {
            super(textView);
            this.textView = textView;
        }
    }
}