package com.fuyuan.marketmanage.user;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fuyuan.marketmanage.R;
import com.fuyuan.marketmanage.base.BaseActivity;
import com.fuyuan.marketmanage.user.adapter.UserAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class UserActivity extends BaseActivity {

    private ListView mLlUser;
    private UserAdapter userAdapter;
    private ArrayList<BmobUser> bmobUserList;
    private TextView mTitle;
    private EditText mEtaddName;
    private EditText mEtAddPwd;
    private Button mBtnAdd;

    @Override
    public void initView() {
        setContentView(R.layout.activity_user);
        mLlUser = (ListView) findViewById(R.id.ll_user);
        mTitle = (TextView) findViewById(R.id.tv_title);
        mEtaddName = (EditText) findViewById(R.id.et_addUsername);
        mEtAddPwd = (EditText) findViewById(R.id.et_addUserpwd);
        mBtnAdd = (Button) findViewById(R.id.btn_adduser);
        queryUser();

    }

    @Override
    public void initListener() {
        mBtnAdd.setOnClickListener(this);

    }

    @Override
    public void initData() {
        mTitle.setText("用户管理");
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.btn_adduser:
                register();
                break;
        }

    }

    private void register() {
        BmobUser bmobUser = new BmobUser();
        String userName = mEtaddName.getText().toString();
        String password = mEtAddPwd.getText().toString();
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "用户名或者密码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            bmobUser.setUsername(userName);
            bmobUser.setPassword(password);
            bmobUser.signUp(new SaveListener<Object>() {
                @Override
                public void done(Object o, BmobException e) {
                    if (o != null) {
                        Toast.makeText(UserActivity.this, "用户添加成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(UserActivity.this, "用户添加失败", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }

    //查询用户
    public void queryUser() {
        BmobQuery<BmobUser> query = new BmobQuery<>();
        query.order("-createdAt");
        query.findObjects(new FindListener<BmobUser>() {
            @Override
            public void done(List<BmobUser> list, BmobException e) {
                if (list.size() != 0) {
                    Toast.makeText(UserActivity.this, "用户信息查询成功", Toast.LENGTH_SHORT).show();

                    bmobUserList = (ArrayList<BmobUser>) list;
                    userAdapter = new UserAdapter(UserActivity.this, bmobUserList);
                    mLlUser.setAdapter(userAdapter);

                } else {
                    Toast.makeText(UserActivity.this, "没有用户信息", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
