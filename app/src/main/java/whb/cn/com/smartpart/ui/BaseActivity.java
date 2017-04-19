package whb.cn.com.smartpart.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;
import whb.cn.com.mylibrary.Listener.OnCallBackListener;
import whb.cn.com.mylibrary.nettcp.OkSocket;
import whb.cn.com.smartpart.BaseApplication;
import whb.cn.com.smartpart.utils.BusUtils;


public abstract class BaseActivity extends AppCompatActivity {

    public Context nowContext;
    protected BaseApplication baseApplication;
    private ProgressDialog progressDialog;
    private boolean destroyed = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        init();

    }

    /**
     * 初始化
     */
    private void init() {
        ButterKnife.bind(this);
        BusUtils.getInstence().register(this);
        baseApplication = (BaseApplication) getApplication();
        nowContext = this;
        initData();//加载数据
        initListener();//初始化监听
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        nowContext = null;
        baseApplication = null;
        ButterKnife.unbind(this);
        BusUtils.getInstence().unregister(this);
    }

    //    /*********************子类实现*****************************/
    //获取布局文件
    public abstract int getLayoutId();

    //加载数据
    public abstract void initData();

    //初始化监听
    public abstract void initListener();

    // ***************************************
    // 加载进度条
    // ***************************************
    public void showLoadingProgressDialog() {
        this.showProgressDialog("请稍后，加载中...");
    }

    public void showProgressDialog(CharSequence message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setIndeterminate(true);
        }
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    public void dismissProgressDialog() {
        if (progressDialog != null && !destroyed) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
    protected void show(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

}
