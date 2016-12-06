package com.bwf.yiqizhuangxiu.mvp.view;

import com.bwf.yiqizhuangxiu.entity.WorkPlayProgressData;
import com.bwf.yiqizhuangxiu.entity.WorkPlayingPeopleAndProgressData;

/**
 * Created by Administrator on 2016/12/5 0005.
 */

public interface WorkPlayingView {
    void showWorkPeopleAndProgress(WorkPlayingPeopleAndProgressData data);
    void showorkPeopleFail(String error);
    void showProgressDataSuccess(WorkPlayProgressData data);
    void showProgressFail(String error);
}
