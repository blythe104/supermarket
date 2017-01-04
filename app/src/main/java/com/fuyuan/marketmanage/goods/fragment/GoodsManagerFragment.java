package com.fuyuan.marketmanage.goods.fragment;

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
import com.fuyuan.marketmanage.bean.GoodsBean;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by xin on 2017/1/3.
 */

public class GoodsManagerFragment extends BaseFragment implements View.OnClickListener {

    private EditText mEtGoods;
    private EditText mEtNum;
    private Button mBtnAdd;
    private EditText mEtBid;
    private EditText mEtPrice;
    private EditText mEtSupplyId;
    private EditText mEtDesc;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.goods_fragment, null);

        //添加商品
        mEtGoods = (EditText) view.findViewById(R.id.et_goods);
        mEtNum = (EditText) view.findViewById(R.id.et_num);
        mEtBid = (EditText) view.findViewById(R.id.et_bid);
        mEtPrice = (EditText) view.findViewById(R.id.et_price);
        mEtSupplyId = (EditText) view.findViewById(R.id.et_supplyId);
        mEtDesc = (EditText) view.findViewById(R.id.et_desc);
        mBtnAdd = (Button) view.findViewById(R.id.btn_addgoods);


        setListener();
        return view;
    }

    private void setListener() {
        mBtnAdd.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_addgoods:
                addGoods();
                break;
        }

    }




    private void addGoods() {
        GoodsBean goodsBean = new GoodsBean();
        String goods = mEtGoods.getText().toString();
        String goodsNum = mEtNum.getText().toString();
        String bid = mEtBid.getText().toString();
        String price = mEtPrice.getText().toString();
        String desc = mEtDesc.getText().toString();
        String supply = mEtSupplyId.getText().toString();

        if (TextUtils.isEmpty(goods) || TextUtils.isEmpty(bid)) {
            Toast.makeText(mActivity, "商品名称或者进价不能为空", Toast.LENGTH_SHORT).show();
        } else {
            goodsBean.setGoodsName(goods);
            goodsBean.setGoodsNum(goodsNum);
            goodsBean.setBid(bid);
            goodsBean.setPrice(price);
            goodsBean.setDesc(desc);
            goodsBean.setSupplyId(supply);
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
