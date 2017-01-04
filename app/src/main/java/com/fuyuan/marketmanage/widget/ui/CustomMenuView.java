package com.fuyuan.marketmanage.widget.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.fuyuan.marketmanage.widget.exception.repeatFlagException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xin on 2016/12/9.
 */

public class CustomMenuView extends LinearLayout {


    public List<ItemDataBean> list = new ArrayList<>();
    private OnMenuListener listener;
    private boolean clickEnable = true;

    public CustomMenuView(Context context) {
        super(context);
        initView();
    }

    public CustomMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CustomMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        this.setOrientation(LinearLayout.VERTICAL);
    }

    public void setData(List<ItemDataBean> datas) throws repeatFlagException {
//        for (int i = 0; i < datas.size() - 1; i++) {
//            String flagTemp = datas.get(i).flag;
//            for (int j = i + 1; j < datas.size(); j++) {
//                if (flagTemp.equals(datas.get(j).flag)) {
//                    throw new repeatFlagException("标记位重复");
//                }
//            }
//        }

        for (ItemDataBean data : datas) {
            this.addView(createItem(data));
        }
    }

    private View createItem(ItemDataBean data) {
        if (data.isDivider) {
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setMinimumHeight(20);
            // TODO: 2016/12/15 测试间隔条目
            linearLayout.setBackgroundResource(android.R.color.darker_gray);
            linearLayout.setTag(data);
            return linearLayout;
        } else {
            MenuMixView menuMixView = createItemMenu(data);
            bindMenuListener(menuMixView);
            return menuMixView;
        }
    }

    private void bindMenuListener(final MenuMixView menuMixView) {
        menuMixView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickEnable) {
                    if (listener != null) {
                        listener.onClickItem((ItemDataBean) menuMixView.getTag());
                    }
                }
            }
        });
    }

    private MenuMixView createItemMenu(ItemDataBean data) {
        MenuMixView menuMixView = new MenuMixView(getContext());
        menuMixView.setData(data);
        menuMixView.setTag(data);
        return menuMixView;
    }

    public CustomMenuView updateValue(int iconId, String value) {
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            if (!((ItemDataBean) childView.getTag()).isDivider) {
                MenuMixView menuMixView = (MenuMixView) childView;
                if (menuMixView.getIcon() == iconId) {
                    menuMixView.setValue(value);
                }
            }
        }
        return this;
    }

    /**
     * 更新菜单左边部分标题
     *
     * @param flag
     * @param title
     * @return
     */
    public CustomMenuView updateTitle(String flag, String title) {
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            if (!((ItemDataBean) childView.getTag()).isDivider) {
                MenuMixView menuMixView = (MenuMixView) childView;
                if (menuMixView.getData().flag.equals(flag)) {
                    menuMixView.setTitle(title);
                }
            }
        }
        return this;
    }

    /**
     * 设置图标动态的显示或者隐藏
     *
     * @param flag
     * @param visible
     * @return
     */
    public CustomMenuView updateItemPointVisible(String flag, int visible) {
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            if (!((ItemDataBean) childView.getTag()).isDivider) {
                MenuMixView menuMixView = (MenuMixView) childView;
                if (menuMixView.getData().flag.equals(flag)) {
                    menuMixView.setImgVisible(visible);
                }
            }

        }
        return this;
    }

    public void setOnMenuListener(OnMenuListener listener) {
        this.listener = listener;
    }

    public void setClickEnable(boolean clickEnable) {
        this.clickEnable = clickEnable;
    }


    /**
     * 添加简单的菜单栏
     *
     * @param title   标题
     * @param visible 向右箭头的显示情况
     * @param flag
     * @return
     */
    public CustomMenuView addItem(String title, int visible, String flag) {
        list.add(new ItemDataBean(title, visible, flag));
        return this;
    }

    /**
     * 添加只有标题和描述菜单栏
     *
     * @param title 标题
     * @param value 描述信息
     * @param flag
     * @return
     */
    public CustomMenuView addItem(String title, String value, String flag) {
        list.add(new ItemDataBean(title, value, flag));
        return this;
    }

    /**
     * @param title       标题
     * @param value       描述信息
     * @param nextVisible 是否显示
     * @param flag        标记位
     * @return
     */
    public CustomMenuView addItem(String title, String value, int nextVisible, String flag) {
        list.add(new ItemDataBean(title, value, nextVisible, flag));
        return this;
    }

    /**
     * @param iconId      ICon
     * @param title       标题
     * @param nextVisible 是否显示
     * @param flag
     * @return
     */

    public CustomMenuView addItem(int iconId, String title, int nextVisible, String flag) {
        list.add(new ItemDataBean(iconId, title, nextVisible, flag));
        return this;
    }

    /**
     * @param iconId      icon
     * @param title       标题
     * @param value       内容
     * @param nextVisible 是否显示
     * @param flag
     * @return
     */
    public CustomMenuView addItem(int iconId, String title, String value, int nextVisible, String flag) {
        list.add(new ItemDataBean(iconId, title, value, nextVisible, flag));
        return this;
    }

    /**
     * @param title 标题
     * @param color 字体色值
     * @param value 描述
     * @param flag
     * @return
     */
    public CustomMenuView addItem(String title, int color, String value, String flag) {
        list.add(new ItemDataBean(title, color, value, flag));
        return this;
    }

    /**
     * @param title
     * @param color
     * @param value
     * @param flag
     * @return
     */
    public CustomMenuView addItem(String title, int color, int textsize, String value, String flag) {
        list.add(new ItemDataBean(title, color, textsize, value, flag));
        return this;
    }

    /**
     * 设置图标和mark图标
     *
     * @param iconId
     * @param title
     * @param markId
     * @param value
     * @param nextVisible
     * @param flag
     * @return
     */

    public CustomMenuView addItem(int iconId, String title, int markId, String value, int nextVisible, String flag) {
        list.add(new ItemDataBean(iconId, title, markId, value, nextVisible, flag));
        return this;
    }


    public CustomMenuView addDivider(boolean isDivider) {
        list.add(new ItemDataBean(isDivider));
        return this;
    }

    public void build() throws repeatFlagException {
        setData(list);
    }

    public interface OnMenuListener {
        void onClickItem(ItemDataBean data);

    }
}
