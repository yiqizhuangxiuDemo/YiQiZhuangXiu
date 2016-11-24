package com.bwf.yiqizhuangxiu.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/11/23.
 */

public class HomepageHeadData {

    /**
     * error : 0
     * message : ok
     * data : [{"title":"中国互联网家装金钻工程首发，全国49城联动","imagesrc":"http://appmanager.17house.com/upload/20161031/58172fa38505f_t.jpg","imagesrc2":"http://appmanager.17house.com/upload/20161031/58172fa3ae658_t.jpg","tid":"","type":4,"banner_url":"http://tuan.17house.com/bj/2673.html"}]
     */

    private int error;
    private String message;
    /**
     * title : 中国互联网家装金钻工程首发，全国49城联动
     * imagesrc : http://appmanager.17house.com/upload/20161031/58172fa38505f_t.jpg
     * imagesrc2 : http://appmanager.17house.com/upload/20161031/58172fa3ae658_t.jpg
     * tid :
     * type : 4
     * banner_url : http://tuan.17house.com/bj/2673.html
     */

    private List<DataBean> data;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String title;
        private String imagesrc;
        private String imagesrc2;
        private String tid;
        private int type;
        private String banner_url;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImagesrc() {
            return imagesrc;
        }

        public void setImagesrc(String imagesrc) {
            this.imagesrc = imagesrc;
        }

        public String getImagesrc2() {
            return imagesrc2;
        }

        public void setImagesrc2(String imagesrc2) {
            this.imagesrc2 = imagesrc2;
        }

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getBanner_url() {
            return banner_url;
        }

        public void setBanner_url(String banner_url) {
            this.banner_url = banner_url;
        }
    }
}
