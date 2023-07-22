package com.mantra.counter;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import androidx.core.app.NotificationCompat;

/* loaded from: classes.dex */
public class Notifier {
    static StatusBarNotification[] notif;
    static int Counter;

    public static void createNotificationSimple(Context context, Intent intent, int i, boolean z, String str, String str2, String str3, int i2) {
        Notification build = new NotificationCompat.Builder(context, str).setSmallIcon(i).setOngoing(z).setContentIntent(PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)).setContentText(str2).setPriority(1).setSubText(str3).setAutoCancel(true).build();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(str, str2, NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription(str3);
            notificationManager.createNotificationChannel(notificationChannel);
            notificationManager.notify(i2, build);
            return;
        }
        print("Android is Lower");
        notificationManager.notify(i2, build);
    }


    public static void print(String str) {
        Log.e("log" + Counter, str);
        Counter = Counter + 1;
    }
}
