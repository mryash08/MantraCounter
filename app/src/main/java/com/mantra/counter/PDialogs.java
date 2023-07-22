package com.mantra.counter;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;

import com.mantra.counter.R;

/* loaded from: classes.dex */
public class PDialogs {
    public static AlertDialog.Builder cb;
    public static AlertDialog cd;

    public static void showCustom(final Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        linearLayout.setBackgroundColor(Color.parseColor("#ffffff"));
        linearLayout.setOrientation(1);
        linearLayout.setPadding(20, 20, 20, 20);
        TextView textView = new TextView(context);
        textView.setId(R.id.title);
        textView.setText("TextView");
        textView.setTextColor(Color.parseColor("#666666"));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(0, 10, 0, 0);
        textView.setLayoutParams(layoutParams);
        textView.setTextSize(20.0f);
        textView.setText("Suggest idea about app");
        TextView textView2 = new TextView(context);
        textView2.setText("Appname");
        textView2.setTextColor(Color.parseColor("#666666"));
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams2.setMargins(0, 10, 0, 0);
        textView2.setLayoutParams(layoutParams2);
        textView2.setText(Suggest.getAppname(context));
        Suggest.edtTexts = new EditText(context);
        Suggest.edtTexts.setBackgroundResource(R.drawable.frame_default);
        Suggest.edtTexts.setEms(10);
        Suggest.edtTexts.setGravity(48);
        Suggest.edtTexts.setHint("Enter Text Here...");
        Suggest.edtTexts.setRawInputType(131072);
        Suggest.edtTexts.setLines(2);
        Suggest.edtTexts.setMinLines(3);
        Suggest.edtTexts.setPadding(20, 20, 20, 20);
        Suggest.edtTexts.setText("");
        Suggest.edtTexts.setTextColor(Color.parseColor("#ffffff"));
        Suggest.edtTexts.setHintTextColor(Color.parseColor("#cccccc"));
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams3.setMargins(0, 25, 0, 0);
        Suggest.edtTexts.setLayoutParams(layoutParams3);
        Suggest.emailTexts = new EditText(context);
        Suggest.emailTexts.setBackgroundResource(R.drawable.frame_default);
        Suggest.emailTexts.setHint("Enter your email");
        Suggest.emailTexts.setPadding(20, 20, 20, 20);
        Suggest.emailTexts.setTextColor(Color.parseColor("#ffffff"));
        Suggest.emailTexts.setHintTextColor(Color.parseColor("#999999"));
        Suggest.emailTexts.setTypeface(Typeface.defaultFromStyle(2));
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams4.setMargins(0, 20, 0, 0);
        Suggest.emailTexts.setLayoutParams(layoutParams4);
        Suggest.nameTexts = new EditText(context);
        Suggest.nameTexts.setBackgroundResource(R.drawable.frame_default);
        Suggest.nameTexts.setHint("Enter your name (optional)");
        Suggest.nameTexts.setPadding(20, 20, 20, 20);
        Suggest.nameTexts.setTextColor(Color.parseColor("#ffffff"));
        Suggest.nameTexts.setHintTextColor(Color.parseColor("#999999"));
        Suggest.nameTexts.setTypeface(Typeface.defaultFromStyle(2));
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams5.setMargins(0, 20, 0, 0);
        Suggest.nameTexts.setLayoutParams(layoutParams5);
        RelativeLayout relativeLayout = new RelativeLayout(context);
        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams6.setMargins(0, 25, 0, 30);
        relativeLayout.setLayoutParams(layoutParams6);
        Suggest.submitbtn = new Button(context);
        Suggest.submitbtn.setBackgroundColor(0);
        Suggest.submitbtn.setPadding(20, 20, 20, 20);
        Suggest.submitbtn.setText("SUBMIT");
        Suggest.submitbtn.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
        Suggest.submitbtn.setTypeface(Typeface.DEFAULT_BOLD);
        RelativeLayout.LayoutParams layoutParams7 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams7.addRule(11);
        Suggest.submitbtn.setLayoutParams(layoutParams7);
        relativeLayout.addView(Suggest.submitbtn);
        Suggest.cancelbtn = new Button(context);
        Suggest.cancelbtn.setBackgroundColor(0);
        Suggest.cancelbtn.setPadding(20, 20, 20, 20);
        Suggest.cancelbtn.setText("CANCEL");
        Suggest.cancelbtn.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
        Suggest.cancelbtn.setTypeface(Typeface.DEFAULT_BOLD);
        RelativeLayout.LayoutParams layoutParams8 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams8.addRule(0, Suggest.submitbtn.getId());
        Suggest.cancelbtn.setLayoutParams(layoutParams8);
        Suggest.cancelbtn.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.PDialogs.1
            @Override 
            public void onClick(View view) {
                PDialogs.dismiss();
                Suggest.setvib(context);
            }
        });
        relativeLayout.addView(Suggest.cancelbtn);
        linearLayout.addView(textView);
        linearLayout.addView(textView2);
        linearLayout.addView(Suggest.edtTexts);
        linearLayout.addView(Suggest.emailTexts);
        linearLayout.addView(Suggest.nameTexts);
        linearLayout.addView(relativeLayout);
        AlertDialog.Builder builder = new AlertDialog.Builder(context, 4);
        cb = builder;
        builder.setCancelable(false);
        cb.setView(linearLayout);
        showD();
    }

    public static void showD() {
        AlertDialog create = cb.create();
        cd = create;
        create.show();
    }

    public static void dismiss() {
        AlertDialog alertDialog = cd;
        if (alertDialog == null || !alertDialog.isShowing()) {
            return;
        }
        cd.dismiss();
    }
}
