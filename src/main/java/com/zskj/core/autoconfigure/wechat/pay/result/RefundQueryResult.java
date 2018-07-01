package com.zskj.core.autoconfigure.wechat.pay.result;

import java.util.ArrayList;
import java.util.List;

/**
 * 退款查询返回字段
 *
 * @author 花开
 * @date 2018/1/30
 */
public class RefundQueryResult extends BaseResult {

    /**
     * 业务结果 必填 SUCCESS/FAIL  SUCCESS退款申请接收成功，结果通过退款查询接口查询  FAIL 提交业务失败
     */
    private String resultCode;

    /**
     * 错误代码 非必填 列表详见错误码列表
     */
    private String errCode;

    /**
     * 错误代码描述 非必填 结果信息描述
     */
    private String errCodeDes;

    /**
     * 订单总退款次数
     * 必填
     * 订单总共已发生的部分退款次数，当请求参数传入offset后有返回
     */
    private Integer totalRefundCount;

    /**
     * 微信订单号
     * 必填
     */
    private String transactionId;

    /**
     * 商户订单号
     * 必填
     */
    private String outTradeNo;

    /**
     * 订单金额
     * 必填
     * 订单总金额，单位为分，只能为整数，详见支付金额
     */
    private Integer totalFee;

    /**
     * 应结订单金额
     * 非必填
     * 当订单使用了免充值型优惠券后返回该参数，应结订单金额=订单金额-免充值优惠券金额。
     */
    private Integer settlementTotalFee;

    /**
     * 货币种类
     * 非必填
     * 订单金额货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
     */
    private String feeType;

    /**
     * 现金支付金额
     * 必填
     * 现金支付金额，单位为分，只能为整数，详见支付金额
     */
    private Integer cashFee;

    /**
     * 退款笔数
     * 必填
     * 当前返回退款笔数
     */
    private Integer refundCount;

    /**
     * 退款总金额
     * 必填
     */
    private Integer refundFee;

//    private String out_refund_no_$n;

    /**
     * 商户退款单号
     * 必填
     */
    private List<String> outRefundNos = new ArrayList<>();

//    private String refund_id_$n;

    /**
     * 微信退款单号
     * 必填
     */
    private List<String> refundIds = new ArrayList<>();

//    private String 	refund_channel_$n;

    /**
     * 退款渠道
     * 非必填
     * ORIGINAL—原路退款
     * BALANCE—退回到余额
     * OTHER_BALANCE—原账户异常退到其他余额账户
     * OTHER_BANKCARD—原银行卡异常退到其他银行卡
     */
    private List<String> refundChannels = new ArrayList<>();

//    private Integer refund_fee_$n;

    /**
     * 申请退款金额
     * 必填
     * 退款总金额,单位为分,可以做部分退款
     */
    private List<Integer> refundFees = new ArrayList<>();

//    private Integer settlement_refund_fee_$n;

    /**
     * 退款金额
     * 非必填
     * 退款金额=申请退款金额-非充值代金券退款金额，退款金额<=申请退款金额
     */
    private List<Integer> settlementRefundFees = new ArrayList<>();

///    private String coupon_type_$n_$m;

//    private Integer coupon_refund_fee_$n;
    /**
     * 总代金券退款金额
     * 非必填
     * 代金券退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为现金，说明详见代金券或立减优惠
     */
    private List<Integer> couponRefundFees = new ArrayList<>();

//    private Integer coupon_refund_count_$n;

    /**
     * 退款代金券使用数量
     * 非必填
     * 退款代金券使用数量 ,$n为下标,从0开始编号
     */
    private List<Integer> couponRefundCounts = new ArrayList<>();

//    private String coupon_refund_id_$n_$m;

//    private Integer coupon_refund_fee_$n_$m;

//    private String refund_status_$n;

    /**
     * 退款状态
     * 必填
     * 退款状态：
     * SUCCESS—退款成功
     * REFUNDCLOSE—退款关闭。
     * PROCESSING—退款处理中
     * CHANGE—退款异常，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，可前往商户平台（pay.weixin.qq.com）-交易中心，手动处理此笔退款。$n为下标，从0开始编号。
     */
    private List<String> refundStatuses = new ArrayList<>();

//    private String refund_account_$n;

    /**
     * 退款资金来源
     * 非必填
     * REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款/基本账户
     * REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款
     * $n为下标，从0开始编号。
     */
    private List<String> refundAccounts = new ArrayList<>();

//    private String 	refund_recv_accout_$n;

    /**
     * 退款入账账户
     * 必填
     * 取当前退款单的退款入账方
     * 1）退回银行卡：
     * {银行名称}{卡类型}{卡尾号}
     * 2）退回支付用户零钱:
     * 支付用户零钱
     * 3）退还商户:
     * 商户基本账户
     * 商户结算银行账户
     * 4）退回支付用户零钱通:
     * 支付用户零钱通
     */
    private List<String> refundRecvAccounts = new ArrayList<>();

//    private String refund_success_time_$n;

    /**
     * 退款成功时间
     * 非必填
     * 退款成功时间，当退款状态为退款成功时有返回。$n为下标，从0开始编号。
     */
    private List<String> refundSuccessTimes = new ArrayList<>();

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

    public Integer getTotalRefundCount() {
        return totalRefundCount;
    }

    public void setTotalRefundCount(Integer totalRefundCount) {
        this.totalRefundCount = totalRefundCount;
    }

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

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public Integer getSettlementTotalFee() {
        return settlementTotalFee;
    }

    public void setSettlementTotalFee(Integer settlementTotalFee) {
        this.settlementTotalFee = settlementTotalFee;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public Integer getCashFee() {
        return cashFee;
    }

    public void setCashFee(Integer cashFee) {
        this.cashFee = cashFee;
    }

    public Integer getRefundCount() {
        return refundCount;
    }

    public void setRefundCount(Integer refundCount) {
        this.refundCount = refundCount;
    }

    public List<String> getOutRefundNos() {
        return outRefundNos;
    }

    public void setOutRefundNos(List<String> outRefundNos) {
        this.outRefundNos = outRefundNos;
    }

    public List<String> getRefundIds() {
        return refundIds;
    }

    public void setRefundIds(List<String> refundIds) {
        this.refundIds = refundIds;
    }

    public List<String> getRefundChannels() {
        return refundChannels;
    }

    public void setRefundChannels(List<String> refundChannels) {
        this.refundChannels = refundChannels;
    }

    public List<Integer> getRefundFees() {
        return refundFees;
    }

    public void setRefundFees(List<Integer> refundFees) {
        this.refundFees = refundFees;
    }

    public List<Integer> getSettlementRefundFees() {
        return settlementRefundFees;
    }

    public void setSettlementRefundFees(List<Integer> settlementRefundFees) {
        this.settlementRefundFees = settlementRefundFees;
    }

    public List<Integer> getCouponRefundFees() {
        return couponRefundFees;
    }

    public void setCouponRefundFees(List<Integer> couponRefundFees) {
        this.couponRefundFees = couponRefundFees;
    }

    public List<Integer> getCouponRefundCounts() {
        return couponRefundCounts;
    }

    public void setCouponRefundCounts(List<Integer> couponRefundCounts) {
        this.couponRefundCounts = couponRefundCounts;
    }

    public List<String> getRefundStatuses() {
        return refundStatuses;
    }

    public void setRefundStatuses(List<String> refundStatuses) {
        this.refundStatuses = refundStatuses;
    }

    public List<String> getRefundAccounts() {
        return refundAccounts;
    }

    public void setRefundAccounts(List<String> refundAccounts) {
        this.refundAccounts = refundAccounts;
    }

    public List<String> getRefundRecvAccounts() {
        return refundRecvAccounts;
    }

    public void setRefundRecvAccounts(List<String> refundRecvAccounts) {
        this.refundRecvAccounts = refundRecvAccounts;
    }

    public List<String> getRefundSuccessTimes() {
        return refundSuccessTimes;
    }

    public void setRefundSuccessTimes(List<String> refundSuccessTimes) {
        this.refundSuccessTimes = refundSuccessTimes;
    }

    public Integer getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(Integer refundFee) {
        this.refundFee = refundFee;
    }
}
