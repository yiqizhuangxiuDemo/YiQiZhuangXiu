package com.bwf.yiqizhuangxiu.gui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/10/14.
 *
 */

public abstract class BaseFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(getContentViewId(),null);
    }
    public abstract int getContentViewId();

}
