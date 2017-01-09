package com.fuyuan.marketmanage.supply.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fuyuan.marketmanage.R;
import com.fuyuan.marketmanage.base.BaseFragment;
import com.fuyuan.marketmanage.bean.Supply;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by xin on 2017/1/3.
 */

public class SupplyManagerFragment extends BaseFragment implements View.OnClickListener {

    private EditText mEtSupply;
    private EditText mEtPhone;
    private Button mBtnAdd;
    private EditText mEtGoods;
    private EditText mEtAddress;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.supply_fragment, null);
        //设置键盘模式
//        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        //添加供货商信息
        mEtSupply = (EditText) view.findViewById(R.id.et_supply);
        mEtPhone = (EditText) view.findViewById(R.id.et_phone);
        mEtGoods = (EditText) view.findViewById(R.id.et_goods);

        mEtAddress = (EditText) view.findViewById(R.id.et_address);
        mBtnAdd = (Button) view.findViewById(R.id.btn_addsupply);


        setListener();
        return view;
    }

    private void setListener() {
        mBtnAdd.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_addsupply:
                addSupply();
                break;
        }

    }


    private void addSupply() {
        Supply supplybean = new Supply();
        String supply = mEtSupply.getText().toString();
        String phone = mEtPhone.getText().toString();
        String goods = mEtGoods.getText().toString();
        String address = mEtAddress.getText().toString();

        if (TextUtils.isEmpty(supply) || TextUtils.isEmpty(phone)) {
            Toast.makeText(mActivity, "商品名称或者手机号码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            supplybean.setSupply(supply);
            supplybean.setPhone(phone);
            supplybean.setGoods(goods);
            supplybean.setAddress(address);
            supplybean.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null) {
                        Toast.makeText(mActivity, "创建数据成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mActivity, "创建数据失败" + e.toString(), Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }
}
