package com.zskj.core.autoconfigure.redis;

import com.alibaba.fastjson.JSONObject;
import org.springframework.data.redis.core.*;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Parameter must not be null: Do not put any annotation. In this case a bug marker appears if null is passed to the method. (I had expected this behaviour when putting the @Nonnull annotation, but when I put it, no bug marker came up at all.)
 * Parameter can be null: Put @Nullable annotation. (Same effect for @CheckForNull. The @Nullable documentation reads: “FindBugs will treat the annotated items as though they had no annotation.” This is not true. If I call string.length() and String string has been marked @Nullable, it causes a bug marker to appear, if there is no annotation no bug marker showed.)
 * Method return value:
 * <p>
 * Method does never return null: Put @Nonnull. Causes a bug marker if you try to return null; from inside the method.
 * Method can return null: Do you want to enforce checks for it? Checks may be an overhead if the return value does depend on the method parameters only which can be assumed known at call time, like “my method returns null if parameter one is negative”. I would not put an annotation in that case. However, you may want to consider throwing an IllegalArgumentException instead of returning null.
 * Method can return null and the return object should always be checked: Put @CheckForNull. However, in many cases there are better ways to go, you may want to consider returning Collections.emptyList() instead of null lists, or throwing MissingResourceException, IOException or another appropriate exception.
 */

/**
 * redis工具类
 *
 * @author 花开
 * @date 2018/1/13
 */
public class RedisCore {

    private StringRedisTemplate redisTemplate;

    private static final String COLON = ":";

    private ValueOperations<String, String> valueOps;
    private HashOperations<String, String, String> hashOps;
    private ListOperations<String, String> listOps;
    private SetOperations<String, String> setOps;
    private ZSetOperations<String, String> zSetOps;
    private GeoOperations<String, String> geoOps;
    private HyperLogLogOperations<String, String> hllOps;

    RedisCore(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.valueOps = redisTemplate.opsForValue();
        this.hashOps = redisTemplate.opsForHash();
        this.listOps = redisTemplate.opsForList();
        this.setOps = redisTemplate.opsForSet();
        this.zSetOps = redisTemplate.opsForZSet();
        this.geoOps = redisTemplate.opsForGeo();
        this.hllOps = redisTemplate.opsForHyperLogLog();
    }

    /**
     * set start
     */
    public Set<String> getSValues(String key) {
        return redisTemplate.boundSetOps(key).members();
    }

    public void addSValue(String key, String... values) {
        redisTemplate.boundSetOps(key).add(values);
    }

    public long removeSValues(String key, Object... values) {
        return redisTemplate.boundSetOps(key).remove(values);
    }

    /**
     * set end
     */

    /** value start */

    /**
     * 根据缓存名获取值
     *
     * @param key 缓存名
     * @return 对应的值，如不存在返回null
     */
    public @CheckForNull
    String getValue(@Nonnull String key) {
        return valueOps.get(key);
    }

    /**
     * 根据缓存名获取值
     *
     * @param prefix 缓存前缀
     * @param id     缓存标识
     * @return 对应的值，如不存在返回null
     */
    public @CheckForNull
    String getValue(@Nonnull String prefix, @Nonnull String id) {
        return getValue(getKey(prefix, id));
    }

    /**
     * 根据缓存名获取值对象
     *
     * @param key   缓存名
     * @param clazz 转换类型
     * @return 对应的值，并转成期望的类型
     */
    public @CheckForNull
    <T> T getValue(@Nonnull String key, @Nonnull Class<T> clazz) {
        String ret = getValue(key);
        if (ret != null) {
            return JSONObject.parseObject(ret, clazz);
        }
        return null;
    }

    /**
     * 根据缓存名获取值对象
     *
     * @param prefix 缓存前缀
     * @param id     缓存标识
     * @param clazz  转换类型
     * @return 对应的值，并转成期望的类型
     */
    public <T> T getValue(@Nonnull String prefix, @Nonnull String id, @Nonnull Class<T> clazz) {
        return getValue(getKey(prefix, id), clazz);
    }

    /**
     * 根据缓存名获取值集合
     *
     * @param key   缓存名
     * @param clazz 转换类型
     * @return 对应的值，并转成期望的类型集合
     */
    public @CheckForNull
    <T> List<T> getValues(@Nonnull String key, @Nonnull Class<T> clazz) {
        String ret = getValue(key);
        if (ret != null) {
            return JSONObject.parseArray(ret, clazz);
        }
        return null;
    }

    /**
     * 根据缓存名获取值集合
     *
     * @param prefix 缓存前缀
     * @param id     缓存标识
     * @param clazz  转换类型
     * @return 对应的值，并转成期望的类型集合
     */
    public @CheckForNull
    <T> List<T> getValues(@Nonnull String prefix, @Nonnull String id, @Nonnull Class<T> clazz) {
        return getValues(getKey(prefix, id), clazz);
    }

    /**
     * 保存值到redis中
     *
     * @param key    缓存名
     * @param value  值
     * @param second 秒，多少秒后过期，传0为不过期
     */
    public void setValue(@Nonnull String key, @Nonnull String value, long second) {
        if (second > 0) {
            valueOps.set(key, value, second, TimeUnit.SECONDS);
        } else {
            valueOps.set(key, value);
        }
    }

    /**
     * 保存值到redis中
     *
     * @param prefix 缓存前缀
     * @param id     缓存标识
     * @param value  值
     * @param second 秒，多少秒后过期，传0为不过期
     */
    public void setValue(@Nonnull String prefix, @Nonnull String id, @Nonnull String value, long second) {
        setValue(getKey(prefix, id), value, second);
    }

    /**
     * 保存值到redis中，不设置过期时间
     *
     * @param key   缓存名
     * @param value 值
     */
    public void setValue(@Nonnull String key, @Nonnull String value) {
        setValue(key, value, 0);
    }

    /**
     * 保存值到redis中，不设置过期时间
     *
     * @param prefix 缓存前缀
     * @param id     缓存标识
     * @param value  值
     */
    public void setValue(@Nonnull String prefix, @Nonnull String id, @Nonnull String value) {
        setValue(getKey(prefix, id), value);
    }

    /**
     * 自增
     *
     * @param key   缓存名
     * @param delta 增长数值
     * @return 增长后的值
     */
    public long increment(@Nonnull String key, long delta) {
        return valueOps.increment(key, delta);
    }

    /**
     * 自增
     *
     * @param prefix 缓存前缀
     * @param id     缓存标识
     * @param delta  增长数值
     * @return 增长后的值
     */
    public long increment(@Nonnull String prefix, @Nonnull String id, long delta) {
        return increment(getKey(prefix, id), delta);
    }

    /************************* hash start ****************************/

    /**
     * 获取hash值
     *
     * @param key     缓存名
     * @param hashKey hash缓存名
     * @return 对应的值
     */
    public @CheckForNull
    String getHashValue(@Nonnull String key, @Nonnull String hashKey) {
        return hashOps.get(key, hashKey);
    }

    /**
     * 获取hash值
     *
     * @param prefix  缓存前缀
     * @param id      缓存标识
     * @param hashKey hash缓存名
     * @return 对应的值
     */
    public @CheckForNull
    String getHashValue(@Nonnull String prefix, @Nonnull String id, @Nonnull String hashKey) {
        return getHashValue(getKey(prefix, id), hashKey);
    }

    /**
     * 获取hash值对象
     *
     * @param key     缓存名
     * @param hashKey hash缓存名
     * @param clazz   需要转换的类型
     * @return hash值对象
     */
    public @CheckForNull
    <T> T getHashValue(@Nonnull String key, @Nonnull String hashKey, @Nonnull Class<T> clazz) {
        String ret = getHashValue(key, hashKey);
        if (ret != null) {
            return JSONObject.parseObject(ret, clazz);
        }
        return null;
    }

    /**
     * 获取hash值对象
     *
     * @param prefix  缓存前缀
     * @param id      缓存标识
     * @param hashKey hash缓存名
     * @param clazz   需要转换的类型
     * @return hash值对象
     */
    public @CheckForNull
    <T> T getHashValue(@Nonnull String prefix, @Nonnull String id, @Nonnull String hashKey, @Nonnull Class<T> clazz) {
        return getHashValue(getKey(prefix, id), hashKey, clazz);
    }

    /**
     * 获取hash值对象集合
     *
     * @param key     缓存名
     * @param hashKey hash缓存名
     * @param clazz   需要转换的类型
     * @return hash值对象集合
     */
    public @CheckForNull
    <T> List<T> getHashValues(@Nonnull String key, @Nonnull String hashKey, @Nonnull Class<T> clazz) {
        String ret = getHashValue(key, hashKey);
        if (ret != null) {
            return JSONObject.parseArray(ret, clazz);
        }
        return null;
    }

    /**
     * 批量获取hash值
     *
     * @param key 缓存名
     * @return hash值集合
     */
    public @CheckForNull
    List<String> getBatchHashValues(@Nonnull String key) {
        return hashOps.values(key);
    }

    /**
     * 批量获取hash值
     *
     * @param prefix 缓存前缀
     * @param id     缓存标识
     * @return hash值集合
     */
    public @CheckForNull
    List<String> getBatchHashValues(@Nonnull String prefix, @Nonnull String id) {
        return getBatchHashValues(getKey(prefix, id));
    }

    /**
     * 设置hash值
     *
     * @param key     缓存名
     * @param hashKey hash缓存名
     * @param value   值
     */
    public void setHashValue(@Nonnull String key, @Nonnull String hashKey, @Nonnull String value) {
        hashOps.put(key, hashKey, value);
    }

    /**
     * 设置hash值
     *
     * @param prefix  缓存前缀
     * @param id      缓存标识
     * @param hashKey hash缓存名
     * @param value   值
     */
    public void setHashValue(@Nonnull String prefix, @Nonnull String id, @Nonnull String hashKey, @Nonnull String value) {
        setHashValue(getKey(prefix, id), hashKey, value);
    }

    /**
     * 获取hash全部的hashKey
     *
     * @param key 缓存名
     * @return hashKey集合
     */
    public Set<String> getHashKeys(@Nonnull String key) {
        return hashOps.keys(key);
    }

    /**
     * 获取hash全部的hashKey
     *
     * @param prefix 缓存前缀
     * @param id     缓存标识
     * @return hashKey集合
     */
    public Set<String> getHashKeys(@Nonnull String prefix, @Nonnull String id) {
        return getHashKeys(getKey(prefix, id));
    }

    /**
     * 删除hashKey
     *
     * @param key      缓存名
     * @param hashKeys 多个hash缓存名
     * @return 删除个数
     */
    public long delete(@Nonnull String key, @Nonnull Object... hashKeys) {
        return hashOps.delete(key, hashKeys);
    }

    /**
     * 删除hashKey
     *
     * @param prefix   缓存前缀
     * @param id       缓存标识
     * @param hashKeys 多个hash缓存名
     * @return 删除个数
     */
    public long delete(@Nonnull String prefix, @Nonnull String id, @Nonnull Object... hashKeys) {
        return delete(getKey(prefix, id), hashKeys);
    }

    /**************************** list start ***********************/

    /**
     * Get element at {@code index} form list at {@code key}.
     *
     * @see <a href="http://redis.io/commands/lindex">Redis Documentation: LINDEX</a>
     */
    public @CheckForNull
    String lIndex(@Nonnull String key, @Nonnull long index) {
        return listOps.index(key, index);
    }

    public @CheckForNull
    String lIndex(@Nonnull String prefix, @Nonnull String id, @Nonnull long index) {
        return listOps.index(getKey(prefix, id), index);
    }

    /**
     * Get the size of list stored at {@code key}.
     *
     * @see <a href="http://redis.io/commands/llen">Redis Documentation: LLEN</a>
     */
    public long lSize(@Nonnull String key) {
        return listOps.size(key);
    }

    public long lSize(@Nonnull String prefix, @Nonnull String id) {
        return listOps.size(getKey(prefix, id));
    }

    /**
     * Removes and returns first element in list stored at {@code key}.
     *
     * @see <a href="http://redis.io/commands/lpop">Redis Documentation: LPOP</a>
     */
    public @CheckForNull
    String leftPop(@Nonnull String key) {
        return listOps.leftPop(key);
    }

    public @CheckForNull
    String leftPop(@Nonnull String prefix, @Nonnull String id) {
        return listOps.leftPop(getKey(prefix, id));
    }
    /**
     * Removes and returns first element from lists stored at {@code key} . <br>
     * <b>Blocks connection</b> until element available or {@code timeout} reached.
     *
     * @see <a href="http://redis.io/commands/blpop">Redis Documentation: BLPOP</a>
     */
    public @CheckForNull
    String leftPop(@Nonnull String key, @Nonnull long timeout, @Nonnull TimeUnit unit) {
        return listOps.leftPop(key, timeout, unit);
    }

    public @CheckForNull
    String leftPop(@Nonnull String prefix, @Nonnull String id, @Nonnull long timeout, @Nonnull TimeUnit unit) {
        return listOps.leftPop(getKey(prefix, id), timeout, unit);
    }

    public @CheckForNull
    String rightPop(@Nonnull String key) {
        return listOps.rightPop(key);
    }

    public @CheckForNull
    String rightPop(@Nonnull String prefix, @Nonnull String id) {
        return rightPop(getKey(prefix, id));
    }

    public long leftPush(@Nonnull String key, @Nonnull String value) {
        return listOps.leftPush(key, value);
    }

    public long leftPush(@Nonnull String prefix, @Nonnull String id, @Nonnull String value) {
        return leftPush(getKey(prefix, id), value);
    }

    public long leftPushAll(@Nonnull String key, @Nonnull String... values) {
        return listOps.leftPushAll(key, values);
    }

    public long leftPushAll(@Nonnull String prefix, @Nonnull String id, @Nonnull String... values) {
        return leftPushAll(getKey(prefix, id), values);
    }

    public long leftPushAll(@Nonnull String key, @Nonnull Collection<String> values) {
        return listOps.leftPushAll(key, values);
    }

    public long leftPushAll(@Nonnull String prefix, @Nonnull String id, @Nonnull Collection<String> values) {
        return leftPushAll(getKey(prefix, id), values);
    }

    public long rightPush(@Nonnull String key, @Nonnull String value) {
        return listOps.rightPush(key, value);
    }

    public long rightPush(@Nonnull String prefix, @Nonnull String id, @Nonnull String value) {
        return rightPush(getKey(prefix, id), value);
    }

    public long rightPushAll(@Nonnull String key, @Nonnull String... values) {
        return listOps.rightPushAll(key, values);
    }

    public long rightPushAll(@Nonnull String prefix, @Nonnull String id, @Nonnull String... values) {
        return rightPushAll(getKey(prefix, id), values);
    }

    public long rightPushAll(@Nonnull String key, @Nonnull Collection<String> values) {
        return listOps.rightPushAll(key, values);
    }

    public long rightPushAll(@Nonnull String prefix, @Nonnull String id, @Nonnull Collection<String> values) {
        return rightPushAll(getKey(prefix, id), values);
    }

    /**************************** list end ***********************/


    /**
     * 拼接缓存名
     * <p>
     * 如用户的信息，我们可能会以这样的方式保存：
     * <b>user:{id}</b>
     * 这里的user就是对应的prefix
     * id对应的就是key
     * </p>
     *
     * @param prefix 缓存名的前缀
     * @param id     缓存的标识
     * @return 拼接后的缓存名
     */
    public String getKey(String prefix, String id) {
        return prefix + COLON + id;
    }
}
