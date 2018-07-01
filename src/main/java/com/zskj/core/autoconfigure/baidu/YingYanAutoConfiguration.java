package com.zskj.core.autoconfigure.baidu;

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
 * 百度鹰眼api自动化配置
 *
 * @author 花开
 * @date 2018/2/2
 */
@Configuration
@EnableConfigurationProperties({BaiduProperties.class})
@ConditionalOnExpression("'${core.baidu.map-enabled}'=='true'")
@AutoConfigureAfter(HttpClientAutoConfiguration.class)
public class YingYanAutoConfiguration {

    @Autowired
    private HttpClientCore httpClientCore;
    @Autowired
    private BaiduProperties properties;

    @Bean
    @ConditionalOnMissingBean(YingYanCore.class)
    public YingYanCore yingYanCore() {
        return new YingYanCore(httpClientCore, properties);
    }
}
