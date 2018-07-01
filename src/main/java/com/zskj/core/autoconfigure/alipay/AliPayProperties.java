package com.zskj.core.autoconfigure.alipay;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 支付宝支付配置
 * <p>
 * 功能描述：支付宝支付的参数配置，参照网址：https://docs.open.alipay.com/204/105465/
 *
 * @author 绽曙科技-zqs & 花开
 * @date 2018年1月9日
 */
@ConfigurationProperties(prefix = AliPayProperties.PREFIX)
public class AliPayProperties {

    static final String PREFIX = "core.ali.pay";

    private boolean enabled;

    /**
     * 支付宝分配给开发者的应用ID
     */
    private String appId;

    /**
     * 应用私钥
     */
    private String appPrivateKey;

    /**
     * 支付宝公钥(SHA256withRsa) 切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
     */
    private String aliPayPublicKey;

    /**
     * 回调地址的域名
     */
    private String domain;

    /**
     * 回调地址
     */
    private String notifyUrl;

    /**
     * 网页支付成功以后跳转的地址
     */
    private String returnUrl;

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

    public String getAppPrivateKey() {
        return appPrivateKey;
    }

    public void setAppPrivateKey(String appPrivateKey) {
        this.appPrivateKey = appPrivateKey;
    }

    public String getAliPayPublicKey() {
        return aliPayPublicKey;
    }

    public void setAliPayPublicKey(String aliPayPublicKey) {
        this.aliPayPublicKey = aliPayPublicKey;
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

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
}
