package com.zskj.core.autoconfigure.aliyun.push.enums;

/**
 * 推送目标
 *
 * @author 花开
 */
public enum Target {

    /**
     * 根据设备推送
     */
    DEVICE,

    /**
     * 根据账号推送
     */
    ACCOUNT,

    /**
     * 根据别名推送
     */
    ALIAS,

    /**
     * 根据标签推送
     */
    TAG,

    /**
     * 推送给全部设备
     */
    ALL
}