package whb.cn.com.smartpart.utils;

import com.squareup.otto.Bus;

/**
 * =============================================
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: whb.cn.com.customeview.bean.BusProvider.java
 * @author: 魏红彬
 * @date: 2017-03-13 11:21
 */
public class BusUtils {

    private static final Bus bus = new Bus();

    public static Bus getInstence() {
        return bus;

    }
    private BusUtils() {
    }
}