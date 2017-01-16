package com.fuyuan.marketmanage.sys.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by xin on 2017/1/13.
 */

public class VersionBean extends BmobObject {
    private String versionName;
    private String versionCode;
    private String versionRemark;//更新内容描述信息
    private BmobFile versionFile;

    public BmobFile getVersionFile() {
        return versionFile;
    }

    public void setVersionFile(BmobFile versionFile) {
        this.versionFile = versionFile;
    }


    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionRemark() {
        return versionRemark;
    }

    public void setVersionRemark(String versionRemark) {
        this.versionRemark = versionRemark;
    }

}
