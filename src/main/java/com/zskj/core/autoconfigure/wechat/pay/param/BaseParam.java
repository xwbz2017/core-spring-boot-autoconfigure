package com.zskj.core.autoconfigure.wechat.pay.param;


import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 公共参数
 *
 * @author 花开
 * @see <a href="https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_1">微信支付api</a>
 */
public class BaseParam {

    /**
     * 公众账号ID<br>
     * 必填<br>
     * 微信分配的公众账号ID（企业号corpid即为此appId）
     */
    @XStreamAlias("appid")
    private String appId;

    /**
     * 商户号<br>
     * 必填<br>
     * 微信支付分配的商户号
     */
    @XStreamAlias("mch_id")
    private String mchId;

    /**
     * 随机字符串<br>
     * 必填<br>
     * 随机字符串，不长于32位。推荐随机数生成算法
     */
    @XStreamAlias("nonce_str")
    private String nonceStr;

    /**
     * 签名<br>
     * 必填<br>
     * 签名，详见签名生成算法
     */
    @XStreamAlias("sign")
    private String sign;

    /**
     * 签名类型<br>
     * 非必填<br>
     * 签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
     */
    @XStreamAlias("sign_type")
    private String signType;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }
}
