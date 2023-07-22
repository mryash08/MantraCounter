package com.mantra.counter.appdata;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Switch;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/* loaded from: classes.dex */
public class AlarmActivity {
    public static long AlarmTime = 0;
    public static int SHour = 22;
    public static int SMin = 0;
    public static String Sampm = "PM";
    static DecimalFormat df = new DecimalFormat("00");
    TextView AutoSaveMsg;
    Switch autoSaveStatus;
    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");

    public static void cancelAlarm(Context context) {
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 108, new Intent(context, ReminderAlarmService.class), PendingIntent.FLAG_IMMUTABLE);
        print("CanCel Alarm ");
        AlarmManager alarmManager = AlarmManagerProvider.getAlarmManager(context);
        if (alarmManager != null) {
            alarmManager.cancel(broadcast);
        }
    }

    public static void setAlaram(Context context, Calendar calendar) {
        Intent intent = new Intent(context, ReminderAlarmService.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 108, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        if (calendar.getTimeInMillis() < System.currentTimeMillis()) {
            AlarmTime = calendar.getTimeInMillis() + 86400000;
            calendar.setTimeInMillis(calendar.getTimeInMillis() + 86400000);
        } else {
            AlarmTime = calendar.getTimeInMillis();
        }
        AlarmManager alarmManager = AlarmManagerProvider.getAlarmManager(context);
      print("Set Alarm ");
        alarmManager.cancel(broadcast);
        if (Build.VERSION.SDK_INT >= 23) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, AlarmTime, broadcast);
        } else if (Build.VERSION.SDK_INT >= 19) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, AlarmTime, broadcast);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, AlarmTime, broadcast);
        }
    }

    static int Counter;

    public static void print(String str) {
        Log.e("log" + Counter, str);
        Counter = Counter + 1;
    }
}
