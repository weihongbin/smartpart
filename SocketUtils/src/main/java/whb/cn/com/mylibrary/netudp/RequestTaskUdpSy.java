package whb.cn.com.mylibrary.netudp;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.net.DatagramSocket;
import java.util.concurrent.ConcurrentLinkedQueue;

import whb.cn.com.mylibrary.Listener.OnCallBackListener;
import whb.cn.com.mylibrary.bean.CustomDataModel;
import whb.cn.com.mylibrary.common.OpenHelperClient;
import whb.cn.com.mylibrary.utils.CloseUtils;


/**
 * =============================================
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: whb.cn.com.smartpart.NetUtil.RequestTask.java
 * @author: 魏红彬
 * @e-mail: weihongbin@t-tron.com
 * @date: 2017-03-23 13:23
 */


public class RequestTaskUdpSy implements Runnable {
    private static final String TAG = "RequestTask";
    private static final int SUCCESS = 100;
    private static final int FAILED = -1;
    private boolean isLongConnection = true;
    private OnCallBackListener<CustomDataModel<String>> onCallBackListener;
    private Handler handler;
    private SendTask sendTask;
    DatagramSocket dsocket = null;
    protected volatile ConcurrentLinkedQueue<String> sendData = new ConcurrentLinkedQueue<>();
    public RequestTaskUdpSy(OnCallBackListener<CustomDataModel<String>> onCallBackListener) {
        this.onCallBackListener = onCallBackListener;
        handler = new MyHandler(onCallBackListener);
    }

    @Override
    public void run() {
        try {
            dsocket = new DatagramSocket(8224);
            sendTask = new SendTask();
            sendTask.dSend = dsocket;
            sendTask.start();
        } catch (IOException e) {
            failedMessage(2, "IOException");
            e.printStackTrace();
        }
    }

    public void stop() {
        Log.e("ReceiverTask", " stop ");
        toNotifyAll(sendData);
        if (sendTask != null) {
            sendTask.interrupt();
            sendTask.isCancle = true;
            if (sendTask.dSend != null) {
                synchronized (sendTask.dSend) {
                    sendTask.dSend = null;
                }
            }
            sendTask = null;
        }
        if (dsocket != null) {
            try {
                dsocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        clearConnection();
    }

    private void clearConnection() {
        sendData.clear();
        isLongConnection = false;
    }


    //write 当没有发送的数据时让发送线程进行等待
    public class SendTask extends Thread {
        private boolean isCancle = false;
        private DatagramSocket dSend;
        @Override
        public void run() {
            while (!isCancle) {
                String dataContent = sendData.poll();
                if (TextUtils.isEmpty(dataContent)) {
                    toWait(sendData);//休眠等待
                } else {
                    if (dsocket.isClosed()) {
                        isCancle = true;
                        RequestTaskUdpSy.this.stop();
                        break;
                    } else {
                        if (dSend != null) {
                            synchronized (dSend) {
                                CustomDataModel<String> receiverData = OpenHelperClient.OutputDataSy(dSend, dataContent);
                                if (receiverData != null) {
                                    successMessage(receiverData);
                                } else {
                                    failedMessage(2, "服务器异常");
                                }
                            }
                        }
                    }
                }
            }
            CloseUtils.close(dsocket);
        }
    }

    /**
     * 等待
     *
     * @param o
     */
    private void toWait(Object o) {
        synchronized (o) {
            try {
                o.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 唤醒
     *
     * @param o
     */
    protected void toNotifyAll(Object o) {
        synchronized (o) {
            o.notifyAll();
        }
    }

    public void printMessage() {

    }

    private void failedMessage(int code, String msg) {
        Message message = handler.obtainMessage(FAILED);
        message.what = FAILED;
        message.arg1 = code;
        message.obj = msg;
        handler.sendMessage(message);
    }

    private void successMessage(CustomDataModel<String> customDataModel) {
        Message message = handler.obtainMessage(SUCCESS);
        message.what = SUCCESS;
        message.obj = customDataModel;
        handler.sendMessage(message);
    }

    public void addRequest(String data) {
        sendData.add(data);
        toNotifyAll(sendData);
        printMessage();
    }


    public class MyHandler extends Handler {
        OnCallBackListener<CustomDataModel<String>> onCallBackListener;

        MyHandler(OnCallBackListener<CustomDataModel<String>> callBack) {
            super(Looper.getMainLooper());
            this.onCallBackListener = callBack;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SUCCESS:
                    onCallBackListener.onSuccess((CustomDataModel<String>) msg.obj);
                    break;
                case FAILED:
                    onCallBackListener.onFailure(msg.arg1, (String) msg.obj);
                    break;
                default:
                    break;
            }
        }
    }
}