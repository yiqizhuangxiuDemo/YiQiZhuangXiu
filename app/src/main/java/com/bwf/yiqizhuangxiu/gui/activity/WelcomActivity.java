package com.bwf.yiqizhuangxiu.gui.activity;

import android.content.Intent;
import android.content.SharedPreferences;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.application.App;

/**
 * Created by Administrator on 2016/12/1.
 */

public class WelcomActivity extends BaseActivity {

    private boolean isFinishByUser;
    public final static String SP_CONFIG_ISFIRSTUSE_KEY = "isfirstuse";

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_welcom;
    }

    @Override
    protected void initViews() {
        setFullScreen();
    }

    @Override
    protected void initDatas() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (isFinishByUser) {
                    return;
                }
                if (isFirstUse()) {
                    gotoGuideActivity();
                } else {
                    gotoMainActivity();
                }
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        isFinishByUser = true;
        super.onDestroy();
    }

    private void gotoMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void gotoGuideActivity() {
        startActivity(new Intent(this, GuideActivity.class));
        finish();
    }

    private boolean isFirstUse() {
        SharedPreferences sp = getSharedPreferences(App.SP_CONFIG, MODE_PRIVATE);
        if (sp.getInt("user_progress", 0) == 0) {
            return true;
        } else {
            return false;
        }
    }
}
