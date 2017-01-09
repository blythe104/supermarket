package com.fuyuan.marketmanage.goods;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.fuyuan.marketmanage.R;
import com.fuyuan.marketmanage.base.BaseActivity;
import com.fuyuan.marketmanage.base.BaseFragment;
import com.fuyuan.marketmanage.goods.fragment.GoodsFragment;
import com.fuyuan.marketmanage.goods.fragment.GoodsManagerFragment;
import com.fuyuan.marketmanage.user.adapter.FragmentAdapter;
import com.fuyuan.marketmanage.widget.title.CustomTitle;

import java.util.ArrayList;
import java.util.List;

public class GoodsActivity extends BaseActivity {

    private ViewPager mViewPager;
    private TabLayout tableLayout;
    private List<BaseFragment> fragments;
    private CustomTitle mTitle;

    @Override
    public void initView() {
        setContentView(R.layout.activity_goods);
        mTitle = (CustomTitle) findViewById(R.id.titlebar);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        tableLayout = (TabLayout) findViewById(R.id.tabs);

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        mTitle.setTitle("商品管理");
        fragments = new ArrayList<>();
        fragments.add(new GoodsFragment());
        fragments.add(new GoodsManagerFragment());
        List<String> tabList = new ArrayList<String>();
        tabList.add("商品列表");
        tabList.add("商品添加");
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments, tabList);
        mViewPager.setAdapter(fragmentAdapter);
        tableLayout.setupWithViewPager(mViewPager);
        tableLayout.setTabMode(TabLayout.MODE_FIXED);

    }

    @Override
    public void processClick(View v) {

    }
}
