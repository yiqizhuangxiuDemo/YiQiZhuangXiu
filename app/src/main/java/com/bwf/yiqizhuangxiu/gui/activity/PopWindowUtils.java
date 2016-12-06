package com.bwf.yiqizhuangxiu.gui.activity;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.bwf.yiqizhuangxiu.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ${yong} on 2016/12/5.
 */

public class PopWindowUtils {
    private PopupWindow pop;
    private Context context;
    private View root;

    public PopWindowUtils(Context context, View root) {
        this.context = context;
        this.root = root;
    }

    public void showPopWindow() {
        if (pop == null) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_share_layout, null);
            ViewHolder holder = new ViewHolder(view);
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (pop.isShowing()) {
                        pop.dismiss();
                    }
                    return false;
                }
            });
            pop = new PopupWindow(context);
            pop.setContentView(view);
            pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            pop.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            pop.setBackgroundDrawable(new BitmapDrawable());
            pop.setFocusable(true);
            pop.setOutsideTouchable(true);
            pop.setAnimationStyle(R.style.popupwindow_anim);
        }
        if (pop.isShowing()) {
            pop.dismiss();
        } else {
            pop.showAtLocation(root, Gravity.BOTTOM, 0, 0);
        }
    }

    public class ViewHolder {
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        @OnClick({R.id.root_content,R.id.item_share_layout_qq, R.id.item_share_layout_wechat, R.id.item_share_layout_wechatmoments, R.id.item_share_layout_qzone, R.id.item_share_layout_sinaweibo, R.id.item_share_layout_btn})
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.item_share_layout_qq:
                    Toast.makeText(context, "QQ", Toast.LENGTH_SHORT).show();
                    pop.dismiss();
                    break;
                case R.id.item_share_layout_wechat:
                    Toast.makeText(context, "微信", Toast.LENGTH_SHORT).show();
                    pop.dismiss();
                    break;
                case R.id.item_share_layout_wechatmoments:
                    Toast.makeText(context, "朋友圈", Toast.LENGTH_SHORT).show();
                    pop.dismiss();
                    break;
                case R.id.item_share_layout_qzone:
                    Toast.makeText(context, "QQ空间", Toast.LENGTH_SHORT).show();
                    pop.dismiss();
                    break;
                case R.id.item_share_layout_sinaweibo:
                    Toast.makeText(context, "新浪微博", Toast.LENGTH_SHORT).show();
                    pop.dismiss();
                    break;
                case R.id.item_share_layout_btn:
                    pop.dismiss();
                    break;
                case R.id.root_content:
                    break;
            }
        }
    }
}
