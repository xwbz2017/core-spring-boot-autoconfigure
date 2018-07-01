package com.zskj.core.autoconfigure.http;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;

/**
 * http请求
 *
 * @author 花开
 * @date 2018/1/29
 */
@Configuration
@EnableConfigurationProperties({HttpClientProperties.class})
@ConditionalOnClass({PoolingHttpClientConnectionManager.class, CloseableHttpClient.class})
@ConditionalOnExpression("'${core.http-client.enabled}'=='true' || '${core.wechat.pay.enabled}'=='true' || '${core.baidu.map-enabled}'=='true'")
public class HttpClientAutoConfiguration {

    private Logger logger = LoggerFactory.getLogger(HttpClientAutoConfiguration.class);

    @Autowired
    private HttpClientProperties httpClientProperties;

    @Bean
    @ConditionalOnMissingBean(HttpClientCore.class)
    public HttpClientCore getHttpClientCore() {
        return new HttpClientCore(httpClientProperties, getPoolingHttpClientConnectionManager());
    }

    @Bean(destroyMethod = "close")
    @ConditionalOnMissingBean(PoolingHttpClientConnectionManager.class)
    public PoolingHttpClientConnectionManager getPoolingHttpClientConnectionManager() {
        try {
            SSLContext sslContext = SSLContexts.custom()
                    .loadTrustMaterial(null, new TrustSelfSignedStrategy())
                    .build();
            SSLConnectionSocketFactory sf = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.getDefaultHostnameVerifier());
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("https", sf)
                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .build();
            PoolingHttpClientConnectionManager clientConnectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            clientConnectionManager.setMaxTotal(httpClientProperties.getMaxTotal());
            clientConnectionManager.setDefaultMaxPerRoute(httpClientProperties.getDefaultMaxPerRoute());
            return clientConnectionManager;
        } catch (Exception e) {
            logger.error("配置httpClient连接池失败，httpclient将无法使用，请检查", e);
        }
        return null;
    }

}
