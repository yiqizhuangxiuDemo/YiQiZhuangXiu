package com.bwf.yiqizhuangxiu.gui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bwf.yiqizhuangxiu.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/29 0029.
 */

public class popwindowGridViewAdapter extends BaseAdapter {
    private List<String> datas;
    private LayoutInflater inflater;
    private Context context;

    public popwindowGridViewAdapter(Context context,List<String> datas) {
        this.datas = datas;
        this.context =context;
        this.inflater = LayoutInflater.from(context);
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
        final ViewHolder  holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.popwindow_beautiful_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.titlePopwindow.setText(datas.get(position));
        holder.titlePopwindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.titlePopwindow.setChecked(true);
                holder.liear_contait.setBackgroundColor(0xff00ff00);
                Toast.makeText(context, "holder.titlePopwindow.getText():" + holder.titlePopwindow.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.title_popwindow)
        CheckedTextView titlePopwindow;
        @Bind(R.id.linear_contait)
        LinearLayout liear_contait;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
