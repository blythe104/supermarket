package com.fuyuan.marketmanage.stock.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fuyuan.marketmanage.R;
import com.fuyuan.marketmanage.bean.Supply;
import com.fuyuan.marketmanage.stock.bean.Stock;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by xin on 2017/1/3.
 */

public class StockAdapter extends BaseAdapter {
    private List<Stock> datalist;
    private LayoutInflater mInflater;
    private Context context;

    public StockAdapter(Context context, List<Stock> datalist) {
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
            convertView = mInflater.inflate(R.layout.stock_layout, null);
            vh.tv_stock = (TextView) convertView.findViewById(R.id.tv_stock);
            vh.tv_num = (TextView) convertView.findViewById(R.id.tv_num);
            vh.tv_supply = (TextView) convertView.findViewById(R.id.tv_supply);
            vh.btn_Del = (Button) convertView.findViewById(R.id.btn_Del);
            convertView.setTag(vh);
        } else
            vh = (viewHolder) convertView.getTag();
        vh.tv_stock.setText(datalist.get(i).getStockgood());
        vh.tv_num.setText(datalist.get(i).getNum());
        vh.tv_supply.setText(datalist.get(i).getSupplymsg());
        vh.btn_Del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Supply goodsBean = new Supply();
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
        TextView tv_stock;
        TextView tv_num;
        TextView tv_supply;
        Button btn_Del;

    }

}
