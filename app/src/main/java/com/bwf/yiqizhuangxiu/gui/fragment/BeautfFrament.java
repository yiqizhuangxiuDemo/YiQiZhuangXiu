package com.bwf.yiqizhuangxiu.gui.fragment;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.entity.EffectBeatifulData;
import com.bwf.yiqizhuangxiu.gui.adapter.EffectBeatifulAdapter;
import com.bwf.yiqizhuangxiu.gui.adapter.EffectViewPagerPopuwindowAdatper;
import com.bwf.yiqizhuangxiu.gui.adapter.popwindowGridViewAdapter;
import com.bwf.yiqizhuangxiu.mvp.presenter.EffectBeatifulPresenter;
import com.bwf.yiqizhuangxiu.mvp.presenter.impl.EffectBeatifulPresenterImpl;
import com.bwf.yiqizhuangxiu.mvp.view.EffectBeatifulView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/28 0028.
 */

public class BeautfFrament extends BaseFragment implements EffectBeatifulView {
    @Bind(R.id.checked_textview_space)
    CheckedTextView checkedTextviewSpace;
    @Bind(R.id.checked_textview_style)
    CheckedTextView checkedTextviewStyle;
    @Bind(R.id.checked_textview_local)
    CheckedTextView checkedTextviewLocal;
    @Bind(R.id.checked_textview_color)
    CheckedTextView checkedTextviewColor;
    @Bind(R.id.linear_beautiful_container)
    LinearLayout linearBeautifulContainer;
    @Bind(R.id.relativie_space)
    RelativeLayout relativieSpace;
    @Bind(R.id.relativie_style)
    RelativeLayout relativieStyle;
    @Bind(R.id.relativie_local)
    RelativeLayout relativieLocal;
    @Bind(R.id.relativie_color)
    RelativeLayout relativieColor;
    @Bind(R.id.beautiful_grid_view)
    GridView beautifulGridView;
    private EffectViewPagerPopuwindowAdatper creamAdatper;
    //数据源
    private List<String> listSpace;
    private List<String> listStyle;
    private List<String> listLocal;
    private List<String> listColor;
    private String[] space = {"全部", "客厅", "玄关", "起居室", "厨房", "餐厅", "衣帽间", "庭院", "卫生间", "阳台", "儿童房", "露台", "卧室", "书房", "娱乐"};
    private String[] style = new String[]{"全部", "现代", "简欧", "简约", "欧式", "田园", "中式", "欧式古典", "北欧", "小资", "新中式", "混搭", "乡村", "美式", "宜家", "日式", "新古典", "地中海", "东南亚"};
    private String[] local = new String[]{"全部", "背景墙", "收纳", "照片墙", "墙面", "窗帘", "餐台", "储物区", "隔断", "飘窗", "楼梯", "门窗", "吧台", "工作区", "吊顶", "地面", "灯具", "过道", "地台", "壁炉"};

    private EffectBeatifulAdapter beatifulAdapter;
    private EffectBeatifulPresenter beatifulPresenter;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_beautf;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initListData();
        for (int i = 0; i < popupWindows.length; i++) {
            initPopwindow(i);
        }


        beatifulAdapter = new EffectBeatifulAdapter(getContext());
        beautifulGridView.setAdapter(beatifulAdapter);
        beatifulPresenter = new EffectBeatifulPresenterImpl(this);
        beatifulPresenter.loadData();
        beaufitIsLoading = true;
        beautifulGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                viewHolder.imagePopupwindowSchool.setCurrentItem(position);
                if (position == 0)
                    setCameraTitle(0);
                popupWindowCearm.showAtLocation(parent, Gravity.CENTER, 0, 0);
            }
        });
        beautifulGridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (!beaufitIsLoading && beautifulGridView.getLastVisiblePosition() == beatifulAdapter.getCount() - 1) {
                    beatifulPresenter.loadData();
                    beaufitIsLoading = true;
                }
            }
        });

    }

    private boolean beaufitIsLoading;


    PopupWindow popupWindowCearm;


    private void initListData() {
        listSpace = new ArrayList<>();
        listStyle = new ArrayList<>();
        listLocal = new ArrayList<>();
        for (int i = 0; i < space.length; i++) {
            listSpace.add(space[i]);
        }
        for (int i = 0; i < style.length; i++) {
            listStyle.add(style[i]);
        }
        for (int i = 0; i < local.length; i++) {
            listLocal.add(local[i]);
        }

    }


    private WindowManager manager;
    private View views[] = new View[3];
    private popwindowGridViewAdapter adapter;
    private PopwindowViewHolder holder;

    private void initPopwindow(final int i) {
        List<String> list = new ArrayList<>();
        if (i == 0) {
            list = listSpace;
        } else if (i == 1) {
            list = listStyle;
        } else {
            list = listLocal;
        }
        views[i] = LayoutInflater.from(getContext()).inflate(R.layout.popwindow_space_beautiful, null, false);
        holder = new PopwindowViewHolder(views[i]);
        popupWindows[i] = new PopupWindow(views[i], WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindows[i].setTouchable(true);
        popupWindows[i].setFocusable(true);
        popupWindows[i].setBackgroundDrawable(new BitmapDrawable());
        views[i].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupWindows[i] != null && popupWindows[i].isShowing()) {
                    popupWindows[i].dismiss();
                }
                setCheckedTextViewIsNo();
                return false;
            }
        });
        popupWindows[i].setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setCheckedTextViewIsNo();
            }
        });
        popupWindows[i].setAnimationStyle(R.style.popupwindow_effect_anim);
        adapter = new popwindowGridViewAdapter(getContext(), list);
        holder.recyclerviewPopwindow.setAdapter(adapter);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        check = new CheckedTextView[]{checkedTextviewSpace, checkedTextviewStyle, checkedTextviewLocal, checkedTextviewColor};
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.relativie_space, R.id.relativie_style, R.id.relativie_local, R.id.relativie_color})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.relativie_space:
                checkedTextviewSpace.setChecked(true);
                popupWindows[0].showAsDropDown(checkedTextviewSpace);
                break;
            case R.id.relativie_style:
                checkedTextviewStyle.setChecked(true);
                popupWindows[1].showAsDropDown(checkedTextviewSpace);
                break;
            case R.id.relativie_local:
                checkedTextviewLocal.setChecked(true);
                popupWindows[2].showAsDropDown(checkedTextviewSpace);
                break;
            case R.id.relativie_color:
                checkedTextviewColor.setChecked(true);
                break;
        }
    }

    @Override
    public void showEffectBeatifulSuccess(List<EffectBeatifulData.DataBean.ListBean> listBeen) {
        beaufitIsLoading = false;
        beatifulAdapter.addData(listBeen);
        inItPopupWindowCearm(listBeen);
        creamAdatper.addData(listBeen);
    }

    private void inItPopupWindowCearm(final List<EffectBeatifulData.DataBean.ListBean> listBeen) {
        if (popupWindowCearm == null) {
            popupWindowCearm = new PopupWindow();
            View view = LayoutInflater.from(getContext()).inflate(R.layout.school_cearm, null);
            if (viewHolder == null)
                viewHolder = new ViewHolder(view);
            popupWindowCearm.setContentView(view);
            popupWindowCearm.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
            popupWindowCearm.setHeight(WindowManager.LayoutParams.MATCH_PARENT);
            popupWindowCearm.setTouchable(true);
            popupWindowCearm.setBackgroundDrawable(new BitmapDrawable());
            popupWindowCearm.setAnimationStyle(R.style.popupwindow_anim_scale);
            creamAdatper = new EffectViewPagerPopuwindowAdatper(getContext());
            viewHolder.imagePopupwindowSchool.setAdapter(creamAdatper);
            viewHolder.imagePopupwindowSchool.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    creamAdatper.notifyDataSetChanged();
                    setCameraTitle(position);

                }
            });
            viewHolder.imagePopupwindowBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindowCearm.dismiss();
                }
            });
        }
    }

    public boolean disMissPopupWindow() {
        if (null != popupWindowCearm && popupWindowCearm.isShowing()) {
            popupWindowCearm.dismiss();
            return true;
        }
        for (int i = 0; i < popupWindows.length; i++) {
            if (popupWindows[i].isShowing()) {
                popupWindows[i].dismiss();
                setCheckedTextViewIsNo();
                return true;
            }
        }
        return false;
    }

    private void setCameraTitle(int position) {
        EffectBeatifulData.DataBean.ListBean bean = creamAdatper.getItemData(position);
        viewHolder.text_title_pop.setText(bean.getTitle());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bean.getTag().size(); i++) {
            sb.append("#" + bean.getTag().get(i).getName());
        }

        viewHolder.text_little_titel_pop.setText(sb.toString());
    }

    @Override
    public void showEffectBeatifulFail() {
        beaufitIsLoading = false;
    }

    static class PopwindowViewHolder {
        @Bind(R.id.recyclerview_popwindow)
        GridView recyclerviewPopwindow;

        PopwindowViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private PopupWindow popupWindows[] = new PopupWindow[3];
    private CheckedTextView[] check;

    public void setCheckedTextViewIsNo() {

        for (int i = 0; i < check.length; i++) {
            if (check[i].isChecked()) {
                check[i].setChecked(false);
            }
        }

    }

    private ViewHolder viewHolder;

    static class ViewHolder {
        @Bind(R.id.image_popupwindow_back)
        ImageView imagePopupwindowBack;
        @Bind(R.id.image_popupwindow_school)
        ViewPager imagePopupwindowSchool;
        @Bind(R.id.text_desing_request)
        TextView textDesingRequest;
        @Bind(R.id.image_zan)
        ImageView imageZan;
        @Bind(R.id.image_zixun)
        ImageView imageZixun;
        @Bind(R.id.image_share)
        ImageView imageShare;
        @Bind(R.id.text_title_pop)
        TextView text_title_pop;
        @Bind(R.id.text_little_titel_pop)
        TextView text_little_titel_pop;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    //定义回调函数及变量
    public interface BackHandlerInterface{
        void setSelectFragment(BeautfFrament fragment);
    }
    protected  BackHandlerInterface backHandlerInterface;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (! (getActivity() instanceof BackHandlerInterface)){

        }else {
            backHandlerInterface = (BackHandlerInterface) getActivity();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        backHandlerInterface.setSelectFragment(this);
    }
}
