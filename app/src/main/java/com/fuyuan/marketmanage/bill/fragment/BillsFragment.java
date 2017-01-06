package com.fuyuan.marketmanage.bill.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fuyuan.marketmanage.R;
import com.fuyuan.marketmanage.base.BaseFragment;
import com.fuyuan.marketmanage.bill.adapter.BillAdapter;
import com.fuyuan.marketmanage.bill.bean.Bill;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

/**
 * Created by xin on 2017/1/3.
 */

public class BillsFragment extends BaseFragment implements View.OnClickListener {

    private ListView mLlBill;
    private BillAdapter goodsAdapter;
    private ArrayList<Bill> bmobUserList;
    private Button mBtnSearch;
    private EditText mEtFindDate;
    private TextView mTvTotal;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bill_fragment, null);
        mLlBill = (ListView) view.findViewById(R.id.ll_bill);
        mBtnSearch = (Button) view.findViewById(R.id.btn_search);
        mEtFindDate = (EditText) view.findViewById(R.id.et_finddate);
        mTvTotal = (TextView) view.findViewById(R.id.tv_total);

        setListener();
        queryGoods();
        getTotal();
        return view;
    }

    private void setListener() {
        mBtnSearch.setOnClickListener(this);
    }


    //查询用户
    public void queryGoods() {
        BmobQuery<Bill> query = new BmobQuery<>();
        query.order("-createdAt");
        query.findObjects(new FindListener<Bill>() {
            @Override
            public void done(List<Bill> list, BmobException e) {
                if (list.size() != 0) {
                    Toast.makeText(mActivity, "账单信息查询成功", Toast.LENGTH_SHORT).show();

                    bmobUserList = (ArrayList<Bill>) list;
                    goodsAdapter = new BillAdapter(mActivity, bmobUserList);
                    mLlBill.setAdapter(goodsAdapter);
                    goodsAdapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(mActivity, "没有账单信息", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search:
                String goods = mEtFindDate.getText().toString();
                if (TextUtils.isEmpty(goods)) {
                    Toast.makeText(mActivity, "账单日期不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    query(goods);
                }
                break;
        }

    }


    private void query(String goods) {
        BmobQuery<Bill> query = new BmobQuery<>();
        query.order("-createdAt");
        query.addWhereEqualTo("billdate", goods);
        query.findObjects(new FindListener<Bill>() {
            @Override
            public void done(List<Bill> list, BmobException e) {
                if (list.size() != 0) {
                    Toast.makeText(mActivity, "账单信息查询成功", Toast.LENGTH_SHORT).show();

                    bmobUserList = (ArrayList<Bill>) list;
                    goodsAdapter = new BillAdapter(mActivity, bmobUserList);
                    mLlBill.setAdapter(goodsAdapter);

                } else {
                    Toast.makeText(mActivity, "没有账单信息", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 获取总收入金额
     */
    private void getTotal() {
        BmobQuery<Bill> query = new BmobQuery();
        //income对应的只能是Integer类型
        query.sum(new String[]{"income"});
        query.findStatistics(Bill.class, new QueryListener<JSONArray>() {
            @Override
            public void done(JSONArray jsonArray, BmobException e) {
                if (e == null) {
                    if (jsonArray != null) {
                        try {
                            JSONObject obj = jsonArray.getJSONObject(0);
                            int sum = obj.getInt("_sumIncome");
                            Log.e("sum", "sum--" + sum);
                            mTvTotal.setText("总收入金额：" + sum);
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                    } else {
                        Toast.makeText(mActivity, "查询无结果", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(mActivity, "查询失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
