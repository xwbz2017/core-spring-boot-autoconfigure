package com.zskj.core.autoconfigure.wechat.pay.param;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 查询订单请求参数
 *
 * @author 花开
 * @date 2018/1/30
 */
@XStreamAlias("xml")
public class OrderQueryParam extends BaseParam{

    /**
     * 二选一 微信生成的订单号，在支付通知中有返回
     */
    @XStreamAlias("transaction_id")
    private String transactionId;

    /**
     * 二选一 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     */
    @XStreamAlias("out_trade_no")
    private String outTradeNo;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }
}
