package com.bwf.yiqizhuangxiu.gui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;

import com.bwf.yiqizhuangxiu.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/29 0029.
 */

public class popwindowGridViewAdapter extends BaseAdapter {
    private List<String> datas;
    private LayoutInflater inflater;

    public popwindowGridViewAdapter(Context context) {
        this.datas = new ArrayList<>();
        this.inflater = LayoutInflater.from(context);
        for (int i = 0; i < 20; i++) {
            datas.add("主题" + i);
        }
    }


    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public String getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder  holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.popwindow_beautiful_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.titlePopwindow.setText(datas.get(position));
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.title_popwindow)
        CheckedTextView titlePopwindow;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
