package com.mantra.counter;

//import SubInapp.AllinOneSub;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.mantra.counter.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

/* loaded from: classes.dex */
public class Country extends Activity {
    TextView app_name;
    ImageView arrow;
    ArrayList<String> lang = new ArrayList<>();
    Spinner sp_lang;

    @Override 
    public void onBackPressed() {
    }

    @Override 
    protected void onResume() {
        super.onResume();
    }

    @SuppressLint("ResourceType")
    @Override 
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.country);
//        AllinOneSub.check(this);
        this.sp_lang = (Spinner) findViewById(R.id.spinner1);
        this.arrow = (ImageView) findViewById(R.id.imageView3);
        this.app_name = (TextView) findViewById(R.id.app_name);
        this.lang.add("English");
        this.lang.add("हिंदी");
        this.lang.add("العربية");
        this.lang.add("বাঙালি");
        this.lang.add("dansk");
        this.lang.add("Deutsche");
        this.lang.add("Español");
        this.lang.add("فارسی");
        this.lang.add("suomalainen");
        this.lang.add("français");
        this.lang.add("italiano");
        this.lang.add("日本語");
        this.lang.add("한국어");
        this.lang.add("português");
        this.lang.add("România");
        this.lang.add("русский");
        this.lang.add("slovenský");
        this.lang.add("ไทย");
        this.lang.add("Türk");
        this.lang.add("Tiếng Việt");
        this.lang.add("中文");
        this.lang.add("ગુજરાતી");
        this.lang.add("afrikaans");
        this.lang.add("Ελληνικά");
        this.lang.add("मराठी");
        this.lang.add("Basa Jawa");
        this.lang.add("Malay");
        this.lang.add("తెలుగు");
        this.lang.add("اردو");
        this.lang.add("Polskie");
        this.lang.add("Sunda");
        this.lang.add("Hausa");
        this.lang.add("नेपाली");
        this.lang.add("Frysk");
        this.lang.add("svenska");
        this.lang.add("Українська");
        this.lang.add("Dutch");
        this.lang.add("Magyar");
        this.lang.add("Монгол хэл");
        this.sp_lang.setAdapter((SpinnerAdapter) new ArrayAdapter(this, 17367050, this.lang));
        this.arrow.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.Country.1
            @Override 
            public void onClick(View view) {
                new File(Environment.getExternalStorageDirectory() + "/.SweetEdge/RealFlute").mkdirs();
                Country.this.startActivity(new Intent(Country.this, MainActivity.class));
                PSharedPreference.setBoolean(Country.this, "FIRSTRUN", false);
                Country.this.finish();
            }
        });
        this.sp_lang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.mantra.counter.Country.2
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                switch (i) {
                    case 0:
                        Country.this.setLang("en");
                        return;
                    case 1:
                        Country.this.setLang("hi");
                        return;
                    case 2:
                        Country.this.setLang("ar");
                        return;
                    case 3:
                        Country.this.setLang("bn");
                        return;
                    case 4:
                        Country.this.setLang("da");
                        return;
                    case 5:
                        Country.this.setLang("de");
                        return;
                    case 6:
                        Country.this.setLang("es");
                        return;
                    case 7:
                        Country.this.setLang("fa");
                        return;
                    case 8:
                        Country.this.setLang("fi");
                        return;
                    case 9:
                        Country.this.setLang("fr");
                        return;
                    case 10:
                        Country.this.setLang("it");
                        return;
                    case 11:
                        Country.this.setLang("ja");
                        return;
                    case 12:
                        Country.this.setLang("ko");
                        return;
                    case 13:
                        Country.this.setLang("pt");
                        return;
                    case 14:
                        Country.this.setLang("ro");
                        return;
                    case 15:
                        Country.this.setLang("ru");
                        return;
                    case 16:
                        Country.this.setLang("sk");
                        return;
                    case 17:
                        Country.this.setLang("th");
                        return;
                    case 18:
                        Country.this.setLang("tr");
                        return;
                    case 19:
                        Country.this.setLang("vi");
                        return;
                    case 20:
                        Country.this.setLang("zh");
                        return;
                    case 21:
                        Country.this.setLang("gu");
                        return;
                    case 22:
                        Country.this.setLang("af");
                        return;
                    case 23:
                        Country.this.setLang("el");
                        return;
                    case 24:
                        Country.this.setLang("mr");
                        return;
                    case 25:
                        Country.this.setLang("jv");
                        return;
                    case 26:
                        Country.this.setLang("ms");
                        return;
                    case 27:
                        Country.this.setLang("te");
                        return;
                    case 28:
                        Country.this.setLang("ur");
                        return;
                    case 29:
                        Country.this.setLang("pl");
                        return;
                    case 30:
                        Country.this.setLang("su");
                        return;
                    case 31:
                        Country.this.setLang("ha");
                        return;
                    case 32:
                        Country.this.setLang("ne");
                        return;
                    case 33:
                        Country.this.setLang("fy");
                        return;
                    case 34:
                        Country.this.setLang("sv");
                        return;
                    case 35:
                        Country.this.setLang("uk");
                        return;
                    case 36:
                        Country.this.setLang("nl");
                        return;
                    case 37:
                        Country.this.setLang("hu");
                        return;
                    case 38:
                        Country.this.setLang("mn");
                        return;
                    default:
                        return;
                }
            }
        });
        if (PSharedPreference.getBoolean(this, "FIRSTRUN", true)) {
            return;
        }
        setLang(PSharedPreference.getString(this, "Language", "en"));
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    protected void setLang(String str) {
        Log.e("language", "" + str);
        PSharedPreference.setString(this, "Language", str);
        Locale locale = new Locale(str);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        this.app_name.setText(getResources().getString(R.string.app_name));
    }
}
