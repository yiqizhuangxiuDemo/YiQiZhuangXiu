package com.bwf.yiqizhuangxiu.gui.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.SchoolConmentData;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/12/1 0001.
 */

public class SchoolComentListAdatper extends BaseAdapter {
    private List<SchoolConmentData.DataBean.ListBean> data;
    private LayoutInflater inflater;
    private Context context;

    public SchoolComentListAdatper(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        data = new ArrayList<>();
    }

    public void addData(List<SchoolConmentData.DataBean.ListBean> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }
    public void notfyDataSetChanged(List<SchoolConmentData.DataBean.ListBean> data){
        this.data.removeAll(this.data);
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public SchoolConmentData.DataBean.ListBean getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.school_contnet_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        SchoolConmentData.DataBean.ListBean listBean = data.get(position);
        if (null == listBean) {
            View view = inflater.inflate(R.layout.footer, parent, false);
            return view;
        }
        holder.imageSchoolConment.setImageURI(Uri.parse(listBean.getImage()));
        holder.schoolContentTitle.setText(listBean.getTitle());
        holder.schoolLookeNum.setText(listBean.getViewCount()+"");
        holder.textStarNum.setText(100+"");
        holder.textCommentNum.setText(listBean.getReplies()+"");

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.school_content_title)
        TextView schoolContentTitle;
        @Bind(R.id.school_looke_num)
        TextView schoolLookeNum;
        @Bind(R.id.text_star_num)
        TextView textStarNum;
        @Bind(R.id.text_comment_num)
        TextView textCommentNum;
        @Bind(R.id.image_school_conment)
        SimpleDraweeView imageSchoolConment;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
