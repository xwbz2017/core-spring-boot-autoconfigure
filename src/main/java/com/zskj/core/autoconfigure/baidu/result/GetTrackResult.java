package com.zskj.core.autoconfigure.baidu.result;

import com.alibaba.fastjson.JSONArray;
import com.zskj.core.autoconfigure.baidu.enums.CoordType;

/**
 * 实时查询某entity一段时间内的行程信息：起终点、总里程、收费里程、轨迹点列表等。 支持进行轨迹去噪、抽稀、绑路、里程补偿等。
 *
 * @author 花开
 * @date 2018/2/12
 */
public class GetTrackResult extends BaseResult {

    /**
     * 本次检索总结果条数
     */
    private int total;

    /**
     * 本页返回的结果条数
     */
    private int size;

    /**
     * 此段轨迹的里程数，单位：米
     */
    private Double distance;

    /**
     * 此段轨迹的收费里程数，单位：米
     */
    private Double tollDistance;

    /**
     * 起点信息
     */
    private StartEndPoint startPoint;

    /**
     * 终点信息
     */
    private StartEndPoint endPoint;

    /**
     * 历史轨迹点列表
     */
    private JSONArray points;

    private class StartEndPoint {

        /**
         * 经度
         */
        private Double longitude;

        /**
         * 纬度
         */
        private Double latitude;

        /**
         * 坐标类型
         */
        private CoordType coordType;

        /**
         * 定位时设备的时间
         */
        private Long locTime;

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public CoordType getCoordType() {
            return coordType;
        }

        public void setCoordType(CoordType coordType) {
            this.coordType = coordType;
        }

        public Long getLocTime() {
            return locTime;
        }

        public void setLocTime(Long locTime) {
            this.locTime = locTime;
        }
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getTollDistance() {
        return tollDistance;
    }

    public void setTollDistance(Double tollDistance) {
        this.tollDistance = tollDistance;
    }

    public StartEndPoint getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(StartEndPoint startPoint) {
        this.startPoint = startPoint;
    }

    public StartEndPoint getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(StartEndPoint endPoint) {
        this.endPoint = endPoint;
    }

    public JSONArray getPoints() {
        return points;
    }

    public void setPoints(JSONArray points) {
        this.points = points;
    }
}
