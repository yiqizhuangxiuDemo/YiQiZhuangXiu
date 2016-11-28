package com.bwf.yiqizhuangxiu.gui.fragment;


import android.widget.LinearLayout;

import com.bwf.yiqizhuangxiu.R;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/11/25 0025.
 */

public class TogetherFragment extends BaseFragment {
    @Bind(R.id.linear_tream)
    LinearLayout linearTream;

    @Override
    public int getContentViewId() {
        return R.layout.together_fragment;
    }
}
