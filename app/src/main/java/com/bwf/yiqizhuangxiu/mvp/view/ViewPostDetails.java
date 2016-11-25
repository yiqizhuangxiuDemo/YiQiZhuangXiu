package com.bwf.yiqizhuangxiu.mvp.view;

import com.bwf.yiqizhuangxiu.entity.PostDetailsContentDataBean;
import com.bwf.yiqizhuangxiu.entity.PostDetailsLikeData;

/**
 * Created by Administrator on 2016/11/25.
 */

public interface ViewPostDetails {
    void onLoadContentFailed(PostDetailsContentDataBean.DataBean data);

    void onLoadContentFailed(String message);

    void onLoadLikeSuccess(PostDetailsLikeData data);

    void onLoadLikeFailed(String message);
}
