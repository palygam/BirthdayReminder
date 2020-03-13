package com.example.birthdayreminder.ui.showevents;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.birthdayreminder.R;
import com.example.birthdayreminder.data.model.Event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.ContactViewHolder> {
    private Context context;
    private List<Event> events = new ArrayList<>();
    private OnItemClickListener itemClickListener;

    public void setEvents(@NonNull List<Event> events) {
        this.events = events;
        notifyDataSetChanged();
    }

    public ContactsListAdapter(Context context) {
        this.context = context;
    }


    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.recycler_view, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Event currentEvent = events.get(position);
        if (currentEvent != null) {
            String date = context.getResources().getString(R.string.birthday);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");
            date = date.concat(simpleDateFormat.format(currentEvent.getDateOfBirth()));
            holder.contactNameView.setText(currentEvent.getName());
            holder.contactLastNameView.setText(currentEvent.getLastName());
            holder.contactAgeView.setText(date);
        } else {
            holder.contactNameView.setText(context.getResources().getString(R.string.no_information));
            holder.contactLastNameView.setText(context.getResources().getString(R.string.no_information));
            holder.contactAgeView.setText(context.getResources().getString(R.string.no_information));
        }
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    public class ContactViewHolder extends RecyclerView.ViewHolder {
        final TextView contactNameView;
        final TextView contactLastNameView;
        final TextView contactAgeView;


        public ContactViewHolder(View itemView) {
            super(itemView);
            contactNameView = itemView.findViewById(R.id.name_text_view);
            contactAgeView = itemView.findViewById(R.id.age_text_view);
            contactLastNameView = itemView.findViewById(R.id.last_name_text_view);

            itemView.setOnClickListener(v -> {
                if (itemClickListener != null) {
                    itemClickListener.onClick(itemView, getAdapterPosition());

                }
            });

            itemView.setOnLongClickListener(itemView1 -> {
                if (itemClickListener != null) {
                    itemClickListener.onLongClick(itemView1, getAdapterPosition());
                }
                return false;
            });
        }
    }


    public Event getContactAtPosition(int position) {
        return events.get(position);
    }
}

