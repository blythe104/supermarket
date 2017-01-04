package com.fuyuan.marketmanage.widget.ui;

import android.graphics.Color;
import android.view.View;

/**
 * 条目中的所需字段处理
 */
public class ItemDataBean {
    public int iconId;
    public int markId;
    public String title;
    public String value;
    public String unit;
    public String flag;

    public int textColor = Color.GRAY;

    public int nextVisible = -1;
    public boolean isDivider = false;
    public float textsize = 13;
    public int iconVisible = -1;
    public int markVisible = -1;
    public int unitVisible = -1;
    private int nextId;
    private int valueColor;

    /**
     * @param title       菜单标题
     * @param nextVisible 向右侧图标的显示或者隐藏
     * @param flag
     */
    public ItemDataBean(String title, int nextVisible, String flag) {
        this.nextVisible = nextVisible;
        this.title = title;
        this.flag = flag;
    }

    /**
     * @param title 标题
     * @param value 描述信息
     * @param flag
     */
    public ItemDataBean(String title, String value, String flag) {
        this.title = title;
        this.value = value;
        this.flag = flag;
    }

    /**
     * @param title       标题
     * @param value       描述
     * @param nextVisible 是否显示
     * @param flag
     */
    public ItemDataBean(String title, String value, int nextVisible, String flag) {
        this.flag = flag;
        this.nextVisible = nextVisible;
        this.title = title;
        this.value = value;
    }

    /**
     * @param iconId      ICon
     * @param title       标题
     * @param nextVisible 是否显示
     * @param flag
     */
    public ItemDataBean(int iconId, String title, int nextVisible, String flag) {
        this.iconVisible = View.VISIBLE;
        this.title = title;
        this.iconId = iconId;
        this.nextVisible = nextVisible;
        this.flag = flag;
    }

    /**
     * @param iconId      icon
     * @param title       标题
     * @param value       描述
     * @param nextVisible 是否显示
     * @param flag
     */
    public ItemDataBean(int iconId, String title, String value, int nextVisible, String flag) {
        this.iconVisible = View.VISIBLE;
        this.value = value;
        this.title = title;
        this.iconId = iconId;
        this.nextVisible = nextVisible;
        this.flag = flag;
    }

    /**
     * @param iconId icon
     * @param title  标题
     * @param markId markIcon
     * @param value  内容
     * @param flag
     */
    public ItemDataBean(int iconId, String title, int markId, String value, int nextVisible, String flag) {
        this.iconVisible = View.VISIBLE;
        this.markVisible = View.VISIBLE;
        this.iconId = iconId;
        this.title = title;
        this.value = value;
        this.flag = flag;
        this.markId = markId;
        this.nextVisible = nextVisible;
    }

    /**
     * 设置字体色值
     *
     * @param title
     * @param textColor
     * @param value
     * @param flag
     */
    public ItemDataBean(String title, int textColor, String value, String flag) {
        this.flag = flag;
        this.textColor = textColor;
        this.title = title;
        this.value = value;
    }

    /**
     * 设置字体色值和大小
     *
     * @param title
     * @param textColor
     * @param textsize
     * @param value
     * @param flag
     */

    public ItemDataBean(String title, int textColor, int textsize, String value, String flag) {
        this.flag = flag;
        this.textColor = textColor;
        this.textsize = textsize;
        this.title = title;
        this.value = value;
    }

    public ItemDataBean(String title, String value, String unit, String flag) {
        this.value = value;
        this.title = title;
        this.unit = unit;
        this.flag = flag;
    }


    public ItemDataBean(int iconId) {
        this.iconId = iconId;
    }


    public ItemDataBean(String title, String value, int textColor, float textsize, String flag) {
        this.flag = flag;
        this.textColor = textColor;
        this.title = title;
        this.value = value;
        this.textsize = textsize;
    }


    public ItemDataBean(boolean isDivider) {
        this.isDivider = isDivider;
    }
}
