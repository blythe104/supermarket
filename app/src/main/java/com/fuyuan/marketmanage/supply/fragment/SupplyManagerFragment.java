package com.fuyuan.marketmanage.supply.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.fuyuan.marketmanage.R;
import com.fuyuan.marketmanage.base.BaseFragment;
import com.fuyuan.marketmanage.bean.Supply;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

import static cn.bmob.v3.Bmob.getApplicationContext;
import static com.fuyuan.marketmanage.R.id.et_goods;

/**
 * Created by xin on 2017/1/3.
 */

public class SupplyManagerFragment extends BaseFragment implements View.OnClickListener {

    CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            CheckBox box = (CheckBox) buttonView;

            Toast.makeText(getApplicationContext(), "选择的是" + box.getText(), Toast.LENGTH_LONG).show();

        }
    };
    private EditText mEtSupply;
    private EditText mEtPhone;
    private Button mBtnAdd;
    private EditText mEtGoods;
    private EditText mEtAddress;
    private List<CheckBox> checkBoxes = new ArrayList<>();
    private CheckBox mCb4;
    private CheckBox mCb3;
    private CheckBox mCb2;
    private CheckBox mCb1;
    private Button mBtnAddSs;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.supply_fragment, null);
        //设置键盘模式
        //        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        //添加供货商信息
        mEtSupply = (EditText) view.findViewById(R.id.et_supply);
        mEtPhone = (EditText) view.findViewById(R.id.et_phone);
        mEtGoods = (EditText) view.findViewById(et_goods);

        mEtAddress = (EditText) view.findViewById(R.id.et_address);
        mBtnAdd = (Button) view.findViewById(R.id.btn_addsupply);

        mCb1 = (CheckBox) view.findViewById(R.id.checkbox1);
        mCb2 = (CheckBox) view.findViewById(R.id.checkbox2);
        mCb3 = (CheckBox) view.findViewById(R.id.checkbox3);
        mCb4 = (CheckBox) view.findViewById(R.id.checkbox4);
        mBtnAddSs = (Button) view.findViewById(R.id.btn_add);
        initData();
        setListener();

        return view;
    }

    private void initData() {
        mCb1.setChecked(true);
        checkBoxes.add(mCb1);
        checkBoxes.add(mCb2);
        checkBoxes.add(mCb3);
        checkBoxes.add(mCb4);
    }

    private void setListener() {
        mBtnAdd.setOnClickListener(this);
        mBtnAddSs.setOnClickListener(this);
        mCb1.setOnCheckedChangeListener(listener);
        mCb2.setOnCheckedChangeListener(listener);
        mCb3.setOnCheckedChangeListener(listener);
        mCb4.setOnCheckedChangeListener(listener);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_addsupply:
                addSupply();
                break;
            case R.id.btn_add:
                getValues();
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

    public void getValues() {
        String content = "";
        for (CheckBox cbx : checkBoxes) {
            if (cbx.isChecked()) {
                content += cbx.getText() + ", ";
                mEtGoods.setText(content);
            }
        }
        if ("".equals(content)) {
            content = "您还没有选择供应商品";
        }
    }
}
