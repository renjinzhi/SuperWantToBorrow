package com.superwanttoborrow.bean;

import com.superwanttoborrow.utils.BeanSetHelper;

import java.io.Serializable;

/**
 * Created by renji on 2018/1/19.
 */

public class RequestEncryotionBean implements Serializable {

    private String serviceId;
    private String channelNumber = BeanSetHelper.CHANNELNUMBER;
    private String mechanismNumber = BeanSetHelper.MECHANISMNUMBER;
    private String versionNumber = BeanSetHelper.VERSIONNUMBER;
    private String data;
    private String dataKey;

    public String getDataKey() {
        return dataKey;
    }

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
