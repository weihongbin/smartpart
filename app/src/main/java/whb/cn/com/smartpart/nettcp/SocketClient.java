package whb.cn.com.smartpart.nettcp;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import org.json.JSONObject;

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
import whb.cn.com.smartpart.BaseApplication;
import whb.cn.com.smartpart.bean.InnerMeassge;
import whb.cn.com.smartpart.server.ServerListener;
import whb.cn.com.smartpart.server.maneger.BaseManager;
import whb.cn.com.smartpart.utils.T;


/**
 * Created by Administrator on 2017/2/3
 * 网络请求.
 */
public class SocketClient extends BaseManager implements Runnable {
    private static final String TAG = "SocketClient";
    private static final int SUCCESS = 100;
    private static final int FAILED = -1;
    private boolean isLongConnection = true;
    private SendTask sendTask;
    private ReceiverTask receiverTask;
    private HeartBeatTask heartBeatTask = null;
    private  ServerListener<?>   listener;
    private Socket socket = null;
    private MyHandler handler;
    protected volatile ConcurrentLinkedQueue<String> sendData = new ConcurrentLinkedQueue<>();
    public SocketClient() {
        handler=new MyHandler();
    }

    @Override
    public void run() {
        try {

            try {
                socket = SocketFactory.getDefault().createSocket(Config.IP, Config.PORT);
            } catch (ConnectException e) {
                failedMessageInner(1, "服务器连接异常,请检查网络");
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
//            failedMessage(2, "IOException");
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
                    SocketClient.this.stop();
                    break;
                }
                try {
                    CustomDataModel<String> receiverData = OpenHelperClient.dealResponseResult(inputStreamReciver);
                    if (receiverData != null) {
                        successMessage(receiverData);
                    }else{
                        failedMessageInner(2,"服务器异常，请稍后再试");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("ReceiverTask", " 222222222222222222222222222 ");
                    isCancle = true;
                    SocketClient.this.stop();
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
                        SocketClient.this.stop();
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

    public void addRequest(String data, ServerListener<?> listener) {
        this.listener=listener;
        sendData.add(data);
        toNotifyAll(sendData);
        printMessage();

    }
    private void failedMessageInner(int code, String msg) {
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
    public  class MyHandler extends Handler {


        MyHandler() {
            super(Looper.getMainLooper());
        }



        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SUCCESS:
                    T.show(BaseApplication.getAppContext(), "成功");
                    CustomDataModel<String> customDataModel= (CustomDataModel<String>) msg.obj;
                    InnerMeassge view = JSON.parseObject(customDataModel.getData(), InnerMeassge.class);
                    if(listener!=null){
                        listener.setMessage(view);
                        listener.dowork();
                    }else{
                        Log.e("ReceiverTask", "空");
                    }

                    break;
                case FAILED:
                    String coed= (String) msg.obj;
//                    InnerMeassge viewFail=new InnerMeassge();
//                    viewFail.setResultName(coed);
//                    if(listener!=null){
//                        listener.setMessage(viewFail);
//                        listener.dowork();
//                    }else{
                    T.show(BaseApplication.getAppContext(), coed);
//                    }
                    break;
                default:
                    break;
            }
        }
    }
}
