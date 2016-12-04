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
 * Created by Administrator on 2016/12/2 0002.
 */

public class SchoolPopupwindowGridViewAdatper extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    private List<String> datas;
    private int flag;

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public SchoolPopupwindowGridViewAdatper(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        datas = new ArrayList<>();
    }

    public void setDatas(List<String> datas) {
        this.datas = datas;
        notifyDataSetChanged();
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
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.school_popuwindow_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textSelector.setText(datas.get(position));
        if (position==flag){
            holder.textSelector.setChecked(true);
        }else {
            holder.textSelector.setChecked(false);
        }
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.text_selector)
        CheckedTextView textSelector;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
