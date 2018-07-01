package com.zskj.core.autoconfigure.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.*;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * 支付宝支付api
 * 支付API: https://docs.open.alipay.com/api_1
 *
 * @author 花开
 * @date 2018/1/31
 */
public class AliPayCore {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(AliPayCore.class);

    /**
     * 正式环境请求地址
     */
    private static final String SERVER_URL = "https://openapi.alipay.com/gateway.do";

    /**
     * 参数配置
     */
    private AliPayProperties properties;

    private AlipayClient alipayClient;

    public AliPayCore(AliPayProperties properties) {
        this.properties = properties;
        alipayClient = new DefaultAlipayClient(SERVER_URL, properties.getAppId(),
                properties.getAppPrivateKey(), AlipayConstants.FORMAT_JSON, AlipayConstants.CHARSET_UTF8, properties.getAliPayPublicKey(), AlipayConstants.SIGN_TYPE_RSA2);
    }


    /**
     * App支付-外部商户App唤起快捷SDK创建订单并支付
     *
     * @param outTradeNo     是-64-商户网站唯一订单号
     * @param totalAmount    是-9-订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
     * @param subject        是-256-商品的标题/交易标题/订单标题/订单关键字等。
     * @param body           否 -128-对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。
     * @param passBackParams 否-512-公用回传参数，如果请求时传递了该参数，则返回给商户时会回传该参数。支付宝会在异步通知时将该参数原样返回。本参数必须进行UrlEncode之后才可以发送给支付宝
     * @return AlipayTradeAppPayResponse
     * @throws AlipayApiException
     */
    public AlipayTradeAppPayResponse appPay(String outTradeNo, String totalAmount, String subject, String body, String passBackParams) throws AlipayApiException {
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        // 设置Model参数都不需要判空，在com.alipay.api.internal.util.json.JSONWriter 198行左右已经对value判空，空value不会序列化
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setOutTradeNo(outTradeNo);
        model.setTotalAmount(totalAmount);
        model.setSubject(subject);
        model.setBody(body);
        model.setPassbackParams(passBackParams);
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl(properties.getDomain() + properties.getNotifyUrl());
        return alipayClient.sdkExecute(request);
    }

    /**
     * 统一收单交易退款查询
     * 文档：https://docs.open.alipay.com/api_1/alipay.trade.fastpay.refund.query
     *
     * @param outTradeNo   订单支付时传入的商户订单号,和支付宝交易号不能同时为空。 trade_no,out_trade_no如果同时存在优先取trade_no
     * @param tradeNo      支付宝交易号，和商户订单号不能同时为空
     * @param outRequestNo 请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的外部交易号
     * @return AlipayTradeFastpayRefundQueryResponse
     * @throws AlipayApiException
     */
    public AlipayTradeFastpayRefundQueryResponse refundQuery(@Nullable String outTradeNo, @Nullable String tradeNo,
                                                             @Nonnull String outRequestNo) throws AlipayApiException {
        AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
        AlipayTradeFastpayRefundQueryModel model = new AlipayTradeFastpayRefundQueryModel();
        model.setTradeNo(tradeNo);
        model.setOutTradeNo(outTradeNo);
        model.setOutRequestNo(outRequestNo);
        request.setBizModel(model);
        return alipayClient.execute(request);
    }

    /**
     * 统一收单交易结算接口
     * 文档：https://docs.open.alipay.com/api_1/alipay.trade.order.settle/
     *
     * @param outRequestNo                  结算请求流水号 开发者自行生成并保证唯一性
     * @param tradeNo                       支付宝订单号
     * @param openApiRoyaltyDetailInfoPojos 分账明细信息
     * @return AlipayTradeOrderSettleResponse
     * @throws AlipayApiException
     */
    public AlipayTradeOrderSettleResponse orderSettle(@Nonnull String outRequestNo, @Nonnull String tradeNo, @Nonnull List<OpenApiRoyaltyDetailInfoPojo> openApiRoyaltyDetailInfoPojos) throws AlipayApiException {
        AlipayTradeOrderSettleRequest request = new AlipayTradeOrderSettleRequest();
        AlipayTradeOrderSettleModel model = new AlipayTradeOrderSettleModel();
        model.setOutRequestNo(outRequestNo);
        model.setTradeNo(tradeNo);
        model.setRoyaltyParameters(openApiRoyaltyDetailInfoPojos);
        return alipayClient.execute(request);
    }

    //

    /**
     * 统一收单交易关闭接口
     * 文档：https://docs.open.alipay.com/api_1/alipay.trade.close/
     *
     * @param outTradeNo 订单支付时传入的商户订单号,和支付宝交易号不能同时为空。
     * @param tradeNo    该交易在支付宝系统中的交易流水号。最短 16 位，最长 64 位。和out_trade_no不能同时为空，如果同时传了 out_trade_no和 trade_no，则以 trade_no为准。
     * @return AlipayTradeCloseResponse
     * @throws AlipayApiException
     */
    public AlipayTradeCloseResponse close(String outTradeNo, String tradeNo) throws AlipayApiException {
        AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
        AlipayTradeCloseModel model = new AlipayTradeCloseModel();
        model.setTradeNo(tradeNo);
        model.setOutTradeNo(outTradeNo);
        return alipayClient.execute(request);
    }

    /**
     * 统一收单交易撤销接口
     * 文档：https://docs.open.alipay.com/api_1/alipay.trade.cancel/
     *
     * @param outTradeNo 原支付请求的商户订单号,和支付宝交易号不能同时为空
     * @param tradeNo    支付宝交易号，和商户订单号不能同时为空
     * @return AlipayTradeCancelResponse
     * @throws AlipayApiException
     */
    public AlipayTradeCancelResponse cancel(String outTradeNo, String tradeNo) throws AlipayApiException {
        AlipayTradeCancelRequest request = new AlipayTradeCancelRequest();
        AlipayTradeCancelModel model = new AlipayTradeCancelModel();
        model.setTradeNo(tradeNo);
        model.setOutTradeNo(outTradeNo);
        return alipayClient.execute(request);
    }

    /**
     * 统一收单交易退款接口
     * 文档：https://docs.open.alipay.com/api_1/alipay.trade.refund
     *
     * @param outTradeNo   订单支付时传入的商户订单号,不能和 trade_no同时为空。
     * @param tradeNo      支付宝交易号，和商户订单号不能同时为空
     * @param refundAmount 需要退款的金额，该金额不能大于订单金额,单位为元，支持两位小数
     * @param refundReason 退款的原因说明
     * @param outRequestNo 标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传。
     * @return AlipayTradeRefundResponse
     * @throws AlipayApiException
     */
    public AlipayTradeRefundResponse refund(@Nullable String outTradeNo, @Nullable String tradeNo, @Nonnull String refundAmount, @Nullable String refundReason, @Nullable String outRequestNo) throws AlipayApiException {
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        AlipayTradeRefundModel model = new AlipayTradeRefundModel();
        model.setOutTradeNo(outTradeNo);
        model.setTradeNo(tradeNo);
        model.setRefundAmount(refundAmount);
        model.setRefundReason(refundReason);
        model.setOutRequestNo(outRequestNo);
        request.setBizModel(model);
        return alipayClient.execute(request);
    }

    /**
     * 统一收单线下交易预创建
     * 文档：https://docs.open.alipay.com/api_1/alipay.trade.precreate/
     *
     * @param outTradeNo  商户订单号,64个字符以内、只能包含字母、数字、下划线；需保证在商户端不重复
     * @param totalAmount 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000] 如果同时传入了【打折金额】，【不可打折金额】，【订单总金额】三者，则必须满足如下条件：【订单总金额】=【打折金额】+【不可打折金额】
     * @param subject     订单标题
     * @param body        对交易或商品的描述
     * @return AlipayTradePrecreateResponse
     * @throws AlipayApiException
     */
    public AlipayTradePrecreateResponse preCreate(@Nonnull String outTradeNo, @Nonnull String totalAmount, @Nonnull String subject, @Nullable String body) throws AlipayApiException {
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
        model.setOutTradeNo(outTradeNo);
        model.setTotalAmount(totalAmount);
        model.setSubject(subject);
        model.setBody(body);
        return alipayClient.execute(request);
    }

    /**
     * 统一收单交易创建接口
     * 文档：https://docs.open.alipay.com/api_1/alipay.trade.create/
     *
     * @param outTradeNo  商户订单号,64个字符以内、只能包含字母、数字、下划线；需保证在商户端不重复
     * @param totalAmount 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000] 如果同时传入了【打折金额】，【不可打折金额】，【订单总金额】三者，则必须满足如下条件：【订单总金额】=【打折金额】+【不可打折金额】
     * @param subject     订单标题
     * @param body        对交易或商品的描述
     * @return AlipayTradePrecreateResponse
     * @throws AlipayApiException
     */
    public AlipayTradeCreateResponse create(@Nonnull String outTradeNo, @Nonnull String totalAmount, @Nonnull String subject, @Nullable String body) throws AlipayApiException {
        AlipayTradeCreateRequest request = new AlipayTradeCreateRequest();
        AlipayTradeCreateModel model = new AlipayTradeCreateModel();
        model.setOutTradeNo(outTradeNo);
        model.setTotalAmount(totalAmount);
        model.setSubject(subject);
        model.setBody(body);
        return alipayClient.execute(request);
    }

    /**
     * 统一收单交易支付接口
     * 文档：https://docs.open.alipay.com/api_1/alipay.trade.pay/
     *
     * @param outTradeNo 商户订单号,64个字符以内、只能包含字母、数字、下划线；需保证在商户端不重复
     * @param scene      支付场景
     *                   条码支付，取值：bar_code
     *                   声波支付，取值：wave_code
     * @param authCode   支付授权码，25~30开头的长度为16~24位的数字，实际字符串长度以开发者获取的付款码长度为准
     * @param subject    订单标题
     * @param body       对交易或商品的描述
     * @return AlipayTradePrecreateResponse
     * @throws AlipayApiException
     */
    public AlipayTradePayResponse pay(@Nonnull String outTradeNo, @Nonnull String scene, @Nonnull String authCode, @Nonnull String subject, @Nullable String body) throws AlipayApiException {
        AlipayTradePayRequest request = new AlipayTradePayRequest();
        AlipayTradePayModel model = new AlipayTradePayModel();
        model.setOutTradeNo(outTradeNo);
        model.setScene(scene);
        model.setAuthCode(authCode);
        model.setSubject(subject);
        model.setBody(body);
        return alipayClient.execute(request);
    }

    /**
     * 统一收单线下交易查询
     * 文档：https://docs.open.alipay.com/api_1/alipay.trade.query/
     *
     * @param outTradeNo 订单支付时传入的商户订单号,和支付宝交易号不能同时为空。 trade_no,out_trade_no如果同时存在优先取trade_no
     * @param tradeNo    支付宝交易号，和商户订单号不能同时为空
     * @return AlipayTradeQueryResponse
     * @throws AlipayApiException
     */
    public AlipayTradeQueryResponse query(@Nullable String outTradeNo, @Nullable String tradeNo) throws AlipayApiException {
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        AlipayTradeQueryModel model = new AlipayTradeQueryModel();
        model.setTradeNo(tradeNo);
        model.setOutTradeNo(outTradeNo);
        request.setBizModel(model);
        return alipayClient.execute(request);
    }
}
