package com.bwf.yiqizhuangxiu.gui.activity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2016/11/24.
 */
public class EntityFitment {

    /**
     * data : [{"title":"建材","icon":"http://apptest-picture.oss-cn-beijing.aliyuncs.com/icon/jiancai@3x.png","children":[{"id":"12087","title":"瓷砖","icon":"http://static-news.17house.com/web/bbs/201604/20/1400025645.jpg","threadsnum":"375","postsnum":779},{"id":"5104","title":"卫浴","icon":"http://static-news.17house.com/web/bbs/201605/27/1041099278.jpg","threadsnum":"553","postsnum":1133},{"id":"10475","title":"橱柜","icon":"http://static-news.17house.com/web/bbs/201604/20/1358332470.jpg","threadsnum":"891","postsnum":1810},{"id":"5996","title":"地板","icon":"http://static-news.17house.com/web/bbs/201604/20/1357349797.jpg","threadsnum":"1169","postsnum":2365},{"id":"15965","title":"门窗","icon":"http://static-news.17house.com/web/bbs/201605/27/1041324340.jpg","threadsnum":"439","postsnum":906},{"id":"10027","title":"木门","icon":"http://static-news.17house.com/web/bbs/201604/20/1358187528.jpg","threadsnum":"348","postsnum":719},{"id":"5494","title":"吊顶","icon":"http://static-news.17house.com/web/bbs/201604/20/1356522038.jpg","threadsnum":"306","postsnum":638},{"id":"4364","title":"净水器","icon":"http://static-news.17house.com/web/bbs/201605/30/1715445493.jpg","threadsnum":"236","postsnum":475},{"id":"9347","title":"散热器","icon":"http://static-news.17house.com/web/bbs/201605/27/1039419177.jpg","threadsnum":"212","postsnum":448},{"id":"11650","title":"热水器","icon":"http://static-news.17house.com/web/bbs/201605/27/1059077899.jpg","threadsnum":"256","postsnum":535},{"id":"7032","title":"定制衣柜","icon":"http://static-news.17house.com/web/bbs/201605/27/1056052818.jpg","threadsnum":"13","postsnum":39},{"id":"21626","title":"烟机灶具","icon":"http://static-news.17house.com/web/bbs/201605/27/1417416643.jpg","threadsnum":"4","postsnum":26},{"id":"19602","title":"墙面材料","icon":"http://static-news.17house.com/web/bbs/201605/27/1054472824.jpg","threadsnum":"1","postsnum":8},{"id":"22497","title":"其他建材","icon":"http://static-news.17house.com/web/bbs/201605/27/1055268152.jpg","threadsnum":"14","postsnum":54}]},{"title":"家具","icon":"http://apptest-picture.oss-cn-beijing.aliyuncs.com/icon/jiaju@3x.png","children":[{"id":"7958","title":"床垫","icon":"http://static-news.17house.com/web/bbs/201605/30/162529682.jpg","threadsnum":"160","postsnum":343},{"id":"10872","title":"沙发","icon":"http://static-news.17house.com/web/bbs/201605/30/1625081084.jpg","threadsnum":"183","postsnum":371},{"id":"7109","title":"实木家具","icon":"http://static-news.17house.com/web/bbs/201605/30/1626407232.jpg","threadsnum":"221","postsnum":453},{"id":"10224","title":"板式家具","icon":"http://static-news.17house.com/web/bbs/201605/30/1625455585.jpg","threadsnum":"108","postsnum":219},{"id":"17060","title":"儿童家具","icon":"http://static-news.17house.com/web/bbs/201605/30/1626051935.jpg","threadsnum":"41","postsnum":87},{"id":"7030","title":"定制家具","icon":"http://static-news.17house.com/web/bbs/201605/30/1624307191.jpg","threadsnum":"172","postsnum":364},{"id":"22501","title":"其他家具","icon":"http://static-news.17house.com/web/bbs/201605/27/1057465912.jpg","threadsnum":"2","postsnum":15}]},{"title":"电器","icon":"http://apptest-picture.oss-cn-beijing.aliyuncs.com/icon/dianqi@3x.png","children":[{"id":"7235","title":"家用电器","icon":"http://static-news.17house.com/web/bbs/201605/27/1059544564.jpg","threadsnum":"10","postsnum":40}]},{"title":"整装套餐","icon":"http://apptest-picture.oss-cn-beijing.aliyuncs.com/icon/zhengzhuang@3x.png","children":[{"id":"22504","title":"新房整装套餐","icon":"http://static-news.17house.com/web/bbs/201605/27/1100242653.jpg","threadsnum":"54","postsnum":130},{"id":"22499","title":"老房整装套餐","icon":"http://static-news.17house.com/web/bbs/201605/27/1100426971.jpg","threadsnum":"72","postsnum":148}]}]
     * error : 0
     * message : 成功
     */

    private String error;
    private String message;
    /**
     * title : 建材
     * icon : http://apptest-picture.oss-cn-beijing.aliyuncs.com/icon/jiancai@3x.png
     * children : [{"id":"12087","title":"瓷砖","icon":"http://static-news.17house.com/web/bbs/201604/20/1400025645.jpg","threadsnum":"375","postsnum":779},{"id":"5104","title":"卫浴","icon":"http://static-news.17house.com/web/bbs/201605/27/1041099278.jpg","threadsnum":"553","postsnum":1133},{"id":"10475","title":"橱柜","icon":"http://static-news.17house.com/web/bbs/201604/20/1358332470.jpg","threadsnum":"891","postsnum":1810},{"id":"5996","title":"地板","icon":"http://static-news.17house.com/web/bbs/201604/20/1357349797.jpg","threadsnum":"1169","postsnum":2365},{"id":"15965","title":"门窗","icon":"http://static-news.17house.com/web/bbs/201605/27/1041324340.jpg","threadsnum":"439","postsnum":906},{"id":"10027","title":"木门","icon":"http://static-news.17house.com/web/bbs/201604/20/1358187528.jpg","threadsnum":"348","postsnum":719},{"id":"5494","title":"吊顶","icon":"http://static-news.17house.com/web/bbs/201604/20/1356522038.jpg","threadsnum":"306","postsnum":638},{"id":"4364","title":"净水器","icon":"http://static-news.17house.com/web/bbs/201605/30/1715445493.jpg","threadsnum":"236","postsnum":475},{"id":"9347","title":"散热器","icon":"http://static-news.17house.com/web/bbs/201605/27/1039419177.jpg","threadsnum":"212","postsnum":448},{"id":"11650","title":"热水器","icon":"http://static-news.17house.com/web/bbs/201605/27/1059077899.jpg","threadsnum":"256","postsnum":535},{"id":"7032","title":"定制衣柜","icon":"http://static-news.17house.com/web/bbs/201605/27/1056052818.jpg","threadsnum":"13","postsnum":39},{"id":"21626","title":"烟机灶具","icon":"http://static-news.17house.com/web/bbs/201605/27/1417416643.jpg","threadsnum":"4","postsnum":26},{"id":"19602","title":"墙面材料","icon":"http://static-news.17house.com/web/bbs/201605/27/1054472824.jpg","threadsnum":"1","postsnum":8},{"id":"22497","title":"其他建材","icon":"http://static-news.17house.com/web/bbs/201605/27/1055268152.jpg","threadsnum":"14","postsnum":54}]
     */

    private List<DataBean> data;

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
        private String title;
        private String icon;
        /**
         * id : 12087
         * title : 瓷砖
         * icon : http://static-news.17house.com/web/bbs/201604/20/1400025645.jpg
         * threadsnum : 375
         * postsnum : 779
         */

        private List<ChildrenBean> children;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public List<ChildrenBean> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBean> children) {
            this.children = children;
        }

        public static class ChildrenBean implements Parcelable {
            private String id;
            private String title;
            private String icon;
            private String threadsnum;
            private int postsnum;

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

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getThreadsnum() {
                return threadsnum;
            }

            public void setThreadsnum(String threadsnum) {
                this.threadsnum = threadsnum;
            }

            public int getPostsnum() {
                return postsnum;
            }

            public void setPostsnum(int postsnum) {
                this.postsnum = postsnum;
            }


            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.id);
                dest.writeString(this.title);
                dest.writeString(this.icon);
                dest.writeString(this.threadsnum);
                dest.writeInt(this.postsnum);
            }

            public ChildrenBean() {
            }

            protected ChildrenBean(Parcel in) {
                this.id = in.readString();
                this.title = in.readString();
                this.icon = in.readString();
                this.threadsnum = in.readString();
                this.postsnum = in.readInt();
            }

            public static final Creator<ChildrenBean> CREATOR = new Creator<ChildrenBean>() {
                @Override
                public ChildrenBean createFromParcel(Parcel source) {
                    return new ChildrenBean(source);
                }

                @Override
                public ChildrenBean[] newArray(int size) {
                    return new ChildrenBean[size];
                }
            };
        }
    }
}
