package com.fuyuan.marketmanage.supply.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.fuyuan.marketmanage.R;
import com.fuyuan.marketmanage.base.BaseFragment;
import com.fuyuan.marketmanage.bean.Supply;
import com.fuyuan.marketmanage.supply.adapter.SupplyAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by xin on 2017/1/3.
 */

public class SupplyFragment extends BaseFragment implements View.OnClickListener {

    private ListView mLlSupply;
    private SupplyAdapter supplyAdapter;
    private ArrayList<Supply> supplyList;
    private EditText mEtSupply;
    private Button mBtnSupply;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.supply_fragment_layout, null);
        mLlSupply = (ListView) view.findViewById(R.id.ll_supply);
        mEtSupply = (EditText) view.findViewById(R.id.et_supply);
        mBtnSupply = (Button) view.findViewById(R.id.btn_supply);

        setListener();
        queryGoods();
        return view;
    }

    private void setListener() {
        mBtnSupply.setOnClickListener(this);
    }


    //查询用户
    public void queryGoods() {
        BmobQuery<Supply> query = new BmobQuery<>();
        query.order("-createdAt");
        query.findObjects(new FindListener<Supply>() {
            @Override
            public void done(List<Supply> list, BmobException e) {
                if (list.size() != 0) {
                    Toast.makeText(mActivity, "供货商信息查询成功", Toast.LENGTH_SHORT).show();

                    supplyList = (ArrayList<Supply>) list;
                    supplyAdapter = new SupplyAdapter(mActivity, supplyList);
                    mLlSupply.setAdapter(supplyAdapter);

                } else {
                    Toast.makeText(mActivity, "没有供货商信息", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_supply:
                String supply = mEtSupply.getText().toString();
                if (TextUtils.isEmpty(supply)) {
                    Toast.makeText(mActivity, "供货商名称不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    query(supply);
                }
                break;
        }

    }

    private void query(String supply) {
        BmobQuery<Supply> query = new BmobQuery<>();
        query.order("-createdAt");
        query.addWhereEqualTo("supply", supply);
        query.findObjects(new FindListener<Supply>() {
            @Override
            public void done(List<Supply> list, BmobException e) {
                if (list.size() != 0) {
                    Toast.makeText(mActivity, "商品信息查询成功", Toast.LENGTH_SHORT).show();

                    supplyList = (ArrayList<Supply>) list;
                    supplyAdapter = new SupplyAdapter(mActivity, supplyList);
                    mLlSupply.setAdapter(supplyAdapter);

                } else {
                    Toast.makeText(mActivity, "没有商品信息", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
