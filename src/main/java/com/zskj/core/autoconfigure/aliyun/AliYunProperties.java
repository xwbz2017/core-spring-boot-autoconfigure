package com.zskj.core.autoconfigure.aliyun;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 阿里云配置文件
 *
 * @author 花开
 * @date 2017/12/12
 */
@ConfigurationProperties(prefix = AliYunProperties.PREFIX)
public class AliYunProperties {

    static final String PREFIX = "core.aliyun";

    /** 是否开启oss自动注入 */
    private boolean ossEnabled;

    private String ossEndPoint;

    private String ossBucketName;

    private String accessKeyId;

    private String accessKeySecret;

    /** 是否开启mns注入 */
    private boolean smsEnabled;

    /** 产品名称:云通信短信API产品,开发者无需替换 */
    private String smsProduct = "Dysmsapi";

    /** 产品域名,开发者无需替换 */
    private String smsDomain = "dysmsapi.aliyuncs.com";

    /**
     * 短信发送需要设置的默认连接超时时间
     */
    private String smsDefaultConnectTimeout = "5000";

    /**
     * 短信发送需要设置的默认获取数据超时时间
     */
    private String smsDefaultReadTimeout = "5000";

    /** 开启推送 */
    private boolean pushEnabled;

    /** 推送的appKey */
    private Long pushAppKey;

    /** MNS 服务的地址 */
    private String mnsAccountEndPoint;

    /** 消息服务开关 */
    private boolean mnsEnabled;

    public String getOssEndPoint() {
        return ossEndPoint;
    }

    public void setOssEndPoint(String ossEndPoint) {
        this.ossEndPoint = ossEndPoint;
    }

    public String getOssBucketName() {
        return ossBucketName;
    }

    public void setOssBucketName(String ossBucketName) {
        this.ossBucketName = ossBucketName;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public boolean isOssEnabled() {
        return ossEnabled;
    }

    public void setOssEnabled(boolean ossEnabled) {
        this.ossEnabled = ossEnabled;
    }

    public boolean isSmsEnabled() {
        return smsEnabled;
    }

    public void setSmsEnabled(boolean smsEnabled) {
        this.smsEnabled = smsEnabled;
    }

    public String getSmsProduct() {
        return smsProduct;
    }

    public void setSmsProduct(String smsProduct) {
        this.smsProduct = smsProduct;
    }

    public String getSmsDomain() {
        return smsDomain;
    }

    public void setSmsDomain(String smsDomain) {
        this.smsDomain = smsDomain;
    }

    public String getSmsDefaultConnectTimeout() {
        return smsDefaultConnectTimeout;
    }

    public void setSmsDefaultConnectTimeout(String smsDefaultConnectTimeout) {
        this.smsDefaultConnectTimeout = smsDefaultConnectTimeout;
    }

    public String getSmsDefaultReadTimeout() {
        return smsDefaultReadTimeout;
    }

    public void setSmsDefaultReadTimeout(String smsDefaultReadTimeout) {
        this.smsDefaultReadTimeout = smsDefaultReadTimeout;
    }

    public boolean isPushEnabled() {
        return pushEnabled;
    }

    public void setPushEnabled(boolean pushEnabled) {
        this.pushEnabled = pushEnabled;
    }

    public Long getPushAppKey() {
        return pushAppKey;
    }

    public void setPushAppKey(Long pushAppKey) {
        this.pushAppKey = pushAppKey;
    }

    public String getMnsAccountEndPoint() {
        return mnsAccountEndPoint;
    }

    public void setMnsAccountEndPoint(String mnsAccountEndPoint) {
        this.mnsAccountEndPoint = mnsAccountEndPoint;
    }

    public boolean getMnsEnabled() {
        return mnsEnabled;
    }

    public void setMnsEnabled(boolean mnsEnabled) {
        this.mnsEnabled = mnsEnabled;
    }
}
