package com.fuyuan.marketmanage.supply;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.fuyuan.marketmanage.R;
import com.fuyuan.marketmanage.base.BaseActivity;
import com.fuyuan.marketmanage.base.BaseFragment;
import com.fuyuan.marketmanage.supply.fragment.SupplyFragment;
import com.fuyuan.marketmanage.supply.fragment.SupplyManagerFragment;
import com.fuyuan.marketmanage.user.adapter.FragmentAdapter;

import java.util.ArrayList;
import java.util.List;

public class SupplyActivity extends BaseActivity {
    private TextView mTvTitle;
    private ViewPager mViewPager;
    private TabLayout tableLayout;
    private List<BaseFragment> fragments;

    @Override
    public void initView() {
        setContentView(R.layout.activity_supply);
        mTvTitle = (TextView) findViewById(R.id.tv_title);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        tableLayout = (TabLayout) findViewById(R.id.tabs);

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        mTvTitle.setText("供货商管理");
        fragments = new ArrayList<>();
        fragments.add(new SupplyFragment());
        fragments.add(new SupplyManagerFragment());
        List<String> tabList = new ArrayList<String>();
        tabList.add("供货商列表");
        tabList.add("供货商添加");
       FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments, tabList);
        mViewPager.setAdapter(fragmentAdapter);
        tableLayout.setupWithViewPager(mViewPager);
        tableLayout.setTabMode(TabLayout.MODE_FIXED);

    }

    @Override
    public void processClick(View v) {

    }
}
