package com.zskj.core.autoconfigure.baidu.result;

import com.alibaba.fastjson.annotation.JSONField;
import com.zskj.core.autoconfigure.baidu.enums.CoordType;

import java.util.List;

/**
 * 停留点
 *
 * @author 花开
 * @date 2018/2/26
 */
public class StayPointResult extends BaseResult{

    @JSONField(name = "staypoint_num")
    private int staypointNum;

    @JSONField(name = "stay_points")
    private List<StayPoint> stayPoints;

    public int getStaypointNum() {
        return staypointNum;
    }

    public void setStaypointNum(int staypointNum) {
        this.staypointNum = staypointNum;
    }

    public List<StayPoint> getStayPoints() {
        return stayPoints;
    }

    public void setStayPoints(List<StayPoint> stayPoints) {
        this.stayPoints = stayPoints;
    }

    private class StayPoint {

        @JSONField(name = "start_time")
        private long startTime;
        @JSONField(name = "end_time")
        private long endTime;
        private int duration;
        @JSONField(name = "stay_point")
        private Point point;

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public Point getPoint() {
            return point;
        }

        public void setPoint(Point point) {
            this.point = point;
        }
    }

    private class Point {
        private double longitude;
        private double latitude;
        @JSONField(name = "coord_type")
        private CoordType coordType;

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

        public CoordType getCoordType() {
            return coordType;
        }

        public void setCoordType(CoordType coordType) {
            this.coordType = coordType;
        }
    }
}
