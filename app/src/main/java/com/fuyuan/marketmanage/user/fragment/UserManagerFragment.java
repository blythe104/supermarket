package com.fuyuan.marketmanage.user.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fuyuan.marketmanage.R;
import com.fuyuan.marketmanage.base.BaseFragment;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by xin on 2017/1/3.
 */

public class UserManagerFragment extends BaseFragment implements View.OnClickListener {

    private EditText mEtaddName;
    private EditText mEtAddPwd;
    private Button mBtnAdd;
    private EditText mEtDelUser;
    private Button mBtnDelUser;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.manager_fragment, null);
        mEtaddName = (EditText) view.findViewById(R.id.et_addUsername);
        mEtAddPwd = (EditText) view.findViewById(R.id.et_addUserpwd);
        mBtnAdd = (Button) view.findViewById(R.id.btn_adduser);
        mEtDelUser = (EditText) view.findViewById(R.id.et_delUser);
        mBtnDelUser = (Button) view.findViewById(R.id.btn_delUser);
        setListener();
        return view;
    }

    private void setListener() {
        mBtnAdd.setOnClickListener(this);
        mBtnDelUser.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_adduser:
                register();
                break;
            case R.id.btn_delUser:
                delUser();
                break;
        }

    }

    private void delUser() {
        String userName = mEtDelUser.getText().toString();
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(mActivity, "删除时用户名不能为空", Toast.LENGTH_SHORT).show();
        } else {
            BmobUser del = new BmobUser();
            del.setUsername(userName);
            del.delete(new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        Toast.makeText(mActivity, "用户删除成功"+e.toString(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mActivity, "用户删除失败"+e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    private void register() {
        BmobUser bmobUser = new BmobUser();
        String userName = mEtaddName.getText().toString();
        String password = mEtAddPwd.getText().toString();
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            Toast.makeText(mActivity, "用户名或者密码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            bmobUser.setUsername(userName);
            bmobUser.setPassword(password);
            bmobUser.signUp(new SaveListener<Object>() {
                @Override
                public void done(Object o, BmobException e) {
                    if (o != null) {
                        Toast.makeText(mActivity, "用户添加成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mActivity, "用户添加失败", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }
}
