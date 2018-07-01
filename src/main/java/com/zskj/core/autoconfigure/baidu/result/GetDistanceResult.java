package com.zskj.core.autoconfigure.baidu.result;

/**
 * 查询某 entity 一段时间内行驶里程，可应用于无需查询轨迹仅查询里程的场景，如：用车实时计费,支持：
 * 1. 计算总里程，支持计算纠偏后里程
 * 2. 对于中断或丢失的轨迹区间进行补偿，支持使用直线或驾车/骑行/步行路线规划的里程进行补偿
 *
 * @author 花开
 * @date 2018/2/12
 */
public class GetDistanceResult extends BaseResult {

    private Double distance;
}
