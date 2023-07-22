package com.mantra.counter.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.os.Build;

/* loaded from: classes.dex */
public class MyFirebaseInstanceService {
    String link = "No";
    PendingIntent pendingIntent;

//    @Override // com.google.firebase.messaging.FirebaseMessagingService
//    public void onMessageReceived(RemoteMessage remoteMessage) {
//        super.onMessageReceived(remoteMessage);
//        String body = remoteMessage.getNotification().getBody();
//        String title = remoteMessage.getNotification().getTitle();
//        String str = remoteMessage.getData().get("Link");
//        this.link = str;
//        showNotification(title, body, str);
//    }

    public void showNotification(String str, String str2, String str3) {
        if (str3.length() > 5) {
//            this.pendingIntent = PendingIntent.getActivity(this, 0, new Intent("android.intent.action.VIEW", Uri.parse(str3)), BasicMeasure.EXACTLY);
        }
//        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel("example.firebase.services.test", "Notification", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription(str2);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(-16776961);
            notificationChannel.enableLights(true);
//            notificationManager.createNotificationChannel(notificationChannel);
        }
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "example.firebase.services.test");
//        builder.setAutoCancel(true).setDefaults(-1).setWhen(System.currentTimeMillis()).setSmallIcon(R.mipmap.ic_launcher).setTicker(getResources().getString(R.string.app_name)).setColor(-16711936).setContentTitle(str).setContentText(str2).setContentInfo("Info");
        if (str3.length() > 5) {
//            builder.setContentIntent(this.pendingIntent);
        }
//        notificationManager.notify(new Random().nextInt(), builder.build());
    }

//    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onNewToken(String str) {
//        super.onNewToken(str);
    }
}
