package whb.cn.com.smartpart.server;

import android.util.Log;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Type;

import whb.cn.com.smartpart.Lisntener.ActivityTaskCallback;
import whb.cn.com.smartpart.bean.InnerMeassge;

/**
 * 回调
 * @param <T>
 */
public abstract class ServerListener<T> {


    //数据类型
    private Type cla;
    //回调
    private ActivityTaskCallback<T> callback;
    //网络状态
    protected boolean isNetwork;
    //网络数据交换
    private InnerMeassge message;

    public ServerListener() {
    }

    //构造方法传值
    public ServerListener(Type cla, ActivityTaskCallback<T> callback) {
        this.cla = cla;
        this.callback = callback;
    }

    //设置网络是否可用
    public void setNetwork(boolean network) {
        isNetwork = network;
    }

    public void dowork() {


        if (isNetwork) {
            callback.setNetWork(true);
//            if ("0".equals(message.getResultCode())) {
//                if (message.getJsonData() == null) {
//                    success(null);
//                    return;
//                }
                success((T) message);
                handleDB((T) message);
//            } else if ("-2".equals(message.getResultCode())) {
//                doServerErr();
//            } else {
//                fail(message.getResultCode(), message.getJsonData());
//            }
        } else {
            callback.setNetWork(false);
            T obj = queryCacheData();
            if (obj == null) {
                fail("9998", "网络连接失败，请检查网络设置");
            } else {
                success(obj);
            }
        }
    }

    ;

    public void success(T t) {
        callback.dismissProgress();
        callback.success(t);
    }

    public void fail(String code, String description) {
        callback.dismissProgress();
        callback.fail(code, description);
    }

    public void doServerErr() {
        T obj = queryCacheData();
        if (obj == null) {
            fail(message.getResultCode(), message.getResultName());
        } else {
            isNetwork = false;
            callback.setNetWork(false);
            success(obj);
        }
    }

    protected void handleDB(T t) {
    }

    protected T queryCacheData() {
        return null;
    }

    public void setMessage(InnerMeassge message) {
        this.message = message;
    }
}
