package com.zskj.core.autoconfigure.aliyun.push;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.push.model.v20160801.BindAliasRequest;
import com.zskj.core.autoconfigure.aliyun.AliYunProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 推送自动化配置
 *
 * @author 花开
 * @date 2018/2/1
 */
@Configuration
@EnableConfigurationProperties({AliYunProperties.class})
@ConditionalOnClass({IAcsClient.class, BindAliasRequest.class})
@ConditionalOnExpression("'${core.aliyun.push-enabled}'=='true'")
public class PushAutoConfiguration {

    @Autowired
    private AliYunProperties properties;

    @Bean("pushIAcsClient")
    @ConditionalOnMissingBean(name = "pushIAcsClient")
    public IAcsClient iAcsClient() {
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", properties.getAccessKeyId(), properties.getAccessKeySecret());
        return new DefaultAcsClient(profile);
    }

    @Bean
    @ConditionalOnBean(name = "pushIAcsClient")
    public PushCore pushCore(AliYunProperties properties) {
        return new PushCore(iAcsClient(), properties);
    }

}
