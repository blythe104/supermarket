package com.fuyuan.marketmanage.sale.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fuyuan.marketmanage.R;
import com.fuyuan.marketmanage.base.BaseFragment;
import com.fuyuan.marketmanage.codescan.MipcaActivityCapture;
import com.fuyuan.marketmanage.sale.bean.Sales;
import com.fuyuan.marketmanage.utils.ToastUtils;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

import static android.app.Activity.RESULT_OK;

/**
 * Created by xin on 2017/1/3.
 */

public class SaleManagerFragment extends BaseFragment implements View.OnClickListener {

    private static final int SCANNIN_GREQUEST_CODE = 1001;
    private EditText mEtGoods;
    private EditText mEtNum;
    private Button mBtnAdd;
    private TextView mTvQrscan;
    private EditText mEtQrcode;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sales_fragment, null);
        //添加商品
        mEtQrcode = (EditText) view.findViewById(R.id.et_qrcode);
        mEtGoods = (EditText) view.findViewById(R.id.et_goods);
        mEtNum = (EditText) view.findViewById(R.id.et_num);
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
        Sales goodsBean = new Sales();
        String qrcode = mEtQrcode.getText().toString();
        String goods = mEtGoods.getText().toString();
        String goodsNum = mEtNum.getText().toString();

        if (TextUtils.isEmpty(goods)) {
            ToastUtils.toast(mActivity, "商品名称不能为空");
        } else {
            goodsBean.setQrcode(qrcode);
            goodsBean.setGoodname(goods);
            goodsBean.setSalenum(Integer.parseInt(goodsNum));
            goodsBean.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null) {
                        ToastUtils.toast(mActivity, "创建数据成功");
                    } else {
                        ToastUtils.toast(mActivity, "创建数据失败");
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
