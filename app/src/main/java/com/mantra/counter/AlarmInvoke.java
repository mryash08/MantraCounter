package com.mantra.counter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mantra.counter.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class AlarmInvoke extends Activity {
    TextView close;
    TextView time;
    TextView title;
    Button yes;

    @Override 
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_alarm_invoke);
        this.yes = (Button) findViewById(R.id.btn_click);
        this.time = (TextView) findViewById(R.id.time);
        this.title = (TextView) findViewById(R.id.title);
        this.close = (TextView) findViewById(R.id.close);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        try {
            this.time.setText(new SimpleDateFormat("hh:mm a").format(simpleDateFormat.parse(PSharedPreference.getInteger(this, "ALHOUR", 19) + ":" + PSharedPreference.getInteger(this, "ALMIN", 0))));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        PSetTypeface.setTextView(this, this.close, "sweetedge_lib.otf");
        TextView textView = this.title;
        textView.setText(getResources().getString(R.string.app_name) + " \n" + getResources().getString(R.string.reminder));
        this.yes.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.AlarmInvoke.1
            @Override 
            public void onClick(View view) {
                Intent intent = new Intent(AlarmInvoke.this, Country.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                AlarmInvoke.this.startActivity(intent);
                AlarmInvoke.this.finish();
            }
        });
        this.close.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.AlarmInvoke.2
            @Override 
            public void onClick(View view) {
                AlarmInvoke.this.finish();
            }
        });
    }
}
