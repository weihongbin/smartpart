package whb.cn.com.smartpart.Lisntener;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import whb.cn.com.smartpart.ui.BaseActivity;


/**
 * 请求回调
 * @param <T>
 */
public abstract class ActivityTaskCallback<T> {
    //是否网络
    protected boolean isNetWork = true;
    //是否加载更多
    protected boolean isLoadMore = true;
    //应用上下文
    private Context activity;
    //进度条
    private boolean isProgress;
    /**
     * 加载条目
     */
    private ProgressDialog progressDialog;

    public ActivityTaskCallback() {
    }

    public ActivityTaskCallback(Context activity, boolean isProgress) {
        this.activity = activity;
        this.isProgress = isProgress;
        if (isProgress) {
            if (activity instanceof BaseActivity) {
                ((BaseActivity) activity).showLoadingProgressDialog();
            } else {
                showLoadingProgressDialog(activity);
            }
        }
    }

    // ***************************************
    // 加载/取消进度条
    // ***************************************
    public void showLoadingProgressDialog(Context activity) {
        this.showProgressDialog(activity, "加载中...");
    }

    //取消
    public void dismissProgress() {
        if (isProgress) {
            if (activity instanceof BaseActivity) {
                ((BaseActivity) activity).dismissProgressDialog();
            } else {
                dismissProgressDialog();
            }
        }
    }

    // 显示进度条
    public void showProgressDialog(Context activity, CharSequence message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(activity);
            progressDialog.setIndeterminate(true);
        }
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    //取消进条
    public void dismissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    //成功回调处理
    public abstract void success(T t);

    //失败及失败原因
    public void fail(String code, String description) {
        if (this.isProgress) {
            Toast.makeText(activity,description,Toast.LENGTH_SHORT).show();
        }
    }

    public void setNetWork(boolean netWork) {
        isNetWork = netWork;
    }

    public void setLoadMore(boolean loadMore) {
        isLoadMore = loadMore;
    }
}
