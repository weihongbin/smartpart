package whb.cn.com.smartpart.nettcp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import whb.cn.com.smartpart.server.ServerListener;


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
    SocketClient requestTask;
    public OkSocket(){
        requestTask=new SocketClient();
        executorService.execute(requestTask);
    }

    public void addNewRequest(String data, ServerListener<?> listener){
        if (requestTask!=null)
            requestTask.addRequest(data,listener);
    }

    public void closeConnect() {
        requestTask.stop();
    }
}