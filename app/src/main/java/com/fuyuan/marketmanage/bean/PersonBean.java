package com.fuyuan.marketmanage.bean;

import java.io.Serializable;

import cn.bmob.v3.BmobUser;

/**
 * Created by xin on 2016/12/29.
 */

public class PersonBean extends BmobUser implements Serializable {
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
