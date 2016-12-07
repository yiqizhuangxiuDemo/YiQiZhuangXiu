package com.bwf.yiqizhuangxiu.gui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.SchoolConmentData;
import com.bwf.yiqizhuangxiu.gui.activity.ArticleDetailsActivity;
import com.bwf.yiqizhuangxiu.gui.adapter.SchoolComentListAdatper;
import com.bwf.yiqizhuangxiu.mvp.presenter.SchoolCommentPresenter;
import com.bwf.yiqizhuangxiu.mvp.presenter.SchoolTitlePresenter;
import com.bwf.yiqizhuangxiu.mvp.presenter.impl.SchoolCommentPresenterImpl;
import com.bwf.yiqizhuangxiu.mvp.presenter.impl.SchoolTitlePresenterImpl;
import com.bwf.yiqizhuangxiu.mvp.view.SchoolComentView;
import com.bwf.yiqizhuangxiu.mvp.view.SchoolTitleView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/30 0030.
 */

public class FragmentSchoolMain extends BaseFragment implements SchoolTitleView, SchoolComentView {

    @Bind(R.id.scholl_linear_contantier)
    RadioGroup schollLinearContantier;
    @Bind(R.id.school_listview)
    ListView schoolListview;
    ProgressBar progressbarSubviewRecycleviewLoadfooter;
    TextView textviewSubviewRecycleviewLoadfooter;
    private int tag;
    private SchoolTitlePresenter titlePresenter;
    private SchoolCommentPresenter commentPresenter;
    private SchoolComentListAdatper adatper;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_school_main;
    }

    public static FragmentSchoolMain newInstance(int tag) {
        FragmentSchoolMain fragment = new FragmentSchoolMain();
        Bundle bundle = new Bundle();
        bundle.putInt("tag", tag);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        tag = bundle.getInt("tag");
        Log.d("FragmentSchoolMain", "tag:" + tag);

        titlePresenter = new SchoolTitlePresenterImpl(this);
        titlePresenter.loadData(tag);
        isLoading = true;
        commentPresenter = new SchoolCommentPresenterImpl(this);
        adatper = new SchoolComentListAdatper(getContext());
        View view =LayoutInflater.from(getContext()).inflate(R.layout.recycleview_footer,null);
        schoolListview.addFooterView(view);
        progressbarSubviewRecycleviewLoadfooter = (ProgressBar) view.findViewById(R.id.progressbar_subview_recycleview_loadfooter);
        textviewSubviewRecycleviewLoadfooter = (TextView) view.findViewById(R.id.textview_subview_recycleview_loadfooter);
        schoolListview.setAdapter(adatper);
        schoolListview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if (!isLoading && view.getLastVisiblePosition() == adatper.getCount() - 1) {
                    isLoading = true;
                    commentPresenter.loadData(tag);

                }
            }
        });

        schoolListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position != adatper.getCount()-1) {
                    SchoolConmentData.DataBean.ListBean bean = adatper.getItem(position);
                    Intent intent = new Intent(getContext(), ArticleDetailsActivity.class);
                    if (null == bean)
                        return;
                    intent.putExtra(ArticleDetailsActivity.TAG_ID_EXTRA, bean.getNewsId() + "");
                    Toast.makeText(getContext(), "bean.getNewsId():" + bean.getNewsId(), Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
            }
        });
        textviewSubviewRecycleviewLoadfooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentPresenter.loadData(tag);
            }
        });

    }

    private boolean isLoading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void showSchoolTitelDtas(Map<String, String> data) {
        setTitle(data);
        commentPresenter.loadData(tag);


    }

    private LayoutInflater inflater;
    private List<CheckedTextView> list;

    public void setTitle(Map<String, String> data) {
        inflater = LayoutInflater.from(getActivity());
        Set<String> keySet = data.keySet();
        Iterator<String> iterator = keySet.iterator();
        int i = 0;
        list = new ArrayList<>();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String name = data.get(key);
            View view = inflater.inflate(R.layout.scholl_title_bar_item, schollLinearContantier, false);
            final CheckedTextView ctv = (CheckedTextView) view.findViewById(R.id.school_bar_title);
            list.add(ctv);
            ctv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ctv.isChecked()) {
                        return;
                    }
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    if (!ctv.isChecked()) {
                        for (int j = 0; j < list.size(); j++) {
                            if (v == list.get(j)) {
                                ctv.setChecked(true);
                            } else {
                                list.get(j).setChecked(false);
                            }
                        }

                    }
                }
            });
            if (i == 0) {
                ctv.setText("全部");
                i++;
                schollLinearContantier.addView(ctv);
                ctv.setChecked(true);
                continue;
            }
            ctv.setText(name);
            schollLinearContantier.addView(ctv);

//            (RadioButton)View.inflate(getContext(),R.layout.scholl_title_bar_item,null)
//            RadioButton radioButton = new RadioButton(getContext());
//            RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(((BaseActivity)getContext()).dip2px(80), ((BaseActivity)getContext()).dip2px(20));
//            params.setMargins(((BaseActivity)getContext()).dip2px(5),0,0,0);
//            radioButton.setBackgroundResource(R.drawable.popupwindow_title_select);
//            radioButton.setText(name);
//            radioButton.setButtonDrawable(null);
//            radioButton.setTextColor(getResources().getColor(R.color.rdiobutton));
//            radioButton.setTextSize(12);
//            radioButton.setLayoutParams(params);
//            schollLinearContantier.addView(radioButton);
        }
    }


    @Override
    public void showSchoolTitelFial() {

    }

    private void updateFooterState(int state){
        switch (state){
            case 0:
                progressbarSubviewRecycleviewLoadfooter.setVisibility(View.VISIBLE);
                textviewSubviewRecycleviewLoadfooter.setVisibility(View.GONE);
                break;
            case 1:
                progressbarSubviewRecycleviewLoadfooter.setVisibility(View.GONE);
                textviewSubviewRecycleviewLoadfooter.setVisibility(View.VISIBLE);
                textviewSubviewRecycleviewLoadfooter.setText("加载失败，点击重试");
                break;
            case 2:
                progressbarSubviewRecycleviewLoadfooter.setVisibility(View.GONE);
                textviewSubviewRecycleviewLoadfooter.setVisibility(View.VISIBLE);
                textviewSubviewRecycleviewLoadfooter.setText("没有更多数据了~");
                break;

        }
    }

    @Override
    public void showSchoolComment(List<SchoolConmentData.DataBean.ListBean> listBeen) {
        adatper.addData(listBeen);
        Log.d("FragmentSchoolMain",listBeen.toString());
        isLoading=false;
        if (null == listBeen || listBeen.size() == 0 || null == listBeen.get(0)){
            Log.d("FragmentSchoolMain", "gong");
            progressbarSubviewRecycleviewLoadfooter.setVisibility(View.GONE);
            textviewSubviewRecycleviewLoadfooter.setVisibility(View.GONE);
        }else
            updateFooterState(0);
    }

    @Override
    public void showSchoolComentFail() {
        isLoading=false;
        updateFooterState(1);
    }
}
