package com.fuyuan.marketmanage;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fuyuan.marketmanage.base.BaseActivity;
import com.fuyuan.marketmanage.user.UserActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {


//    private TextView mTvTitle;
    private Button mBtnUser;

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
//        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mBtnUser = (Button) findViewById(R.id.btn_user);

    }

    @Override
    public void initListener() {
        mBtnUser.setOnClickListener(this);
    }

    @Override
    public void initData() {
//        mTvTitle.setText("福源管理系统");

    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.btn_user:
                startActivity(new Intent(MainActivity.this, UserActivity.class));
                break;
        }

    }


    private void toast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
