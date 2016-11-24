package com.bwf.yiqizhuangxiu.gui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.HomepageContentData;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/23.
 */

public class HomepageContentAdapter extends RecyclerViewWithHeaderOrFooterAdapter<HomepageContentData.DataBean> {

    public final static int ARTICLE_TYPE = 1;
    public final static int POST_TYPE = 3;
    public final static int TYPE_CONTENT_ARTICLEDETAILS = 3;
    public final static int TYPE_CONTENT_POSTDETAILS = 4;

    public HomepageContentAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getFooterCount() {
        return 0;
    }

    @Override
    protected int getHeaderCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (getItemData(position).getType() == ARTICLE_TYPE) {
            return TYPE_CONTENT_ARTICLEDETAILS;
        } else if (getItemData(position).getType() == POST_TYPE) {
            return TYPE_CONTENT_POSTDETAILS;
        }
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        if (viewType == TYPE_CONTENT_ARTICLEDETAILS) {
            view = inflater.inflate(R.layout.fragment_homepage_recyclerview_item_article, parent, false);
        } else if (viewType == TYPE_CONTENT_POSTDETAILS) {

        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }


    public static class ViewHolder {
        @Bind(R.id.title_articledetatils_text)
        TextView titleArticledetatilsText;
        @Bind(R.id.simpleDraweeView_articledetails)
        SimpleDraweeView simpleDraweeViewArticledetails;
        @Bind(R.id.time_articledetails_text)
        TextView timeArticledetailsText;
        @Bind(R.id.comment_articledetails)
        TextView commentArticledetails;
        @Bind(R.id.look_articledetails)
        TextView lookArticledetails;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
