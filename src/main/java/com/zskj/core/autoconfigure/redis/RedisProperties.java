package com.zskj.core.autoconfigure.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *redis 配置
 *
 * @author 花开
 * @date 2018/1/31
 */
@ConfigurationProperties(prefix = RedisProperties.PREFIX)
public class RedisProperties {

    static final String PREFIX = "core.redis";

    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
