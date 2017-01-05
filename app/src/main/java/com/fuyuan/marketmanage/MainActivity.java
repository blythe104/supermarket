package com.fuyuan.marketmanage;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fuyuan.marketmanage.base.BaseActivity;
import com.fuyuan.marketmanage.goods.GoodsActivity;
import com.fuyuan.marketmanage.money.MoneyActivity;
import com.fuyuan.marketmanage.supply.SupplyActivity;
import com.fuyuan.marketmanage.sys.SysActivity;
import com.fuyuan.marketmanage.user.UserActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    private Button mBtnUser;
    private Button mBtnGoods;
    private Button mBtnSupply;
    private Button mBtnSys;
    private Button mBtnMoney;

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        mBtnUser = (Button) findViewById(R.id.btn_user);
        mBtnGoods = (Button) findViewById(R.id.btn_goods);
        mBtnSupply = (Button) findViewById(R.id.btn_supply);
        mBtnSys = (Button) findViewById(R.id.btn_sys);
        mBtnMoney = (Button) findViewById(R.id.btn_money);


    }

    @Override
    public void initListener() {
        mBtnUser.setOnClickListener(this);
        mBtnGoods.setOnClickListener(this);
        mBtnSupply.setOnClickListener(this);
        mBtnSys.setOnClickListener(this);
        mBtnMoney.setOnClickListener(this);
    }

    @Override
    public void initData() {
    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.btn_user:
                startActivity(new Intent(MainActivity.this, UserActivity.class));
                break;
            case R.id.btn_goods:
                startActivity(new Intent(MainActivity.this, GoodsActivity.class));
                break;
            case R.id.btn_supply:
                startActivity(new Intent(MainActivity.this, SupplyActivity.class));
                break;
            case R.id.btn_sys:
                startActivity(new Intent(MainActivity.this, SysActivity.class));
                break;
            case R.id.btn_money:
                startActivity(new Intent(MainActivity.this,MoneyActivity.class));
                break;
        }

    }


    private void toast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
