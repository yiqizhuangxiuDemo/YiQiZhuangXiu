package com.bwf.yiqizhuangxiu.utils;

/**
 * Created by Administrator on 2016/11/23.
 */

public interface Apis {
    /**
     * 首页接口
     */
    String API_HOMEPAGE_HEAD = "http://118.178.142.34/YiQiHouse/HomeAD";
    String API_HOMEPAGE_CONTENT_FIRST_PAGE = "http://118.178.142.34/YiQiHouse/HomeBBS?page=1";
    String API_HOMEPAGE_CONTENT = "http://118.178.142.34/YiQiHouse/HomeBBS?page={$0}&type={$1}&id={$2}";
}
