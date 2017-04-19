package whb.cn.com.mylibrary.Listener;

/**
 * =============================================
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: whb.cn.com.smartpart.Lisntener.OnCallBackListener.java
 * @author: 魏红彬
 * @e-mail: weihongbin@t-tron.com
 * @date: 2017-03-18 14:45
 */


public interface OnCallBackListener<T> {


     void onSuccess(T t);

     void  onFailure(int code,String  content);



}