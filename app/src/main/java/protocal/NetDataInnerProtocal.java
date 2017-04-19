package protocal;


import java.io.Serializable;

public class NetDataInnerProtocal implements Serializable{


/**
	 * 
	 */
	private static final long serialVersionUID = -6688001935508859908L;

/**
 * 消息协议
 * 与客户端
 */
	
	private  int len;
	
	private String data;

	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	public NetDataInnerProtocal() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NetDataInnerProtocal(int len, String data) {
		super();
		this.len = len;
		this.data = data;
	}

	@Override
	public String toString() {
		return "NetDataClient [len=" + len + ", data=" + data + "]";
	}
	
}
