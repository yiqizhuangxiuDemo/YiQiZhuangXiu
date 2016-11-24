package com.bwf.yiqizhuangxiu.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/11/23.
 */

public class HomepageContentData {

    /**
     * data : [{"type":1,"id":"257199","title":"装修万万不能毁在辅材选购上","author":"","uid":0,"avtUrl":"","path":"http://static-news.17house.com/web/news/201611/09/201611091052254306.jpg","views":573,"like_num":0,"replies":"0","dateline":"7天前","sort":"1478659962","forum":{"fid":0,"name":""},"followed":0,"h5Url":"http://m.17house.com/news/257199.html"}]
     * newCount : 0
     * currentPage : 2
     * totalCount : 150
     * error : 0
     * message : 成功
     */

    private int newCount;
    private int currentPage;
    private int totalCount;
    private String error;
    private String message;
    /**
     * type : 1
     * id : 257199
     * title : 装修万万不能毁在辅材选购上
     * author :
     * uid : 0
     * avtUrl :
     * path : http://static-news.17house.com/web/news/201611/09/201611091052254306.jpg
     * views : 573
     * like_num : 0
     * replies : 0
     * dateline : 7天前
     * sort : 1478659962
     * forum : {"fid":0,"name":""}
     * followed : 0
     * h5Url : http://m.17house.com/news/257199.html
     */

    private List<DataBean> data;

    public int getNewCount() {
        return newCount;
    }

    public void setNewCount(int newCount) {
        this.newCount = newCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
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
        private int type;
        private String id;
        private String title;
        private String author;
        private int uid;
        private String avtUrl;
        private String path;
        private int views;
        private int like_num;
        private String replies;
        private String dateline;
        private String sort;
        /**
         * fid : 0
         * name :
         */

        private ForumBean forum;
        private int followed;
        private String h5Url;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getAvtUrl() {
            return avtUrl;
        }

        public void setAvtUrl(String avtUrl) {
            this.avtUrl = avtUrl;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public int getLike_num() {
            return like_num;
        }

        public void setLike_num(int like_num) {
            this.like_num = like_num;
        }

        public String getReplies() {
            return replies;
        }

        public void setReplies(String replies) {
            this.replies = replies;
        }

        public String getDateline() {
            return dateline;
        }

        public void setDateline(String dateline) {
            this.dateline = dateline;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public ForumBean getForum() {
            return forum;
        }

        public void setForum(ForumBean forum) {
            this.forum = forum;
        }

        public int getFollowed() {
            return followed;
        }

        public void setFollowed(int followed) {
            this.followed = followed;
        }

        public String getH5Url() {
            return h5Url;
        }

        public void setH5Url(String h5Url) {
            this.h5Url = h5Url;
        }

        public static class ForumBean {
            private int fid;
            private String name;

            public int getFid() {
                return fid;
            }

            public void setFid(int fid) {
                this.fid = fid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
