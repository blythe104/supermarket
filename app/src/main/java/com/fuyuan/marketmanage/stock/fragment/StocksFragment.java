package com.fuyuan.marketmanage.stock.fragment;

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
import com.fuyuan.marketmanage.stock.adapter.StockAdapter;
import com.fuyuan.marketmanage.stock.bean.Stock;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by xin on 2017/1/3.
 */

public class StocksFragment extends BaseFragment implements View.OnClickListener {

    private ListView mLlStock;
    private StockAdapter stockAdapter;
    private ArrayList<Stock> stockList;
    private EditText mEtStock;
    private Button mBtnStock;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stock_fragment_layout, null);
        mLlStock = (ListView) view.findViewById(R.id.ll_stock);
        mEtStock = (EditText) view.findViewById(R.id.et_stock);
        mBtnStock = (Button) view.findViewById(R.id.btn_stock);

        setListener();
        queryGoods();
        return view;
    }

    private void setListener() {
        mBtnStock.setOnClickListener(this);
    }


    //查询用户
    public void queryGoods() {
        BmobQuery<Stock> query = new BmobQuery<>();
        query.order("-createdAt");
        query.findObjects(new FindListener<Stock>() {
            @Override
            public void done(List<Stock> list, BmobException e) {
                if (list.size() != 0) {
                    Toast.makeText(mActivity, "供货商信息查询成功", Toast.LENGTH_SHORT).show();

                    stockList = (ArrayList<Stock>) list;
                    stockAdapter = new StockAdapter(mActivity, stockList);
                    mLlStock.setAdapter(stockAdapter);
                    stockAdapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(mActivity, "没有供货商信息", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_stock:
                String stock = mEtStock.getText().toString();
                if (TextUtils.isEmpty(stock)) {
                    Toast.makeText(mActivity, "供货商名称不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    query(stock);
                }
                break;
        }

    }

    private void query(String stock) {
        BmobQuery<Stock> query = new BmobQuery<>();
        query.order("-createdAt");
        query.addWhereEqualTo("stockgood", stock);
        query.findObjects(new FindListener<Stock>() {
            @Override
            public void done(List<Stock> list, BmobException e) {
                if (list.size() != 0) {
                    Toast.makeText(mActivity, "商品信息查询成功", Toast.LENGTH_SHORT).show();

                    stockList = (ArrayList<Stock>) list;
                    stockAdapter = new StockAdapter(mActivity, stockList);
                    mLlStock.setAdapter(stockAdapter);

                } else {
                    Toast.makeText(mActivity, "没有商品信息", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
