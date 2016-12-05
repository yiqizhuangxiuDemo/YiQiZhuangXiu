package com.bwf.yiqizhuangxiu.gui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.CityActivityData;

import java.text.ParseException;
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

    public CityActivityAdapter(Context context) {
        this.datas = new ArrayList<>();
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void addDatas(List<CityActivityData.DataBean.ForumlistBean> datas) {
        if (datas.size() == 0) {

        }
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener = null;

    public interface OnRecyclerViewItemClickListener {
        void onItemClickListener(View view, CityActivityData.DataBean.ForumlistBean datas);
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
        if (getItemViewType(position)==R.layout.item_load_more){

        }else {
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            CityActivityData.DataBean.ForumlistBean bean = datas.get(position);
            myViewHolder.itemView.setTag(bean);
            myViewHolder.cityActivityPic.setImageURI(Uri.parse(bean.getAttachments()));
            if (bean.getLastpost() != null || bean.getLastpost() != "") {
                timeCompare(myViewHolder, bean.getLastpost());
            } else {
                timeCompare(myViewHolder, "2016-8-25");
            }
            if (bean.getLastpost() != null) {
                myViewHolder.activityEndTime.setText("报名截止日期:" + bean.getLastpost());
            }
        }
//        if (holder instanceof LoadMoreViewHolder ) {
//            callBack.loadMore();

//        } else if (holder instanceof MyViewHolder) {
//            MyViewHolder myViewHolder = (MyViewHolder) holder;
//            CityActivityData.DataBean.ForumlistBean bean = datas.get(position);
//            myViewHolder.itemView.setTag(bean);
//            myViewHolder.cityActivityPic.setImageURI(Uri.parse(bean.getAttachments()));
//            if (bean.getLastpost() != null || bean.getLastpost() != "") {
//                timeCompare(myViewHolder, bean.getLastpost());
//            } else {
//                timeCompare(myViewHolder, "2016-8-25");
//            }
//            if (bean.getLastpost() != null) {
//                myViewHolder.activityEndTime.setText("报名截止日期:" + bean.getLastpost());
//            }
//        }
    }

    private void timeCompare(MyViewHolder myViewHolder, String str) {
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Log.d("CityActivityAdapter", "--------------------------------------------------------------");
        try {
            Date parse = time.parse(str);
            if (date.getTime() - parse.getTime() > 0) {
                myViewHolder.cityActivityNoticeText.setText("活动已结束...");
                myViewHolder.cityActivityNoticeText.setTextColor(0x55555555);
                myViewHolder.cityActivityNoticePic.setImageResource(R.mipmap.activity_end_sign);
            } else {
                myViewHolder.cityActivityNoticeText.setText("火热报名中...");
                myViewHolder.cityActivityNoticePic.setImageResource(R.mipmap.activity_hot_sign);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
            Toast.makeText(context, "--", Toast.LENGTH_SHORT).show();
            if (onRecyclerViewItemClickListener != null) {
                onRecyclerViewItemClickListener.onItemClickListener(v, (CityActivityData.DataBean.ForumlistBean) v.getTag());
            }
        }
    }
    static class LoadMoreViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.more)
        LinearLayout more;

        LoadMoreViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
