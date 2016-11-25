package com.bwf.yiqizhuangxiu.gui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.OwnerSayPlatePageData;

import java.util.List;

/**
 * Created by ${yong} on 2016/11/25.
 */

public class FragmentOwnerSayPlateAdapter extends RecyclerView.Adapter {
   private LayoutInflater inflater;
    private List<OwnerSayPlatePageData.DataBean> datas;
    public FragmentOwnerSayPlateAdapter(Context context,List<OwnerSayPlatePageData.DataBean> datas) {
        this.datas = datas;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_fragment_ownersay_plate_head, parent, false);
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
