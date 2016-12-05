package com.bwf.yiqizhuangxiu.gui.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.CompanyTogtherData;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/28 0028.
 */

public class CompanyGridAdapter extends BaseAdapter {
    private List<CompanyTogtherData.DataBean> dataBeen;
    private LayoutInflater inflater;
    private Context context;

    public CompanyGridAdapter(Context context) {
        this.dataBeen = new ArrayList<>();
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void addData(List<CompanyTogtherData.DataBean> dataBeen) {
        this.dataBeen.addAll(dataBeen);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dataBeen.size();
    }

    @Override
    public CompanyTogtherData.DataBean getItem(int position) {
        return dataBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_together_team, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        CompanyTogtherData.DataBean bean = dataBeen.get(position);
        holder.desinTextName.setText(bean.getVendorName());
        holder.textDesignNum.setText(context.getString(R.string.caseNumber,bean.getCaseNumber()));
        holder.itemImageTogether.setImageURI(Uri.parse(bean.getAvatar()));
        return convertView;
    }



    static class ViewHolder {
        @Bind(R.id.item_image_together)
        SimpleDraweeView itemImageTogether;
        @Bind(R.id.desin_text_name)
        TextView desinTextName;
        @Bind(R.id.text_design_num)
        TextView textDesignNum;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
