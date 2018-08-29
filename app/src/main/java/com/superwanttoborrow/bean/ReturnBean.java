package com.superwanttoborrow.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    public static class DataBean implements Serializable {

        private String mobile;
        private String token;
        private String imgCodeKey;
        private String imgCodeString;
        private String requestId;
        private UseBean use;
        private String status;
        private String callNumber;
        private int id;
        private int billPeriods;
        private double presentAmtForSettled;
        private double presentDueTot;
        private double presentPenalty;
        private double presentTotDue;
        private long repaidDeadline;
        private String repaymentStatus;
        private ArrayList<String> maxMin;
        private ArrayList<SupportPeriodBean> supportPeriod;
        private HistoryRecordsBean currentRecord;
        private List<HistoryRecordsBean> historyRecords;

        public HistoryRecordsBean getCurrentRecord() {
            return currentRecord;
        }

        public void setCurrentRecord(HistoryRecordsBean currentRecord) {
            this.currentRecord = currentRecord;
        }

        public List<HistoryRecordsBean> getHistoryRecords() {
            return historyRecords;
        }

        public void setHistoryRecords(List<HistoryRecordsBean> historyRecords) {
            this.historyRecords = historyRecords;
        }

        public ArrayList<String> getMaxMin() {
            return maxMin;
        }

        public void setMaxMin(ArrayList<String> maxMin) {
            this.maxMin = maxMin;
        }

        public ArrayList<SupportPeriodBean> getSupportPeriod() {
            return supportPeriod;
        }

        public void setSupportPeriod(ArrayList<SupportPeriodBean> supportPeriod) {
            this.supportPeriod = supportPeriod;
        }

        public String getCallNumber() {
            return callNumber;
        }

        public void setCallNumber(String callNumber) {
            this.callNumber = callNumber;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getBillPeriods() {
            return billPeriods;
        }

        public void setBillPeriods(int billPeriods) {
            this.billPeriods = billPeriods;
        }

        public double getPresentAmtForSettled() {
            return presentAmtForSettled;
        }

        public void setPresentAmtForSettled(double presentAmtForSettled) {
            this.presentAmtForSettled = presentAmtForSettled;
        }

        public double getPresentDueTot() {
            return presentDueTot;
        }

        public void setPresentDueTot(double presentDueTot) {
            this.presentDueTot = presentDueTot;
        }

        public double getPresentPenalty() {
            return presentPenalty;
        }

        public void setPresentPenalty(double presentPenalty) {
            this.presentPenalty = presentPenalty;
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

        public String getRepaymentStatus() {
            return repaymentStatus;
        }

        public void setRepaymentStatus(String repaymentStatus) {
            this.repaymentStatus = repaymentStatus;
        }

        public String getRequestId() {
            return requestId;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public UseBean getUse() {
            return use;
        }

        public void setUse(UseBean use) {
            this.use = use;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public static class UseBean {
            /**
             * addresCode : 北京-北京市-东城区
             * addrs : {"area":"东城区","bizAddr":"汉威国际广场"}
             * applicantName :
             * bankCardId : 6226 2201 **** 9082
             * bizType : F
             * bizWorkfor : 北京水象
             * borrowMoneyUse : 06
             * cardId :
             * depositBank : 民生银行(借记卡)
             * linkman1Cell :
             * linkman1Name :
             * linkmanInfo : {"linkman1Name":"周玉召","linkman1Cell":""}
             * mail : renjinzhi@163.com
             * mobile :
             * bizAddr : 汉威国际广场
             */

            private String addresCode;
            private String addrs;
            private String applicantName;
            private String bankCardId;
            private String bizType;
            private String bizWorkfor;
            private String borrowMoneyUse;
            private String cardId;
            private String depositBank;
            private String linkman1Cell;
            private String linkman2Cell;
            private String linkman1Name;
            private String linkman2Name;
            private String linkmanInfo;
            private String mail;
            private String mobile;
            private String bizAddr;
            private String rentalSituation;
            private String monthlyIncome;

            public String getRentalSituation() {
                return rentalSituation;
            }

            public void setRentalSituation(String rentalSituation) {
                this.rentalSituation = rentalSituation;
            }

            public String getMonthlyIncome() {
                return monthlyIncome;
            }

            public void setMonthlyIncome(String monthlyIncome) {
                this.monthlyIncome = monthlyIncome;
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

            public String getAddresCode() {
                return addresCode;
            }

            public void setAddresCode(String addresCode) {
                this.addresCode = addresCode;
            }

            public String getAddrs() {
                return addrs;
            }

            public void setAddrs(String addrs) {
                this.addrs = addrs;
            }

            public String getApplicantName() {
                return applicantName;
            }

            public void setApplicantName(String applicantName) {
                this.applicantName = applicantName;
            }

            public String getBankCardId() {
                return bankCardId;
            }

            public void setBankCardId(String bankCardId) {
                this.bankCardId = bankCardId;
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

            public String getBorrowMoneyUse() {
                return borrowMoneyUse;
            }

            public void setBorrowMoneyUse(String borrowMoneyUse) {
                this.borrowMoneyUse = borrowMoneyUse;
            }

            public String getCardId() {
                return cardId;
            }

            public void setCardId(String cardId) {
                this.cardId = cardId;
            }

            public String getDepositBank() {
                return depositBank;
            }

            public void setDepositBank(String depositBank) {
                this.depositBank = depositBank;
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

            public String getLinkmanInfo() {
                return linkmanInfo;
            }

            public void setLinkmanInfo(String linkmanInfo) {
                this.linkmanInfo = linkmanInfo;
            }

            public String getMail() {
                return mail;
            }

            public void setMail(String mail) {
                this.mail = mail;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getBizAddr() {
                return bizAddr;
            }

            public void setBizAddr(String bizAddr) {
                this.bizAddr = bizAddr;
            }
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

        public static class SupportPeriodBean {
            /**
             * dictCode : R3
             * enabled : 1
             */

            private String dictCode;
            private String enabled;

            public String getDictCode() {
                return dictCode;
            }

            public void setDictCode(String dictCode) {
                this.dictCode = dictCode;
            }

            public String getEnabled() {
                return enabled;
            }

            public void setEnabled(String enabled) {
                this.enabled = enabled;
            }
        }

        public static class MaxMinBean {
            /**
             * min : 2000
             * max : 10000
             */

            private int min;
            private int max;

            public int getMin() {
                return min;
            }

            public void setMin(int min) {
                this.min = min;
            }

            public int getMax() {
                return max;
            }

            public void setMax(int max) {
                this.max = max;
            }
        }

        public static class CurrentRecordBean implements Serializable {
            /**
             * createTime : 1496470676000
             * loanAmount : 2600
             * loanPeriods : 12
             * loanStatus : 8
             * loanType : 001
             * requestId : 1706030180157958
             */

            private long createTime;
            private double loanAmount;
            private int loanPeriods;
            private String loanStatus;
            private String loanType;
            private String requestId;

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public double getLoanAmount() {
                return loanAmount;
            }

            public void setLoanAmount(double loanAmount) {
                this.loanAmount = loanAmount;
            }

            public int getLoanPeriods() {
                return loanPeriods;
            }

            public void setLoanPeriods(int loanPeriods) {
                this.loanPeriods = loanPeriods;
            }

            public String getLoanStatus() {
                return loanStatus;
            }

            public void setLoanStatus(String loanStatus) {
                this.loanStatus = loanStatus;
            }

            public String getLoanType() {
                return loanType;
            }

            public void setLoanType(String loanType) {
                this.loanType = loanType;
            }

            public String getRequestId() {
                return requestId;
            }

            public void setRequestId(String requestId) {
                this.requestId = requestId;
            }
        }

        public static class HistoryRecordsBean implements Serializable {
            /**
             * createTime : 1514020191000
             * loanAmount : 5000
             * loanPeriods : 2
             * loanStatus : 5
             * loanType : 001
             * presentStatus : 0
             * requestId : 161011101148294
             */

            private long createTime;
            private double loanAmount;
            private int loanPeriods;
            private String loanStatus;
            private String loanType;
            private String presentStatus;
            private String requestId;

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public double getLoanAmount() {
                return loanAmount;
            }

            public void setLoanAmount(double loanAmount) {
                this.loanAmount = loanAmount;
            }

            public int getLoanPeriods() {
                return loanPeriods;
            }

            public void setLoanPeriods(int loanPeriods) {
                this.loanPeriods = loanPeriods;
            }

            public String getLoanStatus() {
                return loanStatus;
            }

            public void setLoanStatus(String loanStatus) {
                this.loanStatus = loanStatus;
            }

            public String getLoanType() {
                return loanType;
            }

            public void setLoanType(String loanType) {
                this.loanType = loanType;
            }

            public String getPresentStatus() {
                return presentStatus;
            }

            public void setPresentStatus(String presentStatus) {
                this.presentStatus = presentStatus;
            }

            public String getRequestId() {
                return requestId;
            }

            public void setRequestId(String requestId) {
                this.requestId = requestId;
            }
        }

    }

}
