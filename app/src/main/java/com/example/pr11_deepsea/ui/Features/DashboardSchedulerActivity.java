package com.example.pr11_deepsea.ui.Features;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.example.pr11_deepsea.R;
import com.example.pr11_deepsea.databinding.ActivityDashboardSchedulerBinding;

public class DashboardSchedulerActivity extends AppCompatActivity {

    ActivityDashboardSchedulerBinding binding;
    private static final String CHANNEL_ID = "Channel1";
    private static final int NOTIFICATION_ID = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardSchedulerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.googleicon, null);

        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        Bitmap largeIcon = bitmapDrawable.getBitmap();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification notification;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(this)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.app_logo4)
                    .setContentText("New Message")
                    .setSubText("New Message from ..")
                    .setChannelId(CHANNEL_ID)
                    .build();

            notificationManager.createNotificationChannel(new NotificationChannel(CHANNEL_ID, "Channel1",NotificationManager.IMPORTANCE_HIGH));

        }else {
            notification = new Notification.Builder(this)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.app_logo4)
                    .setContentText("New Message")
                    .setSubText("New Message from ..")
                    .build();
        }

        notificationManager.notify(NOTIFICATION_ID, notification);



    }
}