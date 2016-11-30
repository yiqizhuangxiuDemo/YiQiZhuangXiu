package com.bwf.yiqizhuangxiu.gui.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.EffectBeatifulData;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/29 0029.
 */

public class EffectBeatifulAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<EffectBeatifulData.DataBean.ListBean> datas;

    public EffectBeatifulAdapter(Context context) {
        this.datas = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    public void addData(List<EffectBeatifulData.DataBean.ListBean> datas) {
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public EffectBeatifulData.DataBean.ListBean getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.beatiful_item_gridview, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        EffectBeatifulData.DataBean.ListBean listBean = datas.get(position);
        holder.gridviewBeatiful.setImageURI(Uri.parse(listBean.getUrl()));
        holder.beatifulTextConment.setText(listBean.getCollected()+"");
        holder.beatifulTextLike.setText(listBean.getLike_num()+"");
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.gridview_beatiful)
        SimpleDraweeView gridviewBeatiful;
        @Bind(R.id.beatiful_text_conment)
        TextView beatifulTextConment;
        @Bind(R.id.beatiful_text_like)
        TextView beatifulTextLike;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
