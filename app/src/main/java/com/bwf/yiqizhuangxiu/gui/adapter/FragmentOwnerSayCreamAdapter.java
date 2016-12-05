package com.bwf.yiqizhuangxiu.gui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.OwnerSayCreamPageData;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${yong} on 2016/11/24.
 */

public class FragmentOwnerSayCreamAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<OwnerSayCreamPageData.DataBean> mdatas;
    private LayoutInflater inflater;
    private Context context;

    public FragmentOwnerSayCreamAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.mdatas = new ArrayList<>();
    }

    public void addData(List<OwnerSayCreamPageData.DataBean> datas) {
        this.mdatas.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mdatas.size()) {
            return R.layout.item_load_more;
        } else {
            return R.layout.item_fragment_ownersaypagecream_layout;
        }
    }

    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener = null;
    public void setOnItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener){
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    public interface OnRecyclerViewItemClickListener{
        void onItemclick(View view,OwnerSayCreamPageData.DataBean Datas);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(viewType,parent,false);
        if (viewType == R.layout.item_load_more) {
            LoadMoreViewHolder moreHolder = new LoadMoreViewHolder(view);
            return moreHolder;
        } else {
            MyViewHolder viewHolder = new MyViewHolder(view);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LoadMoreViewHolder) {
            callBack.loadMoreData();
        } else if (holder instanceof MyViewHolder) {
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            OwnerSayCreamPageData.DataBean dataBean = mdatas.get(position);
            myViewHolder.itemView.setTag(mdatas.get(position));
            myViewHolder.ownersaypagecreamTitleImg.setImageURI(Uri.parse(dataBean.getAvtUrl()));
            myViewHolder.ownersaypagecreamTitleName.setText(dataBean.getAuthor());
            Log.d("FragmentOwnerSayCreamAd", "business.get(i).getHouseInfo():" + dataBean.getHouseInfo());
            if (dataBean.getHouseInfo() != null) {
                myViewHolder.ownersaypagecreamTitleContent.setText(
                        dataBean.getHouseInfo().getArea()+"平、"
                                +dataBean.getHouseInfo().getBudget()+"萬、");
            }
            myViewHolder.ownersaypagecreamContentText.setText(dataBean.getSubject());

            if (dataBean.getAttachments() != null) {
                myViewHolder.ownersaypagecreamContentImg.setImageURI(Uri.parse(dataBean.getAttachments().get(0)));
            } else {
                myViewHolder.ownersaypagecreamContentImg.setVisibility(View.GONE);
            }

            myViewHolder.itemOwnersaypagecreamBottomDate.setText(dataBean.getDateline());
            Log.d("F---------------", "business.get(i).getZan():" + dataBean.getZan());
            myViewHolder.itemOwnersaypagecreamBottomZanTitle.setText(dataBean.getZan()+"");
            myViewHolder.itemOwnersaypagecreamBottomCommentText.setText(dataBean.getReplies());
        }

    }

    public interface LoadMoreCallBack {
        void loadMoreData();
    }
    private LoadMoreCallBack callBack;
    public void setLLoadMoreCallBack(LoadMoreCallBack callBack) {
        this.callBack = callBack;
    }
    @Override
    public int getItemCount() {
        return this.mdatas.size()+1;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
            itemOwnersaypagecreamBottomShare.setOnClickListener(this);
            itemOwnersaypagecreamBottomCommentImg.setOnClickListener(this);
            itemOwnersaypagecreamBottomZanImg.setOnClickListener(this);
            ownersaypagecreamTitleImg.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onRecyclerViewItemClickListener != null) {
                onRecyclerViewItemClickListener.onItemclick(v,mdatas.get(getAdapterPosition()));
            }
        }
    }
    static class LoadMoreViewHolder extends RecyclerView.ViewHolder {

        public LoadMoreViewHolder(View itemView) {
            super(itemView);
        }
    }
}


