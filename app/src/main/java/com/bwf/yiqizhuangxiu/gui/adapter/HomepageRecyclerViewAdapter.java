package com.bwf.yiqizhuangxiu.gui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.HomepageContentData;
import com.bwf.yiqizhuangxiu.entity.HomepageHeadData;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/23.
 */

public class HomepageRecyclerViewAdapter extends RecyclerViewWithHeaderOrFooterAdapter<HomepageContentData.DataBean> {

    public final static int ARTICLE_TYPE = 1;
    public final static int POST_TYPE = 3;
    public final static int TYPE_HEAD = 0;
    public final static int TYPE_CONTENT_ARTICLEDETAILS = 1;
    public final static int TYPE_CONTENT_POSTDETAILS = 2;
    public final static int TYPE_FOOTER = 3;

    public HomepageRecyclerViewAdapter(Context context) {
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
        }
        if (getItemData(position).getType() == ARTICLE_TYPE) {
            return TYPE_CONTENT_ARTICLEDETAILS;
        } else {
            return TYPE_CONTENT_POSTDETAILS;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder viewHolder = null;
        if (viewType == TYPE_CONTENT_ARTICLEDETAILS) {
            view = inflater.inflate(R.layout.fragment_homepage_recyclerview_item_article, parent, false);
            viewHolder = new ArticleViewHolder(view);
        } else if (viewType == TYPE_CONTENT_POSTDETAILS) {
            view = inflater.inflate(R.layout.fragment_homepage_recyclerview_item_post, parent, false);
            viewHolder = new PostViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            view = inflater.inflate(R.layout.recycleview_footer, parent, false);
            viewHolder = new FooterViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemType = getItemViewType(position);
        if (itemType == TYPE_CONTENT_ARTICLEDETAILS) {
            ArticleViewHolder viewHolder = (ArticleViewHolder) holder;
            HomepageContentData.DataBean itemData = getItemData(position);
            viewHolder.titleArticledetatilsText.setText(itemData.getTitle());
            viewHolder.simpleDraweeViewArticledetails.setImageURI(Uri.parse(itemData.getPath()));
            viewHolder.timeArticledetailsText.setText(itemData.getDateline());
            viewHolder.commentArticledetails.setText(itemData.getReplies() + "");
            viewHolder.viewNum.setText(itemData.getViews() + "");
        } else if (itemType == TYPE_CONTENT_POSTDETAILS) {
            PostViewHolder viewHolder = (PostViewHolder) holder;
            HomepageContentData.DataBean itemData = getItemData(position);
            viewHolder.simpleDraweeViewArticledetailsHead.setImageURI(Uri.parse(itemData.getAvtUrl()));
            if (itemData.getPath()!=null){
                viewHolder.simpleDraweeViewArticledetails.setVisibility(View.VISIBLE);
                viewHolder.simpleDraweeViewArticledetails.setImageURI(Uri.parse(itemData.getPath()));
            }else {
                viewHolder.simpleDraweeViewArticledetails.setVisibility(View.GONE);
            }
            viewHolder.nameArticledetails.setText(itemData.getAuthor());
            viewHolder.timeArticledetailsText.setText(itemData.getDateline());
            viewHolder.commentArticledetails.setText(itemData.getReplies() + "");
            viewHolder.viewArticledetails.setText(itemData.getViews() + "");
            viewHolder.titleArticledetatilsText.setText(itemData.getTitle());
            viewHolder.fromArticledetails.setText(context.getString(R.string.homepage_post, itemData.getForum().getName()));
        } else {
            FooterViewHolder viewHolder = (FooterViewHolder) holder;
            if (footerState == 0) {
                viewHolder.progressbarSubviewRecycleviewLoadfooter.setVisibility(View.VISIBLE);
                viewHolder.textviewSubviewRecycleviewLoadfooter.setVisibility(View.GONE);
            } else if (footerState == 1) {
                viewHolder.progressbarSubviewRecycleviewLoadfooter.setVisibility(View.GONE);
                viewHolder.textviewSubviewRecycleviewLoadfooter.setVisibility(View.VISIBLE);
                viewHolder.textviewSubviewRecycleviewLoadfooter.setText(context.getString(R.string.restoreData));
            } else {
                viewHolder.progressbarSubviewRecycleviewLoadfooter.setVisibility(View.GONE);
                viewHolder.textviewSubviewRecycleviewLoadfooter.setVisibility(View.VISIBLE);
                viewHolder.textviewSubviewRecycleviewLoadfooter.setText(context.getString(R.string.noMoreData));
            }
        }
    }

    private int footerState;

    public void setFooterState(int footerState) {
        this.footerState = footerState;
        notifyItemChanged(getItemCount() - 1);
    }

    private boolean headIsReady;
    private List<HomepageHeadData.DataBean> headDatas;
    private boolean isUpdateHead;

    public void updateHeadView(List<HomepageHeadData.DataBean> headDatas) {
        headIsReady = true;
        this.headDatas = headDatas;
        notifyItemChanged(0);
    }

    public class ArticleViewHolder extends ItemViewHolder {
        @Bind(R.id.title_articledetatils_text)
        TextView titleArticledetatilsText;
        @Bind(R.id.simpleDraweeView_articledetails)
        SimpleDraweeView simpleDraweeViewArticledetails;
        @Bind(R.id.time_articledetails_text)
        TextView timeArticledetailsText;
        @Bind(R.id.comment_articledetails)
        TextView commentArticledetails;
        @Bind(R.id.viewNum)
        TextView viewNum;

        ArticleViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class PostViewHolder extends ItemViewHolder {
        @Bind(R.id.simpleDraweeView_articledetails_head)
        SimpleDraweeView simpleDraweeViewArticledetailsHead;
        @Bind(R.id.name_articledetails)
        TextView nameArticledetails;
        @Bind(R.id.time_articledetails_text)
        TextView timeArticledetailsText;
        @Bind(R.id.title_articledetatils_text)
        TextView titleArticledetatilsText;
        @Bind(R.id.simpleDraweeView_articledetails)
        SimpleDraweeView simpleDraweeViewArticledetails;
        @Bind(R.id.comment_articledetails)
        TextView commentArticledetails;
        @Bind(R.id.view_articledetails_Nums)
        TextView viewArticledetails;
        @Bind(R.id.from_articledetails)
        TextView fromArticledetails;

        PostViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.progressbar_subview_recycleview_loadfooter)
        ProgressBar progressbarSubviewRecycleviewLoadfooter;
        @Bind(R.id.textview_subview_recycleview_loadfooter)
        TextView textviewSubviewRecycleviewLoadfooter;
        @Bind(R.id.layout_subview_recycleview_loadfooter)
        RelativeLayout layoutSubviewRecycleviewLoadfooter;

        FooterViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick({R.id.textview_subview_recycleview_loadfooter})
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.textview_subview_recycleview_loadfooter:
                    if (onloadMoreDataListener != null) {
                        setFooterState(0);
                        onloadMoreDataListener.onLoadMoreData();
                    }
                    break;
            }
        }
    }

    public interface OnloadMoreDataListener {
        void onLoadMoreData();
    }

    private OnloadMoreDataListener onloadMoreDataListener;

    public void setOnloadMoreDataListener(OnloadMoreDataListener onloadMoreDataListener) {
        this.onloadMoreDataListener = onloadMoreDataListener;
    }
}
