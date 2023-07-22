package com.mantra.counter;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.Button;
import android.widget.TextView;

/* loaded from: classes2.dex */
public class PSetTypeface {
    public static Typeface typeface;

    public static void setTextView(Context context, TextView textView, String str) {
        Typeface createFromAsset = Typeface.createFromAsset(context.getAssets(), str);
        typeface = createFromAsset;
        textView.setTypeface(createFromAsset);
    }

    public static void setTextViewBulk(Context context, TextView[] textViewArr, String str) {
        typeface = Typeface.createFromAsset(context.getAssets(), str);
        for (TextView textView : textViewArr) {
            textView.setTypeface(typeface);
        }
    }

    public static void setButton(Context context, Button button, String str) {
        Typeface createFromAsset = Typeface.createFromAsset(context.getAssets(), str);
        typeface = createFromAsset;
        button.setTypeface(createFromAsset);
    }

    public static void setButtonBulk(Context context, Button[] buttonArr, String str) {
        typeface = Typeface.createFromAsset(context.getAssets(), str);
        for (Button button : buttonArr) {
            button.setTypeface(typeface);
        }
    }
}
