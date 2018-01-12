package com.ifuture.demo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author Ethan.Lam 2011-7-27
 */
public class StringUtil {

    /**
     * 判断字符串不是null或者空或者多个空白
     */
    public static boolean isNulOrBlank(String str) {
        return str == null || "".equals(str.trim()) || str.equals("null") || str.equals("undefined")
            ? true : false;
    }

    /**
     * 把obj转成String
     */
    public static String valueOf(Object obj) {
        return (obj == null || "".equals(obj) || "null".equals(obj)) ? "" : obj
            .toString().trim();
    }

    /**
     * 自动补对应的 字符位
     *
     * @param isLeft 是否从左边补位
     * @param fillChar 自动填补的字符
     * @param length 填补后的总字符串长度
     * @param originalValue 原字符串
     */
    public static String fillString(boolean isLeft, char fillChar, int length,
        String originalValue) {
        if (null == originalValue || "".equals(originalValue)) {
            return "";
        }

        int originalLength = originalValue.length();
        if (length <= originalLength) {
            return originalValue;
        }

        String fillCharStr = "";
        for (int op = 1; op <= length - originalLength; op++) {
            fillCharStr = fillChar + fillCharStr;
        }
        return isLeft ? (fillCharStr + originalValue)
            : (originalValue + fillCharStr);
    }

    /**
     * 去除填充字符
     */
    public static String removeFillChar(boolean isLeft, char fillChar,
        String originalValue) {
        if (null == originalValue || "".equals(originalValue)) {
            return "";
        }
        int fillLength = 0;
        int originalLength = originalValue.length();

        if (isLeft) {
            for (int op = 0; op < originalLength; op++) {
                if (originalValue.substring(op, op + 1).equals(
                    String.valueOf(fillChar))) {
                    fillLength++;
                } else {
                    break;
                }
            }
            return originalValue.substring(fillLength);
        } else {
            for (int op = originalLength; op > 0; op--) {
                if (originalValue.substring(op - 1, op).equals(
                    String.valueOf(fillChar))) {
                    fillLength++;
                } else {
                    break;
                }
            }
            return originalValue.substring(0, originalLength - fillLength);
        }
    }

    /**
     * 检查是否是数字
     */
    public static boolean isNumeric(String str) {
        if (StringUtil.isNulOrBlank(str) == true) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查是不是guid得格式
     */
    public static boolean isGUID(String id) {
        if (id == null || "".equals(id)) {
            return false;
        } else if ("1".equals(id)) {
            return true;
        } else if (Pattern.matches("^\\w{8}-(\\w{4}-){3}\\w{12}$", id)) {
            return true;
        } else if (Pattern.matches("^\\d{25}$", id)) { //25位长度纯数字的订单号
            return true;
        }
        return false;
    }

    /**
     * 字符串按字节截取
     *
     * @param str 原字符
     * @param len 截取长度
     * @return String
     */

    public static String SubString(String str, int len) {

        return splitString(str, len, "...");

    }

    /**
     * 字符串按字节截取
     * 原字符
     */

    public String SubString(String str) {
        if (str == null) {
            return null;
        }

        int ilenght = 0;
        int i = str.getBytes().length;
        int j = str.length();

        if (i == j) {
            ilenght = 20;
        } else {
            ilenght = 11;
        }

        return this.sub(str, ilenght);
    }

    /**
     * 字符串按字节截取
     *
     * @param str 原字符
     * @param len 截取长度
     * @param elide 省略符
     * @return String
     */

    public static String splitString(String str, int len, String elide) {

        if (str == null) {

            return "";

        }

        byte[] strByte = str.getBytes();

        int strLen = strByte.length;

        int elideLen = (elide.trim().length() == 0) ? 0
            : elide.getBytes().length;

        if (len >= strLen || len < 1) {

            return str;

        }

        if (len - elideLen > 0) {

            len = len - elideLen;

        }

        int count = 0;

        for (int i = 0; i < len; i++) {

            int value = (int) strByte[i];

            if (value < 0) {

                count++;

            }

        }

        if (count % 2 != 0) {

            len = (len == 1) ? len + 1 : len - 1;

        }

        return new String(strByte, 0, len) + elide.trim();

    }


    private String sub(String value, int lenght) {
        if (value != null && value.length() > lenght) {
            return value.substring(0, lenght) + "...";
        } else {
            return value;
        }
    }

    /**
     * 判断是否正整数、正小数
     */
    public static boolean isDeclimals(String str) {
        Pattern pattern = Pattern.compile("^\\d+$|^\\d+\\.\\d+$");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public static Object nvl(Object obj) {
        if (obj == null || String.valueOf(obj) == "") {
            return "0";
        } else {
            return obj;
        }
    }

    public static String trim(String str) {
        if (str == null) {
            return null;
        }

        return str.trim();
    }

    /**
     * 截断日期字符串（用于全课网首页公告的课程公告栏）
     */
    public String SubString(String string, int start, String end) {
        if (isNulOrBlank(string)) {
            return null;
        } else if (string.length() < start) {
            return null;
        } else if (isNumeric(end) && Integer.parseInt(end) < start) {
            return null;
        } else {
            if (isNumeric(end)) {
                return string.substring(start, Integer.parseInt(end));
            } else {
                return string.substring(start).replace("-", "月").replace(" ", "日 ");
            }
        }
    }

    /**
     * 判断是否手机号码
     */
    public static boolean isPhone(String str) {
        Pattern pattern = Pattern.compile("^1[3|5|6|7|8|9]\\d{9}$");
        Matcher is = pattern.matcher(str);
        if (!is.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 过滤标签字符串，返回纯文本
     */
    public static String html2Text(String inputString) {
        String htmlStr = inputString; // 含html标签的字符串
        String textStr = "";
        java.util.regex.Pattern p_script;
        java.util.regex.Matcher m_script;
        java.util.regex.Pattern p_style;
        java.util.regex.Matcher m_style;
        java.util.regex.Pattern p_html;
        java.util.regex.Matcher m_html;

        try {
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
            // }
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
            // }
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签

            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); // 过滤style标签

            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签

            textStr = htmlStr;

        } catch (Exception e) {
            System.err.println("Html2Text: " + e.getMessage());
        }

        return textStr;// 返回文本字符串
    }
}
