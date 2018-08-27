package com.superwanttoborrow.bean;

/**
 *
 * @author renji
 * @date 2018/3/2
 */

public class ImgCodeBean {

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

        private String imgCodeKey;
        private String imgCodeString;

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
