package com.fuyuan.marketmanage.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by xin on 2017/1/12.
 */

public class ToastUtils {
    public static void toast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
