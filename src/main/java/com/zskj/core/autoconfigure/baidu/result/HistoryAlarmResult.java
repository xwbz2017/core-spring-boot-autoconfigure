package com.zskj.core.autoconfigure.baidu.result;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 查询某监控对象的历史报警信息
 *
 * @author 花开
 * @date 2018/2/26
 */
public class HistoryAlarmResult {

    private int size;

    private List<Alarm> alarms;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Alarm> getAlarms() {
        return alarms;
    }

    public void setAlarms(List<Alarm> alarms) {
        this.alarms = alarms;
    }

    private class Alarm {
        @JSONField(name = "fence_id")
        private int fenceId;
        @JSONField(name = "fence_name")
        private String fenceName;
        @JSONField(name = "monitored_person")
        private String monitoredPerson;
        private String action;
        @JSONField(name = "alarm_point")
        private Point alarmPoint;
        @JSONField(name = "pre_point")
        private Point prePoint;

        public int getFenceId() {
            return fenceId;
        }

        public void setFenceId(int fenceId) {
            this.fenceId = fenceId;
        }

        public String getFenceName() {
            return fenceName;
        }

        public void setFenceName(String fenceName) {
            this.fenceName = fenceName;
        }

        public String getMonitoredPerson() {
            return monitoredPerson;
        }

        public void setMonitoredPerson(String monitoredPerson) {
            this.monitoredPerson = monitoredPerson;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public Point getAlarmPoint() {
            return alarmPoint;
        }

        public void setAlarmPoint(Point alarmPoint) {
            this.alarmPoint = alarmPoint;
        }

        public Point getPrePoint() {
            return prePoint;
        }

        public void setPrePoint(Point prePoint) {
            this.prePoint = prePoint;
        }
    }

    private class Point{
        private double longitude;
        private double latitude;
        private int radius;
        @JSONField(name = "loc_time")
        private long locTime;
        @JSONField(name = "create_time")
        private long createTime;
        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public int getRadius() {
            return radius;
        }

        public void setRadius(int radius) {
            this.radius = radius;
        }

        public long getLocTime() {
            return locTime;
        }

        public void setLocTime(long locTime) {
            this.locTime = locTime;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }
    }
}
