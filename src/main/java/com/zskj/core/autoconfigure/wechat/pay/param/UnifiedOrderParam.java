package com.zskj.core.autoconfigure.wechat.pay.param;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 统一下单参数集合
 *
 * @author 花开
 * @date 2018/1/26
 * @see <a href="https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_1">统一下单</a>
 */
@XStreamAlias("xml")
public class UnifiedOrderParam extends BaseParam {

    /**
     * 设备号<br>
     * 非必填<br>
     * 自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
     */
    @XStreamAlias("device_info")
    private String deviceInfo;

    /**
     * 商品描述<br>
     * 必填<br>
     * 商品简单描述，该字段请按照规范传递，具体请见参数规定
     */
    @XStreamAlias("body")
    private String body;

    /**
     * 商品详情<br>
     * 非必填<br>
     * 商品详细描述，对于使用单品优惠的商户，改字段必须按照规范上传，详见“单品优惠参数说明”
     */
    @XStreamAlias("detail")
    private String detail;

    /**
     * 附加数据<br>
     * 非必填<br>
     * 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用。
     */
    @XStreamAlias("attach")
    private String attach;

    /**
     * 商户订单号<br>
     * 必填<br>
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。详见商户订单号
     */
    @XStreamAlias("out_trade_no")
    private String outTradeNo;

    /**
     * 标价币种<br>
     * 非必填<br>
     * 符合ISO 4217标准的三位字母代码，默认人民币：CNY，详细列表请参见货币类型
     */
    @XStreamAlias("fee_type")
    private String feeType;

    /**
     * 标价金额<br>
     * 必填<br>
     * 订单总金额，单位为分，详见支付金额
     */
    @XStreamAlias("total_fee")
    private Integer totalFee;

    /**
     * 终端IP<br>
     * 必填<br>
     * APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP
     */
    @XStreamAlias("spbill_create_ip")
    private String spbillCreateIp;

    /**
     * 交易起始时间<br>
     * 非必填<br>
     * 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
     */
    @XStreamAlias("time_start")
    private String timeStart;

    /**
     * 交易结束时间<br>
     * 非必填<br>
     * 订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。订单失效时间是针对订单号而言的，由于在请求支付的时候有一个必传参数prepay_id只有两小时的有效期，所以在重入时间超过2小时的时候需要重新请求下单接口获取新的prepay_id。其他详见时间规则
     * <font color="red">建议：最短失效时间间隔大于1分钟</font>
     */
    @XStreamAlias("time_expire")
    private String timeExpire;

    /**
     * 订单优惠标记<br>
     * 非必填<br>
     * 订单优惠标记，使用代金券或立减优惠功能时需要的参数，说明详见代金券或立减优惠
     */
    @XStreamAlias("goods_tag")
    private String goodsTag;

    /**
     * 通知地址<br>
     * 必填<br>
     * 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数
     */
    @XStreamAlias("notify_url")
    private String notifyUrl;

    /**
     * 交易类型<br>
     * 必填<br>
     * 取值如下：JSAPI，NATIVE，APP等，说明详见参数规定
     */
    @XStreamAlias("trade_type")
    private String tradeType;

    /**
     * 商品ID<br>
     * 非必填<br>
     * trade_type=NATIVE时（即扫码支付），此参数必传。此参数为二维码中包含的商品ID，商户自行定义
     */
    @XStreamAlias("product_id")
    private String productId;


    /**
     * 指定支付方式<br>
     * 非必填<br>
     * 上传此参数no_credit--可限制用户不能使用信用卡支付
     */
    @XStreamAlias("limit_pay")
    private String limitPay;

    /**
     * 用户标识<br>
     * 非必填<br>
     * trade_type=JSAPI时（即公众号支付），此参数必传，此参数为微信用户在商户对应appid下的唯一标识。openid如何获取，可参考【获取openid】。企业号请使用【企业号OAuth2.0接口】获取企业号内成员userid，再调用【企业号userid转openid接口】进行转换
     */
    @XStreamAlias("openid")
    private String openId;

    /**
     * 场景信息<br>
     * 非必填<br>
     * 该字段用于上报场景信息，目前支持上报实际门店信息。该字段为JSON对象数据，对象格式为{"store_info":{"id": "门店ID","name": "名称","area_code": "编码","address": "地址" }} ，字段详细说明请点击行前的+展开
     */
    @XStreamAlias("scene_info")
    private String sceneInfo;

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }

    public String getGoodsTag() {
        return goodsTag;
    }

    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getLimitPay() {
        return limitPay;
    }

    public void setLimitPay(String limitPay) {
        this.limitPay = limitPay;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSceneInfo() {
        return sceneInfo;
    }

    public void setSceneInfo(String sceneInfo) {
        this.sceneInfo = sceneInfo;
    }
}
