package com.mantra.counter;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.mantra.counter.R;

import java.util.ArrayList;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;


/* loaded from: classes.dex */
public class Graph extends Activity {
    LinearLayout Graph_bg;
    LineChartView chart1;
    LineChartView chart2;
    Button inapp;
    ListView listview;
    ImageView savePdf;
    GraphPojo[] tenpj;
    Animation translateDownGraph;
    Animation translateList;
    ArrayList<String> arrayList1 = new ArrayList<>();
    ArrayList<String> arrayList2 = new ArrayList<>();
    ArrayList<GraphPojo> listviewArrayList = new ArrayList<>();

    public static String getMonthName(int i) {
        switch (i) {
            case 1:
                return "Jan";
            case 2:
                return "Feb";
            case 3:
                return "Mar";
            case 4:
                return "Apr";
            case 5:
                return "May";
            case 6:
                return "Jun";
            case 7:
                return "Jul";
            case 8:
                return "Aug";
            case 9:
                return "Sep";
            case 10:
                return "Oct";
            case 11:
                return "Nov";
            case 12:
                return "Dec";
            default:
                return "";
        }
    }

    @Override 
    public void onDestroy() {
        super.onDestroy();
    }

    private void updateTextView() {
        PSharedPreference.setInteger(this, "DBROWS", 14);
    }

    @Override 
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override 
    protected void onResume() {
        super.onResume();
        updateTextView();
    }

    @SuppressLint("ResourceType")
    @Override 
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
       changeColor(getWindow(), getResources().getColor(R.color.colorAccent));
        setContentView(R.layout.activity_graph);
        this.inapp = (Button) findViewById(R.id.inapp);
        this.Graph_bg = (LinearLayout) findViewById(R.id.graph_bg);
        this.chart1 = (LineChartView) findViewById(R.id.linechart);
        this.chart2 = (LineChartView) findViewById(R.id.dailylinechart);
        this.listview = (ListView) findViewById(R.id.listviewofGraph);
        this.savePdf = (ImageView) findViewById(R.id.savePdf);
        if (MainActivity.DARK) {
            this.Graph_bg.setBackgroundColor(getResources().getColor(R.color.darkbgcolor));
        } else {
            this.Graph_bg.setBackgroundColor(getResources().getColor(R.color.whitecolor));
        }
        this.translateDownGraph = AnimationUtils.loadAnimation(this, R.anim.photo_up);
        this.translateList = AnimationUtils.loadAnimation(this, R.anim.photo_down);
        this.savePdf.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.Graph.1
            @Override 
            public void onClick(View view) {
                Graph.this.chart1.setDrawingCacheEnabled(true);
                Graph.this.chart2.setDrawingCacheEnabled(true);
                Graph.this.chart1.buildDrawingCache();
                Graph.this.chart2.buildDrawingCache();
                Bitmap createBitmap = Bitmap.createBitmap(Graph.this.chart1.getDrawingCache());
                Bitmap createBitmap2 = Bitmap.createBitmap(Graph.this.chart2.getDrawingCache());
                Graph graph = Graph.this;
                PDFClass.Generate(graph, createBitmap, createBitmap2, graph.listviewArrayList, Graph.this.savePdf);
            }
        });
        this.translateList.setAnimationListener(new Animation.AnimationListener() { // from class: com.mantra.counter.Graph.2
            @Override 
            public void onAnimationRepeat(Animation animation) {
            }

            @Override 
            public void onAnimationStart(Animation animation) {
            }

            @Override 
            public void onAnimationEnd(Animation animation) {
                Graph.this.listview.setVisibility(0);
                Graph.this.listview.clearAnimation();
            }
        });
        this.translateDownGraph.setAnimationListener(new Animation.AnimationListener() { // from class: com.mantra.counter.Graph.3
            @Override 
            public void onAnimationRepeat(Animation animation) {
            }

            @Override 
            public void onAnimationStart(Animation animation) {
            }

            @Override 
            public void onAnimationEnd(Animation animation) {
                Graph.this.chart1.setVisibility(0);
                Graph.this.chart1.clearAnimation();
            }
        });
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        this.arrayList1.add("0");
        this.arrayList2.add("0");
        arrayList.add(new PointValue(0.0f, 0.0f));
        arrayList2.add(new PointValue(0.0f, 0.0f));
        Database database = new Database(this);
        database.open();
        Cursor all = database.getAll();
        if (all != null && all.moveToFirst()) {
            GraphPojo[] graphPojoArr = new GraphPojo[all.getCount() + 1];
            this.tenpj = graphPojoArr;
            graphPojoArr[0] = new GraphPojo();
            this.tenpj[0].counts = getResources().getString(R.string.total);
            this.tenpj[0].dailycounts = getResources().getString(R.string.app_name);
            this.tenpj[0].date = getResources().getString(R.string.dt);
            this.listviewArrayList.add(this.tenpj[0]);
            int i = 1;
            while (true) {
                Log.e("Database = ", all.getString(0) + " - " + all.getString(1));
                String string = all.getString(0);
                int parseInt = Integer.parseInt(string.substring(string.indexOf("-") + 1));
                this.tenpj[i] = new GraphPojo();
                this.tenpj[i].counts = all.getString(1);
                if (i > 1) {
                    GraphPojo graphPojo = this.tenpj[i];
                    graphPojo.dailycounts = "" + (Integer.parseInt(this.tenpj[i].counts) - Integer.parseInt(this.tenpj[i - 1].counts));
                } else if (i == 1) {
                    GraphPojo[] graphPojoArr2 = this.tenpj;
                    graphPojoArr2[i].dailycounts = graphPojoArr2[i].counts;
                }
                GraphPojo graphPojo2 = this.tenpj[i];
                graphPojo2.date = string.substring(0, string.indexOf("-")) + " - " + getMonthName(parseInt);
                this.arrayList1.add(all.getString(0));
                this.arrayList2.add(all.getString(0));
                float f = (float) i;
                arrayList2.add(new PointValue(f, (float) Integer.parseInt(this.tenpj[i].dailycounts)));
                arrayList.add(new PointValue(f, Integer.parseInt(all.getString(1))));
                this.listviewArrayList.add(this.tenpj[i]);
                i++;
                if (!all.moveToNext()) {
                    break;
                }
            }
        }
        database.close();
        all.close();
        Line cubic = new Line(arrayList2).setColor(Color.parseColor("#dd4b48")).setCubic(false);
        Line cubic2 = new Line(arrayList).setColor(Color.parseColor("#00b4ff")).setCubic(false);
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(cubic);
        cubic.setFilled(true);
        cubic.setHasLabels(true);
        ArrayList arrayList4 = new ArrayList();
        arrayList4.add(cubic2);
        cubic2.setFilled(true);
        cubic2.setHasLabels(true);
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        float f2 = 1.0f;
        for (int i2 = 1; i2 < this.arrayList1.size(); i2++) {
            arrayList5.add(new AxisValue(f2).setLabel(this.arrayList1.get(i2)));
            f2 += 1.0f;
        }
        float f3 = 1.0f;
        for (int i3 = 1; i3 < this.arrayList2.size(); i3++) {
            arrayList6.add(new AxisValue(f3).setLabel(this.arrayList2.get(i3)));
            f3 += 1.0f;
        }
        Axis axis = new Axis(arrayList6);
        Axis hasLines = new Axis().setHasLines(true);
        axis.setName(getResources().getString(R.string.dt));
        hasLines.setName(getResources().getString(R.string.app_name));
        axis.setTextColor(Color.parseColor("#616196"));
        axis.setTextSize(15);
        hasLines.setTextColor(Color.parseColor("#dd4b48"));
        hasLines.setTextSize(15);
        axis.setInside(false);
        LineChartData lineChartData = new LineChartData();
        lineChartData.setLines(arrayList3);
        lineChartData.setAxisXBottom(axis);
        lineChartData.setAxisYLeft(hasLines);
        lineChartData.setBaseValue(-2.1474836E9f);
        this.chart1.setLineChartData(lineChartData);
        Axis axis2 = new Axis(arrayList5);
        Axis hasLines2 = new Axis().setHasLines(true);
        axis2.setName(getResources().getString(R.string.dt));
        hasLines2.setName(getResources().getString(R.string.total));
        axis2.setTextColor(Color.parseColor("#616196"));
        axis2.setTextSize(15);
        hasLines2.setTextColor(Color.parseColor("#00b4ff"));
        hasLines2.setTextSize(15);
        axis2.setInside(false);
        LineChartData lineChartData2 = new LineChartData();
        lineChartData2.setLines(arrayList4);
        lineChartData2.setAxisXBottom(axis2);
        lineChartData2.setAxisYLeft(hasLines2);
        lineChartData2.setBaseValue(-2.1474836E9f);
        this.chart2.setLineChartData(lineChartData2);
        this.listview.setAdapter((ListAdapter) new GraphAdapter(this.listviewArrayList, this));
        this.listview.setSelector(17170432);
        this.chart1.setVisibility(View.INVISIBLE);
        this.listview.setVisibility(View.INVISIBLE);
        this.chart1.startAnimation(this.translateDownGraph);
        this.listview.startAnimation(this.translateList);
        this.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.mantra.counter.Graph.4
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i4, long j) {
            }
        });
        this.inapp.setOnClickListener(new View.OnClickListener() { // from class: com.mantra.counter.Graph.5
            @Override 
            public void onClick(View view) {
                PDialog.showSimple(Graph.this, new DialogInterface.OnClickListener() { // from class: com.mantra.counter.Graph.5.1
                    @Override 
                    public void onClick(DialogInterface dialogInterface, int i4) {
                    }
                }, "", "This feature coming soon", "Ok", "");
            }
        });
    }

    public static void changeColor(Window window, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            window.setStatusBarColor(i);
            return;
        }
        window.addFlags(67108864);
        window.addFlags(134217728);
    }
}
