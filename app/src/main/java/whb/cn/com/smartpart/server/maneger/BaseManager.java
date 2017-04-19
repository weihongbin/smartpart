package whb.cn.com.smartpart.server.maneger;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;

import java.util.List;


import whb.cn.com.smartpart.bean.InnerMeassge;
import whb.cn.com.smartpart.nettcp.OkSocket;
import whb.cn.com.smartpart.server.ManagerEngine;
import whb.cn.com.smartpart.server.ServerListener;
import whb.cn.com.smartpart.utils.NetWorkUtils;

/**
 * 基类
 */
public class BaseManager {
    //交易服务
    protected String transService = "";
    protected Context context;
    //获取网络客户端
    protected OkSocket okSocket;

    public BaseManager() {
    }

    public BaseManager(Context context) {
        this.context = context;
        okSocket= ManagerEngine.getSingleton().getOkSocket();
    }

    public BaseManager(Context context, String transService) {
        this(context);//注意
        this.transService = transService;
    }

    /**
     * 请求数据
     *
     * @param transMethod
     * @param request
     * @param listener
     */
    protected void send2server(String transMethod, Object request, ServerListener<?> listener) {
        send2server(transMethod, request, null, listener);
    }

    private void send2server(String transMethod, Object request, List<String> files, ServerListener<?> listener) {
            if(NetWorkUtils.isNetConnected(context)){
                 listener.setNetwork(true);
                InnerMeassge message = ManagerEngine.getSingleton().getMessage();
                message.setTag(transMethod);
                if (request != null && !TextUtils.isEmpty(request.toString())) {
                    message.setJsonData(JSON.toJSONString(request));
                }
                // 判断是否带文件
                if (files != null) {
                    //文件
//                    Map<String, String> params = getHeadValue(message);
//                    List<File> listFile = new ArrayList<File>();
//                    File file = null;
//                    for (String path : files) {
//                        file = new File(path);
//                        listFile.add(file);
//                    }
//
//                    //TODO 文件上传
//                    okHttpClient.execPost(params, listFile, listener);
                } else {
                    Log.e("魏红彬","没有文件");
                    okSocket.addNewRequest(JSON.toJSONString(message),listener);
                }

            }else{
                //无网络
                listener.setNetwork(false);
                listener.dowork();
            }
    }


}
