package com.bwf.yiqizhuangxiu.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/12/5 0005.
 */

public class WorkPlayingPeopleAndProgressData {
    /**
     * code : 0
     * message : success
     */

    private BaseOutputBean baseOutput;
    /**
     * buildingSite : {"buildingId":1546502802369429320,"statusId":0,"progressId":3,"acceptanceProgressId":0,"orderId":1546502802369429320,"createTime":1479180845000,"updateTime":1480895114000,"userId":1546502802168102727,"startDisclosure":2,"startDisclosureTime":1480582454000,"splitAlter":0,"splitAlterTime":1479180845000,"waterElectricity":1,"waterElectricityTime":1480582454000,"cementWood":0,"cementWoodTime":1479180845000,"paint":0,"paintTime":1479180845000,"installation":0,"installationTime":1479180845000,"finish":0,"finishTime":1479180845000,"buildingIdStr":"","acceptanceStatus":0,"bespeakExpired":0,"messageNumber":0,"scheduleStatus":0,"isShow":1}
     * orderHouse : {"orderId":1546502802369429320,"layout":"二室一厅一卫一厨","area":"89","doorplate":"2-2-1104","community":"顺义区金地悦景台","address":"顺义区金地悦景台2-2-1104","newHouse":1,"deliveryHouse":2,"lng":116.536467,"lat":40.102761}
     * userDetail : {"userId":1546502802168102727,"realName":"黄女士","gender":0,"emailVerified":0,"provinceId":0,"cityId":0,"districtId":0,"userPoint":0,"userLevel":0,"hasOrder":0,"updateTime":1474860003000,"userPointIncrement":0,"userPointDate":1474860003000,"mobile":"15810105359","mobileLocation":"北京","userType":0}
     * imageUrl : http://apptest-picture.oss-cn-beijing.aliyuncs.com/yiqizhuagnxiu-gongdizhibo/tu10.png
     * members : [{"vendorId":1549969683034164702,"vendorName":"何宇杰","realName":"何宇杰","nickName":"何宇杰","avatar":"http://jiazhuang-picture.oss-cn-beijing.aliyuncs.com/jiagenjin/f627b039-d730-42ec-a3b2-cb3bae7babdd","lifePhoto":"http://jiazhuang-picture.oss-cn-beijing.aliyuncs.com/jiagenjin/64e070d3-4c4b-4342-9a96-f4a406e5ba3f","signature":"业主您好，我是一起装修网监理，您的贴心管家；负责工地质量、辅材、施工节点验收，处理施工中任何疑问与投诉。","provinceId":1,"workYear":2009,"goodAt":"简欧,欧式,现代简约,北欧","updateTime":1478166278000,"bossId":1702},{"vendorId":1543537729109770366,"vendorName":"姚志平","realName":"姚志平","nickName":"姚志平","provinceId":0,"workYear":0,"updateTime":1472032289000,"bossId":1703},{"vendorId":327,"vendorName":"关键","realName":"关键","nickName":"关键","avatar":"http://jiazhuang-picture.oss-cn-beijing.aliyuncs.com/jiagenjin/ef47bf46-6dd6-446e-9564-a450a8a62543","lifePhoto":"http://jiazhuang-picture.oss-cn-beijing.aliyuncs.com/jiagenjin/a6fd9e02-da26-4e46-bc40-a5e39642f47d","signature":"1","provinceId":8,"workYear":2016,"updateTime":1460440500000,"bossId":1705}]
     * progress : [{"progressId":1,"progressName":"开工","progressStatus":2,"createTime":1480582454000},{"progressId":3,"progressName":"水电","progressStatus":1,"createTime":1480582454000},{"progressId":4,"progressName":"泥木","progressStatus":0,"createTime":1479180845000},{"progressId":5,"progressName":"油漆","progressStatus":0,"createTime":1479180845000},{"progressId":6,"progressName":"安装","progressStatus":0,"createTime":1479180845000},{"progressId":7,"progressName":"竣工","progressStatus":0,"createTime":1479180845000}]
     * latestTrackProgressId : 3
     */

    private DataBean data;

    public BaseOutputBean getBaseOutput() {
        return baseOutput;
    }

    public void setBaseOutput(BaseOutputBean baseOutput) {
        this.baseOutput = baseOutput;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class BaseOutputBean {
        private int code;
        private String message;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class DataBean {
        /**
         * buildingId : 1546502802369429320
         * statusId : 0
         * progressId : 3
         * acceptanceProgressId : 0
         * orderId : 1546502802369429320
         * createTime : 1479180845000
         * updateTime : 1480895114000
         * userId : 1546502802168102727
         * startDisclosure : 2
         * startDisclosureTime : 1480582454000
         * splitAlter : 0
         * splitAlterTime : 1479180845000
         * waterElectricity : 1
         * waterElectricityTime : 1480582454000
         * cementWood : 0
         * cementWoodTime : 1479180845000
         * paint : 0
         * paintTime : 1479180845000
         * installation : 0
         * installationTime : 1479180845000
         * finish : 0
         * finishTime : 1479180845000
         * buildingIdStr :
         * acceptanceStatus : 0
         * bespeakExpired : 0
         * messageNumber : 0
         * scheduleStatus : 0
         * isShow : 1
         */

        private BuildingSiteBean buildingSite;
        /**
         * orderId : 1546502802369429320
         * layout : 二室一厅一卫一厨
         * area : 89
         * doorplate : 2-2-1104
         * community : 顺义区金地悦景台
         * address : 顺义区金地悦景台2-2-1104
         * newHouse : 1
         * deliveryHouse : 2
         * lng : 116.536467
         * lat : 40.102761
         */

        private OrderHouseBean orderHouse;
        /**
         * userId : 1546502802168102727
         * realName : 黄女士
         * gender : 0
         * emailVerified : 0
         * provinceId : 0
         * cityId : 0
         * districtId : 0
         * userPoint : 0
         * userLevel : 0
         * hasOrder : 0
         * updateTime : 1474860003000
         * userPointIncrement : 0
         * userPointDate : 1474860003000
         * mobile : 15810105359
         * mobileLocation : 北京
         * userType : 0
         */

        private UserDetailBean userDetail;
        private String imageUrl;
        private int latestTrackProgressId;
        /**
         * vendorId : 1549969683034164702
         * vendorName : 何宇杰
         * realName : 何宇杰
         * nickName : 何宇杰
         * avatar : http://jiazhuang-picture.oss-cn-beijing.aliyuncs.com/jiagenjin/f627b039-d730-42ec-a3b2-cb3bae7babdd
         * lifePhoto : http://jiazhuang-picture.oss-cn-beijing.aliyuncs.com/jiagenjin/64e070d3-4c4b-4342-9a96-f4a406e5ba3f
         * signature : 业主您好，我是一起装修网监理，您的贴心管家；负责工地质量、辅材、施工节点验收，处理施工中任何疑问与投诉。
         * provinceId : 1
         * workYear : 2009
         * goodAt : 简欧,欧式,现代简约,北欧
         * updateTime : 1478166278000
         * bossId : 1702
         */

        private List<MembersBean> members;
        /**
         * progressId : 1
         * progressName : 开工
         * progressStatus : 2
         * createTime : 1480582454000
         */

        private List<ProgressBean> progress;

        public BuildingSiteBean getBuildingSite() {
            return buildingSite;
        }

        public void setBuildingSite(BuildingSiteBean buildingSite) {
            this.buildingSite = buildingSite;
        }

        public OrderHouseBean getOrderHouse() {
            return orderHouse;
        }

        public void setOrderHouse(OrderHouseBean orderHouse) {
            this.orderHouse = orderHouse;
        }

        public UserDetailBean getUserDetail() {
            return userDetail;
        }

        public void setUserDetail(UserDetailBean userDetail) {
            this.userDetail = userDetail;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getLatestTrackProgressId() {
            return latestTrackProgressId;
        }

        public void setLatestTrackProgressId(int latestTrackProgressId) {
            this.latestTrackProgressId = latestTrackProgressId;
        }

        public List<MembersBean> getMembers() {
            return members;
        }

        public void setMembers(List<MembersBean> members) {
            this.members = members;
        }

        public List<ProgressBean> getProgress() {
            return progress;
        }

        public void setProgress(List<ProgressBean> progress) {
            this.progress = progress;
        }

        public static class BuildingSiteBean {
            private String buildingId;
            private int statusId;
            private int progressId;
            private int acceptanceProgressId;
            private String orderId;
            private long createTime;
            private long updateTime;
            private String userId;
            private int startDisclosure;
            private long startDisclosureTime;
            private int splitAlter;
            private long splitAlterTime;
            private int waterElectricity;
            private long waterElectricityTime;
            private int cementWood;
            private long cementWoodTime;
            private int paint;
            private long paintTime;
            private int installation;
            private long installationTime;
            private int finish;
            private long finishTime;
            private String buildingIdStr;
            private int acceptanceStatus;
            private int bespeakExpired;
            private int messageNumber;
            private int scheduleStatus;
            private int isShow;

            public String getBuildingId() {
                return buildingId;
            }

            public void setBuildingId(String buildingId) {
                this.buildingId = buildingId;
            }

            public int getStatusId() {
                return statusId;
            }

            public void setStatusId(int statusId) {
                this.statusId = statusId;
            }

            public int getProgressId() {
                return progressId;
            }

            public void setProgressId(int progressId) {
                this.progressId = progressId;
            }

            public int getAcceptanceProgressId() {
                return acceptanceProgressId;
            }

            public void setAcceptanceProgressId(int acceptanceProgressId) {
                this.acceptanceProgressId = acceptanceProgressId;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public int getStartDisclosure() {
                return startDisclosure;
            }

            public void setStartDisclosure(int startDisclosure) {
                this.startDisclosure = startDisclosure;
            }

            public long getStartDisclosureTime() {
                return startDisclosureTime;
            }

            public void setStartDisclosureTime(long startDisclosureTime) {
                this.startDisclosureTime = startDisclosureTime;
            }

            public int getSplitAlter() {
                return splitAlter;
            }

            public void setSplitAlter(int splitAlter) {
                this.splitAlter = splitAlter;
            }

            public long getSplitAlterTime() {
                return splitAlterTime;
            }

            public void setSplitAlterTime(long splitAlterTime) {
                this.splitAlterTime = splitAlterTime;
            }

            public int getWaterElectricity() {
                return waterElectricity;
            }

            public void setWaterElectricity(int waterElectricity) {
                this.waterElectricity = waterElectricity;
            }

            public long getWaterElectricityTime() {
                return waterElectricityTime;
            }

            public void setWaterElectricityTime(long waterElectricityTime) {
                this.waterElectricityTime = waterElectricityTime;
            }

            public int getCementWood() {
                return cementWood;
            }

            public void setCementWood(int cementWood) {
                this.cementWood = cementWood;
            }

            public long getCementWoodTime() {
                return cementWoodTime;
            }

            public void setCementWoodTime(long cementWoodTime) {
                this.cementWoodTime = cementWoodTime;
            }

            public int getPaint() {
                return paint;
            }

            public void setPaint(int paint) {
                this.paint = paint;
            }

            public long getPaintTime() {
                return paintTime;
            }

            public void setPaintTime(long paintTime) {
                this.paintTime = paintTime;
            }

            public int getInstallation() {
                return installation;
            }

            public void setInstallation(int installation) {
                this.installation = installation;
            }

            public long getInstallationTime() {
                return installationTime;
            }

            public void setInstallationTime(long installationTime) {
                this.installationTime = installationTime;
            }

            public int getFinish() {
                return finish;
            }

            public void setFinish(int finish) {
                this.finish = finish;
            }

            public long getFinishTime() {
                return finishTime;
            }

            public void setFinishTime(long finishTime) {
                this.finishTime = finishTime;
            }

            public String getBuildingIdStr() {
                return buildingIdStr;
            }

            public void setBuildingIdStr(String buildingIdStr) {
                this.buildingIdStr = buildingIdStr;
            }

            public int getAcceptanceStatus() {
                return acceptanceStatus;
            }

            public void setAcceptanceStatus(int acceptanceStatus) {
                this.acceptanceStatus = acceptanceStatus;
            }

            public int getBespeakExpired() {
                return bespeakExpired;
            }

            public void setBespeakExpired(int bespeakExpired) {
                this.bespeakExpired = bespeakExpired;
            }

            public int getMessageNumber() {
                return messageNumber;
            }

            public void setMessageNumber(int messageNumber) {
                this.messageNumber = messageNumber;
            }

            public int getScheduleStatus() {
                return scheduleStatus;
            }

            public void setScheduleStatus(int scheduleStatus) {
                this.scheduleStatus = scheduleStatus;
            }

            public int getIsShow() {
                return isShow;
            }

            public void setIsShow(int isShow) {
                this.isShow = isShow;
            }
        }

        public static class OrderHouseBean {
            private String orderId;
            private String layout;
            private String area;
            private String doorplate;
            private String community;
            private String address;
            private int newHouse;
            private int deliveryHouse;
            private double lng;
            private double lat;

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public String getLayout() {
                return layout;
            }

            public void setLayout(String layout) {
                this.layout = layout;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getDoorplate() {
                return doorplate;
            }

            public void setDoorplate(String doorplate) {
                this.doorplate = doorplate;
            }

            public String getCommunity() {
                return community;
            }

            public void setCommunity(String community) {
                this.community = community;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getNewHouse() {
                return newHouse;
            }

            public void setNewHouse(int newHouse) {
                this.newHouse = newHouse;
            }

            public int getDeliveryHouse() {
                return deliveryHouse;
            }

            public void setDeliveryHouse(int deliveryHouse) {
                this.deliveryHouse = deliveryHouse;
            }

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }
        }

        public static class UserDetailBean {
            private String userId;
            private String realName;
            private int gender;
            private int emailVerified;
            private String provinceId;
            private int cityId;
            private String districtId;
            private int userPoint;
            private int userLevel;
            private int hasOrder;
            private long updateTime;
            private int userPointIncrement;
            private long userPointDate;
            private String mobile;
            private String mobileLocation;
            private int userType;

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public int getEmailVerified() {
                return emailVerified;
            }

            public void setEmailVerified(int emailVerified) {
                this.emailVerified = emailVerified;
            }

            public String getProvinceId() {
                return provinceId;
            }

            public void setProvinceId(String provinceId) {
                this.provinceId = provinceId;
            }

            public int getCityId() {
                return cityId;
            }

            public void setCityId(int cityId) {
                this.cityId = cityId;
            }

            public String getDistrictId() {
                return districtId;
            }

            public void setDistrictId(String districtId) {
                this.districtId = districtId;
            }

            public int getUserPoint() {
                return userPoint;
            }

            public void setUserPoint(int userPoint) {
                this.userPoint = userPoint;
            }

            public int getUserLevel() {
                return userLevel;
            }

            public void setUserLevel(int userLevel) {
                this.userLevel = userLevel;
            }

            public int getHasOrder() {
                return hasOrder;
            }

            public void setHasOrder(int hasOrder) {
                this.hasOrder = hasOrder;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public int getUserPointIncrement() {
                return userPointIncrement;
            }

            public void setUserPointIncrement(int userPointIncrement) {
                this.userPointIncrement = userPointIncrement;
            }

            public long getUserPointDate() {
                return userPointDate;
            }

            public void setUserPointDate(long userPointDate) {
                this.userPointDate = userPointDate;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getMobileLocation() {
                return mobileLocation;
            }

            public void setMobileLocation(String mobileLocation) {
                this.mobileLocation = mobileLocation;
            }

            public int getUserType() {
                return userType;
            }

            public void setUserType(int userType) {
                this.userType = userType;
            }
        }

        public static class MembersBean {
            private String vendorId;
            private String vendorName;
            private String realName;
            private String nickName;
            private String avatar;
            private String lifePhoto;
            private String signature;
            private String provinceId;
            private int workYear;
            private String goodAt;
            private long updateTime;
            private int bossId;

            public String getVendorId() {
                return vendorId;
            }

            public void setVendorId(String vendorId) {
                this.vendorId = vendorId;
            }

            public String getVendorName() {
                return vendorName;
            }

            public void setVendorName(String vendorName) {
                this.vendorName = vendorName;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getLifePhoto() {
                return lifePhoto;
            }

            public void setLifePhoto(String lifePhoto) {
                this.lifePhoto = lifePhoto;
            }

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public String getProvinceId() {
                return provinceId;
            }

            public void setProvinceId(String provinceId) {
                this.provinceId = provinceId;
            }

            public int getWorkYear() {
                return workYear;
            }

            public void setWorkYear(int workYear) {
                this.workYear = workYear;
            }

            public String getGoodAt() {
                return goodAt;
            }

            public void setGoodAt(String goodAt) {
                this.goodAt = goodAt;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }

            public int getBossId() {
                return bossId;
            }

            public void setBossId(int bossId) {
                this.bossId = bossId;
            }
        }

        public static class ProgressBean {
            private int progressId;
            private String progressName;
            private int progressStatus;
            private long createTime;

            public int getProgressId() {
                return progressId;
            }

            public void setProgressId(int progressId) {
                this.progressId = progressId;
            }

            public String getProgressName() {
                return progressName;
            }

            public void setProgressName(String progressName) {
                this.progressName = progressName;
            }

            public int getProgressStatus() {
                return progressStatus;
            }

            public void setProgressStatus(int progressStatus) {
                this.progressStatus = progressStatus;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }
        }
    }
}
