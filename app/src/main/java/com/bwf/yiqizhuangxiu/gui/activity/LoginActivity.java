package com.bwf.yiqizhuangxiu.gui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwf.yiqizhuangxiu.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ${yong} on 2016/12/5.
 */

public class LoginActivity extends BaseActivity {
    @Bind(R.id.loginactivity_cancle)
    TextView loginactivityCancle;
    @Bind(R.id.loginactivity_account)
    EditText loginactivityAccount;
    @Bind(R.id.loginactivity_password)
    EditText loginactivityPassword;
    @Bind(R.id.loginactivity_see)
    ImageView loginactivitySee;
    @Bind(R.id.loginactivity_sms)
    TextView loginactivitySms;
    @Bind(R.id.loginactivity_forget_password)
    TextView loginactivityForgetPassword;
    @Bind(R.id.loginactivity_login)
    Button loginactivityLogin;

    @Override
    protected int getContentViewResId() {
        return R.layout.loginactivity;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.loginactivity_cancle, R.id.loginactivity_see, R.id.loginactivity_sms, R.id.loginactivity_forget_password, R.id.loginactivity_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginactivity_cancle:
                finish();
                Toast.makeText(this, "取消", Toast.LENGTH_SHORT).show();
                break;
            case R.id.loginactivity_see:
                Toast.makeText(this, "查看", Toast.LENGTH_SHORT).show();
                break;
            case R.id.loginactivity_sms:
                Toast.makeText(this, "短信登陸", Toast.LENGTH_SHORT).show();
                break;
            case R.id.loginactivity_forget_password:
                Toast.makeText(this, "忘記密碼", Toast.LENGTH_SHORT).show();
                break;
            case R.id.loginactivity_login:
                Toast.makeText(this, "登陸", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
