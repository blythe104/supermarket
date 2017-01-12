package com.fuyuan.marketmanage.user.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.fuyuan.marketmanage.R;
import com.fuyuan.marketmanage.base.BaseFragment;
import com.fuyuan.marketmanage.login.LoginActivity;
import com.fuyuan.marketmanage.utils.ToastUtils;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
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
    private String objectId;
    private String userName;
    private EditText mEtNewPwd;
    private EditText mEtOldPwd;
    private Button mBtnUpdate;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.manager_fragment, null);
        //设置键盘模式
        //        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        mEtaddName = (EditText) view.findViewById(R.id.et_addUsername);
        mEtAddPwd = (EditText) view.findViewById(R.id.et_addUserpwd);
        mBtnAdd = (Button) view.findViewById(R.id.btn_adduser);
        mEtDelUser = (EditText) view.findViewById(R.id.et_delUser);
        mBtnDelUser = (Button) view.findViewById(R.id.btn_delUser);
        mEtNewPwd = (EditText) view.findViewById(R.id.et_newPwd);
        mEtOldPwd = (EditText) view.findViewById(R.id.et_oldPwd);
        mBtnUpdate = (Button) view.findViewById(R.id.btn_updateUser);

        setListener();
        return view;
    }

    private void setListener() {
        mBtnAdd.setOnClickListener(this);
        mBtnDelUser.setOnClickListener(this);
        mBtnUpdate.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_adduser:
                register();
                break;
            case R.id.btn_delUser:
                userName = mEtDelUser.getText().toString();
                if (TextUtils.isEmpty(userName)) {
                    ToastUtils.toast(mActivity, "删除时用户名不能为空");
                } else {
                    getObjectId(userName);
                }
                break;
            case R.id.btn_updateUser:
                String oldPwd = mEtOldPwd.getText().toString();
                String newPwd = mEtNewPwd.getText().toString();
                if (TextUtils.isEmpty(oldPwd) || TextUtils.isEmpty(newPwd)) {
                    ToastUtils.toast(mActivity, "新密码或者旧密码不能为空");
                } else {
                    updatePwd(oldPwd, newPwd);
                }

                break;
        }

    }

    /**
     * 更新密码
     *
     * @param oldPwd
     * @param newPwd
     */
    private void updatePwd(String oldPwd, String newPwd) {
        BmobUser.updateCurrentUserPassword(oldPwd, newPwd, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    ToastUtils.toast(mActivity, "密码修改成功，请重新登录");
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    ToastUtils.toast(mActivity, "失败" + e.getMessage());
                }
            }
        });
    }

    private void delUser(String userName) {
        BmobUser del = new BmobUser();
        del.setUsername(userName);
        del.delete(objectId, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    ToastUtils.toast(mActivity, "用户删除成功" + e.toString());
                } else {
                    ToastUtils.toast(mActivity, "用户删除失败" + e.toString());
                }
            }
        });
    }

    /**
     * 获取objectId
     *
     * @param userName
     * @return
     */
    private String getObjectId(final String userName) {
        BmobQuery<BmobUser> query = new BmobQuery<BmobUser>();
        query.addWhereEqualTo("username", userName);
        query.findObjects(new FindListener<BmobUser>() {
            @Override
            public void done(List<BmobUser> list, BmobException e) {
                if (e == null) {
                    BmobUser user = list.get(0);
                    objectId = user.getObjectId();
                    //删除用户 无权删除用户数据
                    delUser(userName);
                } else {
                    Log.e("*e", "获取objectId错误: " + e.toString());
                }
            }
        });
        return objectId;
    }


    private void register() {
        BmobUser bmobUser = new BmobUser();
        String userName = mEtaddName.getText().toString();
        String password = mEtAddPwd.getText().toString();
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            ToastUtils.toast(mActivity, "用户名或者密码不能为空");
        } else {
            bmobUser.setUsername(userName);
            bmobUser.setPassword(password);
            bmobUser.signUp(new SaveListener<Object>() {
                @Override
                public void done(Object o, BmobException e) {
                    if (o != null) {
                        ToastUtils.toast(mActivity, "用户添加成功");
                    } else {
                        ToastUtils.toast(mActivity, "用户添加失败");
                    }

                }
            });
        }
    }
}
