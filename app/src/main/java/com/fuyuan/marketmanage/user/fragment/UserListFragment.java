package com.fuyuan.marketmanage.user.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.fuyuan.marketmanage.R;
import com.fuyuan.marketmanage.base.BaseFragment;
import com.fuyuan.marketmanage.user.adapter.UserAdapter;
import com.fuyuan.marketmanage.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by xin on 2017/1/3.
 */

public class UserListFragment extends BaseFragment {

    private SwipeRefreshLayout mRefresh;
    private ListView mLlUser;
    private UserAdapter userAdapter;
    private ArrayList<BmobUser> bmobUserList;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_fragment_layout, null);
        mLlUser = (ListView) view.findViewById(R.id.ll_user);
        mRefresh = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRefresh.setRefreshing(false);
                    }
                }, 5000);
            }
        });
        queryUser();
        return view;
    }


    //查询用户
    public void queryUser() {
        BmobQuery<BmobUser> query = new BmobQuery<>();
        query.order("-createdAt");
        query.findObjects(new FindListener<BmobUser>() {
            @Override
            public void done(List<BmobUser> list, BmobException e) {
                if (list.size() != 0) {
                    ToastUtils.toast(mActivity, "用户信息查询成功");

                    bmobUserList = (ArrayList<BmobUser>) list;
                    userAdapter = new UserAdapter(mActivity, bmobUserList);
                    mLlUser.setAdapter(userAdapter);
                    userAdapter.notifyDataSetChanged();

                } else {
                    ToastUtils.toast(mActivity, "没有用户信息");
                }
            }
        });
    }
}
