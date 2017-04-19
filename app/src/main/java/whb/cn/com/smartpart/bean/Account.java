package whb.cn.com.smartpart.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author 
 * 用户账号类
 */
public class Account implements Serializable{
	private static final long serialVersionUID = 1688121996094002321L;
	//主键
    private Integer accId;
    //密码
    private String accPwd;
    //用户名
    private String accName;
    //真实姓名
    private String accRealname;
    //支付密码
    private String accPaypwd;
    //积分
    private Integer accPoints;
    //余额
    private BigDecimal accBalance;
    //注册用手机号
    private String accPhone;
    //注册用邮箱
    private String accEmail;
    //性别
    private String accSex;
    //是否拥有一卡通 0表示没有 1表示有 默认没有  (临时设计)
    private Integer accCard;
    //创建时间
    private Timestamp accCreateTime;
    //头像
    private byte[] accAvatar;
    //该账号下订单
    private List<Order> orders;
    //该账号下卡券
    private List<Coupons> coupons;
    
    /*用户所在的服务器id*/
	private int serverId;
	
	/*客户端要登陆的逻辑服务器ip*/
	private String ip;
	
	/*客户端要登陆的逻辑服务器port*/
	private int port;
    
	public Account() {
	}

	public Account(Integer accId, String accPwd, String accName, String accRealname, String accPaypwd,
			Integer accPoints, BigDecimal accBalance, String accPhone, String accEmail, String accSex, Integer accCard,
			Timestamp accCreateTime, byte[] accAvatar, List<Order> orders, List<Coupons> coupons, int serverId,
			String ip, int port) {
		super();
		this.accId = accId;
		this.accPwd = accPwd;
		this.accName = accName;
		this.accRealname = accRealname;
		this.accPaypwd = accPaypwd;
		this.accPoints = accPoints;
		this.accBalance = accBalance;
		this.accPhone = accPhone;
		this.accEmail = accEmail;
		this.accSex = accSex;
		this.accCard = accCard;
		this.accCreateTime = accCreateTime;
		this.accAvatar = accAvatar;
		this.orders = orders;
		this.coupons = coupons;
		this.serverId = serverId;
		this.ip = ip;
		this.port = port;
	}


	public Integer getAccId() {
		return accId;
	}

	public void setAccId(Integer accId) {
		this.accId = accId;
	}

	public String getAccPwd() {
		return accPwd;
	}

	public void setAccPwd(String accPwd) {
		this.accPwd = accPwd;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public String getAccRealname() {
		return accRealname;
	}

	public void setAccRealname(String accRealname) {
		this.accRealname = accRealname;
	}

	public String getAccPaypwd() {
		return accPaypwd;
	}

	public void setAccPaypwd(String accPaypwd) {
		this.accPaypwd = accPaypwd;
	}

	public Integer getAccPoints() {
		return accPoints;
	}

	public void setAccPoints(Integer accPoints) {
		this.accPoints = accPoints;
	}

	public BigDecimal getAccBalance() {
		return accBalance;
	}

	public void setAccBalance(BigDecimal accBalance) {
		this.accBalance = accBalance;
	}

	public String getAccPhone() {
		return accPhone;
	}

	public void setAccPhone(String accPhone) {
		this.accPhone = accPhone;
	}

	public String getAccEmail() {
		return accEmail;
	}

	public void setAccEmail(String accEmail) {
		this.accEmail = accEmail;
	}

	public String getAccSex() {
		return accSex;
	}

	public void setAccSex(String accSex) {
		this.accSex = accSex;
	}

	public Integer getAccCard() {
		return accCard;
	}

	public void setAccCard(Integer accCard) {
		this.accCard = accCard;
	}

	public Timestamp getAccCreateTime() {
		return accCreateTime;
	}

	public void setAccCreateTime(Timestamp accCreateTime) {
		this.accCreateTime = accCreateTime;
	}

	public byte[] getAccAvatar() {
		return accAvatar;
	}

	public void setAccAvatar(byte[] accAvatar) {
		this.accAvatar = accAvatar;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<Coupons> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupons> coupons) {
		this.coupons = coupons;
	}
	
	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
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


}