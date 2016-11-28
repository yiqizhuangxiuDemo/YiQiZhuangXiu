package com.bwf.yiqizhuangxiu.gui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.OwnerSayUpToDataPageData;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${yong} on 2016/11/25.
 */

public class FragmentOwnerSayUpToDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<OwnerSayUpToDataPageData.DataBean> datas;
    private LayoutInflater inflater;

    public FragmentOwnerSayUpToDataAdapter(Context context, List<OwnerSayUpToDataPageData.DataBean> datas) {
        this.datas = datas;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_fragment_ownersay_uptodata, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myholder = (MyViewHolder)holder;
        OwnerSayUpToDataPageData.DataBean dataBean = datas.get(position);
        myholder.fragmentOwnersayUptodataheadImg.setImageURI(Uri.parse(dataBean.getAvtUrl()));
        myholder.fragmentOwnersayUptodataTitle.setText(dataBean.getSubject());
        myholder.fragmentOwnersayUptodataName.setText(dataBean.getAuthor());
        myholder.fragmentOwnersayUptodataData.setText(dataBean.getDateline());
        if (dataBean.getViews() != null) {
            myholder.fragmentOwnersayUptodataCheckCount.setText(dataBean.getViews()+"");
        }
        if (dataBean.getReplies() != null) {
            myholder.fragmentOwnersayUptodataCommentCount.setText(dataBean.getReplies()+"");
        }


    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder{
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

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
