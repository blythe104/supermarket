package com.fuyuan.marketmanage.stock;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.fuyuan.marketmanage.R;
import com.fuyuan.marketmanage.base.BaseActivity;
import com.fuyuan.marketmanage.base.BaseFragment;
import com.fuyuan.marketmanage.stock.fragment.StockManagerFragment;
import com.fuyuan.marketmanage.stock.fragment.StocksFragment;
import com.fuyuan.marketmanage.user.adapter.FragmentAdapter;
import com.fuyuan.marketmanage.widget.exception.repeatFlagException;
import com.fuyuan.marketmanage.widget.title.CustomTitle;

import java.util.ArrayList;
import java.util.List;

public class StockActivity extends BaseActivity {
    private ViewPager mViewPager;
    private TabLayout tableLayout;
    private List<BaseFragment> fragments;
    private CustomTitle mTitle;

    @Override
    public void initView() {
        setContentView(R.layout.activity_stock);
        mTitle = (CustomTitle) findViewById(R.id.titlebar);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        tableLayout = (TabLayout) findViewById(R.id.tabs);

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() throws repeatFlagException {
        mTitle.setTitle("供货商管理");
        fragments = new ArrayList<>();
        fragments.add(new StocksFragment());
        fragments.add(new StockManagerFragment());
        List<String> tabList = new ArrayList<>();
        tabList.add("库存商品列表");
        tabList.add("库存商品添加");
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments, tabList);
        mViewPager.setAdapter(fragmentAdapter);
        tableLayout.setupWithViewPager(mViewPager);
        tableLayout.setTabMode(TabLayout.MODE_FIXED);


    }

    @Override
    public void processClick(View v) {

    }
}
