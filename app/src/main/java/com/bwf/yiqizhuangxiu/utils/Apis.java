package com.bwf.yiqizhuangxiu.utils;

/**
 * Created by Administrator on 2016/11/23.
 */

public interface Apis {
    /**
     * 首页接口
     */
    String URL_COMPANT_ADVERTING ="http://appapi.17house.com/AppManagerApi.php?version=1&action=integratedpackage&model=android&app_version=android_com.aiyiqi.galaxy_1.1";
    String URL_COMPANT_DECORTAR = "http://hui.17house.com/svc/payment-facade/housekeep/listLatestLiveBuildingSites";
    String API_HOMEPAGE_HEAD = "http://appapi.17house.com/AppManagerApi.php?version=1&action=getownerinfo&cityId=2&model=android";
    String API_HOMEPAGE_CONTENT_FIRST_PAGE = "http://bbs.17house.com/motnt/index.php?a=appindex&c=index&id=1218226&uuid=86305803367590&pageSize=10&uid=1633055&m=misc&type=3&page=1&haspermission=yes&model=android&sessionToken=6U49kCYKE260RqvPqEdFsBGskNQStKhm&app_version=android_com.aiyiqi.galaxy_1.1";
    String API_HOMEPAGE_CONTENT = "http://bbs.17house.com/motnt/index.php?a=appindex&c=index&id={$2}&uuid=86305803367590&pageSize=10&uid=1633055&m=misc&type={$1}&page={$0}&haspermission=yes&model=android&sessionToken=6U49kCYKE260RqvPqEdFsBGskNQStKhm&app_version=android_com.aiyiqi.galaxy_1.1";
    /**
     * 帖子详情
     */
    String API_POSTDETAILS_CONTENT = "http://bbs.17house.com/motnt/index.php?a=viewThread&c=forumThread&imgwidth=330&uuid=86305803367590&tid={$0}&m=forum&haspermission=yes&model=android&app_version=android_com.aiyiqi.galaxy_1.1";
    String API_POSTDETAILS_LIKE = "http://bbs.17house.com/motnt/index.php?a=threadZan&c=forumThread&uuid=86305803367590&tid={$0}&m=forum&haspermission=yes&model=android&app_version=android_com.aiyiqi.galaxy_1.1";
    String API_POSTDETAILS_COMMENTS = "http://bbs.17house.com/motnt/index.php?a=viewThread&c=forumThread&imgwidth=270&uuid=86305803367590&pageSize=10&tid={$0}&uid=1633055&m=forum&type=post&page={$1}&haspermission=yes&model=android&sessionToken=6U49kCYKE260RqvPqEdFsBGskNQStKhm&app_version=android_com.aiyiqi.galaxy_1.1";
    /**
     * 文章详情
     */
    String API_ARTICLEDETAILS_CONTENT = "http://appapi.17house.com/NewsApi.php?version=1&newsId={$0}&page={$1}&action=newsDetail&relatedNum=3&model=android";
    String API_ARTICLEDETAILS_COMMENTS = "http://appapi.17house.com/newsApi.php?version=1&page={$1}&dataId={$0}&action=commentList&limit=10&model=android";

    String URL_NEW_HOUSER = "http://hui.17house.com/svc/payment-facade/h5/activity677/index.html?model=android";
    String URL_OLD_HOUSER = "http://hui.17house.com/svc/payment-facade/h5/activity177/index.html?model=android";
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
    String API_CITY_ACTIVITY = "http://bbs.17house.com/motnt/index.php?a=activityThreadlist&c=forumThreadList&uuid=a444d1b2af4f&pageSize=10&uid=1633055&cityName=%E6%88%90%E9%83%BD&m=forum&page={$0}&model=android&sessionToken=6U49kCYKE260RqvPqEdFsBGskNQStKhm&app_version=android_com.aiyiqi.galaxy_1.1";
    /**
    * 搜索接口
    */
    String API_SEARCH = "http://bbs.17house.com/motnt/index.php?a=searchForum&c=search&uuid=86305803367590&pageSize=10&m=search&page={$0}&haspermission=yes&kw={$1}&model=android&sessionToken=&app_version=android_com.aiyiqi.galaxy_1.1";
    String URL_DESING_TEHEER ="http://hui.17house.com/svc/payment-facade/housekeep/listBuildingSiteVendors" ;
    String URL_FOREMAN ="http://hui.17house.com/svc/payment-facade/housekeep/listBuildingSiteVendors";
    String URL_SUPERVISOR="http://hui.17house.com/svc/payment-facade/housekeep/listBuildingSiteVendors";
    String URL_COLLECTION = "http://appapi.17house.com/xiaoguotuApi.php?version=1&page={$0}&action=albumList2&pageSize=10&tagid=1&model=android";
    /**
     * 设计/量房
     */
    String API_FREE_DESIGN = "http://hui.17house.com/svc/payment-facade/h5/measureDesignFree/measureFree.html?model=android";
    String API_FREE_MEASURE = "http://hui.17house.com/svc/payment-facade/h5/measureDesignFree/designFree.html?model=android";
    /**
     * 自助下单
     */
    String URL_EFFECT_BEATIFUL = "http://appapi.17house.com/xiaoguotuApi.php?version=1&page={$0}&action=imageList2&pageSize=10&tagid=0&model=android";
    String API_BOOOKING = "http://appapi.17house.com/GrouponApi.php?version=1&action=getSingleSupplementInfo&cityId={$0}&model=android&app_version=android_com.aiyiqi.galaxy_1.1";
    String URL_SCHOOL_TITLE = "http://appapi.17house.com/StageApi.php?version=1&action=getTagsByStage&stage={$0}&model=android";
    String URL_SCHOOL_COMMENT = "http://appapi.17house.com/NewsApi.php?version=1&action=getNewsByStage&stage={$0}&page={$1}&pagesize=10&model=android";
    String URL_WOEKPLAY_PEOPLE = "http://hui.17house.com/svc/payment-facade/housekeep/getLiveBuildingSite";
    String URL_WORK_PROGRESS = "http://hui.17house.com/svc/payment-facade/housekeep/listBuildingSiteTrackByProgress";
}
