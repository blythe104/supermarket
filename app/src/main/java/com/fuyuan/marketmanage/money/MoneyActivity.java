package com.fuyuan.marketmanage.money;

import android.view.View;

import com.fuyuan.marketmanage.R;
import com.fuyuan.marketmanage.base.BaseActivity;
import com.fuyuan.marketmanage.widget.exception.repeatFlagException;
import com.fuyuan.marketmanage.widget.title.CustomTitle;

public class MoneyActivity extends BaseActivity {


    private CustomTitle mTitle;

    @Override
    public void initView() {
        setContentView(R.layout.activity_money);
        mTitle = (CustomTitle) findViewById(R.id.titlebar);

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() throws repeatFlagException {
        mTitle.setTitle("收款二维码");
    }

    @Override
    public void processClick(View v) {

    }
}
