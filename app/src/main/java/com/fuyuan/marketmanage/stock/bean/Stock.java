package com.fuyuan.marketmanage.stock.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by xin on 2017/1/5.
 */

public class Stock extends BmobObject implements Serializable {
    private String stockgood;
    private String num;
    private String supplymsg;
    private String isadd;

    public String getStockgood() {
        return stockgood;
    }

    public void setStockgood(String stockgood) {
        this.stockgood = stockgood;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getSupplymsg() {
        return supplymsg;
    }

    public void setSupplymsg(String supplymsg) {
        this.supplymsg = supplymsg;
    }

    public String getIsadd() {
        return isadd;
    }

    public void setIsadd(String isadd) {
        this.isadd = isadd;
    }
}
