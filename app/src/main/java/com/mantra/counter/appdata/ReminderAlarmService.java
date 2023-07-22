package com.mantra.counter.appdata;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.mantra.counter.AlarmInvoke;
import com.mantra.counter.Country;
import com.mantra.counter.Notifier;
import com.mantra.counter.PSharedPreference;
import com.mantra.counter.R;

import java.util.Calendar;


/* loaded from: classes.dex */
public class ReminderAlarmService extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        RepeatAlarm(context);
        if (!PSharedPreference.getBoolean(context, "ISFULLSCREENALM", false)) {
            Intent intent2 = new Intent(context, Country.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Notifier.createNotificationSimple(context, intent2, R.drawable.matrna_reminder, false, "channel", context.getResources().getString(R.string.app_name), context.getResources().getString(R.string.reminder), 108);
            return;
        }
        Intent intent3 = new Intent(context, Country.class);
        intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Notifier.createNotificationSimple(context, intent3, R.drawable.matrna_reminder, false, "channel", context.getResources().getString(R.string.app_name), context.getResources().getString(R.string.reminder), 108);
        Intent intent4 = new Intent(context, AlarmInvoke.class);
        intent4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent4);
    }

    private void RepeatAlarm(Context context) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, PSharedPreference.getInteger(context, "ALHOUR", 17));
        calendar.set(12, PSharedPreference.getInteger(context, "ALMIN", 0));
        calendar.set(13, 0);
        calendar.setTimeInMillis(calendar.getTimeInMillis() + 86400000);
        AlarmActivity.setAlaram(context, calendar);
    }
}
