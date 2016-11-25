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
}
