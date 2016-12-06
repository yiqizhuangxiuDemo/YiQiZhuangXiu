package com.bwf.yiqizhuangxiu.gui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.CityActivityData;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${yong} on 2016/11/28.
 */

public class CityActivityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<CityActivityData.DataBean.ForumlistBean> datas;
    private LayoutInflater inflater;
    private Context context;
    public static final int STATE_GONE = 0;
    public static final int STATE_LOADING = 1;
    public static final int STATE_NO_MORE_DATA = 2;
    public static final int STATE_LOAD_FAILED = 3;
    private int state;

    public void updataFooterState(int state) {
        this.state = state;
        notifyItemChanged(getItemCount() - 1);
    }

    public CityActivityAdapter(Context context) {
        this.datas = new ArrayList<>();
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void addDatas(List<CityActivityData.DataBean.ForumlistBean> datas) {
        if (datas.size() == 0) {
            return;
        }
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener = null;

    public interface OnRecyclerViewItemClickListener {
        void onItemClickListener(CityActivityData.DataBean.ForumlistBean datas);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    public interface LoadMoreDatasCallBack {
        void loadMore();
    }

    private LoadMoreDatasCallBack callBack;

    public void setLoadMoreDatasCallBack(LoadMoreDatasCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return R.layout.item_load_more;
        } else {
            return R.layout.item_city_activity;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(viewType, parent, false);
        if (viewType == R.layout.item_load_more) {
            LoadMoreViewHolder loadMoreViewHolder = new LoadMoreViewHolder(view);
            return loadMoreViewHolder;
        } else {
            MyViewHolder viewHolder = new MyViewHolder(view);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == R.layout.item_load_more) {
            LoadMoreViewHolder loadMoreViewHolder = (LoadMoreViewHolder)holder;
            loadMoreViewHolder.bindLoadMoreViewHolder(loadMoreViewHolder,position);
        } else {
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            CityActivityData.DataBean.ForumlistBean bean = datas.get(position);
            myViewHolder.cityActivityPic.setImageURI(Uri.parse(bean.getAttachments()));
            timeCompare(myViewHolder, bean.getExpiration());
        }
    }

    private void timeCompare(MyViewHolder myViewHolder, long str) {
        SimpleDateFormat time = new SimpleDateFormat("yyyy年MM月dd日");
        Date currentdate = new Date();
        Date date = new Date(str);
        if (currentdate.getTime() - date.getTime() > 0) {
            myViewHolder.cityActivityNoticeText.setText("活动已结束...");
            myViewHolder.cityActivityNoticeText.setTextColor(0x55555555);
            myViewHolder.cityActivityNoticePic.setImageResource(R.mipmap.activity_end_sign);
        } else {
            myViewHolder.cityActivityNoticeText.setText("火热报名中...");
            myViewHolder.cityActivityNoticePic.setImageResource(R.mipmap.activity_hot_sign);
        }
        myViewHolder.activityEndTime.setText("报名截止日期:" + time.format(date));
    }

    @Override
    public int getItemCount() {
        return datas.size() + 1;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.city_activity_pic)
        ImageView cityActivityPic;
        @Bind(R.id.city_activity_notice_pic)
        ImageView cityActivityNoticePic;
        @Bind(R.id.city_activity_notice_text)
        TextView cityActivityNoticeText;
        @Bind(R.id.activity_end_time)
        TextView activityEndTime;

        MyViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            ButterKnife.bind(this, view);
        }

        @Override
        public void onClick(View v) {
            if (onRecyclerViewItemClickListener != null) {
                onRecyclerViewItemClickListener.onItemClickListener(datas.get(getAdapterPosition()));
            }
        }
    }

    class LoadMoreViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.progressbar)
        ProgressBar progressbar;
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.more)
        LinearLayout more;

        LoadMoreViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bindLoadMoreViewHolder(RecyclerView.ViewHolder holder,int position) {
            switch (state) {
                case STATE_GONE:
                    return;
                case STATE_LOADING:
                    progressbar.setVisibility(View.VISIBLE);
                    title.setText("正在加载中...");
                    break;
                case STATE_NO_MORE_DATA:
                    progressbar.setVisibility(View.GONE);
                    title.setText("没有更多数据了哦...");
                    break;
                case STATE_LOAD_FAILED:
                    progressbar.setVisibility(View.GONE);
                    title.setText("获取数据失败，点我重试！");
                    title.setEnabled(true);
                    break;
            }
        }
    }
}
