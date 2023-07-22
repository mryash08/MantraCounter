package com.mantra.counter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.mantra.counter.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class RecordList extends AppCompatActivity {
    public static final int RequestPermissionCode = 1;
    TextView Numbers;
    static int Counter;
    Animation blinking;
    ImageView button_Rec;
    MediaRecorder mediaRecorder;
    ListView recordList;
    RelativeLayout splashLayout;
    int counter = 3;
    ArrayList<String> recordings = new ArrayList<>();
    String AudioSavePathInDevice = null;
    boolean isRec = false;
    String Path = Environment.getExternalStorageDirectory().getAbsolutePath() + Const.SAVE_FOLDER_NAME;

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_record_list);
        this.button_Rec = (ImageView) findViewById(R.id.rec);
        this.recordList = (ListView) findViewById(R.id.recordlist);
        this.Numbers = (TextView) findViewById(R.id.numbers);
        this.splashLayout = (RelativeLayout) findViewById(R.id.splash);
        this.blinking = AnimationUtils.loadAnimation(this, R.anim.blink);
        this.splashLayout.setVisibility(View.INVISIBLE);
        updateListView();
        this.button_Rec.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.RecordList.1
            /* JADX WARN: Type inference failed for: r8v17, types: [com.mantra.counter.RecordList$1$1] */
            @Override 
            public void onClick(View view) {
                long currentTimeMillis = System.currentTimeMillis();
                if (!RecordList.this.isRec) {
                    if (!RecordList.this.checkPermission()) {
                        RecordList.this.requestPermission();
                        return;
                    }
                    RecordList recordList = RecordList.this;
                    recordList.AudioSavePathInDevice = RecordList.this.Path + "/Mantra_ " + currentTimeMillis + ".3gp ";
                    RecordList.this.MediaRecorderReady();
                    RecordList.this.counter = 3;
                    new CountDownTimer(3000L, 1000L) { // from class: com.mantra.counter.RecordList.1.1
                        @Override // android.os.CountDownTimer
                        public void onTick(long j) {
                            RecordList.this.splashLayout.setVisibility(View.VISIBLE);
                            TextView textView = RecordList.this.Numbers;
                            textView.setText(RecordList.this.counter + "");
                            RecordList recordList2 = RecordList.this;
                            recordList2.counter = recordList2.counter + (-1);
                            int i = RecordList.this.counter;
                        }

                        @Override // android.os.CountDownTimer
                        public void onFinish() {
                            RecordList.this.button_Rec.setImageResource(R.drawable.stop_img);
                            RecordList.this.isRec = true;
                            Toast.makeText(RecordList.this, "Recording started", Toast.LENGTH_LONG).show();
                            RecordList.this.splashLayout.setVisibility(View.INVISIBLE);
                            try {
                                print("Rec Started");
                                RecordList.this.mediaRecorder.prepare();
                                RecordList.this.mediaRecorder.start();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (IllegalStateException e2) {
                                e2.printStackTrace();
                            }
                        }
                    }.start();
                    return;
                }
                RecordList.this.mediaRecorder.stop();
                RecordList recordList2 = RecordList.this;
                Toast.makeText(recordList2, "Mantra_ " + currentTimeMillis + ".3gp ", Toast.LENGTH_LONG).show();
                RecordList.this.button_Rec.setImageResource(R.drawable.rec_img);
                RecordList.this.isRec = false;
                RecordList.this.updateListView();
            }
        });
        this.recordList.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.mantra.counter.RecordList.2
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                RecordList recordList = RecordList.this;
                recordList.showOptionDialog(Environment.getExternalStorageDirectory().getAbsolutePath() + Const.SAVE_FOLDER_NAME + RecordList.this.recordings.get(i));
                StringBuilder sb = new StringBuilder();
                sb.append("Saved ");
                sb.append(RecordList.this.recordings.get(i));
               print(sb.toString());
            }
        });
    }

   
    public void showOptionDialog(String str) {
        new CustomDialogClass(this, str).show();
    }

   
    public void updateListView() {
        File file = new File(this.Path + "/");
        this.recordings.clear();
        this.recordings.add("Default");
        if (file.exists()) {
           print("Here");
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (int i = 0; i < listFiles.length; i++) {
                    if (listFiles[i].getName() != null && listFiles[i].getName().contains(".3gp")) {
                        this.recordings.add(listFiles[i].getName());
                    }
                }
            }
           print("Here");
           print("Here");
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, (int) R.layout.text_view_rec, this.recordings);
           print("Here");
           print("Here");
            this.recordList.setAdapter((ListAdapter) arrayAdapter);
           print("Here");
           print("Here");
        }
    }

    public void MediaRecorderReady() {
        MediaRecorder mediaRecorder = new MediaRecorder();
        this.mediaRecorder = mediaRecorder;
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.VOICE_RECOGNITION);
        this.mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        this.mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        this.mediaRecorder.setAudioEncodingBitRate(128000);
        this.mediaRecorder.setAudioSamplingRate(16000);
        this.mediaRecorder.setOutputFile(this.AudioSavePathInDevice);
    }

   
    public void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO"}, 1);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 1 && iArr.length > 0) {
            boolean z = iArr[0] == 0;
            boolean z2 = iArr[1] == 0;
            if (z && z2) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "PermissionDenied", Toast.LENGTH_LONG).show();
            }
        }
    }

    public boolean checkPermission() {
        return ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0 && ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.RECORD_AUDIO") == 0;
    }

    /* loaded from: classes.dex */
    public class CustomDialogClass extends Dialog {
        public Activity c;
        public Dialog d;
        public Button delete;
        String filename;
        MediaPlayer mp;
        public Button play;
        public Button rename;
        public Button set;
        TextView txName;

        public CustomDialogClass(Activity activity, String str) {
            super(activity);
            this.c = activity;
            this.filename = str;
        }

        @Override // android.app.Dialog
        protected void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            requestWindowFeature(1);
            setContentView(R.layout.rec_list_dialog);
            this.txName = (TextView) findViewById(R.id.txt_name);
            this.set = (Button) findViewById(R.id.set);
            this.play = (Button) findViewById(R.id.play);
            this.rename = (Button) findViewById(R.id.rename);
            this.delete = (Button) findViewById(R.id.delete);
            String str = this.filename;
            final String substring = str.substring(str.lastIndexOf("/") + 1);
            this.txName.setText(substring);
            if (substring.equals("Default")) {
                this.play.setVisibility(View.GONE);
                this.rename.setVisibility(View.GONE);
                this.delete.setVisibility(View.GONE);
            }
            this.set.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.RecordList.CustomDialogClass.1
                @Override 
                public void onClick(View view) {
                    PSharedPreference.setString(RecordList.this, "Mantra", substring);
                    RecordList recordList = RecordList.this;
                    Toast.makeText(recordList, getResources().getString(R.string.set) + " : " + substring, Toast.LENGTH_SHORT).show();
                    CustomDialogClass.this.dismiss();
                }
            });
            this.play.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.RecordList.CustomDialogClass.2
                @Override 
                public void onClick(View view) {
                   print(substring);
                    CustomDialogClass.this.mp = new MediaPlayer();
                    try {
                        CustomDialogClass.this.mp.setDataSource(CustomDialogClass.this.filename);
                        CustomDialogClass.this.mp.prepare();
                        CustomDialogClass.this.mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.mantra.counter.RecordList.CustomDialogClass.2.1
                            @Override // android.media.MediaPlayer.OnCompletionListener
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                CustomDialogClass.this.txName.clearAnimation();
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    CustomDialogClass.this.mp.start();
                    CustomDialogClass.this.txName.startAnimation(RecordList.this.blinking);
                }
            });
            this.rename.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.RecordList.CustomDialogClass.3
                @Override 
                public void onClick(View view) {
                    CustomDialogClass.this.dismiss();
                    CustomDialogClass.this.renameDialog(substring);
                }
            });
            this.delete.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.RecordList.CustomDialogClass.4
                @Override 
                public void onClick(View view) {
                    File file = new File(CustomDialogClass.this.filename);
                    if (file.exists() && file.delete()) {
                        if (CustomDialogClass.this.mp != null && CustomDialogClass.this.mp.isPlaying()) {
                            CustomDialogClass.this.mp.stop();
                        }
                        RecordList recordList = RecordList.this;
                        Toast.makeText(recordList, getResources().getString(R.string.delete) + "...!", Toast.LENGTH_SHORT).show();
                        CustomDialogClass.this.dismiss();
                        RecordList.this.updateListView();
                    }
                }
            });
        }

       
        public void renameDialog(String str) {
            View inflate = LayoutInflater.from(RecordList.this).inflate(R.layout.alert_dialog, (ViewGroup) null);
            AlertDialog.Builder builder = new AlertDialog.Builder(RecordList.this);
            builder.setView(inflate);
            final EditText editText = (EditText) inflate.findViewById(R.id.etUserInput);
            editText.setHint(str.substring(0, str.lastIndexOf(".")));
            builder.setCancelable(false).setPositiveButton(RecordList.this.getResources().getString(R.string.Yes), new OnClickListener() { // from class: com.mantra.counter.RecordList.CustomDialogClass.6
                @Override 
                public void onClick(DialogInterface dialogInterface, int i) {
                    RecordList recordList = RecordList.this;

                    Toast.makeText(recordList, ""+getResources().getString(R.string.rename) + " : " + editText.getText().toString().trim(), Toast.LENGTH_SHORT).show();
                    String substring = CustomDialogClass.this.filename.substring(0, CustomDialogClass.this.filename.lastIndexOf("/") + 1);
                    File file = new File(CustomDialogClass.this.filename);
                    if (file.renameTo(new File(substring + editText.getText().toString().trim() + ".3gp"))) {
                        RecordList.this.updateListView();
                    } else {
                       print("Failed to rename file");
                    }
                }
            }).setNegativeButton(RecordList.this.getResources().getString(R.string.No), new DialogInterface.OnClickListener() { // from class: com.mantra.counter.RecordList.CustomDialogClass.5
                @Override 
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            builder.create().show();
        }
    }


    public static void print(String str) {
        Log.e("log" + Counter, str);
        Counter = Counter + 1;
    }
}
