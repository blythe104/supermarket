package com.fuyuan.marketmanage.widget.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fuyuan.marketmanage.R;


/**
 * Created by xin on 2016/12/9.
 */
public class MenuMixView extends LinearLayout {
    ItemDataBean data;
    private ImageView iconIv;
    private TextView titleTv;
    private TextView unitTv;
    private TextView valueTv;
    private ImageView markIv;
    private int markId;
    private int iconId;
    private ImageView nextIv;

    public MenuMixView(Context context) {
        super(context);
        initView();
    }

    public MenuMixView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MenuMixView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public int getMarkId() {
        return markId;
    }

    public void setMarkId(int markId) {
        this.markId = markId;
    }

    public ImageView getMarkIv() {
        return markIv;
    }

    public void setMarkIv(int markId) {
        this.markId = markId;
        this.markIv.setBackgroundResource(markId);
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_menu_mix_view, this);
        iconIv = (ImageView) findViewById(R.id.iv_icon);
        titleTv = (TextView) findViewById(R.id.tv_title);
        unitTv = (TextView) findViewById(R.id.tv_unit);
        valueTv = (TextView) findViewById(R.id.tv_value);
        markIv = (ImageView) findViewById(R.id.iv_mark);
        nextIv = (ImageView) findViewById(R.id.iv_next);
    }

    /**
     * 获取数据对象
     *
     * @return
     */
    public ItemDataBean getData() {

        return data;
    }

    public void setData(ItemDataBean data) {
        this.data = data;
        this.setValue(data.value);
        this.setIcon(data.iconId);
        this.setTitle(data.title);
        this.setUnit(data.unit);
        this.setImgVisible(isVisible(data.iconVisible));
        this.setMarkVisible(isVisible(data.markVisible));
        this.setNextVisible(isVisible(data.nextVisible));
        this.setTextVisible(data.unitVisible);
        this.setTextColor(data.textColor);
        this.setTextSize(data.textsize);
    }

    public int isVisible(int visible) {
        return visible == -1 ? View.GONE : visible;
    }

    public void setValue(String value) {
        this.valueTv.setText(value);
    }

    public int getIcon() {
        return this.iconId;
    }

    public void setIcon(int iconId) {
        this.iconId = iconId;
        this.iconIv.setBackgroundResource(iconId);
    }

    public void setUnit(String unit) {
        this.unitTv.setText(unit);
    }

    public void setTitle(String title) {
        this.titleTv.setText(title);
    }

    /**
     * 设置图标隐藏
     *
     * @param visible 显示情况
     */
    public void setImgVisible(int visible) {
        iconIv.setVisibility(visible);
    }

    /**
     * 设置Mark图标隐藏
     *
     * @param visible 显示情况
     */
    public void setMarkVisible(int visible) {
        markIv.setVisibility(visible);
    }

    /**
     * 设置图标隐藏
     *
     * @param visible 显示情况
     */
    public void setNextVisible(int visible) {
        nextIv.setVisibility(visible);
    }

    /**
     * 设置菜单里的内容显示隐藏
     *
     * @param visible 显示情况
     */
    public void setTextVisible(int visible) {
        unitTv.setVisibility(visible);
    }

    /**
     * 设置字体颜色
     *
     * @param color
     */
    public void setTextColor(int color) {
        valueTv.setTextColor(color);
    }

    /**
     * 设置字体大小
     *
     * @param textsize
     */
    public void setTextSize(float textsize) {
        valueTv.setTextSize(textsize);
    }

}
