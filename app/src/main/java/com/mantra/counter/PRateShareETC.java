package com.mantra.counter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

/* loaded from: classes2.dex */
public class PRateShareETC {
    public static void share(Context context, String str) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", String.valueOf(str) + "\nhttps://play.google.com/store/apps/details?id=" + context.getPackageName());
        context.startActivity(Intent.createChooser(intent, "Share via"));
    }

    public static void rate(Context context) {
        try {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + context.getPackageName())));
        } catch (ActivityNotFoundException unused) {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
        }
    }

    public static boolean askforRate(Context context, String str, int[] iArr) {
        int integer = PSharedPreference.getInteger(context, str, 0);
        StringBuilder sb = new StringBuilder();
        sb.append(integer);
        Log.e("Current Val", sb.toString());
        boolean z = false;
        for (int i : iArr) {
            if (integer == i) {
                z = true;
            }
        }
        PSharedPreference.setInteger(context, str, integer + 1);
        return z;
    }

    public static void showDeveloperPage(Context context, String str) {
        try {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://search?q=pub:" + str)));
        } catch (ActivityNotFoundException unused) {
            String replace = str.replace(" ", "+");
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/developer?id=" + replace)));
        }
    }
}
