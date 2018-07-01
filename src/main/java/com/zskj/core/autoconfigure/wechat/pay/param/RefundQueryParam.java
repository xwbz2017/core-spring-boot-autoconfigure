package com.zskj.core.autoconfigure.wechat.pay.param;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 退款查询请求参数
 *
 * @author 花开
 * @date 2018/1/30
 */
@XStreamAlias("xml")
public class RefundQueryParam extends BaseParam {

    /**
     * 四选一 微信生成的订单号，在支付通知中有返回
     */
    @XStreamAlias("transaction_id")
    private String transactionId;

    /**
     * 四选一 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     */
    @XStreamAlias("out_trade_no")
    private String outTradeNo;

    /**
     * 四选一
     * 商户退款单号
     * 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
     */
    @XStreamAlias("out_refund_no")
    private String outRefundNo;

    /**
     * 四选一
     * 微信退款单号
     * 微信生成的退款单号，在申请退款接口有返回
     */
    @XStreamAlias("refund_id")
    private String refundId;

    @XStreamAlias("offset")
    private Integer offset;

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

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
