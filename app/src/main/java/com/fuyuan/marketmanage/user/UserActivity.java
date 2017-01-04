package com.fuyuan.marketmanage.user;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.fuyuan.marketmanage.R;
import com.fuyuan.marketmanage.base.BaseActivity;
import com.fuyuan.marketmanage.base.BaseFragment;
import com.fuyuan.marketmanage.user.adapter.FragmentAdapter;
import com.fuyuan.marketmanage.user.fragment.UserListFragment;
import com.fuyuan.marketmanage.user.fragment.UserManagerFragment;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends BaseActivity {

    private TextView mTitle;
    private ViewPager mViewPager;
    private List<BaseFragment> fragments;
    private android.support.design.widget.TabLayout tableLayout;

    @Override
    public void initView() {
        setContentView(R.layout.activity_user);
        //设置键盘模式
        getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        tableLayout = (TabLayout) findViewById(R.id.tabs);
        mTitle = (TextView) findViewById(R.id.tv_title);


    }

    @Override
    public void initListener() {


    }

    @Override
    public void initData() {
        mTitle.setText("用户管理");
        fragments = new ArrayList<>();
        fragments.add(new UserListFragment());
        fragments.add(new UserManagerFragment());
        List<String> tabList = new ArrayList<String>();
        tabList.add("用户列表");
        tabList.add("用户操作");
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments, tabList);
        mViewPager.setAdapter(fragmentAdapter);
        tableLayout.setupWithViewPager(mViewPager);
        tableLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
        }

    }


}
