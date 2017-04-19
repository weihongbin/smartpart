package whb.cn.com.mylibrary.bean;

/**
 * ============================================= 自定义数据包的类型
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: whb.cn.com.smartpart.bean.CustomDataModel.java
 * @author: 魏红彬
 * @e-mail: weihongbin@t-tron.com
 * @date: 2017-03-19 12:08
 */

public class CustomDataModel<T> {
	private int tag; // 包头 int，4个字节

	private int totalLen;// 包体总长度 4个字节+包头（4个字节）+包体长度（data.getBytes().length）

	private String data;// 包体泛型

	
	public CustomDataModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomDataModel(int tag, int totalLen, String data) {
		super();
		this.tag = tag;
		this.totalLen = totalLen;
		this.data = data;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public int getTotalLen() {
		return totalLen;
	}

	public void setTotalLen(int totalLen) {
		this.totalLen = totalLen;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "CustomDataModel [tag=" + tag + ", totalLen=" + totalLen + ", data=" + data + "]";
	}

}