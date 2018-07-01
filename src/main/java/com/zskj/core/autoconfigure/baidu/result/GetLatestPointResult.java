package com.zskj.core.autoconfigure.baidu.result;

import com.alibaba.fastjson.JSONObject;

/**
 * 查找某 entity纠偏后的实时位置，以及所在道路的限速信息。适用于持续追踪某一终端，展示纠偏后的实时位置，并实时判断车辆是否超速。
 *
 * @author 花开
 * @date 2018/2/12
 */
public class GetLatestPointResult extends BaseResult {

    private JSONObject latestPoint;

    private Double limitSpeed;

    public JSONObject getLatestPoint() {
        return latestPoint;
    }

    public void setLatestPoint(JSONObject latestPoint) {
        this.latestPoint = latestPoint;
    }

    public Double getLimitSpeed() {
        return limitSpeed;
    }

    public void setLimitSpeed(Double limitSpeed) {
        this.limitSpeed = limitSpeed;
    }
}
