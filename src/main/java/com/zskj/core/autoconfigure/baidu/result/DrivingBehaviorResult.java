package com.zskj.core.autoconfigure.baidu.result;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 驾驶行为分析
 *
 * @author 花开
 * @date 2018/2/26
 */
public class DrivingBehaviorResult extends BaseResult{

    private double distance;

    private int duration;

    @JSONField(name = "average_speed")
    private double averageSpeed;

    @JSONField(name = "max_speed")
    private double maxSpeed;

    @JSONField(name = "speeding_num")
    private int speedingNum;

    @JSONField(name = "harsh_acceleration_num")
    private int harshAccelerationNum;

    @JSONField(name = "harsh_breaking_num")
    private int harshBreakingNum;

    @JSONField(name = "harsh_steering_num")
    private int harshSteeringNum;

    @JSONField(name = "start_point")
    private JSONObject startPoint;

    @JSONField(name = "end_point")
    private JSONObject endPoint;

    private JSONArray speeding;

    @JSONField(name = "harsh_acceleration")
    private JSONArray harshAcceleration;

    @JSONField(name = "harsh_breaking")
    private JSONArray harshBreaking;

    @JSONField(name = "harsh_steering")
    private JSONArray harshSteering;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getSpeedingNum() {
        return speedingNum;
    }

    public void setSpeedingNum(int speedingNum) {
        this.speedingNum = speedingNum;
    }

    public int getHarshAccelerationNum() {
        return harshAccelerationNum;
    }

    public void setHarshAccelerationNum(int harshAccelerationNum) {
        this.harshAccelerationNum = harshAccelerationNum;
    }

    public int getHarshBreakingNum() {
        return harshBreakingNum;
    }

    public void setHarshBreakingNum(int harshBreakingNum) {
        this.harshBreakingNum = harshBreakingNum;
    }

    public int getHarshSteeringNum() {
        return harshSteeringNum;
    }

    public void setHarshSteeringNum(int harshSteeringNum) {
        this.harshSteeringNum = harshSteeringNum;
    }

    public JSONObject getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(JSONObject startPoint) {
        this.startPoint = startPoint;
    }

    public JSONObject getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(JSONObject endPoint) {
        this.endPoint = endPoint;
    }

    public JSONArray getSpeeding() {
        return speeding;
    }

    public void setSpeeding(JSONArray speeding) {
        this.speeding = speeding;
    }

    public JSONArray getHarshAcceleration() {
        return harshAcceleration;
    }

    public void setHarshAcceleration(JSONArray harshAcceleration) {
        this.harshAcceleration = harshAcceleration;
    }

    public JSONArray getHarshBreaking() {
        return harshBreaking;
    }

    public void setHarshBreaking(JSONArray harshBreaking) {
        this.harshBreaking = harshBreaking;
    }

    public JSONArray getHarshSteering() {
        return harshSteering;
    }

    public void setHarshSteering(JSONArray harshSteering) {
        this.harshSteering = harshSteering;
    }
}
