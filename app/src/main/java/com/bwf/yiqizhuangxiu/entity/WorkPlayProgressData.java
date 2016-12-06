package com.bwf.yiqizhuangxiu.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/5 0005.
 */

public class WorkPlayProgressData implements Serializable{
    /**
     * code : 0
     * message : success
     */

    private BaseOutputBean baseOutput;
    /**
     * pageNo : 0
     * pageSize : 10
     * pageTotalNum : 6
     */

    private PageInfoBean pageInfo;
    /**
     * acceptanceId : 0
     * acceptancePassed : 0
     * atList : [{"id":"1325417396","name":"程仪兵"}]
     * avatar : http://bbs.17house.com/uc_server/avatar.php?uid=1371560&size=big
     * buildingId : 1535476603258159617
     * createTime : 1480371020000
     * creatorName : 手机用户_1371560
     * creatorRole : 业主
     * imgSrc : http://jiazhuang-picture.oss-cn-beijing.aliyuncs.com/jiagenjin/IMG_20161128_212513.jpg
     * message : @程仪兵 筒灯～～一举多得。智慧
     * progressId : 1
     * replyList : []
     * sponsorType : 2
     * trackId : 1552281523127855119
     * userId : 1535476603258159616
     * vendorId : 0
     * weiXinShare : {"content":"此工地进入开工阶段，大量现场图片等您来看，目前全国已开通3256个工地","imageUrl":"http://jiazhuang-picture.oss-cn-beijing.aliyuncs.com/jiagenjin/IMG_20161128_212513.jpg","linkUrl":"http://wap.17house.com/zhuangxiu/gongdizhibo/1535476603258159617.html","news":"来自石景山区苹果园一区的装修工地直播","title":"来自石景山区苹果园一区的装修工地直播"}
     */

    private List<DataBean> data;

    public BaseOutputBean getBaseOutput() {
        return baseOutput;
    }

    public void setBaseOutput(BaseOutputBean baseOutput) {
        this.baseOutput = baseOutput;
    }

    public PageInfoBean getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfoBean pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class BaseOutputBean implements Serializable{
        private String code;
        private String message;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class PageInfoBean implements Serializable{
        private String pageNo;
        private String pageSize;
        private String pageTotalNum;

        public String getPageNo() {
            return pageNo;
        }

        public void setPageNo(String pageNo) {
            this.pageNo = pageNo;
        }

        public String getPageSize() {
            return pageSize;
        }

        public void setPageSize(String pageSize) {
            this.pageSize = pageSize;
        }

        public String getPageTotalNum() {
            return pageTotalNum;
        }

        public void setPageTotalNum(String pageTotalNum) {
            this.pageTotalNum = pageTotalNum;
        }
    }

    public static class DataBean implements Serializable{
        private String acceptanceId;
        private String acceptancePassed;
        private String atList;
        private String avatar;
        private String buildingId;
        private String createTime;
        private String creatorName;
        private String creatorRole;
        private String imgSrc;
        private String message;
        private String progressId;
        private String sponsorType;
        private String trackId;
        private String userId;
        private String vendorId;
        /**
         * content : 此工地进入开工阶段，大量现场图片等您来看，目前全国已开通3256个工地
         * imageUrl : http://jiazhuang-picture.oss-cn-beijing.aliyuncs.com/jiagenjin/IMG_20161128_212513.jpg
         * linkUrl : http://wap.17house.com/zhuangxiu/gongdizhibo/1535476603258159617.html
         * news : 来自石景山区苹果园一区的装修工地直播
         * title : 来自石景山区苹果园一区的装修工地直播
         */

        private WeiXinShareBean weiXinShare;
        private List<?> replyList;

        public String getAcceptanceId() {
            return acceptanceId;
        }

        public void setAcceptanceId(String acceptanceId) {
            this.acceptanceId = acceptanceId;
        }

        public String getAcceptancePassed() {
            return acceptancePassed;
        }

        public void setAcceptancePassed(String acceptancePassed) {
            this.acceptancePassed = acceptancePassed;
        }

        public String getAtList() {
            return atList;
        }

        public void setAtList(String atList) {
            this.atList = atList;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getBuildingId() {
            return buildingId;
        }

        public void setBuildingId(String buildingId) {
            this.buildingId = buildingId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreatorName() {
            return creatorName;
        }

        public void setCreatorName(String creatorName) {
            this.creatorName = creatorName;
        }

        public String getCreatorRole() {
            return creatorRole;
        }

        public void setCreatorRole(String creatorRole) {
            this.creatorRole = creatorRole;
        }

        public String getImgSrc() {
            return imgSrc;
        }

        public void setImgSrc(String imgSrc) {
            this.imgSrc = imgSrc;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getProgressId() {
            return progressId;
        }

        public void setProgressId(String progressId) {
            this.progressId = progressId;
        }

        public String getSponsorType() {
            return sponsorType;
        }

        public void setSponsorType(String sponsorType) {
            this.sponsorType = sponsorType;
        }

        public String getTrackId() {
            return trackId;
        }

        public void setTrackId(String trackId) {
            this.trackId = trackId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getVendorId() {
            return vendorId;
        }

        public void setVendorId(String vendorId) {
            this.vendorId = vendorId;
        }

        public WeiXinShareBean getWeiXinShare() {
            return weiXinShare;
        }

        public void setWeiXinShare(WeiXinShareBean weiXinShare) {
            this.weiXinShare = weiXinShare;
        }

        public List<?> getReplyList() {
            return replyList;
        }

        public void setReplyList(List<?> replyList) {
            this.replyList = replyList;
        }

        public static class WeiXinShareBean implements Serializable{
            private String content;
            private String imageUrl;
            private String linkUrl;
            private String news;
            private String title;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getLinkUrl() {
                return linkUrl;
            }

            public void setLinkUrl(String linkUrl) {
                this.linkUrl = linkUrl;
            }

            public String getNews() {
                return news;
            }

            public void setNews(String news) {
                this.news = news;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
