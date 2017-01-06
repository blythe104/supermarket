package com.fuyuan.marketmanage.bill.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fuyuan.marketmanage.R;
import com.fuyuan.marketmanage.base.BaseFragment;
import com.fuyuan.marketmanage.bill.bean.Bill;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by xin on 2017/1/3.
 */

public class BillManagerFragment extends BaseFragment implements View.OnClickListener {

    private EditText mEtIncome;
    private EditText mEtPay;
    private Button mBtnAdd;
    private EditText mEtDesc;
    private EditText mEtDate;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bill_fragment_layout, null);
        //设置键盘模式
        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        //添加账单
        mEtDate = (EditText) view.findViewById(R.id.et_date);
        mEtIncome = (EditText) view.findViewById(R.id.et_income);
        mEtPay = (EditText) view.findViewById(R.id.et_pay);
        mEtDesc = (EditText) view.findViewById(R.id.et_paydesc);
        mBtnAdd = (Button) view.findViewById(R.id.btn_addbill);


        setListener();
        return view;
    }

    private void setListener() {
        mBtnAdd.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_addbill:
                addBill();
                break;
        }

    }


    private void addBill() {
        Bill goodsBean = new Bill();
        String date = mEtDate.getText().toString();
        String income = mEtIncome.getText().toString();
        String pay = mEtPay.getText().toString();
        String desc = mEtDesc.getText().toString();

        if (TextUtils.isEmpty(income)) {
            Toast.makeText(mActivity, "账单收入金额不能为空", Toast.LENGTH_SHORT).show();
        } else {
            goodsBean.setBilldate(date);
            goodsBean.setIncome(income);
            goodsBean.setPay(pay);
            goodsBean.setPaydesc(desc);
            goodsBean.save(new SaveListener<String>() {
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
