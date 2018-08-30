package com.superwanttoborrow.bean;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author renji
 * @date 2017/12/19
 * 首页banner
 */

public class ReturnDataListBean implements Serializable {
    /**
     * code : 000000
     * data : [{"bannerinfoImageLinkurl":"http://www.taobao.com","bannerinfoImageTitle":"淘宝网广告","bannerinfoImageUrl":"http://192.168.1.203:8888/group1/M00/00/00/wKgBy1o4BC2AZ_2dAAIbozWYws0319.jpg","createTime":1512976655000,"createUsername":"zhangsan","enabled":"1","id":1,"updateTime":1512976667000,"updateUsername":"zhangsan"},{"bannerinfoImageLinkurl":"http://www.jingdong.com","bannerinfoImageTitle":"京东网广告","bannerinfoImageUrl":"http://192.168.1.203:8888/group1/M00/00/00/wKgBy1o4BDqAC2z9AAItKMw3VOQ622.jpg","createTime":1513231416000,"createUsername":"zhangsan","enabled":"1","id":2,"updateTime":1513231424000,"updateUsername":"zhangsan"},{"bannerinfoImageLinkurl":"http://www.jumei.com","bannerinfoImageTitle":"聚美广告","bannerinfoImageUrl":"http://192.168.1.203:8888/group1/M00/00/00/wKgBy1o4BC2AZ_2dAAIbozWYws0319.jpg","createTime":1513231495000,"createUsername":"zhangsan","enabled":"1","id":3,"updateTime":1513231507000,"updateUsername":"zhangsan"}]
     * message : 成功
     */

    private String code;
    private String message;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * bannerinfoImageLinkurl : http://www.taobao.com
         * bannerinfoImageTitle : 淘宝网广告
         * bannerinfoImageUrl : http://192.168.1.203:8888/group1/M00/00/00/wKgBy1o4BC2AZ_2dAAIbozWYws0319.jpg
         * createTime : 1512976655000
         * createUsername : zhangsan
         * enabled : 1
         * id : 1
         * updateTime : 1512976667000
         * updateUsername : zhangsan
         */

        private String bannerinfoImageLinkurl;
        private String bannerinfoImageTitle;
        private String bannerinfoImageUrl;
        private long createTime;
        private String createUsername;
        private String enabled;
        private int id;
        private long updateTime;
        private String updateUsername;
        private int billPeriods;
        private double presentTotDue;
        private long repaidDeadline;
        private String requestId;
        private String status;
        private String channel;
        private long presentTime;
        private double repaidServiceFee;
        private String swiftNumber;
        private double totalMoney;

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public long getPresentTime() {
            return presentTime;
        }

        public void setPresentTime(long presentTime) {
            this.presentTime = presentTime;
        }

        public double getRepaidServiceFee() {
            return repaidServiceFee;
        }

        public void setRepaidServiceFee(double repaidServiceFee) {
            this.repaidServiceFee = repaidServiceFee;
        }

        public String getSwiftNumber() {
            return swiftNumber;
        }

        public void setSwiftNumber(String swiftNumber) {
            this.swiftNumber = swiftNumber;
        }

        public double getTotalMoney() {
            return totalMoney;
        }

        public void setTotalMoney(double totalMoney) {
            this.totalMoney = totalMoney;
        }

        public int getBillPeriods() {
            return billPeriods;
        }

        public void setBillPeriods(int billPeriods) {
            this.billPeriods = billPeriods;
        }

        public double getPresentTotDue() {
            return presentTotDue;
        }

        public void setPresentTotDue(double presentTotDue) {
            this.presentTotDue = presentTotDue;
        }

        public long getRepaidDeadline() {
            return repaidDeadline;
        }

        public void setRepaidDeadline(long repaidDeadline) {
            this.repaidDeadline = repaidDeadline;
        }

        public String getRequestId() {
            return requestId;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getBannerinfoImageLinkurl() {
            return bannerinfoImageLinkurl;
        }

        public void setBannerinfoImageLinkurl(String bannerinfoImageLinkurl) {
            this.bannerinfoImageLinkurl = bannerinfoImageLinkurl;
        }

        public String getBannerinfoImageTitle() {
            return bannerinfoImageTitle;
        }

        public void setBannerinfoImageTitle(String bannerinfoImageTitle) {
            this.bannerinfoImageTitle = bannerinfoImageTitle;
        }

        public String getBannerinfoImageUrl() {
            return bannerinfoImageUrl;
        }

        public void setBannerinfoImageUrl(String bannerinfoImageUrl) {
            this.bannerinfoImageUrl = bannerinfoImageUrl;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getCreateUsername() {
            return createUsername;
        }

        public void setCreateUsername(String createUsername) {
            this.createUsername = createUsername;
        }

        public String getEnabled() {
            return enabled;
        }

        public void setEnabled(String enabled) {
            this.enabled = enabled;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getUpdateUsername() {
            return updateUsername;
        }

        public void setUpdateUsername(String updateUsername) {
            this.updateUsername = updateUsername;
        }
    }
}
