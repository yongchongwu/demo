package com.ifuture.demo.util;

public class SqlInjectionUtil {

    public static String replaceSQL(String v) {
        if (v == null) {
            return null;
        }
        String ret = v.replaceAll("'", "").replaceAll("\"", "").replaceAll("\\\\", "")
            .replaceAll("\\|", "").replaceAll(";", "");
        if (isSqlKey(ret)) {
            return "99999999";
        }
        return ret;
    }

    public static boolean isSqlKey(String str) {
        if (str == null) {
            return false;
        }
        String tmp = str.toLowerCase();
        String inj_str = "and|=|like|begin|alter|drop|exec|insert|select|delete|update|count|chr(|master|truncate|char(|declare|;";

        String inj_stra[] = inj_str.split("\\|");

        for (int i = 0; i < inj_stra.length; i++) {
            if (tmp.indexOf(inj_stra[i]) != -1) {
                return true;
            }
        }
        return false;
    }

    //效验
    protected static boolean sqlValidate(String str) {
        str = str.toLowerCase();//统一转为小写
        String badStr =
            "'|and|exec|execute|insert|select|delete|update|count|drop|*|%|chr|mid|master|truncate|"
                +
                "char|declare|sitename|net user|xp_cmdshell|;|or|+|like'|and|exec|execute|insert|create|drop|"
                +
                "table|from|grant|use|group_concat|column_name|" +
                "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|*|"
                +
                "chr|mid|master|truncate|char|declare|or|;|--|+|like|//|%|#";//过滤掉的sql关键字，可以手动添加
        String[] badStrs = badStr.split("\\|");
        for (int i = 0; i < badStrs.length; i++) {
            if (str.indexOf(badStrs[i]) >= 0) {
                return true;
            }
        }
        return false;
    }
}
