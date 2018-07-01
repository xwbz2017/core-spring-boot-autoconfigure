package com.zskj.core.autoconfigure.aliyun.mns;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.MNSClient;
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
 * 消息服务自动配置
 *
 * @author 花开
 * @date 2018/2/2
 */
@Configuration
@EnableConfigurationProperties({AliYunProperties.class})
@ConditionalOnClass({CloudAccount.class})
@ConditionalOnExpression("'${core.aliyun.mns-enabled}'=='true'")
public class MnsAutoConfiguration {

    @Autowired
    private AliYunProperties properties;

    @Bean(destroyMethod = "close")
    @ConditionalOnMissingBean
    public MNSClient mnsClient() {
        return new CloudAccount(properties.getAccessKeyId(), properties.getAccessKeySecret(), properties.getMnsAccountEndPoint()).getMNSClient();
    }

    @Bean
    @ConditionalOnBean(MNSClient.class)
    public MnsQueueCore mnsCore() {
        return new MnsQueueCore(mnsClient());
    }
}
