package com.bwf.yiqizhuangxiu.gui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.OwnerSayUpToDataPageData;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${yong} on 2016/11/25.
 */

public class FragmentOwnerSayUpToDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<OwnerSayUpToDataPageData.DataBean> mdatas;
    private LayoutInflater inflater;

    public FragmentOwnerSayUpToDataAdapter(Context context) {
        mdatas = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    private onItemClickListener listener;

    public void setOnItemClikListener(onItemClickListener listener) {
        this.listener = listener;
    }

    public interface onItemClickListener {
        void onItemClick(View view, OwnerSayUpToDataPageData.DataBean dataBean);
    }

    public void addData(List<OwnerSayUpToDataPageData.DataBean> datas) {
        this.mdatas.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mdatas.size()) {
            return R.layout.item_load_more;
        } else {
            return R.layout.item_fragment_ownersay_uptodata;
        }
    }

    private LoadMoreCallBack callBack;

    public void setLoadMoreCallBack(LoadMoreCallBack callBack) {
        this.callBack = callBack;
    }

    public interface LoadMoreCallBack {
        void loadMore();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(viewType, parent, false);
        if (viewType == R.layout.item_load_more) {
            LoadMoreViewHolder viewHolder = new LoadMoreViewHolder(view);
            return viewHolder;
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
            MyViewHolder myholder = (MyViewHolder) holder;
            OwnerSayUpToDataPageData.DataBean dataBean = mdatas.get(position);
            myholder.fragmentOwnersayUptodataheadImg.setImageURI(Uri.parse(dataBean.getAvtUrl()));
            myholder.fragmentOwnersayUptodataTitle.setText(dataBean.getSubject());
            myholder.fragmentOwnersayUptodataName.setText(dataBean.getAuthor());
            myholder.fragmentOwnersayUptodataData.setText(dataBean.getDateline());
            if (dataBean.getAttachments() != null) {
                myholder.fragmentOwnersayUptodataImg.setVisibility(View.VISIBLE);
            } else {
                myholder.fragmentOwnersayUptodataImg.setVisibility(View.GONE);
            }

            if (dataBean.getViews() != null) {
                myholder.fragmentOwnersayUptodataCheckCount.setText(dataBean.getViews() + "");
            }
            if (dataBean.getReplies() != null) {
                myholder.fragmentOwnersayUptodataCommentCount.setText(dataBean.getReplies() + "");
            }
        }
    }

    @Override
    public int getItemCount() {
        return this.mdatas.size() + 1;
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.fragment_ownersay_uptodatahead_img)
        SimpleDraweeView fragmentOwnersayUptodataheadImg;
        @Bind(R.id.fragment_ownersay_uptodata_title)
        TextView fragmentOwnersayUptodataTitle;
        @Bind(R.id.fragment_ownersay_uptodata_img)
        ImageView fragmentOwnersayUptodataImg;
        @Bind(R.id.fragment_ownersay_uptodata_name)
        TextView fragmentOwnersayUptodataName;
        @Bind(R.id.fragment_ownersay_uptodata_data)
        TextView fragmentOwnersayUptodataData;
        @Bind(R.id.fragment_ownersay_uptodata_check)
        ImageView fragmentOwnersayUptodataCheck;
        @Bind(R.id.fragment_ownersay_uptodata_check_count)
        TextView fragmentOwnersayUptodataCheckCount;
        @Bind(R.id.fragment_ownersay_uptodata_comment)
        ImageView fragmentOwnersayUptodataComment;
        @Bind(R.id.fragment_ownersay_uptodata_comment_count)
        TextView fragmentOwnersayUptodataCommentCount;
        @Bind(R.id.fragment_ownersay_uptodata_item)
        LinearLayout fragmentOwnersayUptodataItem;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
            fragmentOwnersayUptodataheadImg.setOnClickListener(this);
            fragmentOwnersayUptodataCheck.setOnClickListener(this);
            fragmentOwnersayUptodataComment.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(v, mdatas.get(getAdapterPosition()));
        }
    }

    static class LoadMoreViewHolder extends RecyclerView.ViewHolder {

        public LoadMoreViewHolder(View itemView) {
            super(itemView);
        }
    }
}
