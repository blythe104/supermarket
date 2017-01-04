package com.fuyuan.marketmanage.sys;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.fuyuan.marketmanage.R;
import com.fuyuan.marketmanage.base.BaseActivity;
import com.fuyuan.marketmanage.login.LoginActivity;
import com.fuyuan.marketmanage.widget.exception.repeatFlagException;
import com.fuyuan.marketmanage.widget.ui.CustomMenuView;
import com.fuyuan.marketmanage.widget.ui.ItemDataBean;

public class SysActivity extends BaseActivity {

    private CustomMenuView menuView;

    @Override
    public void initView() {
        setContentView(R.layout.activity_sys);
        menuView = (CustomMenuView) findViewById(R.id.menu_view);


    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() throws repeatFlagException {
        initMenuData();
    }

    private void initMenuData() throws repeatFlagException {
        menuView.addItem("系统版本号", "desc", "version")//
                .addItem("退出登录", "", "exit")//
                .build();
        menuView.setOnMenuListener(new CustomMenuView.OnMenuListener() {
            @Override
            public void onClickItem(ItemDataBean data) {
                switch (data.flag) {
                    case "exit":
                        startActivity(new Intent(SysActivity.this, LoginActivity.class));
                        break;
                    case "version":
                        Toast.makeText(SysActivity.this, "检查版本号", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });
    }

    @Override
    public void processClick(View v) {

    }
}
