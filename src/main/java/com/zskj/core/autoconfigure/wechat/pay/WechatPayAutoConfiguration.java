package com.zskj.core.autoconfigure.wechat.pay;

import com.zskj.core.autoconfigure.http.HttpClientAutoConfiguration;
import com.zskj.core.autoconfigure.http.HttpClientCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
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
@EnableConfigurationProperties({WechatPayProperties.class})
@ConditionalOnExpression("'${core.wechat.pay.enabled}'=='true'")
@AutoConfigureAfter(HttpClientAutoConfiguration.class)
@ConditionalOnBean(HttpClientCore.class)
public class WechatPayAutoConfiguration {

    @Autowired
    private WechatPayProperties wechatPayProperties;
    @Autowired
    private HttpClientCore httpClientCore;

    @Bean
    @ConditionalOnMissingBean(WechatPayCore.class)
    public WechatPayCore wechatPayCore() {
        return new WechatPayCore(wechatPayProperties, httpClientCore);
    }
}
