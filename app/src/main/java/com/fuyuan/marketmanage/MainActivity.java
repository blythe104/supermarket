package com.fuyuan.marketmanage;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fuyuan.marketmanage.base.BaseActivity;
import com.fuyuan.marketmanage.bean.PersonBean;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEtUserName;
    private EditText mEtPwd;
    private Button mBtnLogin;


    @Override
    public void initView() {
        Bmob.initialize(this, "8cdd584e2e541cdb0d020b6cec378b1c");
        setContentView(R.layout.activity_main);
        mEtUserName = (EditText) findViewById(R.id.et_username);
        mEtPwd = (EditText) findViewById(R.id.et_pwd);
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
                if (TextUtils.isEmpty(mEtUserName.getText().toString()) || TextUtils.isEmpty(mEtPwd.getText()
                        .toString())) {
                    Toast.makeText(this, "用户名或者密码添加不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    PersonBean personBean = new PersonBean();
                    personBean.setUsername(mEtUserName.getText().toString());
                    personBean.setPwd(mEtPwd.getText().toString());
                    personBean.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e == null) {
                                toast("添加数据成功，返回objectId为：" + s);
                            } else {
                                toast("添加数据失败：" + e.getMessage());
                            }

                        }
                    });
                }
                break;
        }

    }


    private void toast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
