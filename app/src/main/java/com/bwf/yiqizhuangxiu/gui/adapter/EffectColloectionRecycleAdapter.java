package com.bwf.yiqizhuangxiu.gui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.EffectCollectionData;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/28 0028.
 */

public class EffectColloectionRecycleAdapter extends RecyclerViewWithHeaderOrFooterAdapter<EffectCollectionData.DataBean.ListBean> {
    public EffectColloectionRecycleAdapter(Context context) {
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
        if (position == getItemCount() - 1) {
            return TYPE_FOOTER;
        } else
            return TYPE_CONTENT;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            return new FooterViewHolder(inflater.inflate(R.layout.footer_loading_data, parent, false));
        }

        return new ColloectionViewHolder(inflater.inflate(R.layout.item_effect_pic, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == getItemCount()-1){
            return;
        }
        ColloectionViewHolder mholer = (ColloectionViewHolder) holder;
        EffectCollectionData.DataBean.ListBean bean = getItemData(position);
        mholer.effectImageSd.setImageURI(Uri.parse(bean.getUrl()));
        mholer.fromArticledetails.setText(bean.getTitle());
        mholer.commentArticledetails.setText(bean.getComment_count()+"");
        mholer.viewArticledetailsNums.setText(bean.getLike_num()+"");
        mholer.textEffectContent.setText(bean.getDesc());
        mholer.textEffectContent.setLines(2);

    }

    static class FooterViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.footer_load_data)
        TextView footerLoadData;

        FooterViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

     class ColloectionViewHolder extends ItemViewHolder{
        @Bind(R.id.effect_image_sd)
        SimpleDraweeView effectImageSd;
        @Bind(R.id.from_articledetails)
        TextView fromArticledetails;
        @Bind(R.id.comment_articledetails)
        TextView commentArticledetails;
        @Bind(R.id.view_articledetails_Nums)
        TextView viewArticledetailsNums;
        @Bind(R.id.text_effect_content)
        TextView textEffectContent;

        ColloectionViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
