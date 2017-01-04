package com.fuyuan.marketmanage.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;

import com.fuyuan.marketmanage.config.MyConfig;
import com.fuyuan.marketmanage.widget.exception.repeatFlagException;

import cn.bmob.v3.Bmob;

/**
 * Created by xin on 2016/12/29.
 */
public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, MyConfig.APP_ID);
        initView();
        initListener();
        try {
            initData();
        } catch (repeatFlagException e) {
            e.printStackTrace();
        }
    }

    public abstract void initView();

    public abstract void initListener();

    public abstract void initData() throws repeatFlagException;

    public abstract void processClick(View v);

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        processClick(v);
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //监听回退键
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
}
