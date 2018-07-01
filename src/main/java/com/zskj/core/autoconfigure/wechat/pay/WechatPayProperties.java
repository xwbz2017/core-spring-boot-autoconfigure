package com.zskj.core.autoconfigure.wechat.pay;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 微信支付相关配置
 *
 * @author 花开
 * @date 2018/1/10
 */
@ConfigurationProperties(prefix = WechatPayProperties.PREFIX)
public class WechatPayProperties {

    static final String PREFIX = "core.wechat.pay";

    /** 是否注入微信支付配置 */
    private boolean enabled;

    /** 应用ID */
    private String appId;

    /** 接口校验KEY */
    private String apiKey;

    /** 商户ID */
    private String mchId;

    /** 回调地址的域名 */
    private String domain;

    /**
     * notify_url 接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。
     * trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。
     */
    private String notifyUrl;

    /** 证书路径，相对在classpath下
     *
     * @see <a href="https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=4_3">证书用法</a>
     */
    private String certPath;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getCertPath() {
        return certPath;
    }

    public void setCertPath(String certPath) {
        this.certPath = certPath;
    }
}
