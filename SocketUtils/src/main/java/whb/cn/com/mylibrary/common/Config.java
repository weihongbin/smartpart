package whb.cn.com.mylibrary.common;

/**
 * =============================================
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: whb.cn.com.smartpart.Config.java
 * @author: 魏红彬
 * @e-mail: weihongbin@t-tron.com
 * @date: 2017-03-18 13:48
 */


public class Config {
    public static final String TAG = "Config";

//    public static  final String  IP= "60.173.195.98";
   public static  final String  IP= "192.168.1.148";
    public static  final  int  PORT= 8223;
    public static final int DATA_TATOL_LENGTH=4;
    public static final int DATA_TAG_LENGTH=4;

    /**
     * 请求
     */
    public static final int REQUEST_LOGIN=1;
    public static final int REQUEST_REGIST=2;
    /**
     * 接收返回结果
     */
    public static final int RESULT_LOGIN=101;
    public static final int RESULT_REGIST=102;

    public static final int SUCCESS = 1000;
    public static final int FAIL = 1001;
    public static final int PRE =1024*5 ;
}