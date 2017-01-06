package com.fuyuan.marketmanage.bill.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by xin on 2017/1/6.
 */

public class Bill extends BmobObject implements Serializable {
    private String billdate;
    private Integer income;
    private String pay;
    private String paydesc;

    public String getBilldate() {
        return billdate;
    }

    public void setBilldate(String billdate) {
        this.billdate = billdate;
    }

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getPaydesc() {
        return paydesc;
    }

    public void setPaydesc(String paydesc) {
        this.paydesc = paydesc;
    }
}
