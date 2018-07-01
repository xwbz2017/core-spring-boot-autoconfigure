package com.zskj.core.autoconfigure.alipay;

import com.alipay.api.AlipayClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微信支付自动配置
 *
 * @author 花开
 * @date 2018/1/10
 */
@Configuration
@EnableConfigurationProperties({AliPayProperties.class})
@ConditionalOnExpression("'${core.ali.pay.enabled}'=='true'")
@ConditionalOnClass(AlipayClient.class)
public class AliPayAutoConfiguration {

    @Autowired
    private AliPayProperties aliPayProperties;

    @Bean
    @ConditionalOnMissingBean(AliPayCore.class)
    public AliPayCore aliPayCore() {
        return new AliPayCore(aliPayProperties);
    }
}
