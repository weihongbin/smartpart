package whb.cn.com.mylibrary.nettcp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import whb.cn.com.mylibrary.Listener.OnCallBackListener;


/**
 * =============================================
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: whb.cn.com.smartpart.NetUtil.OkSocket.java
 * @author: 魏红彬
 * @e-mail: weihongbin@t-tron.com
 * @date: 2017-03-23 13:21
 */


public class OkSocket {
    private static final String TAG = "OkSocket";
    ExecutorService executorService= Executors.newCachedThreadPool();
    RequestTask requestTask;
    public OkSocket(OnCallBackListener onCallBackListener){
        requestTask=new RequestTask(onCallBackListener);
        executorService.execute(requestTask);
    }

    public void addNewRequest(String data){
        if (requestTask!=null)
            requestTask.addRequest(data);
    }

    public void closeConnect() {
        requestTask.stop();
    }
}