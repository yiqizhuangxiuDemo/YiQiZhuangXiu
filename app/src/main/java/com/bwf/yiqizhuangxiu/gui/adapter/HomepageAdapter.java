package com.bwf.yiqizhuangxiu.gui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.HomepageContentData;
import com.bwf.yiqizhuangxiu.entity.HomepageHeadData;
import com.bwf.yiqizhuangxiu.gui.activity.ActivitiesActivity;
import com.bwf.yiqizhuangxiu.gui.activity.BookingActivity;
import com.bwf.yiqizhuangxiu.gui.activity.BudgetActivity;
import com.bwf.yiqizhuangxiu.gui.activity.CompanyActivity;
import com.bwf.yiqizhuangxiu.gui.activity.DesignActivity;
import com.bwf.yiqizhuangxiu.gui.activity.DetailsPageActivity;
import com.bwf.yiqizhuangxiu.gui.activity.EffectActivity;
import com.bwf.yiqizhuangxiu.gui.activity.FitmentActivity;
import com.bwf.yiqizhuangxiu.gui.activity.SchoolActivity;
import com.bwf.yiqizhuangxiu.gui.custom.AutoScrollViewPager;
import com.bwf.yiqizhuangxiu.utils.LogUtils;
import com.bwf.yiqizhuangxiu.utils.indicator.ViewPagerIndicator;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/23.
 */

public class HomepageAdapter extends RecyclerViewWithHeaderOrFooterAdapter<HomepageContentData.DataBean> {

    public final static int ARTICLE_TYPE = 1;
    public final static int POST_TYPE = 3;
    public final static int TYPE_HEAD = 0;
    public final static int TYPE_CONTENT_ARTICLEDETAILS = 1;
    public final static int TYPE_CONTENT_POSTDETAILS = 2;
    public final static int TYPE_FOOTER = 3;

    public HomepageAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getFooterCount() {
        return 1;
    }

    @Override
    protected int getHeaderCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEAD;
        } else if (position == getItemCount() - 1) {
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
        } else if (viewType == TYPE_HEAD) {
            view = inflater.inflate(R.layout.fragment_homepage_item_head, parent, false);
            viewHolder = new HeadViewHolder(view);
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
            viewHolder.simpleDraweeViewArticledetails.setImageURI(Uri.parse(itemData.getPath()));
            viewHolder.nameArticledetails.setText(itemData.getAuthor());
            viewHolder.timeArticledetailsText.setText(itemData.getDateline());
            viewHolder.commentArticledetails.setText(itemData.getReplies() + "");
            viewHolder.viewArticledetails.setText(itemData.getViews() + "");
            viewHolder.titleArticledetatilsText.setText(itemData.getTitle());
            viewHolder.fromArticledetails.setText(context.getString(R.string.homepage_post, itemData.getForum().getName()));
        } else if (itemType == TYPE_HEAD) {
            HeadViewHolder viewHolder = (HeadViewHolder) holder;
            if (headIsReady) {
                HomepageViewPagerAdapter adapter = new HomepageViewPagerAdapter(getContext(), headDatas);
                adapter.setOnItemClickListener(new HomepageViewPagerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        String tag = (String) view.getTag();
                        Intent intent = new Intent(context, DetailsPageActivity.class);
                        intent.putExtra("url", tag);
                        context.startActivity(intent);
                    }
                });
                viewHolder.viewpagerHomepageHead.setAdapter(adapter);
                viewHolder.viewpagerHomepageHeadIndicator.removeAllViews();
                ViewPagerIndicator viewPagerIndicator = new ViewPagerIndicator(getContext()
                        , viewHolder.viewpagerHomepageHead, viewHolder.viewpagerHomepageHeadIndicator, headDatas.size());
                viewPagerIndicator.setDotHeightByDp(8);
                viewPagerIndicator.setDotWidthByDp(8);
                viewPagerIndicator.setMarginByDp(6);
                viewPagerIndicator.create();
                LogUtils.e("headIsReady");
            }
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

    public class HeadViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.viewpager_homepage_head)
        AutoScrollViewPager viewpagerHomepageHead;
        @Bind(R.id.viewpager_homepage_head_indicator)
        LinearLayout viewpagerHomepageHeadIndicator;
        @Bind(R.id.textview_company)
        TextView textviewCompany;
        @Bind(R.id.textview_activities)
        TextView textviewActivities;
        @Bind(R.id.textview_school)
        TextView textviewSchool;
        @Bind(R.id.textview_budget)
        TextView textviewBudget;
        @Bind(R.id.textview_fitment)
        TextView textviewFitment;
        @Bind(R.id.textview_effect)
        TextView textviewEffect;
        @Bind(R.id.textview_booking)
        TextView textviewBooking;
        @Bind(R.id.textview_design)
        TextView textviewDesign;

        HeadViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick({R.id.textview_company, R.id.textview_activities, R.id.textview_school, R.id.textview_budget, R.id.textview_fitment, R.id.textview_effect, R.id.textview_booking, R.id.textview_design})
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.textview_company:
                    jumpToActivity(CompanyActivity.class);
                    break;
                case R.id.textview_activities:
                    jumpToActivity(ActivitiesActivity.class);
                    break;
                case R.id.textview_school:
                    jumpToActivity(SchoolActivity.class);
                    break;
                case R.id.textview_budget:
                    jumpToActivity(BudgetActivity.class);
                    break;
                case R.id.textview_fitment:
                    jumpToActivity(FitmentActivity.class);
                    break;
                case R.id.textview_effect:
                    jumpToActivity(EffectActivity.class);
                    break;
                case R.id.textview_booking:
                    jumpToActivity(BookingActivity.class);
                    break;
                case R.id.textview_design:
                    jumpToActivity(DesignActivity.class);
                    break;
            }
        }

        private void jumpToActivity(Class intentClass) {
            Intent intent = new Intent(getContext(), intentClass);
            context.startActivity(intent);
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
