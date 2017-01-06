package com.fuyuan.marketmanage.sale.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fuyuan.marketmanage.R;
import com.fuyuan.marketmanage.bean.GoodsBean;
import com.fuyuan.marketmanage.sale.bean.Sales;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by xin on 2017/1/3.
 */

public class SalesAdapter extends BaseAdapter {
    private List<Sales> datalist;
    private LayoutInflater mInflater;
    private Context context;

    public SalesAdapter(Context context, List<Sales> datalist) {
        this.context = context;
        this.datalist = datalist;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        viewHolder vh;
        if (convertView == null) {
            vh = new viewHolder();
            convertView = mInflater.inflate(R.layout.sales_layout, null);
            vh.tv_goods = (TextView) convertView.findViewById(R.id.tv_goods);
            vh.tv_num = (TextView) convertView.findViewById(R.id.tv_num);
            vh.btn_Del = (Button) convertView.findViewById(R.id.btn_Del);
            convertView.setTag(vh);
        } else
            vh = (viewHolder) convertView.getTag();
        vh.tv_goods.setText(datalist.get(i).getGoodname());
        vh.tv_num.setText(datalist.get(i).getSalenum()+"");

        vh.btn_Del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodsBean goodsBean = new GoodsBean();
                goodsBean.setObjectId(datalist.get(i).getObjectId());
                goodsBean.delete(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "删除失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        return convertView;
    }

    class viewHolder {
        TextView tv_goods;
        TextView tv_num;
        Button btn_Del;

    }
}
