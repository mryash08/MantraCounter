package com.mantra.counter.appdata;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.mantra.counter.Country;
import com.mantra.counter.PSharedPreference;
import com.mantra.counter.R;


/* loaded from: classes.dex */
public class LockScreenReeiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.SCREEN_ON")) {
           print("Recieved Screen on ");
            if (System.currentTimeMillis() - PSharedPreference.getLong(context, "MANTRAC", System.currentTimeMillis()) > 172800000) {
                PSharedPreference.setLong(context, "MANTRAC", System.currentTimeMillis());
                NotificationCompat.Builder contentText = new NotificationCompat.Builder(context).setSmallIcon(R.mipmap.ic_launcher).setContentTitle(context.getResources().getString(R.string.app_name)).setContentText("Tap here to Start Counter");
                contentText.setContentIntent(PendingIntent.getActivity(context, 0, new Intent(context, Country.class), PendingIntent.FLAG_UPDATE_CURRENT));
                contentText.setAutoCancel(true);
                ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE)).notify(555, contentText.build());
            }
        }
    }

    static int Counter;

    public static void print(String str) {
        Log.e("log" + Counter, str);
        Counter = Counter + 1;
    }
}
