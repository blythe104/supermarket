package com.fuyuan.marketmanage.widget.title;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fuyuan.marketmanage.R;

/**
 * Created by xin on 2017/1/9.
 */

public class CustomTitle extends LinearLayout implements ITitle, View.OnClickListener {

    protected TextView mTvTitle;
    protected TextView mTvTitleTag;
    protected FrameLayout mFrBack;

    public CustomTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.login_title_bar, this);
        mTvTitle = (TextView) view.findViewById(R.id.tv_title);
        mTvTitleTag = (TextView) view.findViewById(R.id.tv_title_tag);
        mFrBack = (FrameLayout) view.findViewById(R.id.fr_back);
        mFrBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fr_back:
                ((Activity) getContext()).finish();
                break;
        }
    }

    @Override
    public void setTitle(String text, String tagText, View.OnClickListener listener) {
        this.setTitle(text);
        mTvTitleTag.setText(tagText);
        mTvTitleTag.setVisibility(VISIBLE);
        mTvTitleTag.setOnClickListener(listener);

    }


    @Override
    public void setTitle(String text) {
        mTvTitle.setText(text);
    }


}
