package com.utils;

//字符串工具
public class StringUtil {
    public static  boolean isEmpty(String str){
        //判断是否为空
        return str == null || str.trim().isEmpty();
    }
    //判断不为空
    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }
}
