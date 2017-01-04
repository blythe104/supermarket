package com.fuyuan.marketmanage.goods.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.fuyuan.marketmanage.R;
import com.fuyuan.marketmanage.base.BaseFragment;
import com.fuyuan.marketmanage.bean.GoodsBean;
import com.fuyuan.marketmanage.goods.adapter.GoodsAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by xin on 2017/1/3.
 */

public class GoodsFragment extends BaseFragment {

    private ListView mLlGoods;
    private GoodsAdapter goodsAdapter;
    private ArrayList<GoodsBean> bmobUserList;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.goods_fragment_layout, null);
        mLlGoods = (ListView) view.findViewById(R.id.ll_goods);
        queryGoods();
        return view;
    }


    //查询用户
    public void queryGoods() {
        BmobQuery<GoodsBean> query = new BmobQuery<>();
        query.order("-createdAt");
        query.findObjects(new FindListener<GoodsBean>() {
            @Override
            public void done(List<GoodsBean> list, BmobException e) {
                if (list.size() != 0) {
                    Toast.makeText(mActivity, "商品信息查询成功", Toast.LENGTH_SHORT).show();

                    bmobUserList = (ArrayList<GoodsBean>) list;
                    goodsAdapter = new GoodsAdapter(mActivity, bmobUserList);
                    mLlGoods.setAdapter(goodsAdapter);

                } else {
                    Toast.makeText(mActivity, "没有商品信息", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
