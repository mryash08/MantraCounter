package com.mantra.counter;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import com.mantra.counter.R;

/* loaded from: classes.dex */
public class Developer_About extends AppCompatActivity {
    TextView a;
    TextView b;
    TextView c;
    TextView d;
    ImageView developer_app_icon;
    TextView e;
    TextView f;
    String Youtube = "http://davdapranav.com/webservice_files/developer_youtube.php";
    String Twitter = "http://davdapranav.com/webservice_files/developer_twitter.php";
    String FB = "http://davdapranav.com/webservice_files/developer_facebook.php";
    String Insta = "http://davdapranav.com/webservice_files/developer_insta.php";
    String Web = "http://davdapranav.com";

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.developer_about);
        this.developer_app_icon = (ImageView) findViewById(R.id.developer_app_icon);
        this.a = (TextView) findViewById(R.id.a);
        this.b = (TextView) findViewById(R.id.b);
        this.c = (TextView) findViewById(R.id.c);
        this.d = (TextView) findViewById(R.id.d);
        this.e = (TextView) findViewById(R.id.e);
        TextView textView = (TextView) findViewById(R.id.f);
        this.f = textView;
        PSetTypeface.setTextViewBulk(this, new TextView[]{this.a, this.b, this.c, this.d, this.e, textView}, "sweetedge_lib.otf");
        this.a.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.a.setShadowLayer(10.0f, 0.0f, 0.0f, -1);
        this.b.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.b.setShadowLayer(10.0f, 0.0f, 0.0f, -1);
        this.c.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.c.setShadowLayer(10.0f, 0.0f, 0.0f, -1);
        this.d.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.d.setShadowLayer(10.0f, 0.0f, 0.0f, -1);
        this.e.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.e.setShadowLayer(10.0f, 0.0f, 0.0f, -1);
        this.f.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.f.setShadowLayer(10.0f, 0.0f, 0.0f, -1);
        this.developer_app_icon.setImageBitmap(convertthis("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"));
        this.a.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.Developer_About.1
            @Override 
            public void onClick(View view) {
                try {
                    Developer_About.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://dev?id=4815588696674572279")));
                } catch (ActivityNotFoundException unused) {
                    Developer_About.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/dev?id=4815588696674572279")));
                }
            }
        });
        this.b.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.Developer_About.2
            @Override 
            public void onClick(View view) {
                Developer_About.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(Developer_About.this.FB)));
            }
        });
        this.c.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.Developer_About.3
            @Override 
            public void onClick(View view) {
                Developer_About.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(Developer_About.this.Youtube)));
            }
        });
        this.d.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.Developer_About.4
            @Override 
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("message/rfc822");
                intent.putExtra("android.intent.extra.EMAIL", new String[]{"er.pranavdavda@gmail.com"});
                intent.putExtra("android.intent.extra.SUBJECT", "Regarding Google Play App");
                intent.putExtra("android.intent.extra.TEXT", "");
                try {
                    Developer_About.this.startActivity(Intent.createChooser(intent, "Send mail..."));
                } catch (ActivityNotFoundException unused) {
                    Toast.makeText(Developer_About.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        this.e.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.Developer_About.5
            @Override 
            public void onClick(View view) {
                Developer_About.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(Developer_About.this.Twitter)));
            }
        });
        this.f.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.Developer_About.6
            @Override 
            public void onClick(View view) {
                Developer_About.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(Developer_About.this.Insta)));
            }
        });
        this.developer_app_icon.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.Developer_About.7
            @Override 
            public void onClick(View view) {
                Developer_About.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(Developer_About.this.Web)));
            }
        });
    }

    public Bitmap convertthis(String str) {
        byte[] decode = Base64.decode(str, 0);
        return BitmapFactory.decodeByteArray(decode, 0, decode.length);
    }
}
