package com.fuyuan.marketmanage.bill.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fuyuan.marketmanage.R;
import com.fuyuan.marketmanage.bill.bean.Bill;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by xin on 2017/1/3.
 */

public class BillAdapter extends BaseAdapter {
    private List<Bill> datalist;
    private LayoutInflater mInflater;
    private Context context;

    public BillAdapter(Context context, List<Bill> datalist) {
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
            convertView = mInflater.inflate(R.layout.bill_layout, null);
            vh.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
            vh.tv_income = (TextView) convertView.findViewById(R.id.tv_income);
            vh.tv_pay = (TextView) convertView.findViewById(R.id.tv_pay);
            vh.btn_Del = (Button) convertView.findViewById(R.id.btn_Del);
            convertView.setTag(vh);
        } else
            vh = (viewHolder) convertView.getTag();
        vh.tv_date.setText(datalist.get(i).getBilldate());
        vh.tv_income.setText(datalist.get(i).getIncome()+"");
        vh.tv_pay.setText(datalist.get(i).getPay());

        vh.btn_Del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bill goodsBean = new Bill();
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
        TextView tv_date;
        TextView tv_income;
        TextView tv_pay;
        Button btn_Del;

    }
}
