package whb.cn.com.smartpart.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author wh
 * 卡券类
 */
public class Coupons implements Serializable{
	private static final long serialVersionUID = 3152925228520275999L;
	//主键
    private Integer couponsId;
    //卡券过期时间
    private Timestamp couponsDate;
    //卡券名
    private String couponsName;
    //该卡券属于哪些用户
    private List<Account> accounts;
    
	public Coupons() {
	}

	public Coupons(Integer couponsId, Timestamp couponsDate, String couponsName, List<Account> accounts) {
		this.couponsId = couponsId;
		this.couponsDate = couponsDate;
		this.couponsName = couponsName;
		this.accounts = accounts;
	}

	public Integer getCouponsId() {
		return couponsId;
	}

	public void setCouponsId(Integer couponsId) {
		this.couponsId = couponsId;
	}

	public Timestamp getCouponsDate() {
		return couponsDate;
	}

	public void setCouponsDate(Timestamp couponsDate) {
		this.couponsDate = couponsDate;
	}

	public String getCouponsName() {
		return couponsName;
	}

	public void setCouponsName(String couponsName) {
		this.couponsName = couponsName;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}


    
}