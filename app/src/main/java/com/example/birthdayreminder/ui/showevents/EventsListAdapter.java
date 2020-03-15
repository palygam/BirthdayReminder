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


public class EventsListAdapter extends RecyclerView.Adapter<EventsListAdapter.ContactViewHolder> {
    private Context context;
    private List<Event> events = new ArrayList<>();
    private OnItemClickListener itemClickListener;

    public void setEvents(@NonNull List<Event> events) {
        this.events = events;
        notifyDataSetChanged();
    }

    public EventsListAdapter(Context context) {
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
            String birthdayEvent;
            String date = context.getResources().getString(R.string.birthday);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");
            date = date.concat(simpleDateFormat.format(currentEvent.getDateOfBirth()));
            long daysLeft = currentEvent.getDaysLeft();
            if (daysLeft == 0) {
                birthdayEvent = (context.getResources().getString(R.string.today));
            } else {
                birthdayEvent = (context.getResources().getString(R.string.days_countdown));
                birthdayEvent = birthdayEvent.concat(String.valueOf(daysLeft));
            }

            holder.contactNameView.setText(currentEvent.getName());
            holder.contactLastNameView.setText(currentEvent.getLastName());
            holder.contactDateView.setText(date);
            holder.numberOfDaysView.setText(birthdayEvent);
        } else {
            holder.contactNameView.setText(context.getResources().getString(R.string.no_information));
            holder.contactLastNameView.setText(context.getResources().getString(R.string.no_information));
            holder.contactDateView.setText(context.getResources().getString(R.string.no_information));
            holder.numberOfDaysView.setText(context.getResources().getString(R.string.no_information));
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
        final TextView contactDateView;
        final TextView numberOfDaysView;


        public ContactViewHolder(View itemView) {
            super(itemView);
            contactNameView = itemView.findViewById(R.id.name_text_view);
            contactDateView = itemView.findViewById(R.id.birthday_text_view);
            contactLastNameView = itemView.findViewById(R.id.last_name_text_view);
            numberOfDaysView = itemView.findViewById(R.id.number_of_days_text_view);

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

