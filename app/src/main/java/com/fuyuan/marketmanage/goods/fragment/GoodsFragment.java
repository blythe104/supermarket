package com.fuyuan.marketmanage.goods.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.fuyuan.marketmanage.R;
import com.fuyuan.marketmanage.base.BaseFragment;
import com.fuyuan.marketmanage.bean.GoodsBean;
import com.fuyuan.marketmanage.codescan.MipcaActivityCapture;
import com.fuyuan.marketmanage.goods.adapter.GoodsAdapter;
import com.fuyuan.marketmanage.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static android.app.Activity.RESULT_OK;

/**
 * Created by xin on 2017/1/3.
 */

public class GoodsFragment extends BaseFragment implements View.OnClickListener {

    private static final int SCANNIN_GREQUEST_CODE = 1001;
    private ListView mLlGoods;
    private GoodsAdapter goodsAdapter;
    private ArrayList<GoodsBean> bmobUserList;
    private EditText mEtFindGoods;
    private Button mBtnGoods;
    private EditText mEtQrcode;
    private Button mBtnQrfind;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.goods_fragment_layout, null);
        mLlGoods = (ListView) view.findViewById(R.id.ll_goods);
        mEtFindGoods = (EditText) view.findViewById(R.id.et_findgoods);
        mBtnGoods = (Button) view.findViewById(R.id.btn_goods);
        mBtnQrfind = (Button) view.findViewById(R.id.btn_qrfind);
        mEtQrcode = (EditText) view.findViewById(R.id.et_qrcode);

        setListener();
        queryGoods();
        return view;
    }

    private void setListener() {
        mBtnGoods.setOnClickListener(this);
        mBtnQrfind.setOnClickListener(this);
    }


    //查询用户
    public void queryGoods() {
        BmobQuery<GoodsBean> query = new BmobQuery<>();
        query.order("-createdAt");
        query.findObjects(new FindListener<GoodsBean>() {
            @Override
            public void done(List<GoodsBean> list, BmobException e) {
                if (list.size() != 0) {
                    ToastUtils.toast(mActivity, "商品信息查询成功");

                    bmobUserList = (ArrayList<GoodsBean>) list;
                    goodsAdapter = new GoodsAdapter(mActivity, bmobUserList);
                    mLlGoods.setAdapter(goodsAdapter);
                    goodsAdapter.notifyDataSetChanged();

                } else {
                    ToastUtils.toast(mActivity, "没有商品信息");
                }
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_goods:
                String goods = mEtFindGoods.getText().toString();
                if (TextUtils.isEmpty(goods)) {
                    ToastUtils.toast(mActivity, "商品名称不能为空");
                } else {
                    query(goods);
                }
                break;
            //二维码扫描获取
            case R.id.btn_qrfind:
                Intent intent = new Intent();
                intent.setClass(mActivity, MipcaActivityCapture.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
                break;
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
                    queryqr(mEtQrcode.getText().toString());
                }
                break;
        }
    }

    private void query(String goods) {
        BmobQuery<GoodsBean> query = new BmobQuery<>();
        query.order("-createdAt");
        query.addWhereEqualTo("goodsName", goods);
        query.findObjects(new FindListener<GoodsBean>() {
            @Override
            public void done(List<GoodsBean> list, BmobException e) {
                if (list.size() != 0) {
                    ToastUtils.toast(mActivity, "商品信息查询成功");

                    bmobUserList = (ArrayList<GoodsBean>) list;
                    goodsAdapter = new GoodsAdapter(mActivity, bmobUserList);
                    mLlGoods.setAdapter(goodsAdapter);

                } else {
                    ToastUtils.toast(mActivity, "没有商品信息");
                }
            }
        });
    }

    private void queryqr(String qr) {
        BmobQuery<GoodsBean> query = new BmobQuery<>();
        query.order("-createdAt");
        query.addWhereEqualTo("qrcode", qr);
        query.findObjects(new FindListener<GoodsBean>() {
            @Override
            public void done(List<GoodsBean> list, BmobException e) {
                if (list.size() != 0) {
                    ToastUtils.toast(mActivity, "商品信息查询成功");

                    bmobUserList = (ArrayList<GoodsBean>) list;
                    goodsAdapter = new GoodsAdapter(mActivity, bmobUserList);
                    mLlGoods.setAdapter(goodsAdapter);

                } else {
                    ToastUtils.toast(mActivity, "没有商品信息");
                }
            }
        });
    }
}
