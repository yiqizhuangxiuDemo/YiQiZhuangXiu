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
    /**
     * 帖子详情
     */
    String API_POSTDETAILS_CONTENT = "http://bbs.17house.com/motnt/index.php?a=viewThread&c=forumThread&imgwidth=330&uuid=86305803367590&tid={$0}&m=forum&haspermission=yes&model=android&app_version=android_com.aiyiqi.galaxy_1.1";
    String API_POSTDETAILS_LIKE = "http://bbs.17house.com/motnt/index.php?a=threadZan&c=forumThread&uuid=86305803367590&tid=1216881&m=forum&haspermission=yes&model=android&app_version=android_com.aiyiqi.galaxy_1.1";
    String URL_COMPANT_ADVERTING ="http://118.178.142.34/YiQiHouse/CompanyAD";
    String URL_COMPANT_DECORTAR = "http://118.178.142.34/YiQiHouse/DecorateLive";
    String URL_NEW_HOUSER = "http://hui.17house.com/svc/payment-facade/h5/activity677/index.html?model=android";
    String URL_OLD_HOUSER ="http://hui.17house.com/svc/payment-facade/h5/activity177/index.html?model=android" ;
    /**
     * 业主说接口
     */
    //精华
     String API_OWNERSAY_CREAM = "http://bbs.17house.com/motnt/index.php?a=allThread&c=forumThreadList&mode=digest&uuid=86305803367590&pageSize=10&m=forum&page={$0}&haspermission=yes&model=android&sessionToken=&app_version=android_com.aiyiqi.galaxy_1.1";
    //最新
     String API_OWNERSAY_UPTODATA = "http://bbs.17house.com/motnt/index.php?a=allThread&c=forumThreadList&uuid=86305803367590&pageSize=10&cityName=%E6%88%90%E9%83%BD&m=forum&page={$0}&haspermission=yes&model=android&sessionToken=&app_version=android_com.aiyiqi.galaxy_1.1";
    //版块
     String API_OWNERSAY_PLATE = "http://bbs.17house.com/motnt/index.php?a=miscForum&uuid=86305803367590&cityName=%E6%88%90%E9%83%BD&m=misc&haspermission=yes&cityId=2&model=android&sessionToken=&app_version=android_com.aiyiqi.galaxy_1.1";
    /**
     * 同城活动接口
     */
    String API_CITY_ACTIVITY = "http://bbs.17house.com/motnt/index.php?a=activityThreadlist&c=forumThreadList&uuid=a444d1b2af4f&pageSize=10&uid=1633055&cityName=%E6%88%90%E9%83%BD&m=forum&page=1&model=android&sessionToken=6U49kCYKE260RqvPqEdFsBGskNQStKhm&app_version=android_com.aiyiqi.galaxy_1.1";
    /**
    * 搜索接口
    */
    String API_SEARCH = "http://bbs.17house.com/motnt/index.php?a=searchForum&c=search&uuid=86305803367590&pageSize=10&m=search&page={$0}&haspermission=yes&kw={$1}&model=android&sessionToken=&app_version=android_com.aiyiqi.galaxy_1.1";
}
