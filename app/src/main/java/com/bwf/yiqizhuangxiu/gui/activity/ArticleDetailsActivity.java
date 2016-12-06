package com.bwf.yiqizhuangxiu.gui.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.ArticleDetailsCommentsData;
import com.bwf.yiqizhuangxiu.entity.ArticleDetailsTagDataBean;
import com.bwf.yiqizhuangxiu.entity.ArticlesNewsData;
import com.bwf.yiqizhuangxiu.gui.custom.CustomFlowLayout;
import com.bwf.yiqizhuangxiu.mvp.presenter.PresenterArticleDetails;
import com.bwf.yiqizhuangxiu.mvp.presenter.impl.PresenterArticleDetailsImpl;
import com.bwf.yiqizhuangxiu.mvp.view.ViewArticleDetails;
import com.bwf.yiqizhuangxiu.utils.FrescoImageUtils;
import com.bwf.yiqizhuangxiu.utils.indicator.TimestampUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/23.
 */

public class ArticleDetailsActivity extends BaseActivity implements ViewArticleDetails {

    public final static String TAG_ID_EXTRA = "newId";
    @Bind(R.id.title_articledetatils_text)
    TextView titleArticledetatilsText;
    @Bind(R.id.tag_container_postdetails)
    CustomFlowLayout tagContainerPostdetails;
    @Bind(R.id.content_container_postdetails)
    LinearLayout contentContainerPostdetails;
    @Bind(R.id.related_container_articledetails)
    LinearLayout relatedContainerArticledetails;
    @Bind(R.id.comment_container_articledetails)
    LinearLayout commentContainerArticledetails;
    @Bind(R.id.textview_nomroecomments_articledetails)
    TextView textviewNomroecommentsArticledetails;
    @Bind(R.id.commentnum_postdetails)
    TextView commentnumPostdetails;
    @Bind(R.id.root)
    LinearLayout root;

    private boolean isRefreshing;
    private PresenterArticleDetails presenter;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_articledetails;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        presenter = new PresenterArticleDetailsImpl(this);
    }

    @Override
    protected void initDatas() {
        loadData();
    }

    private void loadData() {
        if (!isRefreshing) {
            isRefreshing = true;
            presenter.loadArticleDetailsData(getIntent().getStringExtra(TAG_ID_EXTRA));
        }
    }

    @Override
    public void onLoadNewsSuccess(ArticlesNewsData.DataBean data, Map<String, ArticleDetailsTagDataBean> tags) {
        isRefreshing = false;
        titleArticledetatilsText.setText(data.getCurrentNews().getTitle());
        tagContainerPostdetails.removeAllViews();
        for (ArticleDetailsTagDataBean tagValue : tags.values()) {
            TextView textView = (TextView) LayoutInflater.from(this).inflate(
                    R.layout.articledetails_tag_item, tagContainerPostdetails, false);
            textView.setText(tagValue.getName());
            tagContainerPostdetails.addView(textView);
        }
        List<String> contents = data.getCurrentNews().getContent();
        if (contents != null && contents.size() > 0) {
            for (String content : contents) {
                if (content.startsWith("http://")) {
                    SimpleDraweeView sdv = new SimpleDraweeView(this);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    sdv.setLayoutParams(params);
                    FrescoImageUtils.setControllerListener(sdv, content, getScreenWidth(this) - dip2px(16) - dip2px(16));
                    contentContainerPostdetails.addView(sdv);
                } else {
                    TextView textView = new TextView(this);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.setMargins(0, dip2px(12), 0, 0);
                    textView.setLayoutParams(params);
                    textView.setText(content);
                    textView.setTextColor(getResources().getColor(android.R.color.black));
                    contentContainerPostdetails.addView(textView);
                }
            }
        }
        List<ArticlesNewsData.DataBean.RelatedNewsBean> relatedNews = data.getRelatedNews();
        if (relatedNews != null && relatedNews.size() > 0) {
            relatedContainerArticledetails.removeAllViews();
            for (ArticlesNewsData.DataBean.RelatedNewsBean relatednew : relatedNews) {
                View view = LayoutInflater.from(this).inflate(R.layout.articledetails_related_item, relatedContainerArticledetails, false);
                view.setTag(relatednew.getNews_id());
                ViewHolder holder = new ViewHolder(view);
                holder.titleRelatedArticledetails.setText(relatednew.getTitle());
                holder.imgRelatedArticledetails.setImageURI(Uri.parse(relatednew.getThumb()));
                holder.viewArticledetailsNums.setText(relatednew.getClick() + "");
                holder.collectionArticledetails.setText(relatednew.getFavNums() + "");
                holder.commentArticledetails.setText(relatednew.getComment_count() + "");
                relatedContainerArticledetails.addView(view);
            }
        }
    }

    @Override
    public void onLoadNewsFailed(String message) {
        isRefreshing = false;
        if (message != null && !"".equals(message)) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoadCommentsSuccess(ArticleDetailsCommentsData.DataBeanA commentsData) {
        if (commentsData.getTotal() != null && !"0".equals(commentsData.getTotal())) {
            commentnumPostdetails.setText(getString(R.string.comment_articledetails_text, commentsData.getTotal()));
            textviewNomroecommentsArticledetails.setText(getString(R.string.nomorecomment_articledetails));
            if (commentsData.getData() != null && commentsData.getData().size() > 0) {
                commentContainerArticledetails.removeAllViews();
                for (ArticleDetailsCommentsData.DataBeanA.DataBean comment : commentsData.getData()) {
                    View view = LayoutInflater.from(this).inflate(R.layout.articledetails_comment_item, commentContainerArticledetails, false);
                    CommentViewHolder holder = new CommentViewHolder(view);
                    holder.headimagSimpleDraweeView.setImageURI(Uri.parse(comment.getUserheadimage()));
                    holder.nameArticledetails.setText(comment.getComment_user_name());
                    holder.contenTextview.setText(comment.getComment_content());
                    holder.timeArticledetailsText.setText(TimestampUtils.millisecondToTimestamp(Long.parseLong(comment.getComment_time())));
                    commentContainerArticledetails.addView(view);
                }
            }
        } else {
            commentnumPostdetails.setText(getString(R.string.comment_articledetails_text, 0 + ""));
            textviewNomroecommentsArticledetails.setText(getString(R.string.nocomment_articledetails));
        }
    }

    @Override
    public void onLoadCommentsFailed(String message) {
        if (message != null && !"".equals(message)) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    private PopWindowUtils popWindowUtils;

    @OnClick({R.id.titlebar_back, R.id.titlebar_collect, R.id.titlebar_share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titlebar_back:
                finish();
                break;
            case R.id.titlebar_collect:
                break;
            case R.id.titlebar_share:
                if (popWindowUtils == null) {
                    popWindowUtils = new PopWindowUtils(this, root);
                }
                popWindowUtils.showPopWindow();
                break;
        }
    }

    public class ViewHolder implements View.OnClickListener {
        @Bind(R.id.title_related_articledetails)
        TextView titleRelatedArticledetails;
        @Bind(R.id.img_related_articledetails)
        SimpleDraweeView imgRelatedArticledetails;
        @Bind(R.id.view_articledetails_Nums)
        TextView viewArticledetailsNums;
        @Bind(R.id.collection_articledetails)
        TextView collectionArticledetails;
        @Bind(R.id.comment_articledetails)
        TextView commentArticledetails;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ArticleDetailsActivity.this, ArticleDetailsActivity.class);
            intent.putExtra(ArticleDetailsActivity.TAG_ID_EXTRA, (String) v.getTag());
            ArticleDetailsActivity.this.startActivity(intent);
            ArticleDetailsActivity.this.finish();
        }
    }

    public class CommentViewHolder {
        @Bind(R.id.headimag_simpleDraweeView)
        SimpleDraweeView headimagSimpleDraweeView;
        @Bind(R.id.name_articledetails)
        TextView nameArticledetails;
        @Bind(R.id.time_articledetails_text)
        TextView timeArticledetailsText;
        @Bind(R.id.conten_textview)
        TextView contenTextview;

        CommentViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
