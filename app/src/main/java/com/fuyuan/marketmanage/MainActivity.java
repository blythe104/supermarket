package com.fuyuan.marketmanage;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fuyuan.marketmanage.base.BaseActivity;
import com.fuyuan.marketmanage.bill.BillActivity;
import com.fuyuan.marketmanage.goods.GoodsActivity;
import com.fuyuan.marketmanage.money.MoneyActivity;
import com.fuyuan.marketmanage.sale.SaleActivity;
import com.fuyuan.marketmanage.stock.StockActivity;
import com.fuyuan.marketmanage.supply.SupplyActivity;
import com.fuyuan.marketmanage.sys.SysActivity;
import com.fuyuan.marketmanage.user.UserActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    private Button mBtnUser;
    private Button mBtnGoods;
    private Button mBtnSupply;
    private Button mBtnSys;
    private Button mBtnMoney;
    private Button mBtnStock;
    private Button mBtnBill;
    private Button mBtnSale;

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        mBtnUser = (Button) findViewById(R.id.btn_user);
        mBtnGoods = (Button) findViewById(R.id.btn_goods);
        mBtnSupply = (Button) findViewById(R.id.btn_supply);
        mBtnSys = (Button) findViewById(R.id.btn_sys);
        mBtnMoney = (Button) findViewById(R.id.btn_money);
        mBtnStock = (Button) findViewById(R.id.btn_stock);
        mBtnBill = (Button) findViewById(R.id.btn_bill);
        mBtnSale = (Button) findViewById(R.id.btn_saled);


    }

    @Override
    public void initListener() {
        mBtnUser.setOnClickListener(this);
        mBtnGoods.setOnClickListener(this);
        mBtnSupply.setOnClickListener(this);
        mBtnSys.setOnClickListener(this);
        mBtnMoney.setOnClickListener(this);
        mBtnStock.setOnClickListener(this);
        mBtnBill.setOnClickListener(this);
        mBtnSale.setOnClickListener(this);
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
                startActivity(new Intent(MainActivity.this, MoneyActivity.class));
                break;
            case R.id.btn_stock:
                startActivity(new Intent(MainActivity.this, StockActivity.class));
                break;
            case R.id.btn_bill:
                startActivity(new Intent(MainActivity.this, BillActivity.class));
                break;
            case R.id.btn_saled:
                startActivity(new Intent(MainActivity.this, SaleActivity.class));
                break;
        }

    }


    private void toast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
