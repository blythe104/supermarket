package com.fuyuan.marketmanage.supply;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fuyuan.marketmanage.R;
import com.fuyuan.marketmanage.base.BaseActivity;
import com.fuyuan.marketmanage.bean.Supply;
import com.fuyuan.marketmanage.widget.exception.repeatFlagException;
import com.fuyuan.marketmanage.widget.title.CustomTitle;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class SupplyDescActivity extends BaseActivity {


    private TextView mTvSupply;
    private TextView mTvAddress;
    private TextView mTvPhone;
    private TextView mTvGoods;
    private String objectId;
    private Supply supply;
    private Button mbtnCal;
    private CustomTitle mTitle;

    @Override
    public void initView() {
        setContentView(R.layout.activity_supply_desc);
        objectId = (String) getIntent().getExtras().get("objectId");
        mTitle = (CustomTitle) findViewById(R.id.titlebar);
        mTvSupply = (TextView) findViewById(R.id.tv_supply);
        mTvPhone = (TextView) findViewById(R.id.tv_phone);
        mTvGoods = (TextView) findViewById(R.id.tv_goods);
        mTvAddress = (TextView) findViewById(R.id.tv_address);
        mbtnCal = (Button) findViewById(R.id.btn_call);

    }

    private void query(String objectId) {
        BmobQuery<Supply> query = new BmobQuery<>();
        query.addWhereEqualTo("objectId", objectId);
        Log.e("objectId",objectId);
        query.findObjects(new FindListener<Supply>() {
            @Override
            public void done(List<Supply> list, BmobException e) {
                if (list.size() != 0) {
                    Toast.makeText(SupplyDescActivity.this, "查询成功", Toast.LENGTH_SHORT).show();
                    supply = list.get(0);
                    mTvSupply.setText(supply.getSupply());
                    mTvGoods.setText(supply.getGoods());
                    mTvPhone.setText(supply.getPhone());
                    mTvAddress.setText(supply.getAddress());
                } else {
                    Toast.makeText(SupplyDescActivity.this, "获取信息失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void initListener() {
        mbtnCal.setOnClickListener(this);
    }

    @Override
    public void initData() throws repeatFlagException {
        query(objectId);
        mTitle.setTitle("供货商详情");

    }

    @Override
    public void processClick(View v) {
        switch (v.getId()) {
            case R.id.btn_call:
                Intent intentPhone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mTvPhone.getText()));
                startActivity(intentPhone);
                break;
        }

    }
}
