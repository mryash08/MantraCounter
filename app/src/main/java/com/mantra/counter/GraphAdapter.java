package com.mantra.counter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mantra.counter.R;

import java.util.ArrayList;

/* loaded from: classes.dex */
public class GraphAdapter extends BaseAdapter {
    Context _c;
    private ArrayList<GraphPojo> _data;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public GraphAdapter(ArrayList<GraphPojo> arrayList, Context context) {
        this._data = arrayList;
        this._c = context;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this._data.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this._data.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = ((LayoutInflater) this._c.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.graph_list_item, (ViewGroup) null);
        }
        TextView textView = (TextView) view.findViewById(R.id.dateicon);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.malaTableRow);
        GraphPojo graphPojo = this._data.get(i);
        ((TextView) view.findViewById(R.id.date)).setText("" + graphPojo.date);
        ((TextView) view.findViewById(R.id.No_of_mala)).setText("" + graphPojo.counts);
        ((TextView) view.findViewById(R.id.total)).setText("" + graphPojo.dailycounts);
        textView.setText("A");
        PSetTypeface.setTextView(this._c, textView, "graph.ttf");
        return view;
    }
}
