package com.mantra.counter;

import java.util.Calendar;

/* loaded from: classes2.dex */
public class PCalendar {
    static Calendar calendar;

    public static int getSec() {
        Calendar calendar2 = Calendar.getInstance();
        calendar = calendar2;
        return calendar2.get(13);
    }

    public static int getMin() {
        Calendar calendar2 = Calendar.getInstance();
        calendar = calendar2;
        return calendar2.get(12);
    }

    public static int getHour24Format() {
        Calendar calendar2 = Calendar.getInstance();
        calendar = calendar2;
        return calendar2.get(11);
    }

    public static int getHour12Format() {
        Calendar calendar2 = Calendar.getInstance();
        calendar = calendar2;
        if (calendar2.get(10) != 0) {
            return calendar.get(10);
        }
        return 12;
    }

    public static int getDate() {
        Calendar calendar2 = Calendar.getInstance();
        calendar = calendar2;
        return calendar2.get(5);
    }

    public static int getMonth() {
        Calendar calendar2 = Calendar.getInstance();
        calendar = calendar2;
        return calendar2.get(2) + 1;
    }

    public static int getYear() {
        Calendar calendar2 = Calendar.getInstance();
        calendar = calendar2;
        return calendar2.get(1);
    }

    public static String getAMPM() {
        Calendar calendar2 = Calendar.getInstance();
        calendar = calendar2;
        return calendar2.get(9) == 0 ? "am" : "pm";
    }
}
