package com.bwf.yiqizhuangxiu.utils;

/**
 * Created by Administrator on 2016/10/24.
 */

public class UrlHandler {
    public static String handleURL(String urlBean, Object... parms) {
        for (int i = 0; i < parms.length; i++) {
            urlBean = urlBean.replace("{$" + i + "}", parms[i] + "");
        }
        return urlBean;
    }

    public static String regexURL(String urlBean, String regex, Object parms) {
        urlBean = urlBean.replace(regex, parms + "");
        return urlBean;
    }
}
