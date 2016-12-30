package com.fuyuan.marketmanage.login;

import android.app.ProgressDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fuyuan.marketmanage.R;
import com.fuyuan.marketmanage.base.BaseActivity;
import com.fuyuan.marketmanage.bean.PersonBean;

import cn.bmob.v3.Bmob;

public class LoginActivity extends BaseActivity {


    private EditText mUserName;
    private EditText mUserPwd;
    private Button mBtnLogin;

    @Override
    public void initView() {
        setContentView(R.layout.activity_login);
        Bmob.initialize(this, "8cdd584e2e541cdb0d020b6cec378b1c");
        mUserName = (EditText) findViewById(R.id.et_username);
        mUserPwd = (EditText) findViewById(R.id.et_password);
        mBtnLogin = (Button) findViewById(R.id.btn_login);


    }

    @Override
    public void initListener() {
        mBtnLogin.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                String userName = mUserName.getText().toString();
                String password = mUserPwd.getText().toString();
                if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
                    toast("用户名或者密码不能为空");
                } else {
                    final ProgressDialog pd = ProgressDialog.show(LoginActivity.this, "提示", "正在登陆。。。");
                    PersonBean personBean = new PersonBean();
                    personBean.setUsername(userName);
                    personBean.setPassword(password);


                }
                break;
        }

    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
