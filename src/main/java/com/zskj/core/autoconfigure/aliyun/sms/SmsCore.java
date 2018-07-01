package com.zskj.core.autoconfigure.aliyun.sms;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.*;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.zskj.core.autoconfigure.aliyun.AliYunProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 短信发送
 *
 * @author 花开
 * @date 2018/2/1
 */
public class SmsCore {

    private Logger logger = LoggerFactory.getLogger(SmsCore.class);

    private AliYunProperties properties;

    private IAcsClient iAcsClient;

    private static final String OK = "OK";

    public SmsCore(IAcsClient iAcsClient, AliYunProperties properties) {
        this.iAcsClient = iAcsClient;
        this.properties = properties;
        System.setProperty("sun.net.client.defaultConnectTimeout", properties.getSmsDefaultConnectTimeout());
        System.setProperty("sun.net.client.defaultReadTimeout", properties.getSmsDefaultReadTimeout());
    }

    /**
     * 短信发送
     *
     * @param sign          短信签名
     * @param templateCode  短信模板ID
     * @param templateParam 短信模板变量替换
     * @param phones        短信接收号码批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
     * @param outId         外部流水扩展字段
     * @return BizId 发送回执ID biz不为null说明发送成功，否则发送失败
     * 可根据该ID查询具体的发送状态
     */
    public String send(@Nonnull String sign, @Nonnull String templateCode, @Nullable Map<String, Object> templateParam,
                       @Nonnull List<String> phones, @Nullable String outId) {
        SendSmsRequest request = new SendSmsRequest();
        StringBuilder phoneNumbers = new StringBuilder();
        for (String phone : phones) {
            phoneNumbers.append(phone);
        }
        phoneNumbers.substring(0, phoneNumbers.length() - 1);
        request.setPhoneNumbers(phoneNumbers.toString());
        request.setSignName(sign);
        request.setTemplateCode(templateCode);
        request.setTemplateParam(JSON.toJSONString(templateParam));
        request.setOutId(outId);
        try {
            SendSmsResponse sendSmsResponse = iAcsClient.getAcsResponse(request);
            if (logger.isDebugEnabled()) {
                logger.debug("短信发送结果:code:{}, message:{}, requestId:{}, bizId:{}", sendSmsResponse.getCode(), sendSmsResponse.getMessage(), sendSmsResponse.getRequestId(), sendSmsResponse.getBizId());
            }
            if (sendSmsResponse.getCode() != null && OK.equals(sendSmsResponse.getCode())) {
                return sendSmsResponse.getBizId();
            }
        } catch (ClientException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 短信批量发送
     *
     * @param phones         短信接收号码,批量上限为100个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
     * @param signs          短信签名
     * @param templateCode   短信模板ID
     * @param templateParams 短信模板变量替换
     * @return BizId 发送回执ID biz不为null说明发送成功，否则发送失败
     * 可根据该ID查询具体的发送状态
     */
    public String batchSend(@Nonnull List<String> phones, @Nonnull List<String> signs, @Nonnull String templateCode, @Nonnull List<Map<String, Object>> templateParams) {
        SendBatchSmsRequest request = new SendBatchSmsRequest();
        request.setMethod(MethodType.POST);
        request.setPhoneNumberJson(JSON.toJSONString(phones));
        request.setSignNameJson(JSON.toJSONString(signs));
        request.setTemplateCode(templateCode);
        request.setTemplateParamJson(JSON.toJSONString(templateParams));
        try {
            SendBatchSmsResponse sendSmsResponse = iAcsClient.getAcsResponse(request);
            if (logger.isDebugEnabled()) {
                logger.debug("短信发送结果:code:{}, message:{}, requestId:{}, bizId:{}", sendSmsResponse.getCode(), sendSmsResponse.getMessage(), sendSmsResponse.getRequestId(), sendSmsResponse.getBizId());
            }
            if (sendSmsResponse.getCode() != null && OK.equals(sendSmsResponse.getCode())) {
                return sendSmsResponse.getBizId();
            }
        } catch (ClientException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * @param phone
     * @param bizId       调用发送短信接口时返回的BizId
     * @param sendDate    短信发送的日期 支持30天内记录查询（可查其中一天的发送数据），格式yyyyMMdd
     * @param currentPage 当前页码从1开始计数
     * @param pageSize    页大小
     * @return List<QuerySendDetailsResponse.SmsSendDetailDTO>
     */
    public List<QuerySendDetailsResponse.SmsSendDetailDTO> query(@Nonnull String phone, @Nullable String bizId, @Nonnull String sendDate, @Nonnull int currentPage, @Nonnull int pageSize) {
        QuerySendDetailsRequest request = new QuerySendDetailsRequest();
        request.setPhoneNumber(phone);
        request.setBizId(bizId);
        request.setSendDate(sendDate);
        request.setCurrentPage((long) currentPage);
        request.setPageSize((long) pageSize);
        try {
            QuerySendDetailsResponse querySendDetailsResponse = iAcsClient.getAcsResponse(request);
            if (logger.isDebugEnabled()) {
                logger.debug("短信查询结果:code:{}, message:{}, requestId:{}", querySendDetailsResponse.getCode(), querySendDetailsResponse.getMessage(), querySendDetailsResponse.getRequestId());
            }
            if (querySendDetailsResponse.getCode() != null && OK.equals(querySendDetailsResponse.getCode())) {
                return querySendDetailsResponse.getSmsSendDetailDTOs();
            }
        } catch (ClientException e) {
            logger.error(e.getMessage(), e);
        }
        return Collections.emptyList();
    }
}
