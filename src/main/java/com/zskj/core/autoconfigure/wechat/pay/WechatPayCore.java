package com.zskj.core.autoconfigure.wechat.pay;

import ch.qos.logback.core.util.CloseUtil;
import com.zskj.core.autoconfigure.http.HttpClientCore;
import com.zskj.core.autoconfigure.wechat.pay.enums.TradeType;
import com.zskj.core.autoconfigure.wechat.pay.param.*;
import com.zskj.core.autoconfigure.wechat.pay.result.*;
import com.zskj.core.autoconfigure.wechat.pay.utils.SignatureUtil;
import com.zskj.core.util.XmlUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.net.ssl.SSLContext;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Copyright © 2017 zskj Info. Tech Ltd. All rights reserved.
 * <p>
 * 功能描述：微支付统一下单的工具包
 * <P>统一下单接口：https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_1</P>
 *
 * @author 绽曙科技-zqs
 * @date: 2017年12月29日
 */
public class WechatPayCore {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(WechatPayCore.class);

    /**
     * 统一下单
     */
    private static final String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    /**
     * 查询订单
     */
    private static final String ORDER_QUERY_URL = "https://api.mch.weixin.qq.com/pay/orderquery";

    /**
     * 关闭订单
     */
    private static final String CLOSE_ORDER_URL = "https://api.mch.weixin.qq.com/pay/closeorder";

    /**
     * 申请退款
     */
    private static final String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";

    /**
     * 查询退款
     */
    private static final String REFUND_QUERY_URL = "https://api.mch.weixin.qq.com/pay/refundquery";

    /**
     * 参数配置
     */
    private WechatPayProperties wechatPayProperties;
    /**
     * 封装Http请求
     */
    private HttpClientCore httpClientCore;

    public WechatPayCore(WechatPayProperties wechatPayProperties, HttpClientCore httpClientCore) {
        this.wechatPayProperties = wechatPayProperties;
        this.httpClientCore = httpClientCore;
    }

    /**
     * 关闭订单
     * 以下情况需要调用关单接口：商户订单支付失败需要生成新单号重新发起支付，要对原订单号调用关单，避免重复支付；系统下单后，用户支付超时，系统退出不再受理，避免用户继续，请调用关单接口。
     * 注意：订单生成后不能马上调用关单接口，最短调用时间间隔为5分钟。
     *
     * @param outTradeNo 商户订单号
     * @return 关闭订单结果
     */
    public CloseOrderResult closeOrder(@Nonnull String outTradeNo) {
        String nonceStr = createNonceStr();
        Map<String, String> params = new HashMap<>(5);
        params.put("appid", wechatPayProperties.getAppId());
        params.put("mch_id", wechatPayProperties.getMchId());
        params.put("nonce_str", nonceStr);
        params.put("out_trade_no", outTradeNo);
        String sign = SignatureUtil.generateSign(params, wechatPayProperties.getApiKey());
        CloseOrderParam closeOrderParam = new CloseOrderParam();
        closeOrderParam.setAppId(wechatPayProperties.getAppId());
        closeOrderParam.setMchId(wechatPayProperties.getMchId());
        closeOrderParam.setOutTradeNo(outTradeNo);
        closeOrderParam.setNonceStr(nonceStr);
        closeOrderParam.setSign(sign);
        if (logger.isDebugEnabled()) {
            logger.debug(XmlUtils.toXml(closeOrderParam));
        }
        String strXml = httpClientCore.doPostJson(CLOSE_ORDER_URL, null, XmlUtils.toXml(closeOrderParam));
        if (logger.isDebugEnabled()) {
            logger.debug(strXml);
        }
        return XmlUtils.toObject(strXml, CloseOrderResult.class);
    }

    /**
     * 订单查询
     *
     * @param transactionId 微信订单号 二选一
     * @param outTradeNo    商户订单号 二选一
     * @return 订单查询结果
     */
    public OrderQueryResult orderQuery(@Nullable String transactionId, @Nullable String outTradeNo) {
        String nonceStr = createNonceStr();
        Map<String, String> params = new HashMap<>(5);
        params.put("appid", wechatPayProperties.getAppId());
        params.put("mch_id", wechatPayProperties.getMchId());
        params.put("nonce_str", nonceStr);
        if (StringUtils.isNotBlank(transactionId)) {
            params.put("transaction_id", transactionId);
        }
        if (StringUtils.isNotBlank(outTradeNo)) {
            params.put("out_trade_no", outTradeNo);
        }
        String sign = SignatureUtil.generateSign(params, wechatPayProperties.getApiKey());
        OrderQueryParam orderQueryParam = new OrderQueryParam();
        orderQueryParam.setAppId(wechatPayProperties.getAppId());
        orderQueryParam.setMchId(wechatPayProperties.getMchId());
        orderQueryParam.setTransactionId(transactionId);
        orderQueryParam.setOutTradeNo(outTradeNo);
        orderQueryParam.setNonceStr(nonceStr);
        orderQueryParam.setSign(sign);

        if (logger.isDebugEnabled()) {
            logger.debug(XmlUtils.toXml(orderQueryParam));
        }

        String strXml = httpClientCore.doPostJson(ORDER_QUERY_URL, null, XmlUtils.toXml(orderQueryParam));
        if (logger.isDebugEnabled()) {
            logger.debug(strXml);
        }
        return XmlUtils.toObject(strXml, OrderQueryResult.class);
    }

    /**
     * 退款查询
     *
     * @param transactionId 微信订单号 四选一
     * @param outTradeNo    商户订单号 四选一
     * @param outRefundNo   商户退款单号 四选一
     * @param refundId      微信退款单号 四选一
     * @param offset        偏移量，当部分退款次数超过10次时可使用，表示返回的查询结果从这个偏移量开始取记录
     * @return 订单查询结果
     */
    public RefundQueryResult refundQuery(@Nullable String transactionId, @Nullable String outTradeNo, @Nullable String outRefundNo, @Nullable String refundId, @Nullable Integer offset) {
        String nonceStr = createNonceStr();
        Map<String, String> params = new HashMap<>(8);
        params.put("appid", wechatPayProperties.getAppId());
        params.put("mch_id", wechatPayProperties.getMchId());
        params.put("nonce_str", nonceStr);
        if (StringUtils.isNotBlank(transactionId)) {
            params.put("transaction_id", transactionId);
        }
        if (StringUtils.isNotBlank(outTradeNo)) {
            params.put("out_trade_no", outTradeNo);
        }
        if (StringUtils.isNotBlank(outRefundNo)) {
            params.put("out_refund_no", outRefundNo);
        }
        if (StringUtils.isNotBlank(refundId)) {
            params.put("refund_id", refundId);
        }
        if (offset != null) {
            params.put("offset", offset + "");
        }
        String sign = SignatureUtil.generateSign(params, wechatPayProperties.getApiKey());

        RefundQueryParam refundQueryParam = new RefundQueryParam();
        refundQueryParam.setAppId(wechatPayProperties.getAppId());
        refundQueryParam.setMchId(wechatPayProperties.getMchId());
        refundQueryParam.setTransactionId(transactionId);
        refundQueryParam.setOutTradeNo(outTradeNo);
        refundQueryParam.setNonceStr(nonceStr);
        refundQueryParam.setOutRefundNo(outRefundNo);
        refundQueryParam.setRefundId(refundId);
        refundQueryParam.setOffset(offset);
        refundQueryParam.setSign(sign);

        if (logger.isDebugEnabled()) {
            logger.debug(XmlUtils.toXml(refundQueryParam));
        }
        String strXml = httpClientCore.doPostJson(REFUND_QUERY_URL, null, XmlUtils.toXml(refundQueryParam));
        if (logger.isDebugEnabled()) {
            logger.debug(strXml);
        }
        return toRefundQueryResult(strXml);
    }

    /**
     * xml手动转换成实体
     *
     * @param xml xml
     * @return 退款查询实体
     */
    @SuppressWarnings("Unchecked")
    private RefundQueryResult toRefundQueryResult(String xml) {
        try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new ByteArrayInputStream(xml.getBytes("UTF-8")));
            Element root = document.getRootElement();
            List<Element> list = root.elements();
            RefundQueryResult refundQueryResult = new RefundQueryResult();

            for (Element e : list) {
                String name = e.getName();
                String text = e.getText();
                if (StringUtils.isBlank(name) || StringUtils.isBlank(text)) {
                    continue;
                }
                boolean match = true;
                switch (name) {
                    case "return_code":
                        refundQueryResult.setReturnCode(text);
                        break;
                    case "return_msg":
                        refundQueryResult.setReturnMsg(text);
                        break;
                    case "appid":
                        refundQueryResult.setAppId(text);
                        break;
                    case "mch_id":
                        refundQueryResult.setMchId(text);
                        break;
                    case "nonce_str":
                        refundQueryResult.setNonceStr(text);
                        break;
                    case "sign":
                        refundQueryResult.setSign(text);
                        break;
                    case "result_code":
                        refundQueryResult.setResultCode(text);
                        break;
                    case "err_code":
                        refundQueryResult.setErrCode(text);
                        break;
                    case "err_code_des":
                        refundQueryResult.setErrCodeDes(text);
                        break;
                    case "total_refund_count":
                        refundQueryResult.setTotalRefundCount(new Integer(text));
                        break;
                    case "transaction_id":
                        refundQueryResult.setTransactionId(text);
                        break;
                    case "out_trade_no":
                        refundQueryResult.setOutTradeNo(text);
                        break;
                    case "total_fee":
                        refundQueryResult.setTotalFee(new Integer(text));
                        break;
                    case "settlement_total_fee":
                        refundQueryResult.setSettlementTotalFee(new Integer(text));
                        break;
                    case "fee_type":
                        refundQueryResult.setFeeType(text);
                        break;
                    case "cash_fee":
                        refundQueryResult.setCashFee(new Integer(text));
                        break;
                    case "refund_count":
                        refundQueryResult.setRefundCount(new Integer(text));
                        break;
                    case "refund_fee":
                        refundQueryResult.setRefundFee(new Integer(text));
                        break;
                    default:
                        match = false;
                        break;
                }
                if (!match) {
                    if (name.startsWith("out_refund_no_")) {
                        String index = name.split("out_refund_no_")[1];
                        refundQueryResult.getOutRefundNos().add(Integer.valueOf(index), text);
                    } else if (name.startsWith("refund_id_")) {
                        String index = name.split("refund_id_")[1];
                        refundQueryResult.getRefundIds().add(Integer.valueOf(index), text);
                    } else if (name.startsWith("refund_channel_")) {
                        String index = name.split("refund_channel_")[1];
                        refundQueryResult.getRefundChannels().add(Integer.valueOf(index), text);
                    } else if (name.startsWith("refund_fee_")) {
                        String index = name.split("refund_fee_")[1];
                        refundQueryResult.getRefundFees().add(Integer.valueOf(index), Integer.valueOf(text));
                    } else if (name.startsWith("settlement_refund_fee_")) {
                        String index = name.split("settlement_refund_fee_")[1];
                        refundQueryResult.getSettlementRefundFees().add(Integer.valueOf(index), Integer.valueOf(text));
                    } else if (name.startsWith("coupon_refund_fee_")) {
                        String index = name.split("coupon_refund_fee_")[1];
                        refundQueryResult.getCouponRefundFees().add(Integer.valueOf(index), Integer.valueOf(text));
                    } else if (name.startsWith("coupon_refund_count_")) {
                        String index = name.split("coupon_refund_count_")[1];
                        refundQueryResult.getCouponRefundCounts().add(Integer.valueOf(index), Integer.valueOf(text));
                    } else if (name.startsWith("refund_status_")) {
                        String index = name.split("refund_status_")[1];
                        refundQueryResult.getRefundStatuses().add(Integer.valueOf(index), text);
                    } else if (name.startsWith("refund_account_")) {
                        String index = name.split("refund_account_")[1];
                        refundQueryResult.getRefundAccounts().add(Integer.valueOf(index), text);
                    } else if (name.startsWith("refund_recv_accout_")) {
                        String index = name.split("refund_recv_accout_")[1];
                        refundQueryResult.getRefundRecvAccounts().add(Integer.valueOf(index), text);
                    } else if (name.startsWith("refund_success_time_")) {
                        String index = name.split("refund_success_time_")[1];
                        refundQueryResult.getRefundSuccessTimes().add(Integer.valueOf(index), text);
                    }
                }
            }
            return refundQueryResult;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * JSAPI 微信网页支付的预支付订单
     *
     * @param outTradeNo     商户系统内部的订单号,32个字符内、可包含字母, 其他说明见商户订单号
     * @param totalFee       订单总金额，单位为分，详见支付金额
     * @param body           商品或支付单简要描述
     * @param spbillCreateIp 网页支付提交用户端ip
     * @param openId         trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。
     * @return 统一下单微信返回结果
     */
    public UnifiedOrderResult jsapiUnifiedOrder(String outTradeNo, Integer totalFee, String body, String spbillCreateIp,
                                                String openId) {
        String nonceStr = createNonceStr();
        String tradeType = TradeType.JSAPI.name();
        String notifyUrl = wechatPayProperties.getDomain() + wechatPayProperties.getNotifyUrl();
        Map<String, String> params = new HashMap<>(10);
        params.put("appid", wechatPayProperties.getAppId());
        params.put("body", body);
        params.put("mch_id", wechatPayProperties.getMchId());
        params.put("nonce_str", nonceStr);
        params.put("notify_url", notifyUrl);
        params.put("openid", openId);
        params.put("out_trade_no", outTradeNo);
        params.put("spbill_create_ip", spbillCreateIp);
        params.put("total_fee", totalFee + "");
        params.put("trade_type", tradeType);

        String sign = SignatureUtil.generateSign(params, wechatPayProperties.getApiKey());

        UnifiedOrderParam unifiedOrderParam = new UnifiedOrderParam();
        unifiedOrderParam.setAppId(wechatPayProperties.getAppId());
        unifiedOrderParam.setMchId(wechatPayProperties.getMchId());
        unifiedOrderParam.setNonceStr(nonceStr);
        unifiedOrderParam.setNotifyUrl(notifyUrl);
        unifiedOrderParam.setBody(body);
        unifiedOrderParam.setOutTradeNo(outTradeNo);
        unifiedOrderParam.setTotalFee(totalFee);
        unifiedOrderParam.setSpbillCreateIp(spbillCreateIp);
        unifiedOrderParam.setTradeType(tradeType);
        unifiedOrderParam.setOpenId(openId);
        unifiedOrderParam.setSign(sign);

        if (logger.isDebugEnabled()) {
            logger.debug(XmlUtils.toXml(unifiedOrderParam));
        }
        String strXml = httpClientCore.doPostJson(UNIFIED_ORDER_URL, null, XmlUtils.toXml(unifiedOrderParam));
        if (logger.isDebugEnabled()) {
            logger.debug(strXml);
        }
        return XmlUtils.toObject(strXml, UnifiedOrderResult.class);
    }

    /**
     * 原生扫码支付统一下单
     *
     * @param outTradeNo     商户系统内部的订单号,32个字符内、可包含字母, 其他说明见商户订单号
     * @param productId      trade_type=NATIVE时（即扫码支付），此参数必传。此参数为二维码中包含的商品ID，
     * @param totalFee       订单总金额，单位为分，详见支付金额
     * @param body           商品或支付单简要描述
     * @param spbillCreateIp 网页支付提交用户端ip
     * @return 统一下单微信返回结果
     */
    public UnifiedOrderResult nativeUnifiedOrder(String outTradeNo, String productId, Integer totalFee, String body,
                                                 String spbillCreateIp) {
        String nonceStr = createNonceStr();
        String tradeType = TradeType.NATIVE.name();
        String notifyUrl = wechatPayProperties.getDomain() + wechatPayProperties.getNotifyUrl();
        Map<String, String> params = new HashMap<>(10);
        params.put("appid", wechatPayProperties.getAppId());
        params.put("mch_id", wechatPayProperties.getMchId());
        params.put("nonce_str", nonceStr);
        params.put("body", body);
        params.put("out_trade_no", outTradeNo);
        params.put("total_fee", totalFee + "");
        params.put("spbill_create_ip", spbillCreateIp);
        params.put("notify_url", notifyUrl);
        params.put("trade_type", tradeType);
        params.put("product_id", productId);
        String sign = SignatureUtil.generateSign(params, wechatPayProperties.getApiKey());

        UnifiedOrderParam unifiedOrderParam = new UnifiedOrderParam();
        unifiedOrderParam.setAppId(wechatPayProperties.getAppId());
        unifiedOrderParam.setMchId(wechatPayProperties.getMchId());
        unifiedOrderParam.setNonceStr(nonceStr);
        unifiedOrderParam.setNotifyUrl(notifyUrl);
        unifiedOrderParam.setBody(body);
        unifiedOrderParam.setOutTradeNo(outTradeNo);
        unifiedOrderParam.setTotalFee(totalFee);
        unifiedOrderParam.setSpbillCreateIp(spbillCreateIp);
        unifiedOrderParam.setTradeType(tradeType);
        unifiedOrderParam.setProductId(productId);
        unifiedOrderParam.setSign(sign);

        if (logger.isDebugEnabled()) {
            logger.debug(XmlUtils.toXml(unifiedOrderParam));
        }
        String strXml = httpClientCore.doPostJson(UNIFIED_ORDER_URL, null, XmlUtils.toXml(unifiedOrderParam));
        if (logger.isDebugEnabled()) {
            logger.debug(strXml);
        }
        return XmlUtils.toObject(strXml, UnifiedOrderResult.class);
    }

    /**
     * APP统一下单
     *
     * @param outTradeNo     商户系统内部的订单号,32个字符内、可包含字母, 其他说明见商户订单号
     * @param totalFee       订单总金额，单位为分，详见支付金额
     * @param body           商品或支付单简要描述
     * @param spbillCreateIp 网页支付提交用户端ip
     * @return 预支付的微信订单
     */
    public UnifiedOrderResult appUnifiedOrder(String outTradeNo, Integer totalFee, String body, String spbillCreateIp) {
        String nonceStr = createNonceStr();
        String tradeType = TradeType.APP.name();
        String notifyUrl = wechatPayProperties.getDomain() + wechatPayProperties.getNotifyUrl();
        Map<String, String> params = new HashMap<>(8);
        params.put("appid", wechatPayProperties.getAppId());
        params.put("mch_id", wechatPayProperties.getMchId());
        params.put("nonce_str", nonceStr);
        params.put("body", body);
        params.put("out_trade_no", outTradeNo);
        params.put("total_fee", totalFee + "");
        params.put("spbill_create_ip", spbillCreateIp);
        params.put("notify_url", notifyUrl);
        params.put("trade_type", tradeType);
        String sign = SignatureUtil.generateSign(params, wechatPayProperties.getApiKey());

        UnifiedOrderParam unifiedOrderParam = new UnifiedOrderParam();
        unifiedOrderParam.setAppId(wechatPayProperties.getAppId());
        unifiedOrderParam.setMchId(wechatPayProperties.getMchId());
        unifiedOrderParam.setNonceStr(nonceStr);
        unifiedOrderParam.setNotifyUrl(notifyUrl);
        unifiedOrderParam.setBody(body);
        unifiedOrderParam.setOutTradeNo(outTradeNo);
        unifiedOrderParam.setTotalFee(totalFee);
        unifiedOrderParam.setSpbillCreateIp(spbillCreateIp);
        unifiedOrderParam.setTradeType(tradeType);
        unifiedOrderParam.setSign(sign);

        if (logger.isDebugEnabled()) {
            logger.debug(XmlUtils.toXml(unifiedOrderParam));
        }
        String strXml = httpClientCore.doPostJson(UNIFIED_ORDER_URL, null, XmlUtils.toXml(unifiedOrderParam));
        if (logger.isDebugEnabled()) {
            logger.debug(strXml);
        }
        return XmlUtils.toObject(strXml, UnifiedOrderResult.class);
    }

    /**
     * 申请微信退款接口
     *
     * @param transactionId 微信订单号 二选一
     * @param outTradeNo    商户订单号 二选一
     * @param outRefundNo   商户退款单号，商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
     * @param totalFee      订单总金额，单位为分，只能为整数
     * @param refundFee     退款金额，单位为分,可以做部分退款
     * @param refundDesc    退款描述，80字
     * @return 退款结果
     */
    @SuppressWarnings("ALL")
    public RefundResult refund(@Nullable String transactionId, @Nullable String outTradeNo, @Nonnull String outRefundNo,
                               @Nonnull Integer totalFee, @Nonnull Integer refundFee, @Nullable String refundDesc) {
        String nonceStr = createNonceStr();

        Map<String, String> params = new HashMap<>(9);
        params.put("appid", wechatPayProperties.getAppId());
        params.put("mch_id", wechatPayProperties.getMchId());
        params.put("nonce_str", nonceStr);
        if (StringUtils.isNotBlank(transactionId)) {
            params.put("transaction_id", transactionId);
        }
        if (StringUtils.isNotBlank(outTradeNo)) {
            params.put("out_trade_no", outTradeNo);
        }
        params.put("out_refund_no", outRefundNo);
        params.put("total_fee", totalFee + "");
        params.put("refund_fee", refundFee + "");
        if (StringUtils.isNotBlank(refundDesc)) {
            params.put("refund_desc", refundDesc);
        }
        String sign = SignatureUtil.generateSign(params, wechatPayProperties.getApiKey());

        RefundParam refundParam = new RefundParam();
        refundParam.setAppId(wechatPayProperties.getAppId());
        refundParam.setMchId(wechatPayProperties.getMchId());
        refundParam.setNonceStr(nonceStr);
        refundParam.setSign(sign);
        refundParam.setTransactionId(transactionId);
        refundParam.setOutTradeNo(outTradeNo);
        refundParam.setOutRefundNo(outRefundNo);
        refundParam.setTotalFee(totalFee);
        refundParam.setRefundFee(refundFee);
        refundParam.setRefundDesc(refundDesc);

        InputStream in = null;
        CloseableHttpResponse response = null;
        CloseableHttpClient httpclient = null;
        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");

            ClassPathResource classPathResource = new ClassPathResource(wechatPayProperties.getCertPath());
            in = classPathResource.getInputStream();

            keyStore.load(in, wechatPayProperties.getMchId().toCharArray());
            SSLContext sslcontext = SSLContexts.custom()
                    .loadKeyMaterial(keyStore, wechatPayProperties.getMchId().toCharArray()).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null,
                    SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

            httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
            String data = XmlUtils.toXml(refundParam);
            HttpPost httpPost = new HttpPost(REFUND_URL);
            httpPost.setEntity(new StringEntity(data, "UTF-8"));
            response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
                String text;
                StringBuilder sb = new StringBuilder();
                while ((text = bufferedReader.readLine()) != null) {
                    sb.append(text);
                }
                if (logger.isDebugEnabled()) {
                    logger.debug("退款返回信息：" + sb.toString());
                }
                EntityUtils.consume(entity);
                return XmlUtils.toObject(sb.toString(), RefundResult.class);
            }
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        } finally {
            CloseUtil.closeQuietly(in);
            CloseUtil.closeQuietly(response);
            CloseUtil.closeQuietly(httpclient);
        }
    }

    /**
     * 获取微信网页支付的签名
     *
     * @param appId           微信分配的公众账号ID（企业号corpid即为此appId）
     * @param nonceStr        随机字符串
     * @param packagePrepayId 统一下单接口返回的prepay_id参数值，提交格式如：prepay_id=***
     * @param timeStamp       时间戳
     * @param key             支付签名的key 如何配置，详见文档
     * @return 签名
     */

    public String getJsApiPaySign(String appId, String nonceStr,
                                  String packagePrepayId, String timeStamp, String key) {
        String str = "appId=" + appId + "&nonceStr=" + nonceStr + "&package="
                + packagePrepayId + "&signType=MD5&timeStamp=" + timeStamp
                + "&key=" + key;
        if (logger.isDebugEnabled()) {
            logger.debug(str);
        }
        return DigestUtils.md5Hex(str).toUpperCase();
    }

    /**
     * 随机字符串，不长于32位。推荐随机数生成算法
     *
     * @return
     */
    private static String createNonceStr() {
        return UUID.randomUUID().toString().substring(0, 30);
    }


}
