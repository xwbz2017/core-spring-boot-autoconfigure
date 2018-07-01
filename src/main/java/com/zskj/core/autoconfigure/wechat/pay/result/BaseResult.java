package com.zskj.core.autoconfigure.wechat.pay.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 公共返回字段
 *
 * @author 花开
 * @date 2018/1/26
 */
public class BaseResult {

    /**
     * 返回状态码<br>
     * 必填<br>
     * SUCCESS/FAIL 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
     */
    @XStreamAlias("return_code")
    private String returnCode;

    /**
     * 返回信息<br>
     * 非必填<br>
     * 返回信息，如非空，为错误原因签名失败参数格式校验错误<br>
     */
    @XStreamAlias("return_msg")
    private String returnMsg;

    /**
     * 公众账号ID 必填 微信分配的公众账号ID
     */
    @XStreamAlias("appid")
    private String appId;

    /**
     * 商户号 必填 微信支付分配的商户号
     */
    @XStreamAlias("mch_id")
    private String mchId;

    /**
     * 随机字符串 必填 随机字符串，不长于32位
     */
    @XStreamAlias("nonce_str")
    private String nonceStr;

    /**
     * 签名 必填 签名，详见签名算法
     */
    @XStreamAlias("sign")
    private String sign;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

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
}
