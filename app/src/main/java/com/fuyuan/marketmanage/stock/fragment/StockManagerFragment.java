package com.fuyuan.marketmanage.stock.fragment;

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
import com.fuyuan.marketmanage.stock.bean.Stock;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by xin on 2017/1/3.
 */

public class StockManagerFragment extends BaseFragment implements View.OnClickListener {

    private EditText mEtStock;
    private EditText mEtNum;
    private Button mBtnAdd;
    private EditText mEtSupplyMsg;
    private EditText mEtAdd;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stock_fragment, null);
        //设置键盘模式
//        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        //添加供货商信息
        mEtStock = (EditText) view.findViewById(R.id.et_stock);
        mEtNum = (EditText) view.findViewById(R.id.et_num);
        mEtSupplyMsg = (EditText) view.findViewById(R.id.et_supply);

        mEtAdd = (EditText) view.findViewById(R.id.et_isAdd);
        mBtnAdd = (Button) view.findViewById(R.id.btn_addstock);


        setListener();
        return view;
    }

    private void setListener() {
        mBtnAdd.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_addstock:
                addStock();
                break;
        }

    }


    private void addStock() {
        Stock supplybean = new Stock();
        String stock = mEtStock.getText().toString();
        String goodnum = mEtNum.getText().toString();
        String msg = mEtSupplyMsg.getText().toString();
        String isadd = mEtAdd.getText().toString();

        if (TextUtils.isEmpty(stock) || TextUtils.isEmpty(goodnum)) {
            Toast.makeText(mActivity, "商品名称或者数量不能为空", Toast.LENGTH_SHORT).show();
        } else {
            supplybean.setStockgood(stock);
            supplybean.setNum(goodnum);
            supplybean.setSupplymsg(msg);
            supplybean.setIsadd(isadd);
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
