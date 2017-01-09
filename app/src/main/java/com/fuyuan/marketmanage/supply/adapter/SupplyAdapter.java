package com.fuyuan.marketmanage.supply.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fuyuan.marketmanage.R;
import com.fuyuan.marketmanage.bean.Supply;
import com.fuyuan.marketmanage.supply.SupplyDescActivity;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by xin on 2017/1/3.
 */

public class SupplyAdapter extends BaseAdapter {
    private List<Supply> datalist;
    private LayoutInflater mInflater;
    private Context context;

    public SupplyAdapter(Context context, List<Supply> datalist) {
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
            convertView = mInflater.inflate(R.layout.supply_layout, null);
            vh.tv_supply = (TextView) convertView.findViewById(R.id.tv_supply);
            vh.tv_phone = (TextView) convertView.findViewById(R.id.tv_phone);
            vh.tv_good = (TextView) convertView.findViewById(R.id.tv_good);
            vh.btn_Del = (Button) convertView.findViewById(R.id.btn_Del);
            vh.btn_cal = (Button) convertView.findViewById(R.id.btn_cal);
            convertView.setTag(vh);
        } else
            vh = (viewHolder) convertView.getTag();
        vh.tv_supply.setText(datalist.get(i).getSupply());
        vh.tv_phone.setText(datalist.get(i).getPhone());
        vh.tv_good.setText(datalist.get(i).getGoods());
        vh.btn_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDetail = new Intent(context, SupplyDescActivity.class);
                intentDetail.putExtra("objectId", datalist.get(i).getObjectId());
                context.startActivity(intentDetail);
                //                Intent intentPhone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + datalist.get
                // (i).getPhone()));
                //                context.startActivity(intentPhone);

            }
        });
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
        TextView tv_supply;
        TextView tv_phone;
        TextView tv_good;
        Button btn_Del;
        Button btn_cal;

    }

}
