package com.bwf.yiqizhuangxiu.gui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.CompanyDecorateData;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/24 0024.
 */

public class CompanyDecoratorRecycleViewAdapter extends RecyclerViewWithHeaderOrFooterAdapter<CompanyDecorateData.DataBean> {

    public CompanyDecoratorRecycleViewAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getFooterCount() {
        return 1;
    }

    @Override
    protected int getHeaderCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1)
            return TYPE_FOOTER;
        return TYPE_CONTENT;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_FOOTER) {
            view = inflater.inflate(R.layout.footer, parent, false);
            return new FootViewHolder(view);
        } else {
            view = inflater.inflate(R.layout.decorate_playing_item, parent, false);
        }
        return new MyDecorateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == getItemCount() - 1) {
            return;
        }
        MyDecorateViewHolder mHolder = (MyDecorateViewHolder) holder;
        CompanyDecorateData.DataBean dataBean = getItemData(position);
        mHolder.imageDecorator.setImageURI(Uri.parse(dataBean.getImageUrl()));
        mHolder.textTitleBig.setText(dataBean.getOrderHouse().getCommunity());
        mHolder.textTitleLittle.setText(dataBean.getOrderHouse().getLayout());

    }

    public class MyDecorateViewHolder extends ItemViewHolder {
        @Bind(R.id.image_decorator)
        SimpleDraweeView imageDecorator;
        @Bind(R.id.text_title_big)
        TextView textTitleBig;
        @Bind(R.id.text_title_little)
        TextView textTitleLittle;

        MyDecorateViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class FootViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.foot)
        TextView foot;

        FootViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
