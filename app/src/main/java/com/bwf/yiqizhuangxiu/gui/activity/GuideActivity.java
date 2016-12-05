package com.bwf.yiqizhuangxiu.gui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bwf.yiqizhuangxiu.R;
import com.bwf.yiqizhuangxiu.application.App;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/1.
 */

public class GuideActivity extends BaseActivity {
    @Bind(R.id.guide_chenck_sex)
    RadioGroup guideChenckSex;
    @Bind(R.id.guide_check_birthday)
    DatePicker guideCheckBirthday;
    @Bind(R.id.guide_check_progress)
    RadioGroup guideCheckProgress;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        Calendar calendar = Calendar.getInstance();
        guideCheckBirthday.init(calendar.get(Calendar.YEAR) - 30, 0, 1, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                birthday = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
            }
        });
        Calendar limitCalendar = Calendar.getInstance();
        limitCalendar.set(calendar.get(Calendar.YEAR) - 80, calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        guideCheckBirthday.setMinDate(limitCalendar.getTimeInMillis());
        limitCalendar.set(calendar.get(Calendar.YEAR) - 18, calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        guideCheckBirthday.setMaxDate(limitCalendar.getTimeInMillis());
    }

    @Override
    protected void initDatas() {
    }

    @OnClick({R.id.cancel_guide, R.id.guide_over})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancel_guide:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case R.id.guide_over:
                if (getBirthday() == null || getSex() == null || getProgress() == 0) {
                    Toast.makeText(this, "您还没有完成所有选项", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences sp = getSharedPreferences(App.SP_CONFIG, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("user_sex", getSex());
                    editor.putString("user_birthday", getBirthday());
                    editor.putInt("user_progress", getProgress());
                    editor.commit();
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                }
                break;
        }
    }

    private String getSex() {
        String result = null;
        switch (guideChenckSex.getCheckedRadioButtonId()) {
            case R.id.sex_man:
                result = "男";
                break;
            case R.id.sex_woman:
                result = "女";
                break;
        }
        return result;
    }

    private String birthday;

    private String getBirthday() {
        return birthday;
    }

    private int getProgress() {
        int result = 0;
        switch (guideCheckProgress.getCheckedRadioButtonId()) {
            case R.id.progress_1:
                result = 1;
                break;
            case R.id.progress_2:
                result = 2;
                break;
            case R.id.progress_3:
                result = 3;
                break;
            case R.id.progress_4:
                result = 4;
                break;
        }
        return result;
    }
}
