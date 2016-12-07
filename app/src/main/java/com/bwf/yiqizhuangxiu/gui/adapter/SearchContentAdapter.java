package com.bwf.yiqizhuangxiu.gui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.SearchData;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${yong} on 2016/11/30.
 */

public class SearchContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public List<SearchData.DataBean> datas;
    private LayoutInflater inflater;
    private String text;
    private static Context context;

    public SearchContentAdapter(Context context) {
        this.datas = new ArrayList<>();
        SearchContentAdapter.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public void sendText(String text) {
        this.text = text;
    }

    public void addDatas(List<SearchData.DataBean> datas) {
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    private LoadMoreDataCallBack callBack;

    public interface LoadMoreDataCallBack {
        void loadMore();
    }

    public void setLoadMoreDataCallBack(LoadMoreDataCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return R.layout.item_load_more;
        } else {
            return R.layout.item_fragment_ownersaypagecream_layout;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(viewType, parent, false);
        if (viewType == R.layout.item_load_more) {
            LoadMoreViewHolder loadMoreViewHolder = new LoadMoreViewHolder(view);
            return loadMoreViewHolder;
        } else {
            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LoadMoreViewHolder) {
            callBack.loadMore();
        } else if (holder instanceof MyViewHolder) {
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            SearchData.DataBean dataBean = datas.get(position);
            myViewHolder.itemView.setTag(datas.get(position));
            myViewHolder.ownersaypagecreamTitleImg.setImageURI(Uri.parse(dataBean.getAvtUrl()));
            myViewHolder.ownersaypagecreamTitleName.setText(dataBean.getAuthor());
            if (dataBean.getSubject() != null || dataBean.getSubject() != "") {
                int start = dataBean.getSubject().indexOf(this.text);
                if (start >= 0) {
                    int end = start + this.text.length();
                    SpannableStringBuilder builder = new SpannableStringBuilder(dataBean.getSubject());
                    builder.setSpan(new ForegroundColorSpan(Color.argb(255,0,160,81)), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    myViewHolder.ownersaypagecreamContentText.setText(builder);
                }
            }
            if (dataBean.getAttachments() != null) {
                myViewHolder.ownersaypagecreamContentImg.setImageURI(Uri.parse(dataBean.getAttachments().get(0)));
            } else {
                myViewHolder.ownersaypagecreamContentImg.setVisibility(View.GONE);
            }
            myViewHolder.itemOwnersaypagecreamBottomDate.setText(dataBean.getDateline());
            myViewHolder.itemOwnersaypagecreamBottomZanTitle.setText(dataBean.getZan() + "");
            myViewHolder.itemOwnersaypagecreamBottomCommentText.setText(dataBean.getReplies());
        }
    }

    @Override
    public int getItemCount() {
        return datas.size() + 1;
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, SearchData.DataBean dataBean);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.ownersaypagecream_title_img)
        SimpleDraweeView ownersaypagecreamTitleImg;
        @Bind(R.id.ownersaypagecream_title_name)
        TextView ownersaypagecreamTitleName;
        @Bind(R.id.ownersaypagecream_title_content)
        TextView ownersaypagecreamTitleContent;
        @Bind(R.id.ownersaypagecream_content_text)
        TextView ownersaypagecreamContentText;
        @Bind(R.id.ownersaypagecream_content_img)
        SimpleDraweeView ownersaypagecreamContentImg;
        @Bind(R.id.item_ownersaypagecream_bottom_date)
        TextView itemOwnersaypagecreamBottomDate;
        @Bind(R.id.item_ownersaypagecream_bottom_zan_img)
        ImageView itemOwnersaypagecreamBottomZanImg;
        @Bind(R.id.item_ownersaypagecream_bottom_zan_title)
        TextView itemOwnersaypagecreamBottomZanTitle;
        @Bind(R.id.item_ownersaypagecream_bottom_comment_img)
        ImageView itemOwnersaypagecreamBottomCommentImg;
        @Bind(R.id.item_ownersaypagecream_bottom_comment_text)
        TextView itemOwnersaypagecreamBottomCommentText;
        @Bind(R.id.item_ownersaypagecream_bottom_share)
        ImageView itemOwnersaypagecreamBottomShare;
        @Bind(R.id.ownersaypagecream_content_bottomtext)
        TextView ownersaypagecreamContentBottomtext;
        @Bind(R.id.item_ownersaypagecream)
        LinearLayout itemOwnersaypagecream;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
            ownersaypagecreamTitleImg.setOnClickListener(this);
            itemOwnersaypagecreamBottomShare.setOnClickListener(this);
            itemOwnersaypagecreamBottomCommentImg.setOnClickListener(this);
            itemOwnersaypagecreamBottomZanImg.setOnClickListener(this);
            ownersaypagecreamContentBottomtext.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(v, datas.get(getAdapterPosition()));
            }
        }
    }

    static class LoadMoreViewHolder extends RecyclerView.ViewHolder {

        public LoadMoreViewHolder(View itemView) {
            super(itemView);
        }
    }
}
