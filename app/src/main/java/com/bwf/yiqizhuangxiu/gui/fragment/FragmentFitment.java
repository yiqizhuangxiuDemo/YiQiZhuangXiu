package com.bwf.yiqizhuangxiu.gui.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwf.yiqizhuangxiu.R;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Administrator on 2016/11/26.
 */

public class FragmentFitment extends android.support.v4.app.Fragment {
    TextView textView12;
    TextView textView11;
    TextView textView13;
    SimpleDraweeView simpleDraweeView;


    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_layout_item_fitment, null);
        Bundle bundle = getArguments();
        textView12 = (TextView) view.findViewById(R.id.textView12);
        textView11 = (TextView) view.findViewById(R.id.textView11);
        textView13 = (TextView) view.findViewById(R.id.textView13);
        simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.imageView1);
        if (bundle != null) {
            String title = bundle.getString("title");
            String icon = bundle.getString("icon");
            textView13.setText(title);
            Uri uri = Uri.parse(icon);
            simpleDraweeView.setImageURI(uri);
            String threadsnum = bundle.getString("threadsnum");
            textView11.setText("回贴：" + threadsnum);
            int postsnum = bundle.getInt("postsnum");
            textView12.setText("总贴：" + postsnum);
        }
        return view;
    }


}
