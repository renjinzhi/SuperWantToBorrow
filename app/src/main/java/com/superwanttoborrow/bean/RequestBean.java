package com.superwanttoborrow.bean;


import com.superwanttoborrow.utils.BeanSetHelper;

import java.io.Serializable;

/**
 * @author renji
 * @date 2017/12/28
 * 所有的请求参数
 */

public class RequestBean implements Serializable {

    private String serviceId;
    private String channelNumber = BeanSetHelper.CHANNELNUMBER;
    private String mechanismNumber = BeanSetHelper.MECHANISMNUMBER;
    private String versionNumber = BeanSetHelper.VERSIONNUMBER;
    private DataBean data;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {

        private String mobile;
        private String token;
        private String checkCode;
        private String password;
        private String randomNum;
        private String requestType;
        private String productionCode;
        private String newpassword;
        private String oldpassword;
        private String dictType;
        private String fileContent;
        private String fileName;
        private String fileType;
        private String requestId;
        private String cardNumber;
        private String delta;
        private String applicantName;
        private String applyMoney;
        private String bankCardId;
        private String depositBank;
        private String bizType;
        private String bizWorkfor;
        private String cardId;
        private String linkman1Cell;
        private String linkman2Cell;
        private String linkman1Name;
        private String linkman2Name;
        private String loanReason;
        private String mail;
        private String addrs;
        private String refundPeriods;
        private String addresCode;
        private String realName;
        private String current;
        private String channel;
        private String bqsTokenKey;
        private String bankName;
        private String bizAddr;
        private String idCard;
        private String bankNum;
        private String name;
        private String returnUrl;
        private String openId;
        private String taskId;
        private String imgCode;
        private String imgCodeKey;
        private String status;
        private String productionName;
        private String device_id;
        private String is_root;
        private String is_smulator;
        private String latitude;
        private String longitude;
        private String mac_address;
        private String mobile_sim;
        private String network_type;
        private String os_platform;
        private String os_version;
        private String resolution;
        private String terminal_type;
        private String uuid;
        private String localIPs;
        private String monthlyIncome;
        private String rentalSituation;
        private String update_time;
        private String id;
        private String page_tag;
        private String entry_time;
        private String submit_time;
        private String open_uuid;
        private String local_ips;
        private String appNames;
        private String bankProvince;
        private String bankCity;
        private String customName;
        private String customId;
        private String incomeRange;
        private String linkman1Relationship;
        private String linkman2Relationship;
        private String cardno;
        private String validatecode;
        private String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getValidatecode() {
            return validatecode;
        }

        public void setValidatecode(String validatecode) {
            this.validatecode = validatecode;
        }

        public String getCardno() {
            return cardno;
        }

        public void setCardno(String cardno) {
            this.cardno = cardno;
        }

        public String getIncomeRange() {
            return incomeRange;
        }

        public void setIncomeRange(String incomeRange) {
            this.incomeRange = incomeRange;
        }

        public String getLinkman1Relationship() {
            return linkman1Relationship;
        }

        public void setLinkman1Relationship(String linkman1Relationship) {
            this.linkman1Relationship = linkman1Relationship;
        }

        public String getLinkman2Relationship() {
            return linkman2Relationship;
        }

        public void setLinkman2Relationship(String linkman2Relationship) {
            this.linkman2Relationship = linkman2Relationship;
        }

        public String getCustomName() {
            return customName;
        }

        public void setCustomName(String customName) {
            this.customName = customName;
        }

        public String getCustomId() {
            return customId;
        }

        public void setCustomId(String customId) {
            this.customId = customId;
        }

        public String getBankProvince() {
            return bankProvince;
        }

        public void setBankProvince(String bankProvince) {
            this.bankProvince = bankProvince;
        }

        public String getBankCity() {
            return bankCity;
        }

        public void setBankCity(String bankCity) {
            this.bankCity = bankCity;
        }

        public String getOpen_uuid() {
            return open_uuid;
        }

        public void setOpen_uuid(String open_uuid) {
            this.open_uuid = open_uuid;
        }

        public String getLocal_ips() {
            return local_ips;
        }

        public void setLocal_ips(String local_ips) {
            this.local_ips = local_ips;
        }

        public String getAppNames() {
            return appNames;
        }

        public void setAppNames(String appNames) {
            this.appNames = appNames;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPage_tag() {
            return page_tag;
        }

        public void setPage_tag(String page_tag) {
            this.page_tag = page_tag;
        }

        public String getEntry_time() {
            return entry_time;
        }

        public void setEntry_time(String entry_time) {
            this.entry_time = entry_time;
        }

        public String getSubmit_time() {
            return submit_time;
        }

        public void setSubmit_time(String submit_time) {
            this.submit_time = submit_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }


        public String getMonthlyIncome() {
            return monthlyIncome;
        }

        public void setMonthlyIncome(String monthlyIncome) {
            this.monthlyIncome = monthlyIncome;
        }

        public String getRentalSituation() {
            return rentalSituation;
        }

        public void setRentalSituation(String rentalSituation) {
            this.rentalSituation = rentalSituation;
        }

        public String getLocalIPs() {
            return localIPs;
        }

        public void setLocalIPs(String localIPs) {
            this.localIPs = localIPs;
        }

        public String getDevice_id() {
            return device_id;
        }

        public void setDevice_id(String device_id) {
            this.device_id = device_id;
        }

        public String getIs_root() {
            return is_root;
        }

        public void setIs_root(String is_root) {
            this.is_root = is_root;
        }

        public String getIs_smulator() {
            return is_smulator;
        }

        public void setIs_smulator(String is_smulator) {
            this.is_smulator = is_smulator;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getMac_address() {
            return mac_address;
        }

        public void setMac_address(String mac_address) {
            this.mac_address = mac_address;
        }

        public String getMobile_sim() {
            return mobile_sim;
        }

        public void setMobile_sim(String mobile_sim) {
            this.mobile_sim = mobile_sim;
        }

        public String getNetwork_type() {
            return network_type;
        }

        public void setNetwork_type(String network_type) {
            this.network_type = network_type;
        }

        public String getOs_platform() {
            return os_platform;
        }

        public void setOs_platform(String os_platform) {
            this.os_platform = os_platform;
        }

        public String getOs_version() {
            return os_version;
        }

        public void setOs_version(String os_version) {
            this.os_version = os_version;
        }

        public String getResolution() {
            return resolution;
        }

        public void setResolution(String resolution) {
            this.resolution = resolution;
        }

        public String getTerminal_type() {
            return terminal_type;
        }

        public void setTerminal_type(String terminal_type) {
            this.terminal_type = terminal_type;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getProductionName() {
            return productionName;
        }

        public void setProductionName(String productionName) {
            this.productionName = productionName;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getLinkman2Cell() {
            return linkman2Cell;
        }

        public void setLinkman2Cell(String linkman2Cell) {
            this.linkman2Cell = linkman2Cell;
        }

        public String getLinkman2Name() {
            return linkman2Name;
        }

        public void setLinkman2Name(String linkman2Name) {
            this.linkman2Name = linkman2Name;
        }

        public String getImgCode() {
            return imgCode;
        }

        public void setImgCode(String imgCode) {
            this.imgCode = imgCode;
        }

        public String getImgCodeKey() {
            return imgCodeKey;
        }

        public void setImgCodeKey(String imgCodeKey) {
            this.imgCodeKey = imgCodeKey;
        }

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

        public String getTaskId() {
            return taskId;
        }

        public void setTaskId(String taskId) {
            this.taskId = taskId;
        }

        public String getReturnUrl() {
            return returnUrl;
        }

        public void setReturnUrl(String returnUrl) {
            this.returnUrl = returnUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getBankNum() {
            return bankNum;
        }

        public void setBankNum(String bankNum) {
            this.bankNum = bankNum;
        }

        public String getBizAddr() {
            return bizAddr;
        }

        public void setBizAddr(String bizAddr) {
            this.bizAddr = bizAddr;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getBqsTokenKey() {
            return bqsTokenKey;
        }

        public void setBqsTokenKey(String bqsTokenKey) {
            this.bqsTokenKey = bqsTokenKey;
        }

        public String getCurrent() {
            return current;
        }

        public void setCurrent(String current) {
            this.current = current;
        }

        public String getChannel() {
            return channel;
        }

        public void setChannel(String channel) {
            this.channel = channel;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getAddresCode() {
            return addresCode;
        }

        public void setAddresCode(String addresCode) {
            this.addresCode = addresCode;
        }

        public String getApplicantName() {
            return applicantName;
        }

        public void setApplicantName(String applicantName) {
            this.applicantName = applicantName;
        }

        public String getApplyMoney() {
            return applyMoney;
        }

        public void setApplyMoney(String applyMoney) {
            this.applyMoney = applyMoney;
        }

        public String getBankCardId() {
            return bankCardId;
        }

        public void setBankCardId(String bankCardId) {
            this.bankCardId = bankCardId;
        }

        public String getDepositBank() {
            return depositBank;
        }

        public void setDepositBank(String depositBank) {
            this.depositBank = depositBank;
        }

        public String getBizType() {
            return bizType;
        }

        public void setBizType(String bizType) {
            this.bizType = bizType;
        }

        public String getBizWorkfor() {
            return bizWorkfor;
        }

        public void setBizWorkfor(String bizWorkfor) {
            this.bizWorkfor = bizWorkfor;
        }

        public String getCardId() {
            return cardId;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId;
        }

        public String getLinkman1Cell() {
            return linkman1Cell;
        }

        public void setLinkman1Cell(String linkman1Cell) {
            this.linkman1Cell = linkman1Cell;
        }

        public String getLinkman1Name() {
            return linkman1Name;
        }

        public void setLinkman1Name(String linkman1Name) {
            this.linkman1Name = linkman1Name;
        }

        public String getLoanReason() {
            return loanReason;
        }

        public void setLoanReason(String loanReason) {
            this.loanReason = loanReason;
        }

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getAddrs() {
            return addrs;
        }

        public void setAddrs(String addrs) {
            this.addrs = addrs;
        }

        public String getRefundPeriods() {
            return refundPeriods;
        }

        public void setRefundPeriods(String refundPeriods) {
            this.refundPeriods = refundPeriods;
        }

        public String getDelta() {
            return delta;
        }

        public void setDelta(String delta) {
            this.delta = delta;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public void setCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        public String getFileContent() {
            return fileContent;
        }

        public void setFileContent(String fileContent) {
            this.fileContent = fileContent;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getFileType() {
            return fileType;
        }

        public void setFileType(String fileType) {
            this.fileType = fileType;
        }

        public String getRequestId() {
            return requestId;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public String getDictType() {
            return dictType;
        }

        public void setDictType(String dictType) {
            this.dictType = dictType;
        }

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

        public String getCheckCode() {
            return checkCode;
        }

        public void setCheckCode(String checkCode) {
            this.checkCode = checkCode;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRandomNum() {
            return randomNum;
        }

        public void setRandomNum(String randomNum) {
            this.randomNum = randomNum;
        }

        public String getRequestType() {
            return requestType;
        }

        public void setRequestType(String requestType) {
            this.requestType = requestType;
        }

        public String getProductionCode() {
            return productionCode;
        }

        public void setProductionCode(String productionCode) {
            this.productionCode = productionCode;
        }

        public String getNewpassword() {
            return newpassword;
        }

        public void setNewpassword(String newpassword) {
            this.newpassword = newpassword;
        }

        public String getOldpassword() {
            return oldpassword;
        }

        public void setOldpassword(String oldpassword) {
            this.oldpassword = oldpassword;
        }

        @Override
        public String toString() {
            return "ProvinceEntity{" +
                    "mobile='" + mobile + '\'' +
                    ", token='" + token + '\'' +
                    ", checkCode='" + checkCode + '\'' +
                    ", password='" + password + '\'' +
                    ", randomNum='" + randomNum + '\'' +
                    ", requestType='" + requestType + '\'' +
                    ", productionCode='" + productionCode + '\'' +
                    ", newpassword='" + newpassword + '\'' +
                    ", oldpassword='" + oldpassword + '\'' +
                    '}';
        }
    }
}
