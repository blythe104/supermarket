package com.fuyuan.marketmanage.sale.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by xin on 2017/1/6.
 */

public class Sales extends BmobObject implements Serializable {
    private String qrcode;
    private String goodname;
    private Integer salenum;

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getGoodname() {
        return goodname;
    }

    public void setGoodname(String goodname) {
        this.goodname = goodname;
    }

    public Integer getSalenum() {
        return salenum;
    }

    public void setSalenum(Integer salenum) {
        this.salenum = salenum;
    }
}
