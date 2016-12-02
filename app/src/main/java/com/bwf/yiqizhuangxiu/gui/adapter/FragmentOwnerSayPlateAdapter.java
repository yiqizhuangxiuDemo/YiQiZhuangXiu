package com.bwf.yiqizhuangxiu.gui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.OwnerSayPlatePageData;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${yong} on 2016/11/25.
 */

public class FragmentOwnerSayPlateAdapter extends RecyclerView.Adapter {
    private LayoutInflater inflater;
    private List<OwnerSayPlatePageData.DataBean> datas;

    public FragmentOwnerSayPlateAdapter(Context context, List<OwnerSayPlatePageData.DataBean> datas) {
        this.datas = datas;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_fragment_ownersay_plate, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        OwnerSayPlatePageData.DataBean dataBean = datas.get(position);
        myViewHolder.fragmentOwnersayPlateSubPic.setImageURI(Uri.parse(dataBean.getIcon()));
        myViewHolder.fragmentOwnersayPlateSubTitle.setText(dataBean.getTitle());
        myViewHolder.fragmentOwnersayPlateSubCount.setText("总贴:" + dataBean.getThreadsnum());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.fragment_ownersay_plate_sub_pic)
        SimpleDraweeView fragmentOwnersayPlateSubPic;
        @Bind(R.id.fragment_ownersay_plate_sub_title)
        TextView fragmentOwnersayPlateSubTitle;
        @Bind(R.id.fragment_ownersay_plate_sub_count)
        TextView fragmentOwnersayPlateSubCount;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
