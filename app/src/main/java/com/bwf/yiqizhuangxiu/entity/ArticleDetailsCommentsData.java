package com.bwf.yiqizhuangxiu.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/11/29.
 */

public class ArticleDetailsCommentsData {

    /**
     * error : 0
     * message : ok
     * data : {"total":"1","data":[{"comment_content_id":"1691","is_hidden":"0","comment_obj_id":"259611","comment_column_id":"4","comment_user_id":"1634546","comment_user_ip":"192.168.1.83","comment_time":"1480416526","comment_region_city":"0","comment_content":"哈哈","comment_user_name":"里-2","userheadimage":"http://bbs.17house.com/uc_server/avatar.php?uid=1634546&size=middle"}]}
     */

    private int error;
    private String message;
    /**
     * total : 1
     * data : [{"comment_content_id":"1691","is_hidden":"0","comment_obj_id":"259611","comment_column_id":"4","comment_user_id":"1634546","comment_user_ip":"192.168.1.83","comment_time":"1480416526","comment_region_city":"0","comment_content":"哈哈","comment_user_name":"里-2","userheadimage":"http://bbs.17house.com/uc_server/avatar.php?uid=1634546&size=middle"}]
     */

    private DataBeanA data;

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

    public DataBeanA getData() {
        return data;
    }

    public void setData(DataBeanA data) {
        this.data = data;
    }

    public static class DataBeanA {
        private String total;
        /**
         * comment_content_id : 1691
         * is_hidden : 0
         * comment_obj_id : 259611
         * comment_column_id : 4
         * comment_user_id : 1634546
         * comment_user_ip : 192.168.1.83
         * comment_time : 1480416526
         * comment_region_city : 0
         * comment_content : 哈哈
         * comment_user_name : 里-2
         * userheadimage : http://bbs.17house.com/uc_server/avatar.php?uid=1634546&size=middle
         */

        private List<DataBean> data;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            private String comment_content_id;
            private String is_hidden;
            private String comment_obj_id;
            private String comment_column_id;
            private String comment_user_id;
            private String comment_user_ip;
            private String comment_time;
            private String comment_region_city;
            private String comment_content;
            private String comment_user_name;
            private String userheadimage;

            public String getComment_content_id() {
                return comment_content_id;
            }

            public void setComment_content_id(String comment_content_id) {
                this.comment_content_id = comment_content_id;
            }

            public String getIs_hidden() {
                return is_hidden;
            }

            public void setIs_hidden(String is_hidden) {
                this.is_hidden = is_hidden;
            }

            public String getComment_obj_id() {
                return comment_obj_id;
            }

            public void setComment_obj_id(String comment_obj_id) {
                this.comment_obj_id = comment_obj_id;
            }

            public String getComment_column_id() {
                return comment_column_id;
            }

            public void setComment_column_id(String comment_column_id) {
                this.comment_column_id = comment_column_id;
            }

            public String getComment_user_id() {
                return comment_user_id;
            }

            public void setComment_user_id(String comment_user_id) {
                this.comment_user_id = comment_user_id;
            }

            public String getComment_user_ip() {
                return comment_user_ip;
            }

            public void setComment_user_ip(String comment_user_ip) {
                this.comment_user_ip = comment_user_ip;
            }

            public String getComment_time() {
                return comment_time;
            }

            public void setComment_time(String comment_time) {
                this.comment_time = comment_time;
            }

            public String getComment_region_city() {
                return comment_region_city;
            }

            public void setComment_region_city(String comment_region_city) {
                this.comment_region_city = comment_region_city;
            }

            public String getComment_content() {
                return comment_content;
            }

            public void setComment_content(String comment_content) {
                this.comment_content = comment_content;
            }

            public String getComment_user_name() {
                return comment_user_name;
            }

            public void setComment_user_name(String comment_user_name) {
                this.comment_user_name = comment_user_name;
            }

            public String getUserheadimage() {
                return userheadimage;
            }

            public void setUserheadimage(String userheadimage) {
                this.userheadimage = userheadimage;
            }
        }
    }
}
