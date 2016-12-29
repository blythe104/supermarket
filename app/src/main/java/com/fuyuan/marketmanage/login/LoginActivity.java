package com.fuyuan.marketmanage.login;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fuyuan.marketmanage.R;
import com.fuyuan.marketmanage.base.BaseActivity;
import com.fuyuan.marketmanage.bean.PersonBean;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

public class LoginActivity extends BaseActivity {


    private EditText mUserName;
    private EditText mUserPwd;
    private Button mBtnLogin;

    @Override
    public void initView() {
        setContentView(R.layout.activity_login);
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
                final String password = mUserPwd.getText().toString();
                if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
                    Toast.makeText(this, "用户名或者密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                     BmobQuery<PersonBean> personBeanBmobQuery = new BmobQuery<>();
                    personBeanBmobQuery.getObject(userName, new QueryListener<PersonBean>() {
                        @Override
                        public void done(PersonBean personBean, BmobException e) {
                            if (e == null) {
//                                //表示能够查询到
//                                personBeanBmobQuery.getObject(password, new QueryListener<PersonBean>() {
//                                    @Override
//                                    public void done(PersonBean personBean, BmobException e) {
//                                        if (e == null) {
//                                            //用户名和密码都存在
//                                            toast("此用户存在");
////                                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                                        } else {
//                                            toast("此用户不存在");
//                                        }
//                                    }
//                                });
                            } else {
                                toast("用户名不存在");

                            }
                        }
                    });
                }
                break;
        }

    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
