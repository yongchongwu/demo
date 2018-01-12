package com.ifuture.demo.util;

public class SecretUtil {

    public static String getRandomSecret() {
        String[] pswdStr = {"qwertyuiopasdfghjklzxcvbnm",
            "QWERTYUIOPASDFGHJKLZXCVBNM", "0123456789",
            "@$%&"};
        int pswdLen = 20;
        String pswd = "";
        char[] chs = new char[pswdLen];
        for (int i = 0; i < pswdStr.length; i++) {
            int idx = (int) (Math.random() * pswdStr[i].length());
            chs[i] = pswdStr[i].charAt(idx);
        }
        for (int i = pswdStr.length; i < pswdLen; i++) {
            int arrIdx = (int) (Math.random() * pswdStr.length);
            int strIdx = (int) (Math.random() * pswdStr[arrIdx].length());
            chs[i] = pswdStr[arrIdx].charAt(strIdx);
        }
        for (int i = 0; i < 1000; i++) {
            int idx1 = (int) (Math.random() * chs.length);
            int idx2 = (int) (Math.random() * chs.length);
            if (idx1 == idx2) {
                continue;
            }
            char tempChar = chs[idx1];
            chs[idx1] = chs[idx2];
            chs[idx2] = tempChar;
        }
        pswd = new String(chs);
        return pswd;
    }

}
