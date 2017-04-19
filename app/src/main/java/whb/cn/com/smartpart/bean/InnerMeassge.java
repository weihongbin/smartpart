package whb.cn.com.smartpart.bean;

import java.io.Serializable;
/**
 * 服务器内部数据交换
 * @author Administrator
 *
 */
public class InnerMeassge implements  Serializable{

	 private static final long serialVersionUID = -2847988368314678488L;
	    private String userId;// 用户主体
	    private int requstCode;//请求码
	    private int responsCode;//请求回应码
	    private String resultCode;// 返回码
	    private String resultName;// 返回信息
	    private String jsonData; // 数据集合
	    private String tag;//校验位
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public int getRequstCode() {
			return requstCode;
		}
		public void setRequstCode(int requstCode) {
			this.requstCode = requstCode;
		}
		public int getResponsCode() {
			return responsCode;
		}
		public void setResponsCode(int responsCode) {
			this.responsCode = responsCode;
		}
		public String getResultCode() {
			return resultCode;
		}
		public void setResultCode(String resultCode) {
			this.resultCode = resultCode;
		}
		public String getResultName() {
			return resultName;
		}
		public void setResultName(String resultName) {
			this.resultName = resultName;
		}
		public String getJsonData() {
			return jsonData;
		}
		public void setJsonData(String jsonData) {
			this.jsonData = jsonData;
		}
		public String getTag() {
			return tag;
		}
		public void setTag(String tag) {
			this.tag = tag;
		}
		@Override
		public String toString() {
			return "InnerMeassge [userId=" + userId + ", requstCode=" + requstCode + ", responsCode=" + responsCode
					+ ", resultCode=" + resultCode + ", resultName=" + resultName + ", jsonData=" + jsonData + ", tag="
					+ tag + "]";
		}
	   

}
