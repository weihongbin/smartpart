package whb.cn.com.mylibrary.Listener;

/**
 * =============================================
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: whb.cn.com.smartpart.Lisntener.ReceiveInfoListener.java
 * @author: 魏红彬
 * @e-mail: weihongbin@t-tron.com
 * @date: 2017-03-19 15:22
 */


public interface ReceiveInfoListener<T> {
    public boolean receive(T info);
}
