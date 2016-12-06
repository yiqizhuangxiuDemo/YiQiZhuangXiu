package com.bwf.yiqizhuangxiu.mvp.model;

import com.bwf.yiqizhuangxiu.entity.WorkPlayProgressData;
import com.bwf.yiqizhuangxiu.entity.WorkPlayingPeopleAndProgressData;

/**
 * Created by Administrator on 2016/12/5 0005.
 */

public interface WorkPlayingModel {
    void loadPeopleAndProgressData(String  buildingId,CallBack callBack);
    void loadProgress(String  buildingId,ProgressCallBack callBack);
    interface CallBack{
        void loadPeopleSuccess(WorkPlayingPeopleAndProgressData data);
        void loadPeopleFail(String e);
    }
    interface ProgressCallBack{
        void loadProgressSuccess(WorkPlayProgressData data);
        void loadProgressFaild(String error);
    }
}
