package com.zskj.core.autoconfigure.wechat.pay.param;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * 退款请求参数封装
 *
 * @author 花开
 */
@XStreamAlias("xml")
public class RefundParam extends BaseParam{

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

    /**
     * 必填 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
     */
    @XStreamAlias("out_refund_no")
    private String outRefundNo;

    /**
     * 必填 订单总金额，单位为分，只能为整数，详见支付金额
     */
    @XStreamAlias("total_fee")
    private Integer totalFee;

    /**
     * 必填 退款总金额，订单总金额，单位为分，只能为整数，详见支付金额
     */
    @XStreamAlias("refund_fee")
    private Integer refundFee;

    /**
     * 非必填 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
     */
    @XStreamAlias("refund_fee_type")
    private String refundFeeType;

    /**
     * 非必填 若商户传入，会在下发给用户的退款消息中体现退款原因
     */
    @XStreamAlias("refund_desc")
    private String refundDesc;

    /**
     * 非必填 仅针对老资金流商户使用
     * <p>
     * REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款（默认使用未结算资金退款）
     * <p>
     * REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款
     */
    @XStreamAlias("refund_account")
    private String refundAccount;

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

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public Integer getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(Integer refundFee) {
        this.refundFee = refundFee;
    }

    public String getRefundFeeType() {
        return refundFeeType;
    }

    public void setRefundFeeType(String refundFeeType) {
        this.refundFeeType = refundFeeType;
    }

    public String getRefundDesc() {
        return refundDesc;
    }

    public void setRefundDesc(String refundDesc) {
        this.refundDesc = refundDesc;
    }

    public String getRefundAccount() {
        return refundAccount;
    }

    public void setRefundAccount(String refundAccount) {
        this.refundAccount = refundAccount;
    }
}
