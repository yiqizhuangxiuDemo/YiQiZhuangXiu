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

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${yong} on 2016/11/24.
 */

public class FragmentOwnerSayCreamAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<OwnerSayCreamPageData.DataBean> datas;
    private LayoutInflater inflater;
    private Context context;
//    private String[] str = {"一","二","三","四","五","六","七","八","九","十"};

    public FragmentOwnerSayCreamAdapter(LayoutInflater inflater, Context context, List<OwnerSayCreamPageData.DataBean> datas) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder viewHolder = new MyViewHolder(inflater.inflate(R.layout.item_fragment_ownersaypagecream_layout,
                parent,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        OwnerSayCreamPageData.DataBean dataBean = datas.get(position);
            myViewHolder.ownersaypagecreamTitleImg.setImageURI(Uri.parse(dataBean.getAvtUrl()));
            myViewHolder.ownersaypagecreamTitleName.setText(dataBean.getAuthor());
            Log.d("FragmentOwnerSayCreamAd", "datas.get(i).getHouseInfo():" + dataBean.getHouseInfo());
            if (dataBean.getHouseInfo() != null) {
                myViewHolder.ownersaypagecreamTitleContent.setText(
                        dataBean.getHouseInfo().getArea()+"平、"
                                +dataBean.getHouseInfo().getBudget()+"萬、");
            }
            myViewHolder.ownersaypagecreamContentText.setText(dataBean.getSubject());
            myViewHolder.ownersaypagecreamContentImg.setImageURI(Uri.parse(dataBean.getAttachments().get(0)));
            myViewHolder.itemOwnersaypagecreamBottomDate.setText(dataBean.getDateline());
            Log.d("F---------------", "datas.get(i).getZan():" + dataBean.getZan());
            myViewHolder.itemOwnersaypagecreamBottomZanTitle.setText(dataBean.getZan()+"");
            myViewHolder.itemOwnersaypagecreamBottomCommentText.setText(dataBean.getReplies());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
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
        }
    }
}
