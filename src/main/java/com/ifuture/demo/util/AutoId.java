package com.ifuture.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class AutoId {
    /**
     * 生成一个长度为25的纯数字订单号
     * @return
     */
    public static String getAutoId(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSSSS");
        String str1 = sdf.format(new Date());
        Random rd = new Random();
        String str2 = rd.nextInt(100000)+"";
        if(str2.length()==1){
            str2="0000"+str2;
        }else if(str2.length()==2){
            str2="000"+str2;
        }else if(str2.length()==3){
            str2="00"+str2;
        }else if(str2.length()==4){
            str2="0"+str2;
        }
        return str1 + str2;
    }
}
