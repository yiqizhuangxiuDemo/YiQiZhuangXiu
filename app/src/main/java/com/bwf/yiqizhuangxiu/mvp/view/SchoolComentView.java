package com.bwf.yiqizhuangxiu.mvp.view;

import com.bwf.yiqizhuangxiu.entity.SchoolConmentData;

import java.util.List;

/**
 * Created by Administrator on 2016/12/1 0001.
 */

public interface SchoolComentView {
    void showSchoolComment(List<SchoolConmentData.DataBean.ListBean> listBeen);
    void showSchoolComentFail();
}
