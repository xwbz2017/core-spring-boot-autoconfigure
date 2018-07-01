package com.zskj.core.autoconfigure.http;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * HttpClient配置属性
 *
 * @author 花开
 * @date 2018/1/29
 */
@ConfigurationProperties(prefix = HttpClientProperties.PREFIX)
public class HttpClientProperties {

    static final String PREFIX = "core.http-client";

    private boolean enabled = true;

    /** 连接池的最大连接数 */
    private int maxTotal = 300;

    /** 设置每个路由上的默认连接个数 */
    private int defaultMaxPerRoute = 100;

    /** 数据传输过程中数据包之间间隔的超时时间 */
    private int socketTimeout = 5000;

    /** 连接建立时间 */
    private int connectTimeout = 5000;

    /** 从连接池获取连接的超时时间 */
    private int connectRequestTimeout = 5000;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getDefaultMaxPerRoute() {
        return defaultMaxPerRoute;
    }

    public void setDefaultMaxPerRoute(int defaultMaxPerRoute) {
        this.defaultMaxPerRoute = defaultMaxPerRoute;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getConnectRequestTimeout() {
        return connectRequestTimeout;
    }

    public void setConnectRequestTimeout(int connectRequestTimeout) {
        this.connectRequestTimeout = connectRequestTimeout;
    }
}
