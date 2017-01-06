package com.fuyuan.marketmanage.goods.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fuyuan.marketmanage.R;
import com.fuyuan.marketmanage.base.BaseFragment;
import com.fuyuan.marketmanage.bean.GoodsBean;
import com.fuyuan.marketmanage.codescan.MipcaActivityCapture;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

import static android.app.Activity.RESULT_OK;

/**
 * Created by xin on 2017/1/3.
 */

public class GoodsManagerFragment extends BaseFragment implements View.OnClickListener {

    private static final int SCANNIN_GREQUEST_CODE = 1001;
    private EditText mEtGoods;
    private EditText mEtNum;
    private Button mBtnAdd;
    private EditText mEtBid;
    private EditText mEtPrice;
    private EditText mEtSupplyId;
    private EditText mEtDesc;
    private TextView mTvQrscan;
    private EditText mEtQrcode;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.goods_fragment, null);
        //设置键盘模式
        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        //添加商品
        mEtQrcode = (EditText) view.findViewById(R.id.et_qrcode);
        mEtGoods = (EditText) view.findViewById(R.id.et_goods);
        mEtNum = (EditText) view.findViewById(R.id.et_num);
        mEtBid = (EditText) view.findViewById(R.id.et_bid);
        mEtPrice = (EditText) view.findViewById(R.id.et_price);
        mEtSupplyId = (EditText) view.findViewById(R.id.et_supplyId);
        mEtDesc = (EditText) view.findViewById(R.id.et_desc);
        mBtnAdd = (Button) view.findViewById(R.id.btn_addgoods);
        mTvQrscan = (TextView) view.findViewById(R.id.tv_qrscan);


        setListener();
        return view;
    }

    private void setListener() {
        mBtnAdd.setOnClickListener(this);
        mTvQrscan.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_addgoods:
                addGoods();
                break;
            case R.id.tv_qrscan:
                Intent intent = new Intent();
                intent.setClass(mActivity, MipcaActivityCapture.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
                break;
        }

    }


    private void addGoods() {
        GoodsBean goodsBean = new GoodsBean();
        String qrcode = mEtQrcode.getText().toString();
        String goods = mEtGoods.getText().toString();
        String goodsNum = mEtNum.getText().toString();
        String bid = mEtBid.getText().toString();
        String price = mEtPrice.getText().toString();
        String desc = mEtDesc.getText().toString();
        String supply = mEtSupplyId.getText().toString();

        if (TextUtils.isEmpty(goods) || TextUtils.isEmpty(bid)) {
            Toast.makeText(mActivity, "商品名称或者进价不能为空", Toast.LENGTH_SHORT).show();
        } else {
            goodsBean.setQrcode(qrcode);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SCANNIN_GREQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    //显示扫描到的内容
                    mEtQrcode.setText(bundle.getString("result"));
                }
                break;
        }
    }
}
