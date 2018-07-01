package com.zskj.core.autoconfigure.aliyun.push.model;

import java.util.Date;

/**
 * android高级推送配置
 *
 * @author 花开
 * @date 2018/2/2
 */
public class AndroidAdvancedPushConfig {

    // 下述配置仅作用于Android通知任务

    /**
     * Android通知声音（保留参数，当前暂不起作用）
     * <p>
     * 否
     */
    private String androidMusic;

    /**
     * 点击通知后动作
     * <p>
     * 否
     */
    private String androidOpenType;

    /**
     * 通知的提醒方式
     * <p>
     * 否
     */
    private String androidNotifyType;

    /**
     * 设定通知打开的activity，仅当AndroidOpenType="Activity"有效，如：com.alibaba.cloudpushdemo.bizactivity
     * <p>
     * 否
     */
    private String androidActivity;

    /**
     * Android收到推送后打开对应的url,仅当AndroidOpenType="URL"有效
     * <p>
     * 否
     */
    private String androidOpenUrl;

    /**
     * Android自定义通知栏样式，取值：1-100
     * <p>
     * 否
     */
    private Integer androidNotificationBarType;

    /**
     * Android通知在通知栏展示时排列位置的优先级 -2 -1 0 1 2
     * <p>
     * 否
     */
    private Integer androidNotificationBarPriority;

    /**
     * 设定通知的扩展属性。(注意 : 该参数要以 json map 的格式传入,否则会解析出错)
     * <p>
     * 否
     */
    private String androidExtParameters;

    // 下述配置仅作用于Android辅助弹窗功能

    /**
     * 推送类型为消息时设备不在线，则这条推送会使用辅助弹窗功能。默认值为False，仅当PushType=MESSAGE时生效。
     * <p></p>
     * 否
     */
    private Boolean androidRemind;

    /**
     * 此处指定通知点击后跳转的Activity。注：原AndroidXiaoMiActivity参数已废弃，所有第三方辅助弹窗都由新参数统一支持。
     * <p></p>
     * 否
     */
    private String androidPopupActivity;
    /**
     * 辅助弹窗模式下Title内容,长度限制:<16字符（中英文都以一个字符计算）。注：原AndroidXiaoMiNotifyTitle参数已废弃，所有第三方辅助弹窗都由新参数统一支持。
     * <p></p>
     * 否
     */
    private String androidPopupTitle;

    /**
     * 辅助弹窗模式下Body内容,长度限制:<128字符（中英文都以一个字符计算）。注：原AndroidXiaoMiNotifyBody参数已废弃，所有第三方辅助弹窗都由新参数统一支持。
     * <p></p>
     * 否
     */
    private String androidPopupBody;

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

    public String getAndroidMusic() {
        return androidMusic;
    }

    public void setAndroidMusic(String androidMusic) {
        this.androidMusic = androidMusic;
    }

    public String getAndroidOpenType() {
        return androidOpenType;
    }

    public void setAndroidOpenType(String androidOpenType) {
        this.androidOpenType = androidOpenType;
    }

    public String getAndroidNotifyType() {
        return androidNotifyType;
    }

    public void setAndroidNotifyType(String androidNotifyType) {
        this.androidNotifyType = androidNotifyType;
    }

    public String getAndroidActivity() {
        return androidActivity;
    }

    public void setAndroidActivity(String androidActivity) {
        this.androidActivity = androidActivity;
    }

    public String getAndroidOpenUrl() {
        return androidOpenUrl;
    }

    public void setAndroidOpenUrl(String androidOpenUrl) {
        this.androidOpenUrl = androidOpenUrl;
    }

    public Integer getAndroidNotificationBarType() {
        return androidNotificationBarType;
    }

    public void setAndroidNotificationBarType(Integer androidNotificationBarType) {
        this.androidNotificationBarType = androidNotificationBarType;
    }

    public Integer getAndroidNotificationBarPriority() {
        return androidNotificationBarPriority;
    }

    public void setAndroidNotificationBarPriority(Integer androidNotificationBarPriority) {
        this.androidNotificationBarPriority = androidNotificationBarPriority;
    }

    public String getAndroidExtParameters() {
        return androidExtParameters;
    }

    public void setAndroidExtParameters(String androidExtParameters) {
        this.androidExtParameters = androidExtParameters;
    }

    public Boolean getAndroidRemind() {
        return androidRemind;
    }

    public void setAndroidRemind(Boolean androidRemind) {
        this.androidRemind = androidRemind;
    }

    public String getAndroidPopupActivity() {
        return androidPopupActivity;
    }

    public void setAndroidPopupActivity(String androidPopupActivity) {
        this.androidPopupActivity = androidPopupActivity;
    }

    public String getAndroidPopupTitle() {
        return androidPopupTitle;
    }

    public void setAndroidPopupTitle(String androidPopupTitle) {
        this.androidPopupTitle = androidPopupTitle;
    }

    public String getAndroidPopupBody() {
        return androidPopupBody;
    }

    public void setAndroidPopupBody(String androidPopupBody) {
        this.androidPopupBody = androidPopupBody;
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
