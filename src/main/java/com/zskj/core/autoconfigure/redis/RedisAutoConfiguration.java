package com.zskj.core.autoconfigure.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.Jedis;

/**
 * redis自动化配置
 *
 * @author 花开
 * @date 2018/1/13
 */
@Configuration
@ConditionalOnClass({JedisConnection.class, RedisOperations.class, Jedis.class})
@AutoConfigureAfter(org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration.class)
@ConditionalOnExpression("'${core.redis.enabled}'=='true'")
public class RedisAutoConfiguration {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Bean
    @ConditionalOnMissingBean(RedisCore.class)
    public RedisCore getRedis() {
        return new RedisCore(redisTemplate);
    }
}
