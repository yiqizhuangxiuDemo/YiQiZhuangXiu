package com.bwf.yiqizhuangxiu.mvp.model;

import com.bwf.yiqizhuangxiu.entity.PostDetailsCommentsData;
import com.bwf.yiqizhuangxiu.entity.PostDetailsContentDataBean;
import com.bwf.yiqizhuangxiu.entity.PostDetailsLikeData;

import java.util.List;

/**
 * Created by Administrator on 2016/11/25.
 */

public interface ModelPostDetails {
    void loadAllData(String id, int page, CallBack callBack);

    void loadPostDetailsContentData(String id, CallBack callBack);

    void loadPostDetailsLikeData(String id, CallBack callBack);

    void loadPostDetailsCommentsData(String id, int page, CallBack callBack);

    interface CallBack {
        void onLoadContentSuccess(PostDetailsContentDataBean.DataBean data);

        void onLoadContentFailed(String message);

        void onLoadLikeSuccess(PostDetailsLikeData data);

        void onLoadLikeFailed(String message);

        void onLoadCommentsSuccess(List<PostDetailsCommentsData.DataBean> datas,int commentsNum);

        void onLoadCommentsFailed(String message);

    }
}