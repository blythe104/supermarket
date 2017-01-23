package com.fuyuan.marketmanage.register;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fuyuan.marketmanage.R;
import com.fuyuan.marketmanage.base.BaseActivity;
import com.fuyuan.marketmanage.login.LoginActivity;
import com.fuyuan.marketmanage.utils.ToastUtils;
import com.fuyuan.marketmanage.widget.title.CustomTitle;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends BaseActivity {


    private EditText mUserName;
    private EditText mUserPwd;
    private Button mBtnRegister;
    private CustomTitle mTitle;

    @Override
    public void initView() {
        setContentView(R.layout.activity_register);
        mUserName = (EditText) findViewById(R.id.et_username);
        mUserPwd = (EditText) findViewById(R.id.et_password);
        mBtnRegister = (Button) findViewById(R.id.btn_register);
        mTitle = (CustomTitle) findViewById(R.id.titlebar);
    }

    @Override
    public void initData() {
        mTitle.setTitle("注册");
    }

    @Override
    public void initListener() {
        mBtnRegister.setOnClickListener(this);

    }


    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                register();
                break;
        }

    }

    private void register() {
        BmobUser bmobUser = new BmobUser();
        String userName = mUserName.getText().toString();
        String password = mUserPwd.getText().toString();
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            ToastUtils.toast(this, "用户名或者密码不能为空");
        } else {
            bmobUser.setUsername(userName);
            bmobUser.setPassword(password);
            bmobUser.signUp(new SaveListener<Object>() {
                @Override
                public void done(Object o, BmobException e) {
                    if (o != null) {
                        ToastUtils.toast(getApplicationContext(), "用户注册成功");
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    } else {
                        ToastUtils.toast(getApplicationContext(), "用户注册失败");
                    }

                }
            });
        }
    }
}
