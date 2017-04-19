package whb.cn.com.smartpart.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author wh
 *	订单类
 */
public class Order implements Serializable{
	private static final long serialVersionUID = -4470665089226532578L;
	//主键
    private Integer orderId;
    //订单金额
    private BigDecimal orderSum;
    //订单详情
    private String orderDetail;
    //订单产生时间
    private Timestamp orderDate;
    //是否成功
    private Boolean orderIssuccess;
    //该订单属于哪名用户
    private Account account;
    
	public Order() {
	}

	public Order(Integer orderId, BigDecimal orderSum, String orderDetail, Timestamp orderDate, Boolean orderIssuccess,
			Account account) {
		super();
		this.orderId = orderId;
		this.orderSum = orderSum;
		this.orderDetail = orderDetail;
		this.orderDate = orderDate;
		this.orderIssuccess = orderIssuccess;
		this.account = account;
	}


	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getOrderSum() {
		return orderSum;
	}

	public void setOrderSum(BigDecimal orderSum) {
		this.orderSum = orderSum;
	}

	public String getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(String orderDetail) {
		this.orderDetail = orderDetail;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public Boolean getOrderIssuccess() {
		return orderIssuccess;
	}

	public void setOrderIssuccess(Boolean orderIssuccess) {
		this.orderIssuccess = orderIssuccess;
	}

	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

    
}