package com.ifuture.demo.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
/**
 * MD5 工具
 * @author wyc
 */
public class MD5Util {

    private static char md5Chars[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    /**
     * MD5
     *
     * @param encryptText
     *            等待加密的字符串
     * @return 加密后的字条串
     */
    public static String md5String(String encryptText) {
        return md5Bytes(encryptText.getBytes());
    }

    /**
     * MD5带字符集 课加密中文
     *
     * @param encryptText
     * @param charset
     * @return
     */
    public static String md5String(String encryptText, String charset) {
        try {
            return md5Bytes(encryptText.getBytes(charset));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取一个字符串的md5码
     * @param str
     * @return
     * @throws Exception
     */
    public static String getStringMD5String(String str) throws Exception {
        MessageDigest messagedigest = MessageDigest.getInstance("MD5");
        messagedigest.update(str.getBytes());
        return bufferToHex(messagedigest.digest());
    }

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = md5Chars[(bt & 0xf0) >> 4];
        char c1 = md5Chars[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

    /**
     * MD5返回大写
     * @param encryptText
     * @return
     */
    public static String md5StringToUpperCase(String encryptText) {
        return md5Bytes(encryptText.getBytes()).toUpperCase();
    }

    public static String md5Bytes(byte[] encryptBytes) {
        String str = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(encryptBytes);
            byte b[] = md.digest();

            StringBuffer buf = new StringBuffer("");
            for (int i = 0; i < b.length; i++) {
                String hex = Integer.toHexString(b[i] & 0xFF);
                if (hex.length() == 1) {
                    hex = '0' + hex;
                }
                buf.append(hex);
            }
            str = buf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }



    /**
     * 生成签名结果(新版本使用)
     *
     * @param sArray
     *            要签名的数组
     * @return 签名结果字符串
     */
    public static String buildMysignV1(Map<String, String> sArray,
        String secretKey) {
        String mysign = "";
        try {
            String prestr = createLinkString(sArray); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
            prestr = prestr + "&secret_key=" + secretKey; // 把拼接后的字符串再与安全校验码连接起来
            mysign = getMD5String(prestr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mysign;
    }

    /**
     * 生成签名结果（老版本使用）
     *
     * @param sArray
     *            要签名的数组
     * @return 签名结果字符串
     */
    public static String buildMysign(Map<String, String> sArray,
        String secretKey) {
        String mysign = "";
        try {
            String prestr = createLinkString(sArray); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
            prestr = prestr + secretKey; // 把拼接后的字符串再与安全校验码直接连接起来
            mysign = getMD5String(prestr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mysign;
    }

    /**
     * 生成签名结果（火币使用）
     *
     * @param sArray
     *            要签名的数组
     * @return 签名结果字符串
     */
    public static String buildSignHuobi(Map<String, String> sArray) {
        String mysign = "";
        try {
            String prestr = createLinkString(sArray); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
            mysign = getMD5String(prestr).toLowerCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mysign;
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param params
     *            需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
    }

    /**
     * 生成32位大写MD5值
     */
    private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5',
        '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    public static String getMD5String(String str) {
        try {
            if (str == null || str.trim().length() == 0) {
                return "";
            }
            byte[] bytes = str.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bytes);
            bytes = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(HEX_DIGITS[(bytes[i] & 0xf0) >> 4] + ""
                    + HEX_DIGITS[bytes[i] & 0xf]);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static String getParams(Map<String,String> map){
        StringBuilder params = new StringBuilder("{");
        for(Entry<String,String> param : map.entrySet()){
            params.append("'").append(param.getKey()).append("':'").append(param.getValue()).append("',");
        }
        params.replace(params.length()-1,params.length(),"}");
        return params.toString();
    }
}
