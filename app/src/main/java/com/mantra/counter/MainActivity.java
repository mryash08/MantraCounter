package com.mantra.counter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Vibrator;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.documentfile.provider.DocumentFile;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.soundcloud.android.crop.Crop;
import com.mantra.counter.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Locale;
import lecho.lib.hellocharts.animation.ChartViewportAnimator;
import pl.droidsonroids.gif.GifImageView;


public class MainActivity extends Activity implements DialogInterface.OnClickListener {
    public static boolean DARK = false;
    public static long adCounter;

    static int Counter;
    static Animation dark_mode_anim;
    static Animation fadein;
    static Animation fadeout;
    static Animation scaleTapIn;
    static Animation scaleTapout;
    static LinearLayout themeLayout;
    RelativeLayout Background;
    RelativeLayout PowerSaverButton;
    long TimeinMillisecond;
    LinearLayout adviewContainer;
    private InterstitialAd mInterstitialAd;
    LinearLayout counterLayout;
    ImageView darkMode;
    ProgressDialog dialog;
    ImageView extfileplay;
    ImageView fullscreenBtn;
    GifImageView gifPrime;
    ImageView graph;
    long graph_counter;
    ImageView mainLower;
    ImageView mainUpper;
    ImageView main_counter;
    ImageView main_reset;
    MediaPlayer mp1;
    MediaPlayer mp2;
    SeekBar mySeekBarVol;
    LinearLayout numberLayoutforAnimation;
    TextView numbers;
    TextView numbers2;
    File photoDir;
    Animation photoDown;
    ImageView photoLayoutImg;
    ProgressBar progressBar;
    RelativeLayout rel_layout;
    View seekbarLayout;
    ImageView setting;
    TextView target;
    long temp_count;
    TextView tgt_rounds;
    ImageView themeBlue;
    ImageView themeDark;
    ImageView themeLavender;
    ImageView themeRed;
    ImageView themebutton;
    Animation translateDown;
    Animation translateUp;
    RelativeLayout whiteframeplayer;
    long counter = 0;
    DecimalFormat df_big = new DecimalFormat("000000");
    DecimalFormat df_photo = new DecimalFormat("00000");
    DecimalFormat df_small = new DecimalFormat("000000");
    DecimalFormat df_target_round = new DecimalFormat("000");
    boolean isvib = true;
    boolean isSound = true;
    int BLUR_RADIUS = 10;
    boolean isplay = false;
    boolean isShow = true;
    int themeid = 1;
    String photo_imagefile = "location of image";
    String btnPressed = "AUD";
    boolean isPhotomode = false;
    boolean isFullscreen = false;
    boolean PowerSaverMode = false;

    public void showLoading() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        dialog = progressDialog;
        progressDialog.setMessage(getResources().getString(R.string.videoisstarting));
        dialog.show();
    }

    public void hideLoading() {
        ProgressDialog progressDialog = dialog;
        if (progressDialog == null || !progressDialog.isShowing()) {
            return;
        }
        dialog.dismiss();
    }

    @Override
    public void onDestroy() {
        MediaPlayer mediaPlayer = mp1;
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        MediaPlayer mediaPlayer2 = mp2;
        if (mediaPlayer2 != null) {
            mediaPlayer2.release();
        }
        super.onDestroy();
    }

    @Override 
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 1) {
            if (i == 6709) {
               print("REQ PICK");
                photoLayoutImg.setImageResource(R.drawable.photodefault);
                if (i2 == -1) {
                    handleCrop(i2, intent);
                }
            } else if (i != 9162) {
            } else {
                print("REQ PICk");
                photoLayoutImg.setImageResource(R.drawable.photodefault);
                if (i2 == -1) {
                    beginCrop(intent.getData());
                }
            }
        } else if (i2 != -1 || intent == null || intent.getData() == null) {
        } else {
            ExtFilePlayer.audioFileUri = intent.getData();
            try {
                ExtFilePlayer.fileplayer.reset();
                ExtFilePlayer.fileplayer.setDataSource(getApplicationContext(), ExtFilePlayer.audioFileUri);
                ExtFilePlayer.fileplayer.setLooping(true);
                ExtFilePlayer.fileplayer.prepare();
                ExtFilePlayer.fileplayer.start();
                mySeekBarVol.setVisibility(View.VISIBLE);
                whiteframeplayer.setBackgroundResource(R.drawable.frame_white_player);
                seekbarLayout.setVisibility(View.VISIBLE);
                isplay = true;
                    extfileplay.setImageDrawable(getResources().getDrawable(R.drawable.stop));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e2) {
                e2.printStackTrace();
            } catch (IllegalStateException e3) {
                e3.printStackTrace();
            } catch (SecurityException e4) {
                e4.printStackTrace();
            }
        }
    }
    
    

    private void beginCrop(Uri uri) {
        String str;
        DocumentFile.fromSingleUri(this, uri);
        try {
            str = Environment.getExternalStorageDirectory().getAbsolutePath() + Const.SAVE_FOLDER_NAME + Const.ImageName;
        } catch (Exception e) {
            e.printStackTrace();
            str = null;
        }
        Uri fromFile = Uri.fromFile(new File(str));
        if (getResources().getConfiguration().orientation == 2) {
            Crop.of(uri, fromFile).withAspect(getHeight(this), (getWidth(this) / 8) * 3).start(this);
        } else {
            Crop.of(uri, fromFile).withAspect(getWidth(this), (getHeight(this) / 8) * 3).start(this);
        }
    }

    private void handleCrop(int i, Intent intent) {
        photoLayoutImg.setImageResource(R.drawable.photodefault);
        if (i == -1) {
            moveFile(this, Crop.getOutput(intent).toString());
            print("Set image");
        } else if (i == 404) {
            photoLayoutImg.setImageResource(R.drawable.photodefault);
            Toast.makeText(this, Crop.getError(intent).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override 
    protected void onStart() {
        super.onStart();
        long j = PSharedPreference.getLong(this, "NUMBER", 0L);
        counter = j;
        graph_counter = j;
        TextView textView = numbers;
        textView.setText(df_big.format(counter) + "");
        TextView textView2 = numbers2;
        textView2.setText(df_big.format(counter) + "");
        if (PSharedPreference.getLong(this, "TGT", 0L) > 0) {
            rel_layout.setVisibility(View.VISIBLE);
        }
        tgt_rounds.setText(df_target_round.format(PSharedPreference.getLong(this, "ROUND", 0L)));
        isvib = PSharedPreference.getBoolean(this, "VIB", true);
        isSound = PSharedPreference.getBoolean(this, "SOUND", true);
    }

   
    public void showAdLoadingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.tosetthistheme));
        builder.setMessage(getResources().getString(R.string.tosetthisthemeyouhavetoseevideo));
        builder.setPositiveButton(getResources().getString(R.string.Yes), new DialogInterface.OnClickListener() { // from class: com.mantra.counter.MainActivity.1
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.setNegativeButton(getResources().getString(R.string.No), new DialogInterface.OnClickListener() { // from class: com.mantra.counter.MainActivity.2
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.create().show();
        themeLayout.startAnimation(fadeout);
    }

   

    @Override 
    protected void onStop() {
        super.onStop();
        PSharedPreference.setLong(this, "NUMBER", counter);
        PSharedPreference.setLong(this, "GRPH_COUNTER", graph_counter);
        if (PSharedPreference.getInteger(this, getResources().getStringArray(R.array.ProductID)[0], 0) == 1 || PSharedPreference.getInteger(this, "THEMES", 1) <= 2) {
            return;
        }
        PSharedPreference.setInteger(this, "THEMES", 1);
    }

    @Override 
    protected void onResume() {
        super.onResume();
        Locale locale = new Locale(PSharedPreference.getString(this, "Language", "en"));
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        long j = PSharedPreference.getLong(this, "TGT", 0L);
        if (j > 0) {
            target.setText(df_target_round.format(j));
            progressBar.setVisibility(View.VISIBLE);
            changeProgressBar(progressBar, (int) ((counter * 100) / j));
        } else {
            target.setText(getResources().getString(R.string.target_txt));
            progressBar.setVisibility(View.INVISIBLE);
        }
        if (PSharedPreference.getLong(this, "TGT", 0L) == 0) {
            rel_layout.setVisibility(View.GONE);
        }
        final int width = getWidth(this);
        final int height = getHeight(this);
        int i = width + height;
        final int i2 = i / 54;
        int integer = PSharedPreference.getInteger(this, "THEMES", 1);
        if (integer == 1) {
            setRed();
        } else if (integer == 2) {
            setBlue();
        } else if (integer == 3) {
            setLavender();
        } else if (integer == 4) {
            setDark();
        }
        PSharedPreference.setLong(this, "MANTRAC", System.currentTimeMillis());
        graph_counter = PSharedPreference.getLong(this, "GRPH_COUNTER", 0L);
        isvib = PSharedPreference.getBoolean(this, "VIB", true);
        boolean z = PSharedPreference.getBoolean(this, "POWERSAVER", false);
        PowerSaverMode = z;
        if (z) {
            PowerSaverButton.setVisibility(View.VISIBLE);
        } else {
            PowerSaverButton.setVisibility(View.GONE);
        }
        isSound = PSharedPreference.getBoolean(this, "SOUND", true);
        isPhotomode = PSharedPreference.getBoolean(this, "PHOTOMODE", false);
        if (!PSharedPreference.getBoolean(this, "POWERSAVER", false)) {
            if (isPhotomode) {
                if (PSharedPreference.getBoolean(this, "CHANGESINPHOTO", true)) {
                    PSharedPreference.setBoolean(this, "CHANGESINPHOTO", false);
                    Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.photo_up);
                    photoDown = loadAnimation;
                    loadAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.mantra.counter.MainActivity.4
                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }

                        @Override 
                        public void onAnimationStart(Animation animation) {
                            print("Start Animation");
                            photoLayoutImg.setVisibility(View.VISIBLE);
                            numbers.setText(df_photo.format(counter));
                            print("Width : " + width + " height : " + height + "  numsize: " + i2);
                            if (getResources().getConfiguration().orientation == 2) {
                                TextView textView = numbers;
                                double d = i2;
                                Double.isNaN(d);
                                textView.setTextSize((float) (d / 2.5d));
                            } else {
                                TextView textView2 = numbers;
                                double d2 = i2;
                                Double.isNaN(d2);
                                textView2.setTextSize((float) (d2 / 1.5d));
                            }
                            String string = PSharedPreference.getString(MainActivity.this, "LOC", "no");
                            print("Loc = " + string);
                            if (!string.equals("no")) {
                                photoLayoutImg.setImageBitmap(BitmapFactory.decodeFile(string));
                            } else {
                                photoLayoutImg.setImageResource(R.drawable.photodefault);
                            }
                        }

                        @Override 
                        public void onAnimationEnd(Animation animation) {
                            photoLayoutImg.setVisibility(View.VISIBLE);
                            numbers.setText(df_photo.format(counter));
                            photoLayoutImg.clearAnimation();
                            DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: com.mantra.counter.MainActivity.4.1
                                @Override 
                                public void onClick(DialogInterface dialogInterface, int i3) {
                                }
                            };
                            MainActivity mainActivity = MainActivity.this;
                            PDialog.showSimple(mainActivity, onClickListener, mainActivity.getResources().getString(R.string.app_name), getResources().getString(R.string.longClickToImage), getResources().getString(R.string.Yes), "");
                        }
                    });
                    photoLayoutImg.startAnimation(photoDown);
                } else {
                    print("Execue this");
                    photoLayoutImg.setVisibility(View.VISIBLE);
                    numbers.setText(df_photo.format(counter));
                    if (getResources().getConfiguration().orientation == 2) {
                        TextView textView = numbers;
                        double d = i2;
                        Double.isNaN(d);
                        textView.setTextSize((float) (d / 2.5d));
                    } else {
                        print("/1.5");
                        TextView textView2 = numbers;
                        double d2 = i2;
                        Double.isNaN(d2);
                        textView2.setTextSize((float) (d2 / 1.5d));
                    }
                    String string = PSharedPreference.getString(this, "LOC", "no");
                    print("Loc = " + string);
                    if (!string.equals("no")) {
                        if (checkPermission()) {
                            photoLayoutImg.setImageBitmap(BitmapFactory.decodeFile(string));
                        } else {
                            photoLayoutImg.setImageResource(R.drawable.photodefault);
                        }
                    } else {
                        photoLayoutImg.setImageResource(R.drawable.photodefault);
                    }
                }
            } else {
                photoLayoutImg.setVisibility(View.GONE);
                numbers.setText(df_big.format(counter));
                int i3 = i / 40;
                print("Width : " + width + " height : " + height + "  numsize: " + i2 + " / ns2 " + i3);
                if (getResources().getConfiguration().orientation == 2) {
                    TextView textView3 = numbers;
                    double d3 = i2;
                    Double.isNaN(d3);
                    textView3.setTextSize((float) (d3 / 1.95d));
                } else {
                    numbers.setTextSize(i2);
                }
                numbers2.setTextSize(i3);
            }
        } else {
            numbers2.setTextSize((getWidth(this) + getHeight(this)) / 40);
        }
        if (PSharedPreference.getInteger(this, getResources().getStringArray(R.array.ProductID)[0], 0) == 1) {
            Toast.makeText(this, "You are Prime User !", Toast.LENGTH_SHORT).show();
            gifPrime.setVisibility(View.INVISIBLE);
        } else {
            PSharedPreference.setInteger(this, "TAPID", 0);
            graph.setVisibility(View.VISIBLE);
            themebutton.setVisibility(View.VISIBLE);
//            gifPrime.setVisibility(View.VISIBLE);
            fullscreenBtn.setVisibility(View.VISIBLE);
            extfileplay.setVisibility(View.VISIBLE);
        }
        if (PSharedPreference.getBoolean(this, Settings.purchaseItemIDs.get(0), false)) {
            adviewContainer.setVisibility(View.GONE);
        } else {
        }
    }

    @Override 
    protected void onPause() {
        super.onPause();
        StoredataintoDatabase();
        if (isplay) {
            ExtFilePlayer.fileplayer.pause();
            mySeekBarVol.setVisibility(View.GONE);
            whiteframeplayer.setBackgroundColor(0);
            seekbarLayout.setVisibility(View.GONE);
            isplay = false;
            extfileplay.setImageDrawable(getResources().getDrawable(R.drawable.filechose));
        }
    }

    private void StoredataintoDatabase() {
        Database database = new Database(this);
        database.open();
        if (database.isExistMalaonThisDay(PCalendar.getDate() + "-" + PCalendar.getMonth()) == 0) {
            Cursor all = database.getAll();
            if (all != null) {
                if (all.getCount() > PSharedPreference.getInteger(this, "DBROWS", 14)) {
                    database.deleteallGreaterthanNumbers(all.getCount() - PSharedPreference.getInteger(this, "DBROWS", 14));
                    database.insert_data(PCalendar.getDate() + "-" + PCalendar.getMonth(), graph_counter, System.currentTimeMillis() + "");
                } else {
                    database.insert_data(PCalendar.getDate() + "-" + PCalendar.getMonth(), graph_counter, System.currentTimeMillis() + "");
                }
            } else {
                database.insert_data(PCalendar.getDate() + "-" + PCalendar.getMonth(), graph_counter, System.currentTimeMillis() + "");
            }
        } else {
            database.UpdateMala(PCalendar.getDate() + "-" + PCalendar.getMonth() + "", graph_counter);
        }
        database.close();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (PowerSaverMode) {
            if (i == 25) {
                increaseCounter();
                return true;
            } else if (i == 24) {
                increaseCounter();
                return true;
            } else if (i == 4) {
                PDialog.showSimple(this, new DialogInterface.OnClickListener() { // from class: com.mantra.counter.MainActivity.5
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        if (i2 == -1) {
                            finish();
                        }
                    }
                }, "", getResources().getString(R.string.Exit), getResources().getString(R.string.Yes), getResources().getString(R.string.No));
                return true;
            } else {
                return super.onKeyUp(i, keyEvent);
            }
        } else if (i == 4) {
            if (themeLayout.getVisibility() == View.VISIBLE) {
                themeLayout.startAnimation(fadeout);
                return true;
            } else {
                PDialog.showSimple(this, new DialogInterface.OnClickListener() { // from class: com.mantra.counter.MainActivity.6
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        if (i2 == -1) {
                            finish();
                        }
                    }
                }, "", getResources().getString(R.string.Exit), getResources().getString(R.string.Yes), getResources().getString(R.string.No));
                return true;
            }
        } else {
            return super.onKeyUp(i, keyEvent);
        }
    }
    public static void scanFile(Context context, String str) {
        MediaScannerConnection.scanFile(context, new String[]{(Environment.getExternalStorageDirectory().getAbsolutePath() + Const.SAVE_FOLDER_NAME) + str}, new String[]{"*/*"}, new MediaScannerConnection.MediaScannerConnectionClient() { // from class: SaveData.iUtils.1
            @Override 
            public void onMediaScannerConnected() {
            }

            @Override 
            public void onScanCompleted(String str2, Uri uri) {
                Log.d("path: ", str2);
            }
        });
    }

    public static String moveFile(Context context, String str) {
        String str2 = Environment.getExternalStorageDirectory().getAbsolutePath() + Const.SAVE_FOLDER_NAME + Const.ImageName;
        PSharedPreference.setString(context, "LOC", str2);
        Uri fromFile = Uri.fromFile(new File(str2));
        try {
            InputStream openInputStream = context.getContentResolver().openInputStream(Uri.parse(str));
            OutputStream openOutputStream = context.getContentResolver().openOutputStream(fromFile, "w");
            byte[] bArr = new byte[1024];
            while (true) {
                int read = openInputStream.read(bArr);
                if (read > 0) {
                    openOutputStream.write(bArr, 0, read);
                } else {
                    openInputStream.close();
                    openOutputStream.flush();
                    openOutputStream.close();
                    Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
                    intent.setData(fromFile);
                    context.sendBroadcast(intent);
                    scanFile(context, Const.ImageName);
                    return str2;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override 
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        changeColor(getWindow(), Color.parseColor("#ff4141"));
        main_counter = (ImageView) findViewById(R.id.main_counter);
        main_reset = (ImageView) findViewById(R.id.main_reset);
        mainUpper = (ImageView) findViewById(R.id.mainUpper);
        mainLower = (ImageView) findViewById(R.id.mainLower);
        tgt_rounds = (TextView) findViewById(R.id.tgt_numbers);
        target = (TextView) findViewById(R.id.target);
        rel_layout = (RelativeLayout) findViewById(R.id.rel_layout);
        setting = (ImageView) findViewById(R.id.setting);
        fullscreenBtn = (ImageView) findViewById(R.id.statusbar_hide);
        adviewContainer = (LinearLayout) findViewById(R.id.ad_container);
        counterLayout = (LinearLayout) findViewById(R.id.counterLayout);
        numberLayoutforAnimation = (LinearLayout) findViewById(R.id.numberLayoutforAnimation);
        extfileplay = (ImageView) findViewById(R.id.extfileplay);
        photoLayoutImg = (ImageView) findViewById(R.id.photo_layout_img);
        main_counter.setImageResource(R.drawable.black_btn_big);
        main_reset.setImageResource(R.drawable.black_btn_small);
        Background = (RelativeLayout) findViewById(R.id.background);
        ImageView imageView = (ImageView) findViewById(R.id.darkmode);
        darkMode = imageView;
        imageView.setVisibility(View.GONE);
        mySeekBarVol = (SeekBar) findViewById(R.id.mySeekBar);
        seekbarLayout = findViewById(R.id.seekbarLayout);
        whiteframeplayer = (RelativeLayout) findViewById(R.id.whiteframeplayer);
        mySeekBarVol.setVisibility(View.GONE);
        seekbarLayout.setVisibility(View.GONE);
        whiteframeplayer.setBackgroundColor(0);
//        gifPrime = (GifImageView) findViewById(R.id.gifPrimeBtn);
        PowerSaverButton = (RelativeLayout) findViewById(R.id.powersaver_Layout);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        PowerSaverButton.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.MainActivity.7
            @Override
            public void onClick(View view) {
                increaseCounter();
            }
        });

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this, Const.Interstitial, adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                Log.d("TAG", loadAdError.toString());
                mInterstitialAd = null;
            }

            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                mInterstitialAd = interstitialAd;
                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdClicked() {
                        super.onAdClicked();
                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent();
                        mInterstitialAd = null;
                        startActivity(new Intent(MainActivity.this, Settings.class));
                        print("Start Activity");
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                        super.onAdFailedToShowFullScreenContent(adError);
                        mInterstitialAd = null;
                    }

                    @Override
                    public void onAdImpression() {
                        super.onAdImpression();
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        super.onAdShowedFullScreenContent();
                    }
                });
            }
        });


        mySeekBarVol.setMax(100);
        mySeekBarVol.setProgress(100);
        mySeekBarVol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.mantra.counter.MainActivity.8
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override 
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override 
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                float f = i / 100.0f;
                print("Vol=" + f);
                ExtFilePlayer.fileplayer.setVolume(f, f);
            }
        });
        boolean z = PSharedPreference.getBoolean(this, "POWERSAVER", false);
        PowerSaverMode = z;
        if (z) {
            PowerSaverButton.setVisibility(View.VISIBLE);
        } else {
            PowerSaverButton.setVisibility(View.GONE);
        }
        scaleTapIn = AnimationUtils.loadAnimation(this, R.anim.scale_in);
        scaleTapout = AnimationUtils.loadAnimation(this, R.anim.scale_out);
//        gifPrime.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.MainActivity.9
//            @Override
//            public void onClick(View view) {
//            }
//        });
        themebutton = (ImageView) findViewById(R.id.theme);
        graph = (ImageView) findViewById(R.id.graph);
        themeLayout = (LinearLayout) findViewById(R.id.themeLayout);
        themeRed = (ImageView) findViewById(R.id.themered);
        themeBlue = (ImageView) findViewById(R.id.themeblue);
        themeLavender = (ImageView) findViewById(R.id.themelavender);
        themeDark = (ImageView) findViewById(R.id.themedark);
        fadein = AnimationUtils.loadAnimation(this, R.anim.fadein);
        fadeout = AnimationUtils.loadAnimation(this, R.anim.fadeout);
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.dark_mode);
        dark_mode_anim = loadAnimation;
        loadAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.mantra.counter.MainActivity.10
            @Override 
            public void onAnimationStart(Animation animation) {
                darkMode.setVisibility(View.VISIBLE);
            }

            @Override 
            public void onAnimationEnd(Animation animation) {
                darkMode.setVisibility(View.GONE);
            }

            @Override 
            public void onAnimationRepeat(Animation animation) {
                setDark();
            }
        });
        themeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.MainActivity.11
            @Override 
            public void onClick(View view) {
                MainActivity.themeLayout.startAnimation(MainActivity.fadeout);
            }
        });
        themebutton.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.MainActivity.12
            @Override 
            public void onClick(View view) {
                MainActivity.themeLayout.startAnimation(MainActivity.fadein);
            }
        });
        fadein.setAnimationListener(new Animation.AnimationListener() { // from class: com.mantra.counter.MainActivity.13
            @Override 
            public void onAnimationRepeat(Animation animation) {
            }

            @Override 
            public void onAnimationStart(Animation animation) {
            }

            @Override 
            public void onAnimationEnd(Animation animation) {
                MainActivity.themeLayout.setVisibility(View.VISIBLE);
            }
        });
        fadeout.setAnimationListener(new Animation.AnimationListener() { // from class: com.mantra.counter.MainActivity.14
            @Override 
            public void onAnimationRepeat(Animation animation) {
            }

            @Override 
            public void onAnimationStart(Animation animation) {
            }

            @Override 
            public void onAnimationEnd(Animation animation) {
                MainActivity.themeLayout.setVisibility(View.INVISIBLE);
            }
        });
        themeRed.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.MainActivity.15
            @Override 
            public void onClick(View view) {
                themeid = 1;
                MainActivity.themeLayout.startAnimation(MainActivity.fadeout);
                counterLayout.startAnimation(translateDown);
                numberLayoutforAnimation.startAnimation(translateDown);
            }
        });
        themeBlue.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.MainActivity.16
            @Override 
            public void onClick(View view) {
                themeid = 2;
                MainActivity.themeLayout.startAnimation(MainActivity.fadeout);
                counterLayout.startAnimation(translateDown);
                numberLayoutforAnimation.startAnimation(translateDown);
            }
        });
        themeLavender.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.MainActivity.17
            @Override 
            public void onClick(View view) {
                themeid = 3;
                MainActivity mainActivity = MainActivity.this;
                if (PSharedPreference.getInteger(mainActivity, mainActivity.getResources().getStringArray(R.array.ProductID)[0], 0) == 1) {
                    MainActivity.themeLayout.startAnimation(MainActivity.fadeout);
                    counterLayout.startAnimation(translateDown);
                    numberLayoutforAnimation.startAnimation(translateDown);
                    return;
                }
                MainActivity mainActivity2 = MainActivity.this;
                Toast.makeText(mainActivity2, ""+getResources().getString(R.string.notprime), Toast.LENGTH_SHORT).show();

                showAdLoadingDialog();
            }
        });
        themeDark.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.MainActivity.18
            @Override 
            public void onClick(View view) {
                themeid = 4;
                MainActivity mainActivity = MainActivity.this;
                if (PSharedPreference.getInteger(mainActivity, mainActivity.getResources().getStringArray(R.array.ProductID)[0], 0) == 1) {
                    MainActivity.themeLayout.startAnimation(MainActivity.fadeout);
                    darkMode.startAnimation(MainActivity.dark_mode_anim);
                    counterLayout.startAnimation(translateDown);
                    numberLayoutforAnimation.startAnimation(translateDown);
                    return;
                }
                MainActivity mainActivity2 = MainActivity.this;
                Toast.makeText(mainActivity2, ""+getResources().getString(R.string.notprime), Toast.LENGTH_SHORT).show();
                showAdLoadingDialog();
            }
        });
        graph.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.MainActivity.19
            @Override 
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this , Graph.class));

            }
        });
        counterLayout.setOnTouchListener(new View.OnTouchListener() { // from class: com.mantra.counter.MainActivity.20
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int integer = PSharedPreference.getInteger(MainActivity.this, "TAPID", 0);
                if (integer != 0) {
                    if (motionEvent.getAction() == 0) {
                        if (integer == 1) {
                            main_counter.setAlpha(1.0f);
                        }
                    } else if (motionEvent.getAction() == 1) {
                        if (integer == 1) {
                            main_counter.setAlpha(0.0f);
                            increaseCounter();
                        } else if (integer == 2) {
                            if (isShow) {
                                graph.startAnimation(MainActivity.scaleTapout);
                                themebutton.startAnimation(MainActivity.scaleTapout);
                                gifPrime.startAnimation(MainActivity.scaleTapout);
                                fullscreenBtn.startAnimation(MainActivity.scaleTapout);
                                if (!isplay) {
                                    extfileplay.startAnimation(MainActivity.scaleTapout);
                                }
                                isShow = false;
                            } else {
                                graph.startAnimation(MainActivity.scaleTapIn);
                                themebutton.startAnimation(MainActivity.scaleTapIn);
                                gifPrime.startAnimation(MainActivity.scaleTapIn);
                                fullscreenBtn.startAnimation(MainActivity.scaleTapIn);
                                if (!isplay && extfileplay.getVisibility() != View.VISIBLE) {
                                    extfileplay.startAnimation(MainActivity.scaleTapIn);
                                }
                                isShow = true;
                            }
                            print("ISShow = " + isShow);
                        }
                    }
                }
                return true;
            }
        });
        scaleTapIn.setAnimationListener(new Animation.AnimationListener() { // from class: com.mantra.counter.MainActivity.21
            @Override 
            public void onAnimationRepeat(Animation animation) {
            }

            @Override 
            public void onAnimationStart(Animation animation) {
            }

            @Override 
            public void onAnimationEnd(Animation animation) {
                graph.setVisibility(View.VISIBLE);
                themebutton.setVisibility(View.VISIBLE);
                gifPrime.setVisibility(View.VISIBLE);
                fullscreenBtn.setVisibility(View.VISIBLE);
                extfileplay.setVisibility(View.VISIBLE);
            }
        });
        scaleTapout.setAnimationListener(new Animation.AnimationListener() { // from class: com.mantra.counter.MainActivity.22
            @Override 
            public void onAnimationRepeat(Animation animation) {
            }

            @Override 
            public void onAnimationStart(Animation animation) {
            }

            @Override 
            public void onAnimationEnd(Animation animation) {
                graph.setVisibility(View.INVISIBLE);
                themebutton.setVisibility(View.INVISIBLE);
                gifPrime.setVisibility(View.INVISIBLE);
                fullscreenBtn.setVisibility(View.INVISIBLE);
                if (isplay) {
                    return;
                }
                extfileplay.setVisibility(View.INVISIBLE);
            }
        });
        translateDown = AnimationUtils.loadAnimation(this, R.anim.translate_down);
        translateUp = AnimationUtils.loadAnimation(this, R.anim.translate_up);
        translateDown.setAnimationListener(new Animation.AnimationListener() { // from class: com.mantra.counter.MainActivity.23
            @Override 
            public void onAnimationRepeat(Animation animation) {
            }

            @Override 
            public void onAnimationStart(Animation animation) {
            }

            @Override 
            public void onAnimationEnd(Animation animation) {
                counterLayout.startAnimation(translateUp);
                numberLayoutforAnimation.startAnimation(translateUp);
            }
        });
        translateUp.setAnimationListener(new Animation.AnimationListener() { // from class: com.mantra.counter.MainActivity.24
            @Override 
            public void onAnimationEnd(Animation animation) {
            }

            @Override 
            public void onAnimationRepeat(Animation animation) {
            }

            @Override 
            public void onAnimationStart(Animation animation) {
                if (themeid == 1) {
                    setRed();
                } else if (themeid == 2) {
                    setBlue();
                } else if (themeid == 3) {
                    setLavender();
                } else if (themeid == 4) {
                    setDark();
                }
            }
        });
        photoLayoutImg.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.MainActivity.25
            @Override 
            public void onClick(View view) {
                MainActivity mainActivity = MainActivity.this;
                Toast.makeText(mainActivity, getResources().getString(R.string.longClickToImage), Toast.LENGTH_SHORT).show();
            }
        });
        photoLayoutImg.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.mantra.counter.MainActivity.26
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                btnPressed = "PHO";
                if (!checkPermission() || Build.VERSION.SDK_INT < 30) {
                    return true;
                }
              checkFolder();
                Crop.pickImage(MainActivity.this);
                return true;
            }
        });
        if (PSharedPreference.getBoolean(this, "FULLSCREEN", false)) {
            getWindow().addFlags(1024);
        }
        fullscreenBtn.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.MainActivity.27
            @Override 
            public void onClick(View view) {
                if (!isFullscreen) {
                    isFullscreen = true;
                    getWindow().addFlags(1024);
                    fullscreenBtn.setAlpha(0.3f);
                    return;
                }
                isFullscreen = false;
                getWindow().clearFlags(1024);
                fullscreenBtn.setAlpha(1.0f);
            }
        });
        numbers = (TextView) findViewById(R.id.numbers);
        numbers2 = (TextView) findViewById(R.id.numbers2);
        main_counter.setAlpha(0.0f);
        mp1 = new MediaPlayer();
        PSetTypeface.setTextView(this, numbers, "digital.ttf");
        PSetTypeface.setTextView(this, numbers2, "digital.ttf");
        numbers.setText(df_big.format(counter));
        numbers2.setText(df_big.format(counter));
        main_reset.setPivotX(0.0f);
        main_reset.setPivotY(0.0f);
        main_reset.setScaleX(0.45f);
        main_reset.setScaleY(0.45f);
        main_reset.setAlpha(0.0f);
        extfileplay.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.MainActivity.28
            @Override 
            public void onClick(View view) {
                btnPressed = "AUD";
                if (checkPermission()) {
                    if (isplay) {
                        ExtFilePlayer.fileplayer.pause();
                        mySeekBarVol.setVisibility(View.GONE);
                        seekbarLayout.setVisibility(View.GONE);
                        whiteframeplayer.setBackgroundColor(0);
                        isplay = false;
                        extfileplay.setImageDrawable(getResources().getDrawable(R.drawable.filechose));
                        return;
                    }
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.GET_CONTENT");
                    intent.setType("audio/mpeg");
                  startActivityForResult(Intent.createChooser(intent, getString(R.string.select_audio_file_title)), 1);
                }
            }
        });
        if (PRateShareETC.askforRate(this, "Rate", new int[]{2, 5, 10, 15, 30, 80, ChartViewportAnimator.FAST_ANIMATION_DURATION})) {
            PDialog.showSimple(this, this, "", getResources().getString(R.string.rate_5star), getResources().getString(R.string.Yes), getResources().getString(R.string.No));
        } else {
        }
        target.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.MainActivity.29
            @Override 
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage(getResources().getString(R.string.target));
                final EditText editText = new EditText(MainActivity.this);
                editText.setInputType(2);
                editText.setHint(getResources().getString(R.string.notarget_hint));
                editText.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
                builder.setView(editText);
                builder.setPositiveButton(getResources().getString(R.string.Yes), new DialogInterface.OnClickListener() { // from class: com.mantra.counter.MainActivity.29.1
                    @Override 
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (editText.getText().toString().length() > 0) {
                            long parseLong = Long.parseLong(editText.getText().toString());
                            if (parseLong > 0) {
                                if (parseLong > 999999) {
                                    PSharedPreference.setLong(MainActivity.this, "TGT", 999999L);
                                    target.setText("999999");
                                } else {
                                    if (counter >= Long.parseLong(editText.getText().toString().trim())) {
                                        counter = 0L;
                                        PSharedPreference.setLong(MainActivity.this, "NUMBER", 0L);
                                        numbers.setText(df_small.format(counter));
                                        numbers2.setText(df_small.format(counter));
                                        changeProgressBar(progressBar, 0);
                                    }
                                    progressBar.setVisibility(View.VISIBLE);
                                    PSharedPreference.setLong(MainActivity.this, "TGT", Long.parseLong(editText.getText().toString()));
                                    PSharedPreference.setLong(MainActivity.this, "ROUND", 0L);
                                    tgt_rounds.setText(df_target_round.format(PSharedPreference.getLong(MainActivity.this, "ROUND", 0L)));
                                    target.setText(df_target_round.format(PSharedPreference.getLong(MainActivity.this, "TGT", 0L)));
                                }
                                rel_layout.setVisibility(View.VISIBLE);
                                return;
                            }
                            progressBar.setVisibility(View.INVISIBLE);
                            rel_layout.setVisibility(View.GONE);
                            PSharedPreference.setLong(MainActivity.this, "TGT", Long.parseLong(editText.getText().toString()));
                            target.setText(getResources().getString(R.string.target_txt));
                        }
                    }
                });
                builder.setNegativeButton(getResources().getString(R.string.No), new DialogInterface.OnClickListener() { // from class: com.mantra.counter.MainActivity.29.2
                    @Override 
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.create().show();
            }
        });
        tgt_rounds.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.MainActivity.30
            @Override 
            public void onClick(View view) {
                Snackbar.make(main_reset, getResources().getString(R.string.long_press_reset), BaseTransientBottomBar.LENGTH_LONG).show();
            }
        });
        tgt_rounds.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.mantra.counter.MainActivity.31
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                PSharedPreference.setLong(MainActivity.this, "ROUND", 0L);
                tgt_rounds.setText(df_target_round.format(PSharedPreference.getLong(MainActivity.this, "ROUND", 0L)));
                return true;
            }
        });
        setting.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.MainActivity.32
            @Override 
            public void onClick(View view) {

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mInterstitialAd != null) {
                            mInterstitialAd.show(MainActivity.this);
                        } else {
                            startActivity(new Intent(MainActivity.this, Settings.class));
                            print("Start Activity");
                        }
                    }
                },5000);

            }
        });
        main_reset.setOnTouchListener(new View.OnTouchListener() { // from class: com.mantra.counter.MainActivity.33
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    main_reset.setAlpha(1.0f);
                    setVib(60);
                    TimeinMillisecond = System.currentTimeMillis();
                } else if (motionEvent.getAction() == 1) {
                    main_reset.setAlpha(0.0f);
                    if (System.currentTimeMillis() - TimeinMillisecond >= 1000) {
                        MainActivity mainActivity = MainActivity.this;
                        mainActivity.temp_count = mainActivity.counter;
                        Snackbar.make(main_reset, getResources().getString(R.string.reset_complete), BaseTransientBottomBar.LENGTH_LONG).setAction("UNDO", new View.OnClickListener() { // from class: com.mantra.counter.MainActivity.33.1
                            @Override 
                            public void onClick(View view2) {
                                Snackbar.make(main_reset, getResources().getString(R.string.undo_success), BaseTransientBottomBar.LENGTH_INDEFINITE).show();
                                counter = temp_count;
                                numbers.setText(df_big.format(counter));
                                numbers2.setText(df_big.format(counter));
                                PSharedPreference.setLong(MainActivity.this, "NUMBER", counter);
                            }
                        }).show();
                        counter = 0L;
                        PSharedPreference.setLong(MainActivity.this, "NUMBER", 0L);
                        numbers.setText(df_big.format(counter));
                        numbers2.setText(df_big.format(counter));
                        setVib(500);
                        MainActivity.adCounter = 0L;
                    } else {
                        Snackbar.make(main_reset, getResources().getString(R.string.long_press_reset), BaseTransientBottomBar.LENGTH_LONG).show();
                    }
                }
                return true;
            }
        });
        main_counter.setOnTouchListener(new View.OnTouchListener() { // from class: com.mantra.counter.MainActivity.34
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    main_counter.setAlpha(1.0f);
                } else if (motionEvent.getAction() == 1) {
                    main_counter.setAlpha(0.0f);
                    increaseCounter();
                }
                return true;
            }
        });
    }

   
    public void increaseCounter() {
        long j = counter + 1;
        counter = j;
        graph_counter++;
        if (!isPhotomode) {
            numbers.setText(df_big.format(j));
        } else {
            numbers.setText(df_photo.format(j));
        }
        numbers2.setText(df_big.format(counter));
        setVib(80);
        playTik();
        long j2 = adCounter;
        if (j2 == 3) {
            print("Loading with Ad Counter " + adCounter);
            adCounter = 20L;
        } else {
            adCounter = j2 + 1;
        }
        long j3 = PSharedPreference.getLong(this, "TGT", 0L);
        if (0 != j3) {
            changeProgressBar(progressBar, (int) ((counter * 100) / j3));
        }
        if (counter == PSharedPreference.getLong(this, "TGT", 0L)) {
            changeProgressBar(progressBar, 100);
            PSharedPreference.setLong(this, "ROUND", PSharedPreference.getLong(this, "ROUND", 0L) + 1);
            tgt_rounds.setText(df_target_round.format(PSharedPreference.getLong(this, "ROUND", 0L)));
            counter = 0L;
            PSharedPreference.setLong(this, "NUMBER", 0L);
            if (isPhotomode) {
                numbers.setText(df_photo.format(counter));
            } else {
                numbers.setText(df_big.format(counter));
            }
            numbers2.setText(df_big.format(counter));
            ((Vibrator) getSystemService(Context.VIBRATOR_SERVICE)).vibrate(600L);
            CustomDialogClass customDialogClass = new CustomDialogClass(this);
            customDialogClass.setCancelable(false);
            customDialogClass.show();
            if (isSound) {
                MediaPlayer create = MediaPlayer.create(this, (int) R.raw.target);
                mp2 = create;
                create.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.mantra.counter.MainActivity.35
                    @Override // android.media.MediaPlayer.OnCompletionListener
                    public void onCompletion(MediaPlayer mediaPlayer) {
                    }
                });
                mp2.start();
            }
        }
    }

   
    public void changeProgressBar(ProgressBar progressBar, int i) {
        ProgressbarAnimation progressbarAnimation = new ProgressbarAnimation(progressBar, progressBar.getProgress(), i);
        progressbarAnimation.setDuration(150L);
        progressBar.startAnimation(progressbarAnimation);
    }

    public boolean checkPermission() {
        if (Build.VERSION.SDK_INT < 23 || ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            return true;
        }
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle(R.string.permission_title);
            builder.setMessage(R.string.permission_msg);
            builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() { // from class: com.mantra.counter.MainActivity.36
                @Override 
                public void onClick(DialogInterface dialogInterface, int i) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 101);
                }
            });
            builder.create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 101);
        }
        return false;
    }

    @Override 
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 101 && iArr.length > 0 && iArr[0] == 0) {
            if (btnPressed.equals("AUD")) {
                extfileplay.performClick();
            } else if (btnPressed.equals("PHO")) {
                photoLayoutImg.performLongClick();
            }
        }
    }

    public void setVib(int i) {
        if (isvib) {
            ((Vibrator) getSystemService(Context.VIBRATOR_SERVICE)).vibrate(i);
        }
    }

    public void setRed() {
        mainUpper.setImageResource(R.drawable.red_upper);
        mainLower.setImageResource(R.drawable.red_lower);
        PSharedPreference.setInteger(this, "THEMES", 1);
        changeColor(getWindow(), Color.parseColor("#ff4141"));
        Background.setBackgroundResource(R.drawable.wall);
        numbers.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        DARK = false;
        if (Build.VERSION.SDK_INT >= 21) {
            progressBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#e60b37")));
        }
    }

    public void setLavender() {
        mainUpper.setImageResource(R.drawable.lavender_upper);
        mainLower.setImageResource(R.drawable.lavender_lower);
        PSharedPreference.setInteger(this, "THEMES", 3);
       changeColor(getWindow(), Color.parseColor("#54085f"));
        Background.setBackgroundResource(R.drawable.wall);
        numbers.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        DARK = false;
        if (Build.VERSION.SDK_INT >= 21) {
            progressBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#b304cc")));
        }
    }

    public void setBlue() {
        mainUpper.setImageResource(R.drawable.blue_upper);
        mainLower.setImageResource(R.drawable.blue_lower);
        PSharedPreference.setInteger(this, "THEMES", 2);
       changeColor(getWindow(), -16776961);
        Background.setBackgroundResource(R.drawable.wall);
        numbers.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        DARK = false;
        if (Build.VERSION.SDK_INT >= 21) {
            progressBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#129ce0")));
        }
    }

    public void setDark() {
        mainUpper.setImageResource(R.drawable.black_upper);
        mainLower.setImageResource(R.drawable.black_lower);
        PSharedPreference.setInteger(this, "THEMES", 4);
        changeColor(getWindow(), ViewCompat.MEASURED_STATE_MASK);
        Background.setBackgroundResource(R.drawable.wall_dark);
        numbers.setTextColor(-1);
        DARK = true;
        if (Build.VERSION.SDK_INT >= 21) {
            progressBar.setProgressTintList(ColorStateList.valueOf(-3355444));
        }
    }

    public void playTik() {
        if (isSound) {
            new mytask().execute(new Void[0]);
        }
    }

    @Override 
    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == -1) {
            PRateShareETC.rate(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class mytask extends AsyncTask<Void, Void, Void> {
        AssetFileDescriptor afd;

        mytask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            String string = PSharedPreference.getString(MainActivity.this, "Mantra", "Default");
            if (!string.equals("Default")) {
                try {
                    mp1.reset();
                    MediaPlayer mediaPlayer = mp1;
                    mediaPlayer.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath() + Const.SAVE_FOLDER_NAME + string);
                    mp1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.mantra.counter.MainActivity.mytask.1
                        @Override // android.media.MediaPlayer.OnCompletionListener
                        public void onCompletion(MediaPlayer mediaPlayer2) {
                        }
                    });
                    mp1.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mp1.start();
                return null;
            }
            try {
                mp1.reset();
                afd = getAssets().openFd("tik.mp3");
                mp1.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                mp1.prepare();
                mp1.start();
                return null;
            } catch (IOException e2) {
                e2.printStackTrace();
                return null;
            }
        }
    }

    /* loaded from: classes.dex */
    public class CustomDialogClass extends Dialog implements View.OnClickListener {
        RelativeLayout Dialog_bg;
        public Activity c;
        AnimationDrawable congoD;
        ImageView congoGif;
        public Dialog d;
        public TextView yes;

        public CustomDialogClass(Activity activity) {
            super(activity);
            c = activity;
        }

        @Override // android.app.Dialog
        protected void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            requestWindowFeature(1);
            setContentView(R.layout.congo_dialog);
            Dialog_bg = (RelativeLayout) findViewById(R.id.dialog_bg);
            TextView textView = (TextView) findViewById(R.id.btn_yes);
            yes = textView;
            textView.setOnClickListener(this);
            congoGif = (ImageView) findViewById(R.id.congogif);
            if (MainActivity.DARK) {
                Dialog_bg.setBackgroundColor(getContext().getResources().getColor(R.color.darkbgcolor));
            } else {
                Dialog_bg.setBackgroundColor(getContext().getResources().getColor(R.color.whitecolor));
            }
            congoGif.setBackgroundResource(R.drawable.congo_animation);
            AnimationDrawable animationDrawable = (AnimationDrawable) congoGif.getBackground();
            congoD = animationDrawable;
            animationDrawable.start();
        }

        @Override 
        public void onClick(View view) {
            if (view.getId() == R.id.btn_yes) {
                print("Yes Clicked");
                print("Setting Counter =0");
                MainActivity.adCounter = 0L;
                MainActivity mainActivity = MainActivity.this;
                mainActivity.changeProgressBar(mainActivity.progressBar, 0);
            }
            dismiss();
        }
    }

    public Bitmap blur(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap);
        RenderScript create = RenderScript.create(this);
        Allocation createFromBitmap = Allocation.createFromBitmap(create, bitmap);
        Allocation createFromBitmap2 = Allocation.createFromBitmap(create, createBitmap);
        ScriptIntrinsicBlur create2 = ScriptIntrinsicBlur.create(create, Element.U8_4(create));
        create2.setRadius(BLUR_RADIUS);
        create2.setInput(createFromBitmap);
        create2.forEach(createFromBitmap2);
        createFromBitmap2.copyTo(createBitmap);
        return createBitmap;
    }

    public static void print(String str) {
        Log.e("log" + Counter, str);
        Counter = Counter + 1;
    }

    public static void changeColor(Window window, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            window.setStatusBarColor(i);
            return;
        }
        window.addFlags(67108864);
        window.addFlags(134217728);
    }

    public static int getHeight(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static int getWidth(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static void checkFolder() {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + Const.SAVE_FOLDER_NAME);
        boolean exists = file.exists();
        if (!exists) {
            exists = file.mkdir();
        }
        if (exists) {
          print("FolderAlready Created");
        } else {
           print("Error");
        }
    }
}
