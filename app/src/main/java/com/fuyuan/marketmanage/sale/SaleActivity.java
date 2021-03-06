package com.fuyuan.marketmanage.sale;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.fuyuan.marketmanage.R;
import com.fuyuan.marketmanage.base.BaseActivity;
import com.fuyuan.marketmanage.base.BaseFragment;
import com.fuyuan.marketmanage.sale.fragment.SaleFragment;
import com.fuyuan.marketmanage.sale.fragment.SaleManagerFragment;
import com.fuyuan.marketmanage.user.adapter.FragmentAdapter;
import com.fuyuan.marketmanage.widget.exception.repeatFlagException;
import com.fuyuan.marketmanage.widget.title.CustomTitle;

import java.util.ArrayList;
import java.util.List;

public class SaleActivity extends BaseActivity {
    private ViewPager mViewPager;
    private TabLayout tableLayout;
    private List<BaseFragment> fragments;
    private CustomTitle mTitle;

    @Override
    public void initView() {
        setContentView(R.layout.activity_sale);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        tableLayout = (TabLayout) findViewById(R.id.tabs);
        mTitle = (CustomTitle) findViewById(R.id.titlebar);

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() throws repeatFlagException {
        mTitle.setTitle("销售管理");
        fragments = new ArrayList<>();
        fragments.add(new SaleFragment());
        fragments.add(new SaleManagerFragment());
        List<String> tabList = new ArrayList<String>();
        tabList.add("销售列表");
        tabList.add("销售添加");
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments, tabList);
        mViewPager.setAdapter(fragmentAdapter);
        tableLayout.setupWithViewPager(mViewPager);
        tableLayout.setTabMode(TabLayout.MODE_FIXED);

    }

    @Override
    public void processClick(View v) {

    }
}
