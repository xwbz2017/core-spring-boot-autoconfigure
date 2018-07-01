package com.zskj.core.autoconfigure.aliyun.push.model;

import java.util.Date;

/**
 * iOS高级推送配置
 *
 * @author 花开
 * @date 2018/2/2
 */
public class IOSAdvancedPushConfig {

    private String iOSMusic;

    private Integer iOSBadge;

    private Boolean iOSBadgeAutoIncrement;

    private Boolean iOSSilentNotification;

    private String iOSSubtitle;

    private String iOSNotificationCategory;

    private Boolean iOSMutableContent;

    private String iOSExtParameters;

    private String iOSApnsEnv;

    private Boolean iOSRemind;

    private String iOSRemindBody;

    // 推送控制(push control)

    /**
     * 用于定时发送。不设置缺省是立即发送。
     * <p></p>
     * 否
     */
    private Date pushDate;

    /**
     * 离线消息/通知是否保存。若保存，在推送时候用户不在线，在过期时间（ExpireTime）内用户上线时会被再次发送。StoreOffline默认设置为false，ExpireTime默认为72小时。（iOS通知走Apns链路，不受StoreOffline影响）
     * <p></p>
     * 否
     */
    private Boolean storeOffline;

    /**
     * 离线消息/通知的过期时间，和StoreOffline配合使用，过期则不会再被发送，最长保存72小时。默认为72小时。过期时间不能小于当前时间或者定时发送时间加上3秒（ExpireTime > PushTime + 3秒），3秒是为了冗余网络和系统延迟造成的误差。
     * <p></p>
     * 否
     */
    private Date expireDate;

    public String getiOSMusic() {
        return iOSMusic;
    }

    public void setiOSMusic(String iOSMusic) {
        this.iOSMusic = iOSMusic;
    }

    public Integer getiOSBadge() {
        return iOSBadge;
    }

    public void setiOSBadge(Integer iOSBadge) {
        this.iOSBadge = iOSBadge;
    }

    public Boolean getiOSBadgeAutoIncrement() {
        return iOSBadgeAutoIncrement;
    }

    public void setiOSBadgeAutoIncrement(Boolean iOSBadgeAutoIncrement) {
        this.iOSBadgeAutoIncrement = iOSBadgeAutoIncrement;
    }

    public Boolean getiOSSilentNotification() {
        return iOSSilentNotification;
    }

    public void setiOSSilentNotification(Boolean iOSSilentNotification) {
        this.iOSSilentNotification = iOSSilentNotification;
    }

    public String getiOSSubtitle() {
        return iOSSubtitle;
    }

    public void setiOSSubtitle(String iOSSubtitle) {
        this.iOSSubtitle = iOSSubtitle;
    }

    public String getiOSNotificationCategory() {
        return iOSNotificationCategory;
    }

    public void setiOSNotificationCategory(String iOSNotificationCategory) {
        this.iOSNotificationCategory = iOSNotificationCategory;
    }

    public Boolean getiOSMutableContent() {
        return iOSMutableContent;
    }

    public void setiOSMutableContent(Boolean iOSMutableContent) {
        this.iOSMutableContent = iOSMutableContent;
    }

    public String getiOSExtParameters() {
        return iOSExtParameters;
    }

    public void setiOSExtParameters(String iOSExtParameters) {
        this.iOSExtParameters = iOSExtParameters;
    }

    public String getiOSApnsEnv() {
        return iOSApnsEnv;
    }

    public void setiOSApnsEnv(String iOSApnsEnv) {
        this.iOSApnsEnv = iOSApnsEnv;
    }

    public Boolean getiOSRemind() {
        return iOSRemind;
    }

    public void setiOSRemind(Boolean iOSRemind) {
        this.iOSRemind = iOSRemind;
    }

    public String getiOSRemindBody() {
        return iOSRemindBody;
    }

    public void setiOSRemindBody(String iOSRemindBody) {
        this.iOSRemindBody = iOSRemindBody;
    }

    public Date getPushDate() {
        return pushDate;
    }

    public void setPushDate(Date pushDate) {
        this.pushDate = pushDate;
    }

    public Boolean getStoreOffline() {
        return storeOffline;
    }

    public void setStoreOffline(Boolean storeOffline) {
        this.storeOffline = storeOffline;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
}
