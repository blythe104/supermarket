package com.fuyuan.marketmanage.login;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fuyuan.marketmanage.MainActivity;
import com.fuyuan.marketmanage.R;
import com.fuyuan.marketmanage.base.BaseActivity;
import com.fuyuan.marketmanage.register.RegisterActivity;
import com.fuyuan.marketmanage.utils.ToastUtils;
import com.fuyuan.marketmanage.widget.title.CustomTitle;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;


public class LoginActivity extends BaseActivity {


    private EditText mUserName;
    private EditText mUserPwd;
    private Button mBtnLogin;
    private CustomTitle mTitle;

    @Override
    public void initView() {
        setContentView(R.layout.activity_login);

        mUserName = (EditText) findViewById(R.id.et_username);
        mUserPwd = (EditText) findViewById(R.id.et_password);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mTitle = (CustomTitle) findViewById(R.id.titlebar);


    }

    @Override
    public void initListener() {
        mTitle.setTitle("", "快速注册", this);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = mUserName.getText().toString();
                String password = mUserPwd.getText().toString();
                if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
                    ToastUtils.toast(getApplicationContext(), "用户名或者密码不能为空");
                } else {
                    BmobUser personBean = new BmobUser();
                    personBean.setUsername(userName);
                    personBean.setPassword(password);
                    personBean.loginByAccount(userName, password, new LogInListener<Object>() {

                        @Override
                        public void done(Object o, BmobException e) {
                            if (o != null) {
                                ToastUtils.toast(getApplicationContext(), "登录成功");
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            } else {
                                ToastUtils.toast(getApplicationContext(), "登录失败");
                            }

                        }
                    });

                }

            }
        });

    }

    @Override
    public void initData() {

    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.tv_title_tag:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }

    }

}
