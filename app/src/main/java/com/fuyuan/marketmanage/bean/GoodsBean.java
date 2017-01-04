package com.fuyuan.marketmanage.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by xin on 2017/1/4.
 */

public class GoodsBean extends BmobObject implements Serializable {
    //商品名称
    private String goodsName;
    //数量
    private String goodsNum;
    //进价
    private String bid;
    //售价
    private String price;
    //描述
    private String desc;
    //供货商Id
    private String supplyId;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(String supplyId) {
        this.supplyId = supplyId;
    }
}
