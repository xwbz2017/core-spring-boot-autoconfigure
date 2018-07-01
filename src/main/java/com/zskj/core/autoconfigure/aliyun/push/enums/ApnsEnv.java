package com.zskj.core.autoconfigure.aliyun.push.enums;

/**
 * iOS的通知是通过APNS中心来发送的，需要填写对应的环境信息，DEV表示开发环境，PRODUCT表示生产环境
 *
 * @author 花开
 * @date 2018/2/1
 */
public enum ApnsEnv {

    /**
     * 开发
     */
    DEV,

    /**
     * 生产
     */
    PRODUCT
}
