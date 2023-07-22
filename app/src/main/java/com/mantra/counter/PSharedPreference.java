package com.mantra.counter;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes2.dex */
public class PSharedPreference {
    public static String prefName;
    public static SharedPreferences sharedPrefs;

    public static void setBoolean(Context context, String str, boolean z) {
        String packageName = context.getPackageName();
        prefName = packageName;
        SharedPreferences sharedPreferences = context.getSharedPreferences(packageName, 0);
        sharedPrefs = sharedPreferences;
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(str, z);
        edit.commit();
    }

    public static boolean getBoolean(Context context, String str, boolean z) {
        String packageName = context.getPackageName();
        prefName = packageName;
        SharedPreferences sharedPreferences = context.getSharedPreferences(packageName, 0);
        sharedPrefs = sharedPreferences;
        return sharedPreferences.getBoolean(str, z);
    }

    public static void setString(Context context, String str, String str2) {
        String packageName = context.getPackageName();
        prefName = packageName;
        SharedPreferences sharedPreferences = context.getSharedPreferences(packageName, 0);
        sharedPrefs = sharedPreferences;
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public static String getString(Context context, String str, String str2) {
        String packageName = context.getPackageName();
        prefName = packageName;
        SharedPreferences sharedPreferences = context.getSharedPreferences(packageName, 0);
        sharedPrefs = sharedPreferences;
        return sharedPreferences.getString(str, str2);
    }

    public static void setInteger(Context context, String str, int i) {
        String packageName = context.getPackageName();
        prefName = packageName;
        SharedPreferences sharedPreferences = context.getSharedPreferences(packageName, 0);
        sharedPrefs = sharedPreferences;
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(str, i);
        edit.commit();
    }

    public static int getInteger(Context context, String str, int i) {
        String packageName = context.getPackageName();
        prefName = packageName;
        SharedPreferences sharedPreferences = context.getSharedPreferences(packageName, 0);
        sharedPrefs = sharedPreferences;
        return sharedPreferences.getInt(str, i);
    }

    public static void setFloat(Context context, String str, float f) {
        String packageName = context.getPackageName();
        prefName = packageName;
        SharedPreferences sharedPreferences = context.getSharedPreferences(packageName, 0);
        sharedPrefs = sharedPreferences;
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putFloat(str, f);
        edit.commit();
    }

    public static float getFloat(Context context, String str, float f) {
        String packageName = context.getPackageName();
        prefName = packageName;
        SharedPreferences sharedPreferences = context.getSharedPreferences(packageName, 0);
        sharedPrefs = sharedPreferences;
        return sharedPreferences.getFloat(str, f);
    }

    public static void setLong(Context context, String str, long j) {
        String packageName = context.getPackageName();
        prefName = packageName;
        SharedPreferences sharedPreferences = context.getSharedPreferences(packageName, 0);
        sharedPrefs = sharedPreferences;
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putLong(str, j);
        edit.commit();
    }

    public static long getLong(Context context, String str, long j) {
        String packageName = context.getPackageName();
        prefName = packageName;
        SharedPreferences sharedPreferences = context.getSharedPreferences(packageName, 0);
        sharedPrefs = sharedPreferences;
        return sharedPreferences.getLong(str, j);
    }
}
