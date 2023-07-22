package com.mantra.counter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/* loaded from: classes.dex */
public class Database {
    static int Counter;

    public static final String COUNTS = "counts";
    public static final String DATABASE_NAME = "mantracounter";
    public static final int DATABASE_VERSION = 1;
    public static final String DATE = "date";
    public static final String TABLE_CREATE = "create table graph(date TEXT,counts LONG, millsecond TEXT);";
    public static final String TABLE_NAME = "graph";
    public static final String mILL = "millsecond";
    private Context context;
    SQLiteDatabase db;
    DatabaseHelper dbhelper;

    public Database(Context context) {
        this.context = context;
        this.dbhelper = new DatabaseHelper(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) {
            super(context, Database.DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL(Database.TABLE_CREATE);
            Log.d("Table is Created", "Successfully");
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            sQLiteDatabase.execSQL("drop Table if Exists mantracounter" );
            onCreate(sQLiteDatabase);
        }
    }

    public Database open() throws SQLException {
        this.db = this.dbhelper.getWritableDatabase();
        return this;
    }

    public void close() {
        this.dbhelper.close();
    }

    public void deleteallGreaterthanNumbers(int i) {
        SQLiteDatabase sQLiteDatabase = this.db;
        sQLiteDatabase.execSQL("DELETE FROM graph WHERE date IN ( SELECT date FROM graph ORDER BY millsecond  LIMIT " + i + ")");
    }

    public long insert_data(String str, long j, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, str);
        contentValues.put(COUNTS, Long.valueOf(j));
        contentValues.put(mILL, str2);
        return this.db.insert(TABLE_NAME, null, contentValues);
    }

    public Cursor getAll() {
        return this.db.rawQuery("select * from graph", null);
    }

    public void UpdateMala(String str, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COUNTS, Long.valueOf(j));
        Log.e("update", "Data is Updated Successfully");
        this.db.update(TABLE_NAME, contentValues, "date = ?", new String[]{str});
    }

    public int isExistMalaonThisDay(String str) {
        Cursor rawQuery = this.db.rawQuery("select * from graph where date = '" + str + "';", null);
        StringBuilder sb = new StringBuilder();
        sb.append(rawQuery.getCount());
        sb.append("");
       print(sb.toString());
        if (rawQuery != null) {
            return rawQuery.getCount();
        }
        return 0;
    }

    public static void print(String str) {
        Log.e("log" + Counter, str);
        Counter = Counter + 1;
    }
}
