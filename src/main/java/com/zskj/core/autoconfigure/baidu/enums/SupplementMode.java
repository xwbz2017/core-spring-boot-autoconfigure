package com.zskj.core.autoconfigure.baidu.enums;

/**
 * 里程补偿方式
 *
 * @author 花开
 * @date 2018/2/6
 */
public enum SupplementMode {

    /** 不补充，中断两点间距离不记入里程 */
    no_supplement,

    /** 使用直线距离补充 */
    straight,

    /** 使用最短驾车路线距离补充 */
    driving,

    /** 使用最短骑行路线距离补充 */
    riding,

    /** 使用最短步行路线距离补充 */
    walking
}
