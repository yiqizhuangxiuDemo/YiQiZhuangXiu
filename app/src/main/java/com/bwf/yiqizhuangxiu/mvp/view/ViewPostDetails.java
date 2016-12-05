package com.bwf.yiqizhuangxiu.mvp.view;

import com.bwf.yiqizhuangxiu.entity.PostDetailsCommentsData;
import com.bwf.yiqizhuangxiu.entity.PostDetailsContentDataBean;
import com.bwf.yiqizhuangxiu.entity.PostDetailsLikeData;

import java.util.List;

/**
 * Created by Administrator on 2016/11/25.
 */

public interface ViewPostDetails {
    void onLoadContentSuccess(PostDetailsContentDataBean.DataBean data);

    void onLoadContentFailed(String message);

    void onLoadLikeSuccess(PostDetailsLikeData data);

    void onLoadLikeFailed(String message);

    void onLoadCommentsSuccess(List<PostDetailsCommentsData.DataBean> datas,int commentNum);

    void onLoadCommentsFailed(String message);
}
