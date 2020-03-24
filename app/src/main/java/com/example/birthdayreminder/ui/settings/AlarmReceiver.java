package com.example.birthdayreminder.ui.settings;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;;
import android.os.PowerManager;
import androidx.core.app.NotificationCompat;
import com.example.birthdayreminder.R;
import com.example.birthdayreminder.ui.Constants;
import com.example.birthdayreminder.ui.showevents.ShowEventsActivity;

public class AlarmReceiver extends BroadcastReceiver {
    private NotificationManager notificationManager;
    private String name;
    private String lastName;

    @Override
    public void onReceive(Context context, Intent intent) {
        name = intent.getStringExtra(Constants.FIRST_NAME_KEY);
        lastName = intent.getStringExtra(Constants.LAST_NAME_KEY);
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, Constants.WAKEUP_LOCK_TAG);
        wl.acquire();
        notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        sendNotification(context);
        wl.release();
    }

    private void sendNotification(Context context) {
        Intent contentIntent = new Intent(context, ShowEventsActivity.class);
        PendingIntent contentPendingIntent = PendingIntent.getActivity
                (context, Constants.NOTIFICATION_ID, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, Constants.PRIMARY_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_android)
                .setContentTitle("BirthdayReminder")
                .setContentText(context.getResources().getString(R.string.birthday_notification) + name + " " + lastName + context.getResources().getString(R.string.birthday_notification_part_2))
                .setContentIntent(contentPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL);
        notificationManager.notify(Constants.NOTIFICATION_ID, builder.build());
    }
}

