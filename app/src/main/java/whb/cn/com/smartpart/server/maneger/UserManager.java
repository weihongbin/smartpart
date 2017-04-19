package whb.cn.com.smartpart.server.maneger;

import android.content.Context;

import whb.cn.com.smartpart.Lisntener.ActivityTaskCallback;
import whb.cn.com.smartpart.bean.Account;
import whb.cn.com.smartpart.bean.InnerMeassge;
import whb.cn.com.smartpart.server.ServerListener;
import whb.cn.com.smartpart.server.ServerVariable;


/**
 * Created by Administrator on 2017/2/3.
 */
public class UserManager extends BaseManager{
    /**
     * 构造函数
     *
     * @param context
     * @param transService
     */
    public UserManager(Context context, String transService) {
        super(context, transService);
    }
    /**
     * 登录
     *
     * @param userModel
     * @param callback
     */
    public void getLoginUser(Account userModel, final ActivityTaskCallback<InnerMeassge> callback) {
        this.send2server(ServerVariable.UserMethod.userLogin, userModel, new ServerListener<InnerMeassge>(InnerMeassge.class, callback) {
        });
    }


}
