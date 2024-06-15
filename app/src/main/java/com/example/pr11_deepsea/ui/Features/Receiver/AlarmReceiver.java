package com.example.pr11_deepsea.ui.Features.Receiver;

import android.Manifest;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.pr11_deepsea.R;
import com.example.pr11_deepsea.databinding.FragmentSecrecyBinding;
import com.example.pr11_deepsea.ui.Features.EditProfileActivity;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        // Call requires permission which may be rejected by user:
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


        //Intent intent1 = new Intent(context, FragmentSecrecyBinding.class);
        Intent intent1 = new Intent(context, FragmentSecrecyBinding.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0, intent1, 0);
       // context.startActivity(intent1);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "channelId1") // foxandroid
                .setSmallIcon(R.drawable.app_logo4)
                .setContentTitle("Alarm Manager Content Title")
                .setContentText("Set Content Text Field Here")
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent);

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



















