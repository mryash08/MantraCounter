package com.mantra.counter.appdata;

import android.app.AlarmManager;
import android.content.Context;

import androidx.core.app.NotificationCompat;

/* loaded from: classes.dex */
public class AlarmManagerProvider {
    private static AlarmManager sAlarmManager;

    public static synchronized AlarmManager getAlarmManager(Context context) {
        AlarmManager alarmManager;
        synchronized (AlarmManagerProvider.class) {
            if (sAlarmManager == null) {
                sAlarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
            }
            alarmManager = sAlarmManager;
        }
        return alarmManager;
    }
}
