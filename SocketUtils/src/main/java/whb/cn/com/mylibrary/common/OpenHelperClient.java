package whb.cn.com.mylibrary.common;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import whb.cn.com.mylibrary.bean.CustomDataModel;
import whb.cn.com.mylibrary.utils.CloseUtils;


public class OpenHelperClient {

    /**
     * 异步
     * @param ds
     * @param json
     * @return
     */
    public static void OutputData(DatagramSocket ds, String json) {
        try {
            byte[] data1 = OpenHelperClient.int2ByteArrays(json.getBytes("UTF-8").length);
            byte[] data2 = json.getBytes("UTF-8");
            byte[] data3 = new byte[data1.length + data2.length];
            System.arraycopy(data1, 0, data3, 0, data1.length);
            System.arraycopy(data2, 0, data3, data1.length, data2.length);
            DatagramPacket dp = new DatagramPacket(data3, data3.length, InetAddress.getByName("192.168.1.102"),
                    8222);
            ds.send(dp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 同步
     * @param ds
     * @param json
     * @return
     */
    public static  CustomDataModel<String> OutputDataSy(DatagramSocket ds, String json) {

        try {
            byte[] data1 = OpenHelperClient.int2ByteArrays(json.getBytes("UTF-8").length);
            byte[] data2 = json.getBytes("UTF-8");
            byte[] data3 = new byte[data1.length + data2.length];
            System.arraycopy(data1, 0, data3, 0, data1.length);
            System.arraycopy(data2, 0, data3, data1.length, data2.length);
            DatagramPacket dp = new DatagramPacket(data3, data3.length, InetAddress.getByName("192.168.1.148"),
                    8222);
            ds.send(dp);

            return dealResponseResult(ds);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 写数据
     *
     * @param bos
     * @param json
     */
    public static void OutputData(BufferedOutputStream bos, String json) {
        try {
            System.out.println("走了吗");
            bos.write(int2ByteArrays(json.getBytes("UTF-8").length));// 包体总长度
            bos.write(json.getBytes("UTF-8"));// 包体内容
            bos.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 处理输入流
     *
     * @param inputStream
     * @return
     */
    public static CustomDataModel<String> dealResponseResult(InputStream inputStream) {
        try {
            Log.e("ReceiverTask", "eeeeeeeeeeeeeeeeeeeeee");
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            int tagValue = 0;
            byte[] header = new byte[Config.DATA_TATOL_LENGTH];
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bis.read(header, 0, header.length);// 先读取前4个字节获取总体的长度
            int dataLen = byteArrayToInt(header);
            Log.e("ReceiverTask", dataLen + "");
            if (dataLen == 0) {
                Log.e("ReceiverTask", "空");
            } else {
                byte[] data = new byte[dataLen];
                bis.read(data, 0, dataLen);
                bos.write(data, 0, dataLen);
            }
            String contents= new String(bos.toByteArray());
            Log.e("ReceiverTask","收到服务器返回的数据："+contents);
            CustomDataModel<String> data = new CustomDataModel<String>(dataLen, tagValue,contents);

            CloseUtils.close(bos, bis);
            return data;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 处理
     *
     * @param datagramSocket
     * @return
     */
    public static CustomDataModel<String> dealResponseResult(DatagramSocket datagramSocket) {
        try {
            byte[] bys=new byte[Config.PRE];
            DatagramPacket dp=new DatagramPacket(bys, bys.length);
            Log.e(Config.TAG,"dealResponseResult阻塞");
            datagramSocket.receive(dp);
            byte[] data1=new byte[Config.DATA_TAG_LENGTH];
            System.arraycopy(dp.getData(),0,data1,0,Config.DATA_TAG_LENGTH);
            int len=OpenHelper.byteArrayToInt(data1);
            String data = new String(dp.getData(), Config.DATA_TAG_LENGTH, len,"UTF-8");
            CustomDataModel<String> customDataModel= new CustomDataModel<String>(len, 0, data);
            return customDataModel;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }





    public static byte[] int2ByteArrays(int i) {
        byte[] result = new byte[4];
        result[0] = (byte) ((i >> 24) & 0xFF);
        result[1] = (byte) ((i >> 16) & 0xFF);
        result[2] = (byte) ((i >> 8) & 0xFF);
        result[3] = (byte) (i & 0xFF);
        return result;
    }
    // 字节数组转int
    public static int byteArrayToInt(byte[] b) {
        int intValue = 0;
        for (int i = 0; i < b.length; i++) {
            intValue += (b[i] & 0xFF) << (8 * (3 - i));
        }
        return intValue;
    }

}
