package whb.cn.com.mylibrary.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * =============================================
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: whb.cn.com.smartpart.utils.CloseUtils.java
 * @author: 魏红彬
 * @e-mail: weihongbin@t-tron.com
 * @date: 2017-03-19 09:43
 */


public class CloseUtils {
    private static final String TAG = "CloseUtils";

    public static boolean close(Closeable... objects) {
        for (Closeable o : objects) {
            if (o != null) {
                try {
                    o.close();
                    o=null;
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return false;
    }
}