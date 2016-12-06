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
     * pageTotalNum : 1
     */

    private PageInfoBean pageInfo;
    /**
     * trackId : 1552826687991850287
     * message : 安装完毕。
     * buildingId : 1551754327228432540
     * progressId : 1
     * acceptancePassed : 0
     * acceptanceId : 0
     * createTime : 1480890930000
     * dateInterval : 13
     * vendorId : 1538268363907686936
     * atList : []
     * userId : 1551754327080583323
     * imgSrc : http://jiazhuang-picture.oss-cn-beijing.aliyuncs.com/jiagenjin/9c9020d0-8347-467d-8d63-d4dcf543b85f,http://jiazhuang-picture.oss-cn-beijing.aliyuncs.com/jiagenjin/475ef897-1a2e-42b0-a46d-3a29283559cd,http://jiazhuang-picture.oss-cn-beijing.aliyuncs.com/jiagenjin/95aadd83-e74f-44eb-8940-5b2260a785dd,http://jiazhuang-picture.oss-cn-beijing.aliyuncs.com/jiagenjin/b0863328-b839-4706-bfa8-5ba4f4da13bf
     * sponsorType : 1
     * creatorName : 祝全来
     * creatorRole : 工长
     * replyList : []
     * weiXinShare : {"title":"来自四方景园1区的装修工地直播","content":"此工地进入开工阶段，大量现场图片等您来看，目前全国已开通3256个工地","news":"来自四方景园1区的装修工地直播","imageUrl":"http://jiazhuang-picture.oss-cn-beijing.aliyuncs.com/jiagenjin/9c9020d0-8347-467d-8d63-d4dcf543b85f","linkUrl":"http://wap.17house.com/zhuangxiu/gongdizhibo/1551754327228432540.html"}
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
        private String trackId;
        private String message;
        private String buildingId;
        private String progressId;
        private String acceptancePassed;
        private String acceptanceId;
        private String createTime;
        private String dateInterval;
        private String vendorId;
        private String atList;
        private String userId;
        private String imgSrc;
        private String sponsorType;
        private String creatorName;
        private String creatorRole;
        /**
         * title : 来自四方景园1区的装修工地直播
         * content : 此工地进入开工阶段，大量现场图片等您来看，目前全国已开通3256个工地
         * news : 来自四方景园1区的装修工地直播
         * imageUrl : http://jiazhuang-picture.oss-cn-beijing.aliyuncs.com/jiagenjin/9c9020d0-8347-467d-8d63-d4dcf543b85f
         * linkUrl : http://wap.17house.com/zhuangxiu/gongdizhibo/1551754327228432540.html
         */

        private WeiXinShareBean weiXinShare;
        private List<?> replyList;

        public String getTrackId() {
            return trackId;
        }

        public void setTrackId(String trackId) {
            this.trackId = trackId;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getBuildingId() {
            return buildingId;
        }

        public void setBuildingId(String buildingId) {
            this.buildingId = buildingId;
        }

        public String getProgressId() {
            return progressId;
        }

        public void setProgressId(String progressId) {
            this.progressId = progressId;
        }

        public String getAcceptancePassed() {
            return acceptancePassed;
        }

        public void setAcceptancePassed(String acceptancePassed) {
            this.acceptancePassed = acceptancePassed;
        }

        public String getAcceptanceId() {
            return acceptanceId;
        }

        public void setAcceptanceId(String acceptanceId) {
            this.acceptanceId = acceptanceId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDateInterval() {
            return dateInterval;
        }

        public void setDateInterval(String dateInterval) {
            this.dateInterval = dateInterval;
        }

        public String getVendorId() {
            return vendorId;
        }

        public void setVendorId(String vendorId) {
            this.vendorId = vendorId;
        }

        public String getAtList() {
            return atList;
        }

        public void setAtList(String atList) {
            this.atList = atList;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getImgSrc() {
            return imgSrc;
        }

        public void setImgSrc(String imgSrc) {
            this.imgSrc = imgSrc;
        }

        public String getSponsorType() {
            return sponsorType;
        }

        public void setSponsorType(String sponsorType) {
            this.sponsorType = sponsorType;
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
            private String title;
            private String content;
            private String news;
            private String imageUrl;
            private String linkUrl;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getNews() {
                return news;
            }

            public void setNews(String news) {
                this.news = news;
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
        }
    }
}
