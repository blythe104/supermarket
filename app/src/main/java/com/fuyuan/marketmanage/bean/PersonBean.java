package com.fuyuan.marketmanage.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by xin on 2016/12/29.
 */

public class PersonBean extends BmobObject {
    private String username;
    private String pwd;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
