package com.zskj.core.autoconfigure.aliyun.push.enums;

/**
 * 数据粒度
 * HOUR：是小时粒度，DAY：是天粒度。小时粒度允许查24小时内数据，天粒度允许查31内天数据，目前只支持天粒度查询。
 *
 * @author 花开
 * @date 2018/2/2
 */
public enum Granularity {

    /** 小时粒度 */
    HOUR,

    /** 天粒度 */
    DAY
}
