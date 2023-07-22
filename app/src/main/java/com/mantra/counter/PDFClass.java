package com.mantra.counter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;


import com.mantra.counter.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;



/* loaded from: classes.dex */
public class PDFClass {

    static int Counter;

    private static final int PERMISSION_REQUEST_CODE = 200;
    static Bitmap bmp = null;
    static int chartwitdh = 400;
    static int pageHeight = 1200;
    static int pageWidth = 800;
    static Bitmap scaledbmp;

    public static void Generate(Context context, Bitmap bitmap, Bitmap bitmap2, ArrayList<GraphPojo> arrayList, ImageView imageView) {
        if (checkPermission(context)) {
            generatePDF(context, bitmap, bitmap2, arrayList, imageView);
        } else {
            requestPermission((Activity) context);
        }
    }

    static void generatePDF(final Context context, Bitmap bitmap, Bitmap bitmap2, ArrayList<GraphPojo> arrayList, ImageView imageView) {
        if (Build.VERSION.SDK_INT >= 19) {
           print("Generating....");
            Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
            bmp = decodeResource;
            scaledbmp = Bitmap.createScaledBitmap(decodeResource, 100, 100, false);
            PdfDocument pdfDocument = new PdfDocument();
            Paint paint = new Paint();
            Paint paint2 = new Paint();
            PdfDocument.Page startPage = pdfDocument.startPage(new PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create());
            Canvas canvas = startPage.getCanvas();
            canvas.drawBitmap(scaledbmp, 20.0f, 20.0f, paint);
            paint2.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
            paint2.setTextSize(25.0f);
            paint2.setColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
            canvas.drawText("Mantra Counter - Android App", 130.0f, 60.0f, paint2);
            paint2.setTextSize(20.0f);
            paint2.setColor(ContextCompat.getColor(context, R.color.colorAccent));
            canvas.drawText("Statistics with Graph View", 130.0f, 90.0f, paint2);
            canvas.drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.qr), 170, 170, true), 620.0f, 10.0f, paint);
            paint2.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            paint2.setColor(ContextCompat.getColor(context, R.color.colorPrimary));
            paint2.setTextSize(15.0f);
            paint2.setTextAlign(Paint.Align.CENTER);
            int i = chartwitdh;
            double d = i;
            double width = bitmap.getWidth();
            double height = bitmap.getHeight();
            Double.isNaN(width);
            Double.isNaN(height);
            Double.isNaN(d);
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, i, (int) (d / (width / height)), true);
            int i2 = chartwitdh;
            double d2 = i2;
            double width2 = bitmap2.getWidth();
            double height2 = bitmap2.getHeight();
            Double.isNaN(width2);
            Double.isNaN(height2);
            Double.isNaN(d2);
            Bitmap createScaledBitmap2 = Bitmap.createScaledBitmap(bitmap2, i2, (int) (d2 / (width2 / height2)), true);
            canvas.drawBitmap(createScaledBitmap, 10.0f, 180.0f, paint);
            canvas.drawBitmap(createScaledBitmap2, chartwitdh, 180.0f, paint);
            canvas.drawLine(0.0f, 550.0f, pageWidth, 550.0f, paint);
            Paint paint3 = new Paint();
            paint3.setTextSize(38.0f);
            paint.setTextSize(28.0f);
            paint3.setColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                String str = arrayList.get(i3).date;
                String str2 = arrayList.get(i3).dailycounts;
                String str3 = arrayList.get(i3).counts;
                if (i3 == 0) {
                    canvas.drawText(str, 50.0f, 600.0f, paint3);
                    canvas.drawText(str2, 310.0f, 600.0f, paint3);
                    canvas.drawText(str3, 620.0f, 600.0f, paint3);
                } else {
                    if (i3 % 2 == 0) {
                        paint.setColor(-7829368);
                    } else {
                        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
                    }
                    float f = (i3 * 45) + 600;
                    canvas.drawText(str, 50.0f, f, paint);
                    canvas.drawText(str2, 310.0f, f, paint);
                    canvas.drawText(str3, 620.0f, f, paint);
                }
            }
            pdfDocument.finishPage(startPage);
            String str4 = "Graph_" + System.currentTimeMillis() + ".pdf";
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/Mantra Graphs");
            file.mkdirs();
            File file2 = new File(file, str4);
            try {
                context.getContentResolver().openOutputStream(Uri.fromFile(file2), "w");
                pdfDocument.writeTo(new FileOutputStream(file2));
            } catch (IOException e) {
                print("Exception = " + e.getMessage());
            }
            pdfDocument.close();
            PDialog.showSimple(context, new DialogInterface.OnClickListener() { // from class: SaveData.PDFClass.1
                @Override 
                public void onClick(DialogInterface dialogInterface, int i4) {
                }
            }, context.getResources().getString(R.string.app_name), context.getResources().getString(R.string.pdfsaved) + " \n\n ~/Download/Mantra Graphs/" + str4, "", context.getResources().getString(R.string.ok));
        }
    }

    private static boolean checkPermission(Context context) {
        return ContextCompat.checkSelfPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE") == 0 && ContextCompat.checkSelfPermission(context, "android.permission.READ_EXTERNAL_STORAGE") == 0;
    }

    public static void requestPermission(Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"}, 200);
    }

    public static void print(String str) {
        Log.e("log" + Counter, str);
        Counter = Counter + 1;
    }
}
