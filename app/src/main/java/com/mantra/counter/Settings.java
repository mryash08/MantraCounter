package com.mantra.counter;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.mantra.counter.appdata.AlarmActivity;
import com.mantra.counter.R;

import java.io.File;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/* loaded from: classes.dex */
public class Settings extends Activity {
    public static Switch Reminder;
    public static TextView ReminderText;
    public static ArrayList<String> purchaseItemIDs = new ArrayList<String>() { // from class: com.mantra.counter.Settings.1
        {
            add("com.mantra.counter.adfree");
        }
    };
    RelativeLayout Background_settings;
    ImageView Help;
    Switch PowerSaver;
    Spinner TapOption;
    TextView TitleHeader;
    TextView Version;
    Button about;
    ImageView lang;
    LinearLayout mantralayout;
    Button more;
    ImageView no_Ad;
    File photoDir;
    Switch photoModeSwitch;
    ImageView photomodeImg;
    RelativeLayout powersaverGuide;
    Button rate;
    ImageView rec;
    Button share;
    Switch sound;
    TextView soundfilename;
    Switch vibrate;
    String[] tapOp = new String[0];
    DecimalFormat df = new DecimalFormat("00");

    public static String getAppVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override 
    protected void onResume() {
        super.onResume();
        if (this.sound.isChecked()) {
            this.sound.setText(getResources().getString(R.string.soundOn));
            this.mantralayout.setClickable(true);
            this.mantralayout.setAlpha(1.0f);
        } else {
            this.sound.setText(getResources().getString(R.string.soundOff));
            this.mantralayout.setClickable(false);
            this.mantralayout.setAlpha(0.4f);
        }
        if (PSharedPreference.getInteger(this, getResources().getStringArray(R.array.ProductID)[0], 0) == 1) {
            this.TitleHeader.setText(getResources().getString(R.string.prime));
        } else {
            PSharedPreference.setString(this, "Mantra", "Default");
        }
        updateSoundFile();
    }

    @Override 
    public void onBackPressed() {
        super.onBackPressed();
    }

    void updateSoundFile() {
        this.soundfilename.setText(PSharedPreference.getString(this, "Mantra", "Default"));
    }
    public static void changeColor(Window window, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            window.setStatusBarColor(i);
            return;
        }
        window.addFlags(67108864);
        window.addFlags(134217728);
    }

    @SuppressLint("ResourceType")
    @Override 
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT >= 23) {
            changeColor(getWindow(), getColor(R.color.colorPrimary));
        }
        setContentView(R.layout.activity_settings);
        this.TitleHeader = (TextView) findViewById(R.id.titleHeader);
        ReminderText = (TextView) findViewById(R.id.reminderText);
        this.sound = (Switch) findViewById(R.id.sound);
        this.vibrate = (Switch) findViewById(R.id.vib);
        Reminder = (Switch) findViewById(R.id.reminderSwitch);
        this.PowerSaver = (Switch) findViewById(R.id.powersavermode);
        this.photomodeImg = (ImageView) findViewById(R.id.photo_mode_img);
        this.photoModeSwitch = (Switch) findViewById(R.id.photo_mode_switch);
        this.powersaverGuide = (RelativeLayout) findViewById(R.id.powersaveLayoutGuide);
        this.rec = (ImageView) findViewById(R.id.record);
        this.powersaverGuide.setVisibility(View.GONE);
        this.Background_settings = (RelativeLayout) findViewById(R.id.background_settings);
        this.mantralayout = (LinearLayout) findViewById(R.id.mantralayout);
        this.soundfilename = (TextView) findViewById(R.id.filename);
        this.Version = (TextView) findViewById(R.id.version);
        this.no_Ad = (ImageView) findViewById(R.id.no_ad);
        new Suggest(this, (ImageView) findViewById(R.id.suggest_icon));
        this.rate = (Button) findViewById(R.id.rate);
        this.share = (Button) findViewById(R.id.share);
        this.more = (Button) findViewById(R.id.more);
        this.about = (Button) findViewById(R.id.about);
        this.lang = (ImageView) findViewById(R.id.lang);
        this.Help = (ImageView) findViewById(R.id.help);
        this.TapOption = (Spinner) findViewById(R.id.spinner_tapOption);
        String[] strArr = new String[3];
        this.tapOp = strArr;
        strArr[0] = getResources().getString(R.string.none);
        this.tapOp[1] = getResources().getString(R.string.count);
        this.tapOp[2] = getResources().getString(R.string.hide);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, (int) R.layout.tap_list_textview, this.tapOp);
        arrayAdapter.setDropDownViewResource(17367049);
        this.TapOption.setAdapter((SpinnerAdapter) arrayAdapter);
        if (PSharedPreference.getInteger(this, getResources().getStringArray(R.array.ProductID)[0], 0) == 1) {
            this.TapOption.setSelection(PSharedPreference.getInteger(this, "TAPID", 0), true);
        } else {
            PSharedPreference.setInteger(this, "TAPID", 0);
            this.TapOption.setSelection(PSharedPreference.getInteger(this, "TAPID", 0), true);
        }
        if (MainActivity.DARK) {
            this.Background_settings.setBackgroundColor(getResources().getColor(R.color.darkbgcolor));
        } else {
            this.Background_settings.setBackgroundColor(getResources().getColor(R.color.whitecolor));
        }
        this.sound.setChecked(PSharedPreference.getBoolean(this, "SOUND", true));
        this.vibrate.setChecked(PSharedPreference.getBoolean(this, "VIB", true));
        this.PowerSaver.setChecked(PSharedPreference.getBoolean(this, "POWERSAVER", false));
        if (this.PowerSaver.isChecked()) {
            this.photoModeSwitch.setEnabled(false);
        }
        this.photoModeSwitch.setChecked(PSharedPreference.getBoolean(this, "PHOTOMODE", false));
        if (PSharedPreference.getInteger(this, getResources().getStringArray(R.array.ProductID)[0], 0) == 1) {
            Reminder.setChecked(PSharedPreference.getBoolean(this, "REMINDER", false));
        } else {
            Reminder.setChecked(false);
            PSharedPreference.setBoolean(this, "REMINDER", false);
            AlarmActivity.cancelAlarm(this);
        }
        if (Reminder.isChecked()) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
            try {
                Date parse = simpleDateFormat.parse(PSharedPreference.getInteger(this, "ALHOUR", 19) + ":" + PSharedPreference.getInteger(this, "ALMIN", 0));
                TextView textView = ReminderText;
                textView.setText(getResources().getString(R.string.reminder) + " " + new SimpleDateFormat("hh:mm a").format(parse));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        TextView textView2 = this.Version;
        textView2.setText("Version : " + getAppVersion(this));
        this.Version.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.Settings.3
            @Override 
            public void onClick(View view) {
                PSharedPreference.setBoolean(Settings.this, Settings.purchaseItemIDs.get(0), false);
            }
        });
        this.TapOption.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.mantra.counter.Settings.4
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                Settings settings = Settings.this;
                if (PSharedPreference.getInteger(settings, settings.getResources().getStringArray(R.array.ProductID)[0], 0) == 1) {
                    PSharedPreference.setInteger(Settings.this, "TAPID", i);
                    return;
                }
                Settings.this.TapOption.setSelection(0);
                Settings settings2 = Settings.this;

                Toast.makeText(settings2, settings2.getResources().getString(R.string.notprime),Toast.LENGTH_LONG);
            }
        });
        Reminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.mantra.counter.Settings.5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Settings settings = Settings.this;
                if (PSharedPreference.getInteger(settings, settings.getResources().getStringArray(R.array.ProductID)[0], 0) != 1) {
                    Settings settings2 = Settings.this;
                   Toast.makeText(settings2, settings2.getResources().getString(R.string.notprime),Toast.LENGTH_LONG);
                    Settings.Reminder.setChecked(false);
                } else if (z) {
                    PSharedPreference.setBoolean(Settings.this, "REMINDER", true);
                    startActivity(new Intent(Settings.this,ReminderDialog.class));
                } else {
                    PSharedPreference.setBoolean(Settings.this, "REMINDER", false);
                    AlarmActivity.cancelAlarm(Settings.this);
                    Settings settings3 = Settings.this;
                    Toast.makeText(settings3, Settings.this.getResources().getString(R.string.reminder) + " : " + Settings.this.getResources().getString(R.string.No),Toast.LENGTH_LONG);
                }
            }
        });
        this.Help.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.Settings.6
            @Override 
            public void onClick(View view) {
                Settings.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(Settings.this.getResources().getString(R.string.help))));
            }
        });
        if (this.vibrate.isChecked()) {
            this.vibrate.setText(getResources().getString(R.string.Vibon));
        } else {
            this.vibrate.setText(getResources().getString(R.string.VibOff));
        }
        if (this.photoModeSwitch.isChecked()) {
            this.photomodeImg.setAlpha(1.0f);
        } else {
            this.photomodeImg.setAlpha(0.5f);
        }
        this.about.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.Settings.7
            @Override 
            public void onClick(View view) {
                startActivity(new Intent(Settings.this, Developer_About.class));
            }
        });
        this.rate.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.Settings.8
            @Override 
            public void onClick(View view) {
                PRateShareETC.rate(Settings.this);
            }
        });
        this.share.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.Settings.9
            @Override 
            public void onClick(View view) {
                PRateShareETC.share(Settings.this, "Download Mantra Counter App from Google Play and Enjoy Your Counter in Android Mobile");
            }
        });
        this.more.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.Settings.10
            @Override 
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse("https://www.davdapranav.com"));
                Settings.this.startActivity(intent);
            }
        });
        this.lang.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.Settings.11
            @Override 
            public void onClick(View view) {
                PSharedPreference.setBoolean(Settings.this, "FIRSTRUN", true);
         startActivity(new Intent(Settings.this, Country.class));
                Settings.this.finishAffinity();
            }
        });
        this.photoModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.mantra.counter.Settings.12
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                PSharedPreference.setBoolean(Settings.this, "CHANGESINPHOTO", true);
                if (Settings.this.photoModeSwitch.isChecked()) {
                    Settings.this.photomodeImg.setAlpha(1.0f);
                    PSharedPreference.setBoolean(Settings.this, "PHOTOMODE", true);
                    return;
                }
                Settings.this.photomodeImg.setAlpha(0.5f);
                PSharedPreference.setBoolean(Settings.this, "PHOTOMODE", false);
            }
        });
        this.sound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.mantra.counter.Settings.13
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (Settings.this.sound.isChecked()) {
                    PSharedPreference.setBoolean(Settings.this, "SOUND", true);
                    Settings.this.sound.setText(Settings.this.getResources().getString(R.string.soundOn));
                    Settings.this.mantralayout.setClickable(true);
                    Settings.this.mantralayout.setAlpha(1.0f);
                    return;
                }
                PSharedPreference.setBoolean(Settings.this, "SOUND", false);
                Settings.this.sound.setText(Settings.this.getResources().getString(R.string.soundOff));
                Settings.this.mantralayout.setClickable(false);
                Settings.this.mantralayout.setAlpha(0.4f);
            }
        });
        this.vibrate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.mantra.counter.Settings.14
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (Settings.this.vibrate.isChecked()) {
                    PSharedPreference.setBoolean(Settings.this, "VIB", true);
                    Settings.this.vibrate.setText(Settings.this.getResources().getString(R.string.Vibon));
                    return;
                }
                PSharedPreference.setBoolean(Settings.this, "VIB", false);
                Settings.this.vibrate.setText(Settings.this.getResources().getString(R.string.VibOff));
            }
        });
        this.mantralayout.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.Settings.15
            @Override 
            public void onClick(View view) {
                Settings settings = Settings.this;
                if (PSharedPreference.getInteger(settings, settings.getResources().getStringArray(R.array.ProductID)[0], 0) == 1) {
                    if (!Settings.this.checkPermission()) {
                        Settings.this.requestPermission();
                        return;
                    }
                    Settings settings2 = Settings.this;
                    settings2.photoDir = new File(Environment.getExternalStorageDirectory() + Const.SAVE_FOLDER_NAME);
                    Settings.this.photoDir.mkdirs();
                    startActivity(new Intent(Settings.this, RecordList.class));
                    return;
                }
                Settings settings3 = Settings.this;
              Toast.makeText(settings3, settings3.getResources().getString(R.string.notprime),Toast.LENGTH_LONG);
            }
        });
        this.powersaverGuide.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.Settings.16
            @Override 
            public void onClick(View view) {
                Settings.this.powersaverGuide.setVisibility(8);
                Settings.super.onBackPressed();
            }
        });
        this.PowerSaver.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.mantra.counter.Settings.17
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (Settings.this.PowerSaver.isChecked()) {
                    Settings.this.powersaverGuide.setVisibility(0);
                    PSharedPreference.setBoolean(Settings.this, "POWERSAVER", true);
                    Settings.this.sound.setChecked(false);
                    PSharedPreference.setBoolean(Settings.this, "SOUND", false);
                    Settings.this.sound.setText(Settings.this.getResources().getString(R.string.soundOff));
                    Settings.this.photoModeSwitch.setEnabled(false);
                    return;
                }
                PSharedPreference.setBoolean(Settings.this, "POWERSAVER", false);
                Settings.this.photoModeSwitch.setEnabled(true);
            }
        });
    }

   
    public void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO"}, 1);
    }

    @Override 
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 1 && iArr.length > 0) {
            boolean z = iArr[0] == 0;
            boolean z2 = iArr[1] == 0;
            if (z && z2) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "PermissionDenied", Toast.LENGTH_LONG).show();
            }
        }
    }

    public boolean checkPermission() {
        if (Build.VERSION.SDK_INT < 23 || ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0 || ContextCompat.checkSelfPermission(this, "android.permission.RECORD_AUDIO") == 0) {
            return true;
        }
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.WRITE_EXTERNAL_STORAGE") || ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.RECORD_AUDIO")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle(R.string.permission_title);
            builder.setMessage(R.string.permission_msg);
            builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() { // from class: com.mantra.counter.Settings.18
                @Override 
                public void onClick(DialogInterface dialogInterface, int i) {
                    ActivityCompat.requestPermissions(Settings.this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO"}, 101);
                }
            });
            builder.create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO"}, 101);
        }
        return false;
    }
}
