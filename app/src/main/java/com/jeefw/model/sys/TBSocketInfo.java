package com.jeefw.model.sys;

import java.io.Serializable;


/**
 * 信息交换类
 */
public class TBSocketInfo implements Serializable {


    private static final long serialVersionUID = -2847988368314678488L;
    private String sessionId;// 会话标识
    private String userId;// 用户id
    private String channel;// 访问渠道
    private String versionName;// 应用版本名称
    private String versionCode;// 应用版本号
    private String phoneModel; // 手机型号
    private String imei; // 移动设备码
    private String macAddress;// mac地址
    private String transService;// 交易服务
    private String transMethod;// 服务方法
    private String size;// 每页条数
    private String resultCode;// 返回码
    private String resultName;// 返回信息
    private String jsonData; // 数据集合

    public String getSessionId() {
        return sessionId;
    }

    public TBSocketInfo setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public TBSocketInfo setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getChannel() {
        return channel;
    }

    public TBSocketInfo setChannel(String channel) {
        this.channel = channel;
        return this;
    }

    public String getVersionName() {
        return versionName;
    }

    public TBSocketInfo setVersionName(String versionName) {
        this.versionName = versionName;
        return this;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public TBSocketInfo setVersionCode(String versionCode) {
        this.versionCode = versionCode;
        return this;
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public TBSocketInfo setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
        return this;
    }

    public String getImei() {
        return imei;
    }

    public TBSocketInfo setImei(String imei) {
        this.imei = imei;
        return this;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public TBSocketInfo setMacAddress(String macAddress) {
        this.macAddress = macAddress;
        return this;

    }

    public String getTransService() {
        return transService;
    }

    public TBSocketInfo setTransService(String transService) {
        this.transService = transService;
        return this;
    }

    public String getTransMethod() {
        return transMethod;
    }

    public TBSocketInfo setTransMethod(String transMethod) {
        this.transMethod = transMethod;
        return this;
    }

    public String getSize() {
        return size;
    }

    public TBSocketInfo setSize(String size) {
        this.size = size;
        return this;
    }

    public String getResultCode() {
        return resultCode;
    }

    public TBSocketInfo setResultCode(String resultCode) {
        this.resultCode = resultCode;
        return this;
    }

    public String getResultName() {
        return resultName;
    }

    public TBSocketInfo setResultName(String resultName) {
        this.resultName = resultName;
        return this;
    }

    public String getJsonData() {
        return jsonData;
    }

    public TBSocketInfo setJsonData(String jsonData) {
        this.jsonData = jsonData;
        return this;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TBSocketInfo other = (TBSocketInfo) obj;
        if (channel == null) {
            if (other.channel != null)
                return false;
        } else if (!channel.equals(other.channel))
            return false;
        if (imei == null) {
            if (other.imei != null)
                return false;
        } else if (!imei.equals(other.imei))
            return false;
        if (jsonData == null) {
            if (other.jsonData != null)
                return false;
        } else if (!jsonData.equals(other.jsonData))
            return false;
        if (macAddress == null) {
            if (other.macAddress != null)
                return false;
        } else if (!macAddress.equals(other.macAddress))
            return false;
        if (phoneModel == null) {
            if (other.phoneModel != null)
                return false;
        } else if (!phoneModel.equals(other.phoneModel))
            return false;
        if (resultCode == null) {
            if (other.resultCode != null)
                return false;
        } else if (!resultCode.equals(other.resultCode))
            return false;
        if (resultName == null) {
            if (other.resultName != null)
                return false;
        } else if (!resultName.equals(other.resultName))
            return false;
        if (sessionId == null) {
            if (other.sessionId != null)
                return false;
        } else if (!sessionId.equals(other.sessionId))
            return false;
        if (size == null) {
            if (other.size != null)
                return false;
        } else if (!size.equals(other.size))
            return false;
        if (transMethod == null) {
            if (other.transMethod != null)
                return false;
        } else if (!transMethod.equals(other.transMethod))
            return false;
        if (transService == null) {
            if (other.transService != null)
                return false;
        } else if (!transService.equals(other.transService))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        if (versionCode == null) {
            if (other.versionCode != null)
                return false;
        } else if (!versionCode.equals(other.versionCode))
            return false;
        if (versionName == null) {
            if (other.versionName != null)
                return false;
        } else if (!versionName.equals(other.versionName))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((channel == null) ? 0 : channel.hashCode());
        result = prime * result + ((imei == null) ? 0 : imei.hashCode());
        result = prime * result + ((jsonData == null) ? 0 : jsonData.hashCode());
        result = prime * result + ((macAddress == null) ? 0 : macAddress.hashCode());
        result = prime * result + ((phoneModel == null) ? 0 : phoneModel.hashCode());
        result = prime * result + ((resultCode == null) ? 0 : resultCode.hashCode());
        result = prime * result + ((resultName == null) ? 0 : resultName.hashCode());
        result = prime * result + ((sessionId == null) ? 0 : sessionId.hashCode());
        result = prime * result + ((size == null) ? 0 : size.hashCode());
        result = prime * result + ((transMethod == null) ? 0 : transMethod.hashCode());
        result = prime * result + ((transService == null) ? 0 : transService.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((versionCode == null) ? 0 : versionCode.hashCode());
        result = prime * result + ((versionName == null) ? 0 : versionName.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return " {userId:" + userId + ", channel:" + channel + ", versionName:" + versionName
                + ", versionCode:" + versionCode + ", phoneModel:" + phoneModel + ", imei:" + imei + ", macAddress:"
                + macAddress + ", transService:" + transService + ", transMethod:" + transMethod + ", size:" + size
                + ", resultCode:" + resultCode + ", resultName:" + resultName + ", jsonData:" + jsonData + "}";
    }


}
