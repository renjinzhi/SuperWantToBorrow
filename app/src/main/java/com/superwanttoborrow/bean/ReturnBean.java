package com.superwanttoborrow.bean;

import java.io.Serializable;

public class ReturnBean implements Serializable {

    private String code;
    private DataBean data;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public class DataBean{

        private String mobile;
        private String token;
        private String imgCodeKey;
        private String imgCodeString;


        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getImgCodeKey() {
            return imgCodeKey;
        }

        public void setImgCodeKey(String imgCodeKey) {
            this.imgCodeKey = imgCodeKey;
        }

        public String getImgCodeString() {
            return imgCodeString;
        }

        public void setImgCodeString(String imgCodeString) {
            this.imgCodeString = imgCodeString;
        }
    }
}
