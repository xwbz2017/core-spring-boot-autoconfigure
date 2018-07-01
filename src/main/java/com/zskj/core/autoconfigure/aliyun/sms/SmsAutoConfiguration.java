package com.zskj.core.autoconfigure.aliyun.sms;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.zskj.core.autoconfigure.aliyun.AliYunProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 阿里云短信发送自动化配置
 *
 * @author 花开
 * @date 2017/12/12
 */
@Configuration
@EnableConfigurationProperties({AliYunProperties.class})
@ConditionalOnClass({IAcsClient.class, QuerySendDetailsResponse.class})
@ConditionalOnExpression("'${core.aliyun.sms-enabled}'=='true'")
public class SmsAutoConfiguration {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AliYunProperties properties;

    @Bean("smsIAcsClient")
    @ConditionalOnMissingBean(name = "smsIAcsClient")
    public IAcsClient iAcsClient() {
        try {
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", properties.getAccessKeyId(), properties.getAccessKeySecret());
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", properties.getSmsProduct(), properties.getSmsDomain());
            return new DefaultAcsClient(profile);
        } catch (ClientException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @Bean
    @ConditionalOnBean(name = "smsIAcsClient")
    public SmsCore smsCore(AliYunProperties properties) {
        return new SmsCore(iAcsClient(), properties);
    }


}
