package com.zskj.core.autoconfigure.aliyun.oss;

import com.aliyun.oss.OSSClient;
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
 * oss自动配置
 *
 * @author 花开
 * @date 2017/12/12
 */
@Configuration
@EnableConfigurationProperties({AliYunProperties.class})
@ConditionalOnClass(OSSClient.class)
@ConditionalOnExpression("'${core.aliyun.oss-enabled}'=='true'")
public class OssAutoConfiguration {

    @Autowired
    private AliYunProperties aliYunProperties;

    @Bean(destroyMethod = "shutdown")
    @ConditionalOnMissingBean(OSSClient.class)
    public OSSClient getOssClient() {
        return new OSSClient(aliYunProperties.getOssEndPoint(), aliYunProperties.getAccessKeyId(), aliYunProperties.getAccessKeySecret());
    }

    @Bean
    @ConditionalOnMissingBean(OssCore.class)
    @ConditionalOnBean(OSSClient.class)
    public OssCore getOssCore() {
        return new OssCore(getOssClient(), aliYunProperties);
    }
}
