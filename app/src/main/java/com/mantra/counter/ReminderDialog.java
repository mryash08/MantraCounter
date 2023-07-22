package com.mantra.counter;

import android.app.AppOpsManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mantra.counter.appdata.AlarmActivity;
import com.mantra.counter.R;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/* loaded from: classes.dex */
public class ReminderDialog extends AppCompatActivity {
    DecimalFormat df = new DecimalFormat("00");
    RadioButton r1;
    RadioButton r2;
    RadioGroup radioGp;
    Button set;
    TextView time;

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        Settings.Reminder.setChecked(false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_reminder_dialog);
        this.time = (TextView) findViewById(R.id.time_txtview);
        this.set = (Button) findViewById(R.id.set);
        this.radioGp = (RadioGroup) findViewById(R.id.RadioGroup1);
        this.r1 = (RadioButton) findViewById(R.id.radio1);
        this.r2 = (RadioButton) findViewById(R.id.radio2);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        try {
            this.time.setText(new SimpleDateFormat("hh:mm a").format(simpleDateFormat.parse(PSharedPreference.getInteger(this, "ALHOUR", 19) + ":" + PSharedPreference.getInteger(this, "ALMIN", 0))));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.radioGp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.mantra.counter.ReminderDialog.1
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.radio1) {
                    PSharedPreference.setBoolean(ReminderDialog.this, "ISFULLSCREENALM", false);
                    return;
                }
                PSharedPreference.setBoolean(ReminderDialog.this, "ISFULLSCREENALM", true);
                if (ReminderDialog.this.checkPermission()) {
                    ReminderDialog.this.r2.setChecked(true);
                } else {
                    ReminderDialog.this.r1.setChecked(true);
                }
            }
        });
        this.time.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.ReminderDialog.2
            @Override 
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                new TimePickerDialog(ReminderDialog.this, new TimePickerDialog.OnTimeSetListener() { // from class: com.mantra.counter.ReminderDialog.2.1
                    @Override // android.app.TimePickerDialog.OnTimeSetListener
                    public void onTimeSet(TimePicker timePicker, int i, int i2) {
                        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm");
                        try {
                            ReminderDialog.this.time.setText(new SimpleDateFormat("hh:mm a").format(simpleDateFormat2.parse(i + ":" + i2)));
                        } catch (ParseException e2) {
                            e2.printStackTrace();
                        }
                        PSharedPreference.setInteger(ReminderDialog.this, "ALHOUR", i);
                        PSharedPreference.setInteger(ReminderDialog.this, "ALMIN", i2);
                    }
                }, calendar.get(11), calendar.get(12), false).show();
            }
        });
        this.set.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.ReminderDialog.3
            @Override 
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(11, PSharedPreference.getInteger(ReminderDialog.this, "ALHOUR", 19));
                calendar.set(12, PSharedPreference.getInteger(ReminderDialog.this, "ALMIN", 0));
                calendar.set(13, 0);
                AlarmActivity.setAlaram(ReminderDialog.this, calendar);
                ReminderDialog.this.finish();
                ReminderDialog reminderDialog = ReminderDialog.this;
                Toast.makeText(reminderDialog, getResources().getString(R.string.reminder) + " : " + ReminderDialog.this.getResources().getString(R.string.yes), Toast.LENGTH_SHORT).show();
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm");
                try {
                    Date parse = simpleDateFormat2.parse(PSharedPreference.getInteger(ReminderDialog.this, "ALHOUR", 19) + ":" + PSharedPreference.getInteger(ReminderDialog.this, "ALMIN", 0));
                    TextView textView = Settings.ReminderText;
                    textView.setText(ReminderDialog.this.getResources().getString(R.string.reminder) + "  " + new SimpleDateFormat("hh:mm a").format(parse));
                } catch (Exception unused) {
                }
                ReminderDialog reminderDialog2 = ReminderDialog.this;
                Toast.makeText(reminderDialog2, "Set Alram " + PSharedPreference.getInteger(ReminderDialog.this, "ALHOUR", 19) + " : " + PSharedPreference.getInteger(ReminderDialog.this, "ALMIN", 0), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean checkPermission() {
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                if (android.provider.Settings.canDrawOverlays(this)) {
                    return true;
                }
                AppOpsManager.permissionToOp("android.permission.SYSTEM_ALERT_WINDOW");
                try {
                    android.provider.Settings.canDrawOverlays(getApplicationContext());
                } catch (NoSuchMethodError unused) {
                    canDrawOverlaysUsingReflection(getApplicationContext());
                }
                startActivityForResult(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + getPackageName())), 5439);
                return false;
            }
            return false;
        } catch (Exception unused2) {
            return false;
        }
    }

    public static boolean canDrawOverlaysUsingReflection(Context context) {
        AppOpsManager appOpsManager;
        try {
            Class cls = null;
            if (Build.VERSION.SDK_INT >= 19) {
                cls = AppOpsManager.class;
                appOpsManager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            } else {
                appOpsManager = null;
            }
            return ((Integer) cls.getMethod("checkOp", Integer.TYPE, Integer.TYPE, String.class).invoke(appOpsManager, 24, Integer.valueOf(Binder.getCallingUid()), context.getApplicationContext().getPackageName())).intValue() == 0;
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 5439 || Build.VERSION.SDK_INT < 23) {
            return;
        }
        if (!android.provider.Settings.canDrawOverlays(this)) {
            this.r1.setChecked(true);
        } else {
            this.r2.setChecked(true);
        }
    }
}
