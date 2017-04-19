package whb.cn.com.smartpart.server;

import android.content.Context;

import whb.cn.com.smartpart.BaseApplication;
import whb.cn.com.smartpart.bean.InnerMeassge;
import whb.cn.com.smartpart.bean.UserModel;
import whb.cn.com.smartpart.nettcp.OkSocket;
import whb.cn.com.smartpart.server.maneger.UserManager;

/**
 * Created by Administrator on 2017/2/3.
 * 数据交换的统一入口
 */
public class ManagerEngine {

    // 实体信息头
    private InnerMeassge message;

    private UserManager userManager;

    private OkSocket okSocket;

    private ManagerEngine() {

    }

    public static ManagerEngine getSingleton() {
        return SingleInstanceHolder.sIntance;
    }

    //静态内部类
    private static class SingleInstanceHolder {
        private static final ManagerEngine sIntance = new ManagerEngine();
    }

    /**
     * 初始化
     */
    public void init(Context context) {
        if (message == null) {
            message = new InnerMeassge();
            UserModel user = ((BaseApplication) context).getUser();
            message.setUserId(user.getUserId());
            message.setRequstCode(6);
        }

        if (okSocket == null) {
            okSocket = new OkSocket();
        }

        if (userManager == null) {
            userManager = new UserManager(context, ServerVariable.Service.USER_MANAGER);
        }

    }


    public InnerMeassge getMessage() {
        return message;
    }

    public OkSocket getOkSocket() {
        return okSocket;
    }

    public UserManager getUserManager() {
        return userManager;
    }
}
