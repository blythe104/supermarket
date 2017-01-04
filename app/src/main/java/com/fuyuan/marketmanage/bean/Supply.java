package com.fuyuan.marketmanage.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by xin on 2017/1/4.
 */

public class Supply extends BmobObject implements Serializable {
    private String supply;
    private String phone;
    private String address;
    private String goods;

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getSupply() {
        return supply;
    }

    public void setSupply(String supply) {
        this.supply = supply;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
