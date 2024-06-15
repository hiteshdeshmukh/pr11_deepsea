package com.example.pr11_deepsea.ui.Features;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pr11_deepsea.databinding.ActivityDashboardSchedulerBinding;
import com.example.pr11_deepsea.ui.Features.Receiver.AlarmReceiver;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;

public class DashboardSchedulerActivity extends AppCompatActivity {

    ActivityDashboardSchedulerBinding binding;

    private Calendar calendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    private MaterialTimePicker picker;
    private static final String CHANNEL_ID = "Channel1";
    private static final int NOTIFICATION_ID = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardSchedulerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//
//
//        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.googleicon, null);
//
//        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
//        Bitmap largeIcon = bitmapDrawable.getBitmap();
//
//        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//
//        Notification notification;
//
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            notification = new Notification.Builder(this)
//                    .setLargeIcon(largeIcon)
//                    .setSmallIcon(R.drawable.app_logo4)
//                    .setContentText("New Message")
//                    .setSubText("New Message from ..")
//                    .setChannelId(CHANNEL_ID)
//                    .build();
//
//            notificationManager.createNotificationChannel(new NotificationChannel(CHANNEL_ID, "Channel1",NotificationManager.IMPORTANCE_HIGH));
//
//        }else {
//            notification = new Notification.Builder(this)
//                    .setLargeIcon(largeIcon)
//                    .setSmallIcon(R.drawable.app_logo4)
//                    .setContentText("New Message")
//                    .setSubText("New Message from ..")
//                    .build();
//        }
//
//        notificationManager.notify(NOTIFICATION_ID, notification);

        CreateNotificationChannel();
        binding.schedulerSelectTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowTimePicker();
            }

        });

        binding.schedulerSetAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetAlarm();
            }
        });

        binding.schedulerCancelAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CancelAlarm();
            }
        });
    }



    private void CreateNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "RemainderChannel1";
            String description = "Channel for alarm Manager";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel1 = new NotificationChannel("channelId1", name, importance);  //foxandroid
            channel1.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel1);
        }
    }


    private void ShowTimePicker() {
        picker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Select Alarm Time")
                .build();

        picker.show(getSupportFragmentManager(), "channelId1");

        picker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (picker.getHour()>12){
                    binding.scedulerSelectedTimeText.setText(
                            String.format("%02d", (picker.getHour()-12)) + " : " + String.format("%02d", picker.getMinute())+" PM"
                    );
                }else {
                    binding.scedulerSelectedTimeText.setText(
                            picker.getHour() + " : " + picker.getMinute()+" AM");
                }

                calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, picker.getHour());
                calendar.set(Calendar.MINUTE, picker.getMinute());
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);


            }
        });


    }



    private void SetAlarm() {

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, AlarmReceiver.class);

        pendingIntent = PendingIntent.getBroadcast(this, 0,intent, 0);

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY,pendingIntent);

        Toast.makeText(this, "Alarm set Successfully", Toast.LENGTH_SHORT).show();

    }





    private void CancelAlarm() {

        Intent intent = new Intent(this, AlarmReceiver.class);

        pendingIntent = PendingIntent.getBroadcast(this, 0,intent, PendingIntent.FLAG_IMMUTABLE);

        if (alarmManager == null){
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }

        alarmManager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm Cancelled", Toast.LENGTH_SHORT).show();
    }

}