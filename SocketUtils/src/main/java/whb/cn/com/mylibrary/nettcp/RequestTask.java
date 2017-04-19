package whb.cn.com.mylibrary.nettcp;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.net.SocketFactory;

import whb.cn.com.mylibrary.Listener.OnCallBackListener;
import whb.cn.com.mylibrary.bean.CustomDataModel;
import whb.cn.com.mylibrary.common.Config;
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


public class RequestTask implements Runnable {
    private static final String TAG = "RequestTask";
    private static final int SUCCESS = 100;
    private static final int FAILED = -1;
    private boolean isLongConnection = true;
    private OnCallBackListener<CustomDataModel<String>> onCallBackListener;
    private Handler handler;
    private SendTask sendTask;
    private ReceiverTask receiverTask;
    HeartBeatTask heartBeatTask = null;
    Socket socket = null;
    protected volatile ConcurrentLinkedQueue<String> sendData = new ConcurrentLinkedQueue<>();
    protected volatile ConcurrentLinkedQueue<CustomDataModel<String>> receiveDatas = new ConcurrentLinkedQueue<>();

    public RequestTask(OnCallBackListener<CustomDataModel<String>> onCallBackListener) {
        this.onCallBackListener = onCallBackListener;
        handler = new MyHandler(onCallBackListener);
    }

    @Override
    public void run() {
//        uuid = ProjectApplication.getUUID();
        try {
            failedMessage(0, "服务器连接中");
            try {
                socket = SocketFactory.getDefault().createSocket(Config.IP, Config.PORT);
            } catch (ConnectException e) {
                failedMessage(1, "服务区器连接异常,请检查网络");
                return;
            }
            sendTask = new SendTask();
            sendTask.outputStreamSend = socket.getOutputStream();
            sendTask.start();

            receiverTask = new ReceiverTask();
            receiverTask.inputStreamReciver = socket.getInputStream();
            receiverTask.start();

//            if (isLongConnection) {
//                heartBeatTask = new HeartBeatTask(sendTask.outputStreamSend,socket);
//                executorService = Executors.newCachedThreadPool();
//                executorService.execute(heartBeatTask);
//            }
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
            if (sendTask.outputStreamSend != null) {
                synchronized (sendTask.outputStreamSend) {
                    sendTask.outputStreamSend = null;
                }
            }
            sendTask = null;
        }

        if (receiverTask != null) {
            receiverTask.interrupt();
            receiverTask.isCancle = true;
            if (receiverTask.inputStreamReciver != null) {
                CloseUtils.close(receiverTask.inputStreamReciver);
                receiverTask.inputStreamReciver = null;
            }
            receiverTask = null;
        }
        if (heartBeatTask != null) {
            heartBeatTask.setKeepAlive(false);
            heartBeatTask = null;
        }
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        clearConnection();
    }

    private void clearConnection() {
        sendData.clear();
        receiveDatas.clear();
        isLongConnection = false;
    }


    /**
     * read...
     */
    public class ReceiverTask extends Thread {

        InputStream inputStreamReciver;
        private boolean isCancle = false;

        @Override
        public void run() {

            while (!isCancle) {

                if (socket.isClosed() || !socket.isConnected()) {
                    isCancle = true;
                    Log.e("ReceiverTask", " if ");
                    RequestTask.this.stop();
                    break;
                }
                try {
                    CustomDataModel<String> receiverData = OpenHelperClient.dealResponseResult(inputStreamReciver);
                    if (receiverData != null) {
                        successMessage(receiverData);
                    }
                } catch (Exception e) {
                    isCancle = true;
                    heartBeatTask.setKeepAlive(false);
                    RequestTask.this.stop();
                }
            }
            CloseUtils.close(inputStreamReciver);
        }
    }


    //write 当没有发送的数据时让发送线程进行等待
    public class SendTask extends Thread {
        private boolean isCancle = false;
        private OutputStream outputStreamSend;

        @Override
        public void run() {
            while (!isCancle) {
                String dataContent = sendData.poll();
                if (TextUtils.isEmpty(dataContent)) {
                    Log.e(Config.TAG, dataContent + socket.isConnected() + "SendTask");
                    toWait(sendData);//休眠等待
                } else {
                    if (socket.isClosed()) {
                        Log.e(Config.TAG, socket.isClosed() + "socket.isClosed()");
                        isCancle = true;
                        RequestTask.this.stop();
                        break;
                    } else {
                        if (outputStreamSend != null) {
                            synchronized (outputStreamSend) {
                                OpenHelperClient.OutputData(new BufferedOutputStream(outputStreamSend), dataContent);
                            }
                        }
                    }
                }
            }
            CloseUtils.close(outputStreamSend);
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