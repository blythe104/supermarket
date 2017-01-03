package com.fuyuan.marketmanage.login;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fuyuan.marketmanage.MainActivity;
import com.fuyuan.marketmanage.R;
import com.fuyuan.marketmanage.base.BaseActivity;
import com.fuyuan.marketmanage.config.MyConfig;
import com.fuyuan.marketmanage.register.RegisterActivity;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;


public class LoginActivity extends BaseActivity {


    private EditText mUserName;
    private EditText mUserPwd;
    private Button mBtnLogin;
    private TextView mTitleTag;

    @Override
    public void initView() {
        Bmob.initialize(this, MyConfig.APP_ID);
        setContentView(R.layout.activity_login);

        mUserName = (EditText) findViewById(R.id.et_username);
        mUserPwd = (EditText) findViewById(R.id.et_password);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mTitleTag = (TextView) findViewById(R.id.tv_title_tag);


    }

    @Override
    public void initListener() {
        mTitleTag.setOnClickListener(this);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = mUserName.getText().toString();
                String password = mUserPwd.getText().toString();
                if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
                    toast("用户名或者密码不能为空");
                } else {
                    BmobUser personBean = new BmobUser();
                    personBean.setUsername(userName);
                    personBean.setPassword(password);
                    personBean.loginByAccount(userName, password, new LogInListener<Object>() {

                        @Override
                        public void done(Object o, BmobException e) {
                            if (o != null) {
                                toast("登录成功" + o.toString());
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            } else {
                                toast("登录失败");
                            }

                        }
                    });

                }

            }
        });

    }

    @Override
    public void initData() {
        mTitleTag.setText("快速注册");

    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.tv_title_tag:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }

    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
