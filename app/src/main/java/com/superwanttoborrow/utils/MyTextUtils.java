package com.superwanttoborrow.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author renji
 * @date 2017/12/18
 */

public class MyTextUtils {

    // 两次点击按钮之间的点击间隔不能少于1000毫秒
    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }

    //只能英文与数字
    public static boolean isPassword(String s) {
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,15}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

    //只能中文 英文与数字
    public static boolean isAddres(String s) {
        String regex = "^[\\u4e00-\\u9fa5_a-zA-Z0-9]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

    //弹出软键盘
    public static void showKeyboard(Context context, EditText editText) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(editText, 0);
    }

    //隐藏软键盘
    public static void hideSoftKeyboard(EditText editText, Context context) {
        if (editText != null && context != null) {
            InputMethodManager imm = (InputMethodManager) context
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }

    //时间戳转字符串
    public static String getStrTime(String timeStamp) {
        String timeString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        long l = Long.valueOf(timeStamp);
        timeString = sdf.format(new Date(l));//单位秒
        return timeString;
    }

    //时间戳转日期字符串
    public static String getStrData(String timeStamp) {
        String timeString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
        long l = Long.valueOf(timeStamp);
        timeString = sdf.format(new Date(l));//单位秒
        return timeString;
    }

    //还款状态转换
    public static String getStatus(String status) {
        String statu = null;
        if ("0".equals(status)) {
            statu = "已还款";
        } else if ("-1".equals(status)) {
            statu = "未申请放款";
        } else if ("1".equals(status)) {
            statu = "逾期";
        } else if ("2".equals(status)) {
            statu = "已结清";
        } else if ("3".equals(status)) {
            statu = "一次结清";
        } else if ("4".equals(status)) {
            statu = "取消借款";
        } else if ("5".equals(status)) {
            statu = "已提交";
        } else if ("6".equals(status)) {
            statu = "提交失败";
        } else if ("7".equals(status)) {
            statu = "待还款";
        } else if ("8".equals(status)) {
            statu = "放款成功";
        } else if ("9".equals(status)) {
            statu = "放款失败";
        }
        return statu;
    }

    //还款方式转换
    public static String getChannel(String channel) {
        String chan = null;
        if ("00100".equals(channel)) {
            chan = "银行卡支付";
        } else if ("00101".equals(channel)) {
            chan = "微信支付";
        } else if ("00102".equals(channel)) {
            chan = "支付宝支付";
        }

        return chan;
    }

//    public static int getBankCard(String bank) {
//        int i = 0;
//        switch (bank) {
//            case "中国银行":
//                i = R.mipmap.zhongguobank;
//                break;
//            case "兴业银行":
//                i = R.mipmap.xingyebank;
//                break;
//            case "光大银行":
//                i = R.mipmap.guangdabank;
//                break;
//            case "平安银行":
//                i = R.mipmap.pinganbank;
//                break;
//            case "上海银行":
//                i = R.mipmap.shanghaibank;
//                break;
//            case "工商银行":
//                i = R.mipmap.gongshangbank;
//                break;
//            case "广发银行":
//                i = R.mipmap.guangfabank;
//                break;
//            case "招商银行":
//                i = R.mipmap.zhaoshangbank;
//                break;
//            case "中信银行":
//                i = R.mipmap.zhongxinbank;
//                break;
//            case "农业银行":
//                i = R.mipmap.nongyebank;
//                break;
//            case "华夏银行":
//                i = R.mipmap.huaxiabank;
//                break;
//            case "浦发银行":
//                i = R.mipmap.pufabank;
//                break;
//            case "交通银行":
//                i = R.mipmap.jiaotongbank;
//                break;
//            case "邮储银行":
//                i = R.mipmap.youzhengbank;
//                break;
//            case "建设银行":
//                i = R.mipmap.jianshebank;
//                break;
//            case "北京银行":
//                i = R.mipmap.beijingbank;
//                break;
//            case "民生银行":
//                i = R.mipmap.minshengbank;
//                break;
//            case "杭州银行":
//                i = R.mipmap.hangzhoubank;
//                break;
//            case "宁波银行":
//                i = R.mipmap.ningbobank;
//                break;
//            case "江苏银行":
//                i = R.mipmap.jiangsubank;
//                break;
//            case "浙商银行":
//                i = R.mipmap.zheshangbank;
//                break;
//            case "广州银行":
//                i = R.mipmap.guangzhoubank;
//                break;
//            default:
//                break;
//        }
//        return i;
//    }

}
