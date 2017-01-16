package com.fuyuan.marketmanage.sys;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.fuyuan.marketmanage.R;
import com.fuyuan.marketmanage.base.BaseActivity;
import com.fuyuan.marketmanage.login.LoginActivity;
import com.fuyuan.marketmanage.sys.bean.VersionBean;
import com.fuyuan.marketmanage.utils.PackageManagerUtils;
import com.fuyuan.marketmanage.utils.ToastUtils;
import com.fuyuan.marketmanage.widget.exception.repeatFlagException;
import com.fuyuan.marketmanage.widget.title.CustomTitle;
import com.fuyuan.marketmanage.widget.ui.CustomMenuView;
import com.fuyuan.marketmanage.widget.ui.ItemDataBean;

import java.io.File;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class SysActivity extends BaseActivity {

    private static final int GO_MAIN = 1001;
    private static final int GO_UPDATE = 1002;
    private CustomMenuView menuView;
    private CustomTitle mTitle;
    private List<VersionBean> versionList;

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GO_MAIN:
                    standHere();
                    break;
                case GO_UPDATE:
                    update();
                    break;
            }
        }
    };

    private void update() {
        Toast.makeText(SysActivity.this, "更新ING", Toast.LENGTH_SHORT).show();
    }

    private void standHere() {
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_sys);
        menuView = (CustomMenuView) findViewById(R.id.menu_view);
        mTitle = (CustomTitle) findViewById(R.id.titlebar);
        //        addVersion();

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() throws repeatFlagException {
        mTitle.setTitle("系统管理");
        initMenuData();
    }

    private void initMenuData() throws repeatFlagException {
        menuView.addItem("系统版本号", "desc", "version")//
                .addItem("退出登录", "", "exit")//
                .build();
        menuView.setOnMenuListener(new CustomMenuView.OnMenuListener() {
            @Override
            public void onClickItem(ItemDataBean data) {
                switch (data.flag) {
                    case "exit":
                        startActivity(new Intent(SysActivity.this, LoginActivity.class));
                        break;
                    case "version":
                        queryVersion();
                        break;
                }

            }
        });
    }

    /**
     * 查询服务器上的版本号
     */
    private void queryVersion() {
        BmobQuery<VersionBean> query = new BmobQuery<>();
        query.order("-createdAt");
        query.findObjects(new FindListener<VersionBean>() {
            @Override
            public void done(List<VersionBean> list, BmobException e) {
                if (list.size() != 0) {
                    ToastUtils.toast(SysActivity.this, "版本号信息查询成功");

                    versionList = list;
                    String versionCode = versionList.get(0).getVersionCode();
                    if (PackageManagerUtils.getVersionCode(SysActivity.this) < Integer.parseInt(versionCode)) {
                        mHandler.sendEmptyMessage(GO_UPDATE);
                    } else {
                        mHandler.sendEmptyMessage(GO_MAIN);
                    }
                } else {
                    ToastUtils.toast(SysActivity.this, "没有版本信息");
                }
            }
        });
    }

    private void addVersion() {
        VersionBean version = new VersionBean();
        String goods = "1.0";
        String goodsNum = "";
        BmobFile file = new BmobFile(new File(goodsNum));
        String bid = "1";
        String price = "1";

        if (TextUtils.isEmpty(goods) || TextUtils.isEmpty(bid)) {
            ToastUtils.toast(this, "版本号不能为空");
        } else {
            version.setVersionCode(goods);
            version.setVersionFile(file);
            version.setVersionName(bid);
            version.setVersionRemark(price);
            version.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null) {
                        ToastUtils.toast(SysActivity.this, "创建数据成功");
                    } else {
                        ToastUtils.toast(SysActivity.this, "创建数据失败" + e.toString());
                    }

                }
            });
        }
    }

    @Override
    public void processClick(View v) {

    }
}
