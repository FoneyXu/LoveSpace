package com.foney.lovespace.util;

/**
 * Created by foney on 2017/8/12.
 */

public class SysUtil {

    public static final String RESULT_TRUE = "true";
    public static final String RESULT_FALSE = "false";
    private static final String httpUrl = "http://foney.4kb.cn/LoveSpace";

    public static String getUrl(String url) {
        return httpUrl + url;
    }

}
