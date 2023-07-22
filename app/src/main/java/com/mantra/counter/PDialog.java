package com.mantra.counter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/* loaded from: classes2.dex */
public class PDialog {
    public static AlertDialog.Builder cb = null;
    public static AlertDialog cd = null;
    public static AlertDialog.Builder sb = null;
    public static AlertDialog sd = null;
    static boolean status = false;

    public static void showSimple(Context context, DialogInterface.OnClickListener onClickListener, String str, String str2, String str3, String str4) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        sb = builder;
        builder.setTitle(str);
        sb.setMessage(str2);
        sb.setPositiveButton(str3, onClickListener);
        sb.setNegativeButton(str4, onClickListener);
        AlertDialog create = sb.create();
        sd = create;
        create.show();
    }

    public static void showCustom(Context context, DialogInterface.OnClickListener onClickListener, int i, int i2, String str, String str2, String str3) {
        View inflate = LayoutInflater.from(context).inflate(i, (ViewGroup) null);
        cb = new AlertDialog.Builder(context);
        ((TextView) inflate.findViewById(i2)).setText(str);
        cb.setView(inflate);
        cb.setPositiveButton(str2, onClickListener);
        cb.setNegativeButton(str3, onClickListener);
        AlertDialog create = cb.create();
        cd = create;
        create.show();
    }
}
