package com.zskj.core.autoconfigure.wechat.pay.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 统一下单返回结果
 *
 * @author 花开
 * @date 2018/1/26
 */
@XStreamAlias("xml")
public class UnifiedOrderResult extends BaseResult {

    /******** 以下字段在return_code为SUCCESS的时候有返回 *********/

    /**
     * 设备号<br>
     * 非必填<br>
     * 自定义参数，可以为请求支付的终端设备号等
     */
    @XStreamAlias("device_info")
    private String deviceInfo;

    /**
     * 业务结果<br>
     * 必填<br>
     * SUCCESS/FAIL
     */
    @XStreamAlias("result_code")
    private String resultCode;

    /**
     * 错误代码<br>
     * 非必填<br>
     * 详细参见下文错误列表
     */
    @XStreamAlias("err_code")
    private String errCode;

    /**
     * 错误代码描述<br>
     * 非必填<br>
     * 错误信息描述
     */
    @XStreamAlias("err_code_des")
    private String errCodeDes;

    /************ 以下字段在return_code 和result_code都为SUCCESS的时候有返回 ***************/

    /**
     * 交易类型<br>
     * 必填<br>
     * 交易类型，取值为：JSAPI，NATIVE，APP等，说明详见参数规定
     */
    @XStreamAlias("trade_type")
    private String tradeType;

    /**
     * 预支付交易会话标识<br>
     * 必填<br>
     * 微信生成的预支付会话标识，用于后续接口调用中使用，该值有效期为2小时
     */
    @XStreamAlias("prepay_id")
    private String prepayId;

    /**
     * 二维码链接<br>
     * 非必填<br>
     * trade_type为NATIVE时有返回，用于生成二维码，展示给用户进行扫码支付
     */
    @XStreamAlias("code_url")
    private String codeUrl;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrCodeDes() {
        return errCodeDes;
    }

    public void setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }
}
