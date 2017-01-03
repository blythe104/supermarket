package com.fuyuan.marketmanage.user.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fuyuan.marketmanage.R;

import java.util.List;

import cn.bmob.v3.BmobUser;

/**
 * Created by xin on 2017/1/3.
 */

public class UserAdapter extends BaseAdapter {
    private List<BmobUser> datalist;
    private LayoutInflater mInflater;
    private Context context;

    public UserAdapter(Context context, List<BmobUser> datalist) {
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
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        viewHolder vh;
        if (convertView == null) {
            vh = new viewHolder();
            convertView = mInflater.inflate(R.layout.user_manager_layout, null);
            vh.tv_username = (TextView) convertView.findViewById(R.id.tv_username);
            vh.tv_createTime = (TextView) convertView.findViewById(R.id.tv_createTime);
            convertView.setTag(vh);
        } else
            vh = (viewHolder) convertView.getTag();
        vh.tv_createTime.setText(datalist.get(i).getCreatedAt());
        vh.tv_username.setText(datalist.get(i).getUsername());

        return convertView;
    }

    class viewHolder {
        TextView tv_username;
        TextView tv_createTime;

    }
}
