package whb.cn.com.smartpart.nettcp;

import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import whb.cn.com.mylibrary.common.Config;
import whb.cn.com.mylibrary.common.OpenHelperClient;
import whb.cn.com.mylibrary.utils.CloseUtils;


/**
 * =============================================
 * 心跳包
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: whb.cn.com.smartpart.NetUtil.HeartBeatTask.java
 * @author: 魏红彬
 * @e-mail: weihongbin@t-tron.com
 * @date: 2017-03-23 13:38
 */


public class HeartBeatTask implements Runnable {
    private static final String TAG = "HeartBeatTask";
    private static final int REPEATTIME = 3 * 1000;
    private volatile boolean isKeepAlive = true;
    private OutputStream outputStream;
    Socket socket;

    public HeartBeatTask(OutputStream outputStream, Socket socket) {
        this.outputStream = outputStream;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (isKeepAlive) {
                Log.e("心跳", socket.isConnected() + "");
                OpenHelperClient.OutputData(new BufferedOutputStream(outputStream), "心跳包。。。。。");
                try {
                    Thread.sleep(REPEATTIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                CloseUtils.close(outputStream);
            }
        } catch (Exception e) {
            Log.e(Config.TAG, " : Time is out, request" + " has been closed.");
            e.printStackTrace();
            CloseUtils.close(outputStream);
        } finally {
            if (outputStream != null) {
                CloseUtils.close(outputStream);
            }
        }
    }

    public void setKeepAlive(boolean isKeepAlive) {
        this.isKeepAlive = isKeepAlive;
    }
}