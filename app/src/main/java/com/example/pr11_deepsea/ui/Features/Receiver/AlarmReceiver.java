package com.example.pr11_deepsea.ui.Features.Receiver;

import android.Manifest;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import com.example.pr11_deepsea.R;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        //Call requires permission which may be rejected by user:
        // code should explicitly check to see if permission is available
        // (with `checkPermission`) or explicitly handle a potential `SecurityException`

//        if (ContextCompat.checkSelfPermission( this,android.Manifest.permission.ACCESS_COARSE_LOCATION )
//        != PackageManager.PERMISSION_GRANTED )
//        {
//            ActivityCompat.requestPermissions(
//                    this,
//                    new String [] { android.Manifest.permission.ACCESS_COARSE_LOCATION },
//                    LocationService.MY_PERMISSION_ACCESS_COURSE_LOCATION
//            );
//        }


//        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS
//                != PackageManager.PERMISSION_GRANTED)) {
//        }

        // if (NotificationManagerCompat.from(context).areNotificationsEnabled())


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "channelId1")
                .setSmallIcon(R.drawable.app_background1)
                .setContentTitle("Alarm Manager Content Title")
                .setContentText("Set Content Text Field Here")
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManagerCompat.notify(125, builder.build());





    }
}



















