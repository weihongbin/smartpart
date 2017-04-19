package whb.cn.com.smartpart.server;

/**
 * Created by Administrator on 2017/2/3.
 */
public class ServerVariable {
    /**
     * 远程服务service
     **/
    public static final class Service {
        // 用户服务
        public static final String USER_MANAGER = "appUserService";

    }
    /**
     * 远程用户方法服务方法
     */
    public static final class UserMethod {
        // 获取用户登录方法
        public static final String userLogin = "login";
    }

}
