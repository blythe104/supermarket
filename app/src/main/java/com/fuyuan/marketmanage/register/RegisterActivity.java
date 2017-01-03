package com.fuyuan.marketmanage.register;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fuyuan.marketmanage.R;
import com.fuyuan.marketmanage.base.BaseActivity;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends BaseActivity {


    private EditText mUserName;
    private EditText mUserPwd;
    private Button mBtnRegister;

    @Override
    public void initView() {
        setContentView(R.layout.activity_register);
        mUserName = (EditText) findViewById(R.id.et_username);
        mUserPwd = (EditText) findViewById(R.id.et_password);
        mBtnRegister = (Button) findViewById(R.id.btn_register);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        mBtnRegister.setOnClickListener(this);

    }


    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                BmobUser bmobUser = new BmobUser();
                String userName = mUserName.getText().toString();
                String password = mUserPwd.getText().toString();
                if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
                    Toast.makeText(this, "用户名或者密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    bmobUser.setUsername(userName);
                    bmobUser.setPassword(password);
                    bmobUser.signUp(new SaveListener<Object>() {
                        @Override
                        public void done(Object o, BmobException e) {
                            if (o != null) {
                                Toast.makeText(RegisterActivity.this, "用户注册成功", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RegisterActivity.this, "用户注册失败", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
                break;
        }

    }
}
