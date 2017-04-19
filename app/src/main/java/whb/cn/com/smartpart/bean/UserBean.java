package whb.cn.com.smartpart.bean;

import java.io.Serializable;

public class UserBean implements Serializable{
     /**
	 * 
	 */
	private static final long serialVersionUID = 7044290862779046371L;

	/*用户id*/
	private String userId;
	
	/*用户名*/
	private String userName;
	
	/*密码*/
	private String passWord;
	
	/*用户所在的服务器id*/
	private int serverId;
	
	/*客户端要登陆的逻辑服务器ip*/
	private String ip;
	
	/*客户端要登陆的逻辑服务器port*/
	private int port;
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	@Override
	public String toString() {
		return "UserBean [userId=" + userId + ", userName=" + userName + ", passWord=" + passWord + ", serverId="
				+ serverId + ", ip=" + ip + ", port=" + port + "]";
	}

	

	

}
