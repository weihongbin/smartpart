package whb.cn.com.mylibrary.common;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import whb.cn.com.mylibrary.bean.CustomDataModel;
import whb.cn.com.mylibrary.utils.CloseUtils;


public class OpenHelper {
	/**
	 * 写数据
	 * 
	 * @param bos
	 * @param tag
	 * @param json
	 */
	public static void OutputData(BufferedOutputStream bos, int tag, String json) {
		try {
			System.out.println("走了吗");
			bos.write(int2ByteArrays(json.getBytes().length + Config.DATA_TAG_LENGTH + Config.DATA_TATOL_LENGTH));// 包体总长度
			bos.write(int2ByteArrays(tag));// tag校验
			bos.write(json.getBytes());// 包体内容
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

			BufferedInputStream bis = new BufferedInputStream(inputStream);
			int tagValue = 0;
			byte[] header = new byte[Config.DATA_TATOL_LENGTH];
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			bis.read(header, 0, header.length);// 先读取前4个字节获取总体的长度
			int totalLen = byteArrayToInt(header);
			Log.e("ReceiverTask",totalLen+"");
			if (totalLen == Config.DATA_TATOL_LENGTH+Config.DATA_TAG_LENGTH) {
				// 说明后面的数据为空,返回
				System.out.println("eeeeeeeeeeeeeeeeeeeeee");
			} else {
				byte[] tag = new byte[Config.DATA_TAG_LENGTH];
				bis.read(tag, 0, tag.length);
				tagValue = byteArrayToInt(tag);// 再读取4为的校验码
				Log.e("ReceiverTask",tagValue+"");
				byte[] data = new byte[totalLen - Config.DATA_TATOL_LENGTH - Config.DATA_TAG_LENGTH];
				System.out.println(totalLen + "ddd");
				bis.read(data, 0, totalLen - Config.DATA_TATOL_LENGTH - Config.DATA_TAG_LENGTH);
				bos.write(data, 0, totalLen - Config.DATA_TATOL_LENGTH - Config.DATA_TAG_LENGTH);
			}
			CustomDataModel<String> data = new CustomDataModel<String>(totalLen, tagValue,
					new String(bos.toByteArray()));
			CloseUtils.close(bos, bis);
			return data;
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
